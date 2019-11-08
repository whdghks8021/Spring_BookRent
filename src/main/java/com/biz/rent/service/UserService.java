package com.biz.rent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rent.mapper.UserDao;
import com.biz.rent.modal.UserVO;

@Service
public class UserService {

	@Autowired
	UserDao uDao;
	
	public UserVO findById(String userId) {
		
		return uDao.findById(userId);
	}

	public int insert(UserVO userVO) {
		
		return uDao.insert(userVO);
	}

	public int update(UserVO userVO) {
		
		return uDao.update(userVO);
	}

	public int delete(String user_seq) {
		
		return uDao.delete(user_seq);
	}

	public UserVO findBySeq(String user_seq) {
		
		return uDao.findBySeq(user_seq);
	}

	public List<UserVO> selectAll() {
		
		return uDao.selectAll();
	}

	public List<UserVO> selectSearch(String option, String search) {
		
		return uDao.selectSearch(option, search);
	}
	
}
