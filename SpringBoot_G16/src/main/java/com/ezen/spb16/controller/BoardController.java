package com.ezen.spb16.controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spb16.dto.BoardVO;
import com.ezen.spb16.dto.Paging;
import com.ezen.spb16.dto.ReplyVO;
import com.ezen.spb16.service.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BoardController {

	@Autowired
	BoardService bs;
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/main")
	public ModelAndView goMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)	
			mav.setViewName("loginform");
		else {
			int page=1;
			
			if( request.getParameter("page") != null ) {
				page = Integer.parseInt( request.getParameter("page") );
				session.setAttribute("page", page);
			}else if( session.getAttribute("page")!= null  ) { 
				page = (int) session.getAttribute("page");
			} else { 
				page = 1;
				session.removeAttribute("page");
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = bs.getAllCount();
			paging.setTotalCount( count );
			paging.paging();
			
			mav.addObject("boardList", bs.selectBoardAll( paging ) );
			mav.addObject("paging", paging);
			mav.setViewName("main");
		}
		return mav;
	}
	
	
	
	@RequestMapping("/boardWriteForm")
	public String write_form(Model model, HttpServletRequest request) {
		String url = "board/boardWriteForm";
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null)	url="loginform";		
		return url;
	}
	
	
	/*@RequestMapping(value="boardWrite", method = RequestMethod.POST)
	public String board_write( @ModelAttribute("dto") @Valid BoardVO boardvo, 
			BindingResult result, Model model,	HttpServletRequest request) {
		
		if( result.getFieldError("pass")!=null) {
			return "board/boardWriteForm";
		}else  if(result.getFieldError("title")!=null) {
			return "board/boardWriteForm";
		}else if(result.getFieldError("content")!=null) {
			return "board/boardWriteForm";
		}else {
			// bs.insertBoard(boardvo);
			return "redirect:/main";
		}
		
		
	}*/
	@RequestMapping(value="boardWrite", method = RequestMethod.POST)
	public String board_write(Model model,	HttpServletRequest request) {
		
		String path= context.getRealPath("/upload");
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
				);
			BoardVO bdto = new BoardVO();
			bdto.setPass(multi.getParameter("pass"));
			bdto.setUserid(multi.getParameter("userid"));
			bdto.setEmail(multi.getParameter("email"));
			bdto.setTitle(multi.getParameter("title"));
			bdto.setContent(multi.getParameter("content"));
			if(multi.getFilesystemName("image")==null) bdto.setImgfilename("");
			else bdto.setImgfilename(multi.getFilesystemName("image"));
			
			bs.insertBoard(bdto);
		} catch (IOException e) {e.printStackTrace();
		}
		
		return "redirect:/main";
	}
	
	@RequestMapping("/boardView")
	public ModelAndView boardView(@RequestParam("num") int num, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		BoardVO bdto = bs.boardView(num);
		
		mav.addObject("board", bdto);
		
		ArrayList<ReplyVO> list = bs.selectReply(num);
		
		mav.addObject("replyList", list);
		mav.setViewName("board/boardView");
		return mav;
	}
	
	@RequestMapping("/addReply")
	public String addReply(@RequestParam("boardnum") int boardnum, @RequestParam("userid") String userid,
			@RequestParam("reply") String reply, HttpServletRequest request) {
		
		ReplyVO rvo = new ReplyVO();
		
		rvo.setUserid(userid);
		rvo.setContent(reply);
		rvo.setBoardnum(boardnum);
		
		bs.addReply(rvo);
		
		return "redirect:/boardViewWithoutCount?num=" + boardnum;
	}
	

	@RequestMapping("/boardViewWithoutCount")
	public ModelAndView boardViewNextUpdate(@RequestParam("num") int num, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		BoardVO bdto = bs.getBoard(num);
		mav.addObject("board", bdto);
		
		ArrayList<ReplyVO> list = bs.selectReply(num);
		mav.addObject("replyList", list);
		mav.setViewName("board/boardView");
		return mav;
	}
	
	@RequestMapping("/deleteReply")
	public String reply_delete(@RequestParam("num") int num,
			@RequestParam("boardnum") int boardnum, HttpServletRequest request) {
		bs.deleteReply(num);		
		return "redirect:/boardViewWithoutCount?num=" + boardnum;
	}
		
	@RequestMapping("/boardEditForm")
	public String board_edit_form(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		model.addAttribute("num", num);
		return "board/boardCheckPassForm";
	}
	
	@RequestMapping("/boardEdit")
	public String board_edit(@RequestParam("num") int num, 
			@RequestParam("pass") String pass, Model model, HttpServletRequest request) {
		
		BoardVO bvo = bs.getBoard(num);
		model.addAttribute("num", num);
		if(pass.equals(bvo.getPass())) {
			return "board/boardCheckPass";
		}else{
			model.addAttribute("message","비밀번호가 맞지 않습니다. 확인해주세요");
			return "board/boardCheckPassForm";
		}
		
	}
	
	@RequestMapping("boardUpdateForm")
	public String board_update_from(@RequestParam("num") int num, Model model, HttpServletRequest request) {
		BoardVO bvo = bs.getBoard(num);
		model.addAttribute("num", num);
		model.addAttribute("dto", bvo);
		return "board/boardEditForm";
	}
	
	@RequestMapping("/selectimg")
	public String selectimg() {
		return "board/selectimg";
	}
	
	@RequestMapping(value="/fileupload", method = RequestMethod.POST)
	public String fileupload(Model model, HttpServletRequest request) {
		String path = context.getRealPath("/upload");
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			
			// 전송된 파일은 업로드 되고, 파일 이름은 모델에 저장합니다.
			model.addAttribute("image", multi.getFilesystemName("image"));
		} catch (IOException e) {e.printStackTrace();
		}
		return "board/completupload";
	}
	
	@RequestMapping(value="boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(@ModelAttribute("dto") @Valid BoardVO boardvo,
			BindingResult result,@RequestParam("oldfilename")String oldfilename, 
			HttpServletRequest request, Model model) {
		if(result.getFieldError("pass")!=null) {
			model.addAttribute("message", "비밀번호는 게시물 수정 삭제시 필요합니다");
			return "board/boardEditForm";
		}else if(result.getFieldError("title")!=null) {
			model.addAttribute("message", "제목은 필수입력 사항입니다.");
			return "board/boardEditForm";
		}else if(result.getFieldError("content")!=null) {
			model.addAttribute("message", "게시물 내용은 비워둘 수 없습니다.");
			return "board/boardEditForm";
		}else {
			if(boardvo.getImgfilename().equals("")) {
				boardvo.setImgfilename(oldfilename);
			}
			bs.updateBoard(boardvo);
			return "redirect:/boardViewWithoutCount?num=" + boardvo.getNum();
		}
		
	}
	@RequestMapping("/boardDeleteForm")
	public String boardDeleteForm(@RequestParam("num") int num,
			Model model, HttpServletRequest request) {
		
		model.addAttribute("num", num);
		return "board/boardCheckPassForm";
		
	}
	@RequestMapping("/boardDelete")
	public String boardDelete(@RequestParam("num") int num) {
		bs.boardDelete(num);
		return "redirect:/main";
	}
}






