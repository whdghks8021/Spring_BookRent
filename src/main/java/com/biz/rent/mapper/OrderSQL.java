package com.biz.rent.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.ibatis.jdbc.SQL;

public class OrderSQL {

	public String order_select_sql() {
		SQL sql = new SQL()
				.SELECT("*").FROM("tbl_order").WHERE("${option} like '%' || #{search} || '%'");
		return sql.toString();
	}
	
	public String order_insert_sql() {
		
		LocalDateTime ld = LocalDateTime.now();
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		String today = ld.format(fd);
		int code = (int)(Math.random()*99999999)+10000000; // 주문번호 만들기
		String orderCode = today + String.valueOf(code);
		
		
		SQL sql = new SQL()
				.INSERT_INTO("tbl_order")
				.INTO_COLUMNS("order_seq").INTO_VALUES(orderCode)
				.INTO_COLUMNS("order_user_id").INTO_VALUES("#{order_user_id,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_user_name").INTO_VALUES("#{order_user_name,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_user_phone").INTO_VALUES("#{order_user_phone,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_user_addr").INTO_VALUES("#{order_user_addr,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_book_isbn").INTO_VALUES("#{order_book_isbn,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_book_title").INTO_VALUES("#{order_book_title,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_book_comp").INTO_VALUES("#{order_book_comp,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_book_price").INTO_VALUES("#{order_book_price,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_book_stock").INTO_VALUES("#{order_book_stock,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_date").INTO_VALUES("#{order_date,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_payment_date").INTO_VALUES("#{order_payment_date,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_payment_way").INTO_VALUES("#{order_payment_way,jdbcType=VARCHAR}")
				.INTO_COLUMNS("order_state").INTO_VALUES("#{order_state,jdbcType=VARCHAR}");
		
		return sql.toString();
	}
	
	public String order_update_sql() {
		
		SQL sql = new SQL()
				.UPDATE("tbl_order")
				.SET("order_user_id = #{order_user_id,jdbcType=VARCHAR}")
				.SET("order_user_name = #{order_user_name,jdbcType=VARCHAR}")
				.SET("order_user_phone = #{order_user_phone,jdbcType=VARCHAR}")
				.SET("order_user_addr = #{order_user_addr,jdbcType=VARCHAR}")
				.SET("order_book_isbn = #{order_book_isbn,jdbcType=VARCHAR}")
				.SET("order_book_title = #{order_book_title,jdbcType=VARCHAR}")
				.SET("order_book_comp = #{order_book_comp,jdbcType=INTEGER}")
				.SET("order_book_price = #{order_book_price,jdbcType=VARCHAR}")
				.SET("order_book_stock = #{order_book_stock,jdbcType=VARCHAR}")
				.SET("order_date = #{order_date,jdbcType=VARCHAR}")
				.SET("order_payment_date = #{order_payment_date,jdbcType=VARCHAR}")
				.SET("order_payment_way = #{order_payment_way,jdbcType=VARCHAR}")
				.SET("order_state = #{order_state,jdbcType=VARCHAR}")
				.WHERE("order_seq = #{order_seq,jdbcType=INTEGER}");
		
		return sql.toString();

		
		
	}
	
}