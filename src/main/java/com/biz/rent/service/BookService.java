package com.biz.rent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rent.mapper.BookDao;
import com.biz.rent.modal.BookVO;

@Service
public class BookService {

	
	@Autowired
	BookDao bDao;
	
	public List<BookVO> selectAll() {
		
		return bDao.selectAll();
	}
	public List<BookVO> selectSearch(String option, String search) {
		
		return bDao.selectSearch(option, search);
	}
	public BookVO findById(String book_seq) {
		
		return bDao.findById(book_seq);
	}
	
	public int insert(BookVO bookVO) {
		
		return bDao.insert(bookVO);
	}
	public int update(BookVO bookVO) {
		
		return bDao.update(bookVO);
	}
	public int delete(String book_seq) {
		
		return bDao.delete(book_seq);
	}
	public int update_stock(String book_seq, String book_stock) {
		
		return bDao.update_stock(book_seq, book_stock);
	}
	
}
