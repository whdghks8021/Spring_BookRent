package com.biz.rent.mapper;

import org.apache.ibatis.jdbc.SQL;

public class UserSQL {

	public String user_select_sql() {
		SQL sql = new SQL()
				.SELECT("*").FROM("tbl_user").WHERE("${option} like '%' || #{search} || '%'");
		return sql.toString();
	}
	
	public String user_insert_sql() {

		SQL sql = new SQL().INSERT_INTO("tbl_user")
				.INTO_COLUMNS("USER_SEQ").INTO_VALUES("SEQ_USER.NEXTVAL")
				.INTO_COLUMNS("USER_ID").INTO_VALUES("#{user_id,jdbcType=VARCHAR}")
				.INTO_COLUMNS("USER_PASS").INTO_VALUES("#{user_pass,jdbcType=VARCHAR}")
				.INTO_COLUMNS("USER_NAME").INTO_VALUES("#{user_name,jdbcType=VARCHAR}")
				.INTO_COLUMNS("USER_BIRTH").INTO_VALUES("#{user_birth,jdbcType=VARCHAR}")
				.INTO_COLUMNS("USER_SEX").INTO_VALUES("#{user_sex,jdbcType=VARCHAR}")
				.INTO_COLUMNS("USER_PHONE").INTO_VALUES("#{user_phone,jdbcType=VARCHAR}")
				.INTO_COLUMNS("USER_EMAIL").INTO_VALUES("#{user_email,jdbcType=VARCHAR}")
				.INTO_COLUMNS("USER_IMAGE").INTO_VALUES("#{user_image,jdbcType=VARCHAR}")
				.INTO_COLUMNS("USER_BUY_COUNT").INTO_VALUES("#{user_buy_count}")
				.INTO_COLUMNS("USER_BUY_TOTAL").INTO_VALUES("#{user_buy_total}")
				.INTO_COLUMNS("USER_ROLE").INTO_VALUES("#{user_role}");

		return sql.toString();
	}

	public String user_update_sql() {

		SQL sql = new SQL().UPDATE("tbl_user")
				.SET("USER_PASS = #{user_pass,jdbcType=VARCHAR}")
				.SET("USER_NAME = #{user_name,jdbcType=VARCHAR}")
				.SET("USER_BIRTH = #{user_birth,jdbcType=VARCHAR}")
				.SET("USER_SEX = #{user_sex,jdbcType=VARCHAR}")
				.SET("USER_PHONE = #{user_phone,jdbcType=VARCHAR}")
				.SET("USER_EMAIL = #{user_email,jdbcType=VARCHAR}")
				.SET("USER_IMAGE = #{user_image,jdbcType=VARCHAR}")
				.SET("USER_BUY_COUNT = #{user_buy_count,jdbcType=INTEGER}")
				.SET("USER_BUY_TOTAL = #{user_buy_total,jdbcType=INTEGER}")
				.SET("USER_ROLE = #{user_role,jdbcType=INTEGER}")
				.WHERE("USER_SEQ = #{user_seq}");

		return sql.toString();
	}

	
	
}