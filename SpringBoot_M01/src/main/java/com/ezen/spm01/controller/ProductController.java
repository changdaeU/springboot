package com.ezen.spm01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spm01.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService ps;
	
	@RequestMapping("/")
	public ModelAndView index(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("newProductList", ps.getNewList());
		mav.addObject("bestProductList", ps.getBestList());
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("category")
	public ModelAndView category(Model model, @RequestParam("kind") String kind) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("productKindList", ps.getKindList(kind));
		mav.setViewName("product/productKind");
		return mav;
	}
	
	@RequestMapping("productDetail")
	public ModelAndView product_detail(Model model, @RequestParam("pseq") int pseq) {
		ModelAndView mav= new ModelAndView();
		mav.addObject("productVO", ps.getProduct(pseq));
		mav.setViewName("product/productDetail");
		return mav;
	}
}
