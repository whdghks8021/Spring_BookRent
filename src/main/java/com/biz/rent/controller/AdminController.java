package com.biz.rent.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.rent.modal.BookVO;
import com.biz.rent.modal.UserVO;
import com.biz.rent.service.BookService;
import com.biz.rent.service.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	UserService uService;
	@Autowired
	BookService bService;
	
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model) {
		
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","HOME");
		return "home";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model, @RequestParam(value="OPTION", required=false) String option, 
									@RequestParam(value="SEARCH", required=false) String search) {
		
		List<UserVO> userList = new ArrayList<UserVO>();
		
		if(option == null) {
			userList = uService.selectAll();
		} else {
			userList = uService.selectSearch(option, search);
		}
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","USER");
		model.addAttribute("OPTION",option);
		model.addAttribute("SEARCH",search);
		model.addAttribute("USER_LIST",userList);
		
		return "home";
	}
	
	@RequestMapping(value = "/user/search", method = RequestMethod.POST)
	public String user_search(Model model, String option, String search) {
		
		model.addAttribute("OPTION",option);
		model.addAttribute("SEARCH",search);
		
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value = "/rent", method = RequestMethod.GET)
	public String rent(Model model) {
		
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","RENT");
		return "home";
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String book(Model model, @RequestParam(value="MSG", required=false) String msg, 
									@RequestParam(value="OPTION", required=false) String option, 
									@RequestParam(value="SEARCH", required=false) String search) {
		
		List<BookVO> bookList = new ArrayList<BookVO>();
		
		if(option == null) {
			bookList = bService.selectAll();
		} else {
			bookList = bService.selectSearch(option, search);
		}
		
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","BOOK");
		model.addAttribute("MSG",msg);
		model.addAttribute("OPTION",option);
		model.addAttribute("SEARCH",search);
		model.addAttribute("BOOK_LIST",bookList);
		
		return "home";
	}
	
	@RequestMapping(value = "/book/search", method = RequestMethod.POST)
	public String book_search(Model model, String option, String search) {
		
		model.addAttribute("OPTION",option);
		model.addAttribute("SEARCH",search);
		
		return "redirect:/admin/book";
	}

	@RequestMapping(value = "/book/insert", method = RequestMethod.GET)
	public String book_insert(Model model) {
		
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","BOOK_INSERT");
		return "home";
	}
	
	@RequestMapping(value = "/book/insert", method = RequestMethod.POST)
	public String book_insert(Model model, BookVO bookVO, SessionStatus session) {
		
		int ret ;
		if(bookVO.getBook_seq() < 1) {
			ret = bService.insert(bookVO);
		} else {
			ret = bService.update(bookVO);
		}
		
		if(ret < 1) {
			model.addAttribute("MSG","INSERT_FAIL");
		} else {
			session.setComplete();
			model.addAttribute("MSG","INSERT_SUC");
		}
		return "redirect:/admin/book";
	}
	
	
	@RequestMapping(value = "/book/update", method = RequestMethod.GET)
	public String book_update(Model model, String book_seq) {
		
		BookVO bookVO = bService.findById(book_seq);
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","BOOK_INSERT");
		model.addAttribute("bookVO",bookVO);
		return "home";
	}
	
	
	@RequestMapping(value = "/book/update_stock", method = RequestMethod.GET)
	public String book_update_stock(Model model, String book_seq, String book_stock) {
		
		int ret = bService.update_stock(book_seq, book_stock);
		if(ret < 1) {
			model.addAttribute("MSG","UPDATE_FAIL");
		} else {
			model.addAttribute("MSG","UPDATE_SUC");
		}
		
		return "redirect:/admin/book";
	}
	
	@RequestMapping(value = "/book/delete", method = RequestMethod.GET)
	public String book_delete(Model model, String book_seq) {
		
		int ret = bService.delete(book_seq);
		if(ret < 1) {
			model.addAttribute("MSG","DELETE_FAIL");
		} else {
			model.addAttribute("MSG","DELETE_SUC");
		}
		
		return "redirect:/admin/book";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute UserVO userVO, Model model, HttpSession session) {
		
		UserVO vo = uService.findById(userVO.getUser_id());
		if(vo == null) {
			model.addAttribute("MSG","NULL");
		} else { // vo에 회원정보가 담겨져 있으면
			if(userVO.getUser_pass().equalsIgnoreCase(vo.getUser_pass())) { // 입력한 비밀번호와 저장된 비밀번호를 비교
				if(vo.getUser_role() == 1) { // 권한이 관리자 이면
					session.setAttribute("login_info", vo);
				} else {
					model.addAttribute("MSG","NOTADMIN");
				}
				
			} else {
				model.addAttribute("MSG","FAIL");
			}
		}
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","HOME");
		
		return "home";
	}
	
	
}
