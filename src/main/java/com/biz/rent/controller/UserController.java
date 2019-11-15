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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.rent.modal.UserVO;
import com.biz.rent.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	UserService uService;
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Model model) {
		
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","USER_INSERT");		
		return "home";
	}
	
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("userVO") UserVO userVO, Model model, SessionStatus session) {
		
		String userId = userVO.getUser_id();
		
		UserVO vo = uService.findById(userId);
		if(vo != null) {
			model.addAttribute("MSG","IDOVER");
		} else {
			int ret = uService.insert(userVO);
			if(ret > 0) {
				model.addAttribute("MSG","SUCESS");
			} else {
				model.addAttribute("MSG","FAIL");
			}
			
		}
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","USER_INSERT");		
		
		return "home";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, String user_seq) {
		
		UserVO userVO = uService.findBySeq(user_seq);
		
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","USER_UPDATE");
		model.addAttribute("userVO", userVO);
		
		return "home";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("userVO") UserVO userVO, Model model, SessionStatus session) {
		
		
		int ret = uService.update(userVO);
		
		if(ret > 0) {
			model.addAttribute("MSG","SUCESS");
		} else {
			model.addAttribute("MSG","FAIL");
		}
			
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","USER_UPDATE");		
		
		return "home";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, String user_seq) {
		
		int ret = uService.delete(user_seq);
		
		if(ret > 0) {
			model.addAttribute("MSG","SUCESS");
		} else {
			model.addAttribute("MSG","FAIL");
		}
		
		model.addAttribute("BODY","ADMIN");
		model.addAttribute("ADMIN","USER");		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value = "/id_check", method = RequestMethod.POST)
	public List<String> id_check(@RequestParam String user_id) {
		
		List<String> ret = new ArrayList<String>();
		
		UserVO vo = uService.findById(user_id);
		
		if(vo == null) {
			ret.add("is-valid");
			ret.add("사용 가능한 ID 입니다.");
		}
		else {
			ret.add("is-invalid");
			ret.add("이미 사용중인 ID 입니다.");
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		model.addAttribute("BODY","LOGIN");
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model,@ModelAttribute UserVO userVO, HttpSession session) {
		
		UserVO vo = uService.findById(userVO.getUser_id());
		
		if(vo == null) {
			model.addAttribute("MSG","NULL");
		} else { // vo에 회원정보가 담겨져 있으면
			if(userVO.getUser_pass().equalsIgnoreCase(vo.getUser_pass())) { // 입력한 비밀번호와 저장된 비밀번호를 비교
				session.setAttribute("login_info", vo);
				model.addAttribute("MSG","SUCESS");
			} else {
				model.addAttribute("MSG","FAIL");
			}
		}
		model.addAttribute("BODY","LOGIN");
		return "home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		
		session.removeAttribute("login_info");
		
		return "redirect:/";
	}
	
}
