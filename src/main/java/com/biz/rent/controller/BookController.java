package com.biz.rent.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.rent.modal.BookVO;
import com.biz.rent.service.BookService;

@RequestMapping("/book")
@Controller
public class BookController {
	
	@Autowired
	BookService bService;
	
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public String book(Model model) {
//		
//		List<BookVO> bookList = bService.selectAll();
//		
//		model.addAttribute("BODY","ADMIN");
//		model.addAttribute("ADMIN","BOOK");
//		model.addAttribute("BOOK_LIST",bookList);
//		
//		return "home";
//	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String book(Model model, @RequestParam(value="MSG", required=false) String msg, 
									@RequestParam(value="OPTION", required=false) String option, 
									@RequestParam(value="SEARCH", required=false) String search) {
		
		List<BookVO> bookList = new ArrayList<BookVO>();
		
		if(option == null) {
			bookList = bService.selectAll();
			
		} else {
			bookList = bService.selectSearch(option, search);
		}
		
		model.addAttribute("BODY","MAIN");
		model.addAttribute("MSG",msg);
		model.addAttribute("OPTION",option);
		model.addAttribute("SEARCH",search);
		model.addAttribute("BOOK_LIST",bookList);
//		model.addAttribute("BOOK_LIST",);
		
		return "home";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String book_search(Model model, String option, String search) {
		
		model.addAttribute("OPTION",option);
		model.addAttribute("SEARCH",search);
		
		return "redirect:/book/list";
	}
	
	

}
