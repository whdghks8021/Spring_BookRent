package com.biz.rent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rent.modal.UserVO;

public interface UserDao {

	@Select("SELECT * FROM tbl_user")
	public List<UserVO> selectAll();
	
	@SelectProvider(type=UserSQL.class, method="user_select_sql")
	public List<UserVO> selectSearch(@Param("option") String option, @Param("search") String search);
	
	@Select("SELECT * FROM tbl_user WHERE user_id = #{user_id,jdbcType=VARCHAR}")
	public UserVO findById(String user_id);
	
	@Select("SELECT * FROM tbl_user WHERE user_seq = #{user_seq,jdbcType=VARCHAR}")
	public UserVO findBySeq(String user_seq);

	@InsertProvider(type=UserSQL.class,method="user_insert_sql")
	public int insert(UserVO userVO);
	
	@UpdateProvider(type=UserSQL.class,method="user_update_sql")
	public int update(UserVO userVO);
	
	@Delete("DELETE FROM tbl_user WHERE user_seq = #{user_seq}")
	public int delete(String user_seq);

	
	
}
