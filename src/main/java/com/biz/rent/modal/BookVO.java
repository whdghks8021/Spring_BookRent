package com.biz.rent.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class BookVO {

    
	private long book_seq;
	private String book_isbn;
	private String book_title;
	private String book_author;
	private String book_comp;
	private String book_date;
	private long book_page;
	private long book_price;
	private String book_description;
	private String book_image;
	private String book_stock;	
}
