package com.biz.rent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rent.modal.BookVO;

public interface BookDao {

	@Select("SELECT * FROM tbl_book ORDER BY book_seq DESC")
	public List<BookVO> selectAll();
	
	//@Select("SELECT * FROM tbl_book WHERE ${option} = #{search,jdbcType=VARCHAR} ORDER BY book_seq DESC")
	@SelectProvider(type=BookSQL.class, method="book_select_sql")
	public List<BookVO> selectSearch(@Param("option") String option, @Param("search") String search);
	
	@Select("SELECT * FROM tbl_book WHERE book_seq = #{book_seq,jdbcType=VARCHAR}")
	public BookVO findById(String book_seq);
	
	@Update("UPDATE tbl_book SET book_stock = #{book_stock} WHERE book_seq = #{book_seq,jdbcType=VARCHAR}")
	public int update_stock(@Param("book_seq") String book_seq, @Param("book_stock") String book_stock);

	@UpdateProvider(type=BookSQL.class, method="book_update_sql")
	public int update(BookVO bookVO);
	
	@InsertProvider(type=BookSQL.class, method="book_insert_sql")
	public int insert(BookVO bookVO);
	
	@Delete("DELETE FROM tbl_book WHERE book_seq = #{book_seq,jdbcType=VARCHAR}")
	public int delete(String book_seq);
}
