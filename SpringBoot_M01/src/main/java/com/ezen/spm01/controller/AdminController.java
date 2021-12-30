package com.ezen.spm01.controller;

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

import com.ezen.spm01.dto.Paging;
import com.ezen.spm01.dto.ProductVO;
import com.ezen.spm01.service.AdminService;
import com.ezen.spm01.service.ProductService;
import com.ezen.spm01.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AdminController {
	
	@Autowired
	AdminService as;
	
	@Autowired
	ProductService ps;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	QnaService qs;
	
	@RequestMapping("/")
	public String admin() {
		return "admin/adminLoginForm";
	}
	
	@RequestMapping("adminLogin")
	public ModelAndView admin_login(Model model, HttpServletRequest request,
			@RequestParam("workId") String workId,
			@RequestParam("workPwd") String workPwd) {
		
		ModelAndView mav = new ModelAndView();
		
		int result = as.workerCheck(workId, workPwd);
		// result 값이 1 이면 정상로그인, 0이면 비밀번호 오류, -1이면 아이디 없음
		
		if(workId==null||workId.equals("")) {
			mav.addObject("message", "아이디를 확인하세요");
			mav.setViewName("admin/adminLoginForm"); // 아이디 비번이 비었거나 로그인이 실패했을때
			return mav;
		}else if(workPwd==null||workPwd.equals("")) {
			mav.addObject("message", "비밀번호를 확인하세요");
			mav.setViewName("admin/adminLoginForm"); // 아이디 비번이 비었거나 로그인이 실패했을때
			return mav;
		}
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("workId", workId);
			mav.setViewName("redirect:/productList");
		}else if(result ==0) {
			mav.addObject("message", "비밀번호가 맞지 않습니다.");
			mav.setViewName("admin/adminLoginForm"); // 아이디 비번이 비었거나 로그인이 실패했을때
		}else if(result == -1) {
			mav.addObject("message", "아이디를 확인하세요");
			mav.setViewName("admin/adminLoginForm"); // 아이디 비번이 비었거나 로그인이 실패했을때
		}else {
			mav.addObject("message", "원인미상의 오류로 로그인 실패");
			mav.setViewName("admin/adminLoginForm"); // 아이디 비번이 비었거나 로그인이 실패했을때
		}
		
		
		return mav;
	}
	@RequestMapping("productList")
	public ModelAndView product_List(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id == null) {
			mav.setViewName("admin/adminLoginForm");
		}else {
			
			
			int page = 1;
			if(request.getParameter("first") != null) {
				page=1;
				session.removeAttribute("page");
			}else if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if (session.getAttribute("page")!=null) {
				page =(Integer)session.getAttribute("page");
			}else {
				page=1;
				session.removeAttribute("page");
			}
			
			String key = "";
			if(request.getParameter("first") != null) {
				page=1;
				session.removeAttribute("page");
			}else if(request.getParameter("key")!=null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			}else if (session.getAttribute("key")!=null) {
				key =(String)session.getAttribute("key");
			}else {
				session.removeAttribute("key");
				key = "";
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = as.getAllCount("product", "name", key);
			paging.setTotalCount(count);
			paging.paging();
			
			ArrayList<ProductVO> productList = as.listProduct(paging, key);
			
			
			
			request.setAttribute("paging",paging);
			mav.addObject("productList",productList);
			request.setAttribute("key",key);
			mav.setViewName("admin/product/productList");
		}
		return mav;
	}
	@RequestMapping("productWriteForm")
	public ModelAndView product_write_form(HttpServletRequest request) {
		String kindList[] = {"Heels", "Boots", "Sandals", "Sneakers", "Slipers", "Sale"};
		ModelAndView mav = new ModelAndView();
		mav.addObject("kindList",kindList);
		mav.setViewName("admin/product/productWriteForm");
		return mav;
	}
	
	@RequestMapping(value="productWrite", method=RequestMethod.POST)
	public String product_Write(HttpServletRequest request) {
		String savePath = context.getRealPath("/product_images");
		ProductVO pvo = new ProductVO();
		
		// 전달 파라미터를 pvo에 넣고 서비스의 insertProduct에 전달
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(
					request, savePath , 5*1024*1024,  "UTF-8", new DefaultFileRenamePolicy() );
			pvo.setKind(multi.getParameter("kind"));
			pvo.setContent(multi.getParameter("content"));
			pvo.setName(multi.getParameter("name"));
			pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
			pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
			pvo.setPrice3(Integer.parseInt(multi.getParameter("price2"))-Integer.parseInt(multi.getParameter("price1")));
			pvo.setImage(multi.getFilesystemName("image"));
		} catch (IOException e) {e.printStackTrace();
		}		
		
		as.insertProduct(pvo);
		return "redirect:/productList";
	}
	
	@RequestMapping("adminProductDetail")
	public ModelAndView Product_Detail(HttpServletRequest request, @RequestParam("pseq") int pseq) {
		ModelAndView mav = new ModelAndView();
		
		ProductVO pvo = ps.getProduct(pseq);
		
		String kindList[] = {"0", "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale"};
		int index = Integer.parseInt(pvo.getKind());
		
		mav.addObject("productVO", pvo);
		mav.addObject("kind", kindList[index]);
		mav.setViewName("admin/product/productDetail");
		
		return mav;
	}
	
	@RequestMapping("productUpdateForm")
	public ModelAndView product_Update_Form(HttpServletRequest request, @RequestParam("pseq") int pseq) {
		ModelAndView mav = new ModelAndView();
		ProductVO pvo = ps.getProduct(pseq);
		mav.addObject("dto", pvo);
		String kindList[] = {"Heels", "Boots", "Sandals", "Sneakers", "Slipers", "Sale"};
		mav.addObject("kindList", kindList);
		mav.setViewName("admin/product/productUpdate");
		return mav;
	}
	
	@RequestMapping("/selectimg")
	public String selectimg() {
		return "admin/product/selectimg";
	}
	
	@RequestMapping(value="/fileupload", method = RequestMethod.POST)
	public String fileupload(Model model, HttpServletRequest request) {
		String path = context.getRealPath("/product_images");
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			
			// 전송된 파일은 업로드 되고, 파일 이름은 모델에 저장합니다.
			model.addAttribute("image", multi.getFilesystemName("image"));
			model.addAttribute("originalFilename", multi.getFilesystemName("image"));
		} catch (IOException e) {e.printStackTrace();
		}
		return "admin/product/completupload";
	}
	
	
	@RequestMapping(value="productUpdate", method=RequestMethod.POST)
	public String product_Update(@ModelAttribute("dto") @Valid ProductVO productvo,
			BindingResult result,HttpServletRequest request) {
		ProductVO pvo = new ProductVO();
		int pseq=0;
		String savePath = context.getRealPath("/product_images");
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(
					request, savePath , 5*1024*1024,  "UTF-8", new DefaultFileRenamePolicy() );
			pvo.setPseq(Integer.parseInt(multi.getParameter("pseq")));
			pseq = Integer.parseInt(multi.getParameter("pseq"));
			pvo.setKind(multi.getParameter("kind"));
			pvo.setContent(multi.getParameter("content"));
			pvo.setName(multi.getParameter("name"));
			pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
			pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
			pvo.setPrice3(Integer.parseInt(multi.getParameter("price2"))
					-Integer.parseInt(multi.getParameter("price1")));
			pvo.setUseyn(multi.getParameter("useyn"));
			pvo.setBestyn(multi.getParameter("bestyn"));
			if(multi.getFilesystemName("image")==null) // 수정하려는 이미지가 없을 경우 이전 이미지로 수정대체
				pvo.setImage(multi.getParameter("oldfileimage"));
			else pvo.setImage(multi.getFilesystemName("image"));
		} catch (IOException e) {e.printStackTrace();
		}		
		as.updateProduct(pvo);
		return "redirect:/adminProductDetail?pseq="+pseq;
	}
}
