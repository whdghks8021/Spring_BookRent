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
import com.biz.rent.modal.OrderVO;

public interface OrderDao {

	@Select("SELECT * FROM tbl_order ORDER BY order_date DESC")
	public List<OrderVO> selectAll();
	
	//@Select("SELECT * FROM tbl_book WHERE ${option} = #{search,jdbcType=VARCHAR} ORDER BY book_seq DESC")
	@SelectProvider(type=OrderSQL.class, method="order_select_sql")
	public List<OrderVO> selectSearch(@Param("option") String option, @Param("search") String search);
	
	@Select("SELECT * FROM tbl_order WHERE order_seq = #{order_seq,jdbcType=VARCHAR}")
	public OrderVO findById(String order_seq);
	
	@Update("UPDATE tbl_order SET order_state = #{order_state} WHERE order_seq = #{order_seq,jdbcType=VARCHAR}")
	public int update_state(@Param("order_seq") String order_seq, @Param("order_state") String order_state);

	@UpdateProvider(type=OrderSQL.class, method="order_update_sql")
	public int update(OrderVO orderVO);
	
	@InsertProvider(type=OrderSQL.class, method="order_insert_sql")
	public int insert(OrderVO orderVO);
	
	@Delete("DELETE FROM tbl_order WHERE order_seq = #{order_seq,jdbcType=VARCHAR}")
	public int delete(String order_seq);
	
}
