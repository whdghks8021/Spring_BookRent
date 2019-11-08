package com.biz.rent.modal;

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
public class OrderVO {

	private long order_seq;
    private String order_user_id;
    private String order_user_name;
    private String order_user_phone;	
    private String order_user_addr;
    private String order_book_isbn;
    private String order_book_title;
    private String order_book_comp;
    private long order_book_price;
    private long order_book_stock;
    private String order_date;
    private String order_payment_date;
    private String order_payment_way;
    private String order_state;
	

}
