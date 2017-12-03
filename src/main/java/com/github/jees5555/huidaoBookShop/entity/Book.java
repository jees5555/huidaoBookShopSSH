package com.github.jees5555.huidaoBookShop.entity;

public class Book {
	private int bid;
	private String bookname;
	private double price;
	private String image;
	private int stock;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Books [bid=" + bid + ", bookname=" + bookname + ", price=" + price + ", image=" + image + ", stock="
				+ stock + "]";
	}
	
	
}
