package com.biz.rent.mapper;

import org.apache.ibatis.jdbc.SQL;

public class BookSQL {

	public String book_select_sql() {
		SQL sql = new SQL()
				.SELECT("*").FROM("tbl_book").WHERE("${option} like '%' || #{search} || '%'");
		return sql.toString();
	}
	
	public String book_insert_sql() {
		
		SQL sql = new SQL()
				.INSERT_INTO("tbl_book")
				.INTO_COLUMNS("book_seq").INTO_VALUES("SEQ_BOOK.NEXTVAL")
				.INTO_COLUMNS("book_isbn").INTO_VALUES("#{book_isbn,jdbcType=VARCHAR}")
				.INTO_COLUMNS("book_title").INTO_VALUES("#{book_title,jdbcType=VARCHAR}")
				.INTO_COLUMNS("book_author").INTO_VALUES("#{book_author,jdbcType=VARCHAR}")
				.INTO_COLUMNS("book_comp").INTO_VALUES("#{book_comp,jdbcType=VARCHAR}")
				.INTO_COLUMNS("book_date").INTO_VALUES("#{book_date,jdbcType=VARCHAR}")
				.INTO_COLUMNS("book_page").INTO_VALUES("#{book_page,jdbcType=VARCHAR}")
				.INTO_COLUMNS("book_price").INTO_VALUES("#{book_price,jdbcType=INTEGER}")
				.INTO_COLUMNS("book_description").INTO_VALUES("#{book_description,jdbcType=VARCHAR}")
				.INTO_COLUMNS("book_image").INTO_VALUES("#{book_image,jdbcType=VARCHAR}")
				.INTO_COLUMNS("book_stock").INTO_VALUES("#{book_stock,jdbcType=VARCHAR}");
		
		return sql.toString();
	}
	
	public String book_update_sql() {
		
		SQL sql = new SQL()
				.UPDATE("tbl_book")
				.SET("book_isbn = #{book_isbn,jdbcType=VARCHAR}")
				.SET("book_title = #{book_title,jdbcType=VARCHAR}")
				.SET("book_author = #{book_author,jdbcType=VARCHAR}")
				.SET("book_comp = #{book_comp,jdbcType=VARCHAR}")
				.SET("book_date = #{book_date,jdbcType=VARCHAR}")
				.SET("book_page = #{book_page,jdbcType=VARCHAR}")
				.SET("book_price = #{book_price,jdbcType=INTEGER}")
				.SET("book_description = #{book_description,jdbcType=VARCHAR}")
				.SET("book_image = #{book_image,jdbcType=VARCHAR}")
				.SET("book_stock = #{book_stock,jdbcType=VARCHAR}")
				.WHERE("book_seq = #{book_seq,jdbcType=INTEGER}");
		
		return sql.toString();

		
		
	}
	
}