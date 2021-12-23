package com.ezen.spb16.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spb16.dto.MemberVO;
import com.ezen.spb16.service.MemberService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;
	
	@RequestMapping("/")
	public String index() {
		return "loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			Model model, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			if(result.getFieldError("id")!=null) {
				model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
				return "loginForm";
			}else if(result.getFieldError("pw")!=null) {
				model.addAttribute("message", result.getFieldError("pw").getDefaultMessage());
				return "loginForm";
			}
			
		}
		MemberVO mvo = ms.getMember(membervo.getId());
		if(mvo == null) {
			model.addAttribute("message", "아이디가 없습니다.");
			return "loginForm";
		}else if(mvo.getPw()==null) {
			model.addAttribute("message", "로그인 오류. 관리자에게 문의하세요");
			return "loginForm";
		}else if(!mvo.getPw().equals(membervo.getPw())) {
			model.addAttribute("message", "비밀번호가 맞지 않습니다.");
			return "loginForm";
		}else if(mvo.getPw().equals(membervo.getPw())){
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/main";
		}else {
			model.addAttribute("message", "원인 미상의 오류로 로그인 실패");
			return "loginForm";
		}
		
	}
	
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm(Model model,HttpServletRequest request) {
		return "member/memberJoinForm";
	}
	
	@RequestMapping("/idcheck")
	public ModelAndView idcheck(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO mvo = ms.getMember(id);
		if(mvo == null) mav.addObject("result", "-1");
		else mav.addObject("result", 1);
		
		mav.addObject("id", id);
		mav.setViewName("member/idcheck");
		return mav;
	}
	
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute("dto") @Valid MemberVO membervo, 
			BindingResult result , @RequestParam("re_id") String reid, 
			@RequestParam("pw_check") String pwchk, Model model) {
		ModelAndView mav= new ModelAndView();
		
		if(result.getFieldError("id")!=null) {
			mav.addObject("message", "아이디를 입력하세요");
			mav.addObject("re_id", reid);
			mav.setViewName("member/memberJoinForm");
		}else if(result.getFieldError("pw")!=null) {
			mav.addObject("message", "비밀번호를 입력하세요");
			mav.setViewName("member/memberJoinForm");
		}else if(result.getFieldError("name")!=null) {
			mav.addObject("message", result.getFieldError("name").getDefaultMessage());
			mav.setViewName("member/memberJoinForm");
		}else if(!membervo.getId().equals(reid)) {
			mav.addObject("message", "아이디 중복체크가 되지 않았습니다.");
			mav.setViewName("member/memberJoinForm");
		}else if(!membervo.getPw().equals(pwchk)) {
			mav.addObject("message", "비밀번호 확인이 일치하지 않습니다.");
			mav.setViewName("member/memberJoinForm");
		}else {
			ms.insertMember(membervo);
			mav.addObject("message", "회원가입이 완료되었습니다. 로그인 하세요");
			mav.setViewName("loginForm");
		}
		return mav;
	}
	

	@RequestMapping("/memberEditForm")
	public String memberEditForm(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) return "loginForm";
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		MemberVO dto = new MemberVO();
		dto.setId(loginUser.getId());
		dto.setName(loginUser.getName());
		dto.setEmail(loginUser.getEmail());
		dto.setPhone1(loginUser.getPhone1());
		dto.setPhone2(loginUser.getPhone2());
		dto.setPhone3(loginUser.getPhone3());
		model.addAttribute("dto", dto);
		return "member/memberEditForm";
	}
	
	@RequestMapping(value="/memberEdit", method=RequestMethod.POST)
	public String memberEdit(@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result, @RequestParam("pw_check") String pwchk, Model model,
			HttpServletRequest request) {
		if(result.getFieldError("pw") != null) {
			model.addAttribute("message", "비밀번호를 입력하세요");
			return "member/memberEditForm";
		}else if(result.getFieldError("name")!=null) {
			model.addAttribute("message", "이름을 입력하세요");
			return "member/memberEditForm";
		}else if(!membervo.getPw().equals(pwchk)) {
			model.addAttribute("message", "비밀번호 확인이 일치하지 않습니다.");
			return ("member/memberJoinForm");
		}else {
			ms.updateMember(membervo);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", membervo);
			return "redirect:/main";
		}
	}
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout(Model model,HttpServletRequest request) {
		String url = "board/boardWriteForm";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null) url = "loginForm";
		return url;
		
	}
}
