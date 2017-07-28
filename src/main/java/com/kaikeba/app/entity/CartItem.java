package com.kaikeba.app.entity;

import java.math.BigDecimal;

public class CartItem {

	private String cartItemId;
	private int quantity;
	private Book book;
	private User user;
	private int orderBy;
	private double subtotal;
	
	public double getSubtotal() {
		
		//在java中计算金钱用一个类：BigDecimal
		BigDecimal quantitybBigDecimal = new BigDecimal(quantity + "");
		BigDecimal currPricebBigDecimal = new BigDecimal(book.getCurrPrice() + "");
		BigDecimal result = quantitybBigDecimal.multiply(currPricebBigDecimal); //相乘
        subtotal = result.doubleValue();
		return subtotal;
	}
	public void setSubtotal(double sumMoney) {
		this.subtotal = sumMoney;
	}
	public CartItem() {
		super();
	}
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", quantity=" + quantity +
                ", book=" + book +
                ", user=" + user +
                ", orderBy=" + orderBy +
                ", subtotal=" + subtotal +
                '}';
    }
}
