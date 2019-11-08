package com.biz.rent.modal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ScriptAssert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserVO {

	private long user_seq; //	number
	
	private String user_id; //	nvarchar2(100)
	
	private String user_pass; //	nvarchar2(100)
	
	private String user_repass; //	nvarchar2(100)
	
	private String user_name; //	nvarchar2(100)
	
	private String user_birth; //	char(10)
	
	private String user_sex; //	nvarchar2(100)
	
	private String user_phone; //	nvarchar2(100)
	
	private String user_email; //	nvarchar2(1000)
	private String user_image; //	nvarchar2(125)
	
	private long user_buy_count; //	number
	private long user_buy_total; //	number
	private long user_role; //	number

}
