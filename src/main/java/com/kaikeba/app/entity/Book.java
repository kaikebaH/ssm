package com.kaikeba.app.entity;

public class Book {

	/* `bid` char(32) NOT NULL,
	  `bname` varchar(200) DEFAULT NULL,
	  `author` varchar(50) DEFAULT NULL,
	  `price` decimal(8,2) DEFAULT NULL,
	  `currPrice` decimal(8,2) DEFAULT NULL,
	  `discount` decimal(3,1) DEFAULT NULL,
	  `press` varchar(100) DEFAULT NULL,
	  `publishtime` char(10) DEFAULT NULL,
	  `edition` int(11) DEFAULT NULL,
	  `pageNum` int(11) DEFAULT NULL,
	  `wordNum` int(11) DEFAULT NULL,
	  `printtime` char(10) DEFAULT NULL,
	  `booksize` int(11) DEFAULT NULL,
	  `paper` varchar(50) DEFAULT NULL,
	  `cid` char(32) DEFAULT NULL,
	  `image_w` varchar(100) DEFAULT NULL,
	  `image_b` varchar(100) DEFAULT NULL,
	  `orderBy` int(11) NOT NULL AUTO_INCREMENT,*/
	private String bid;
	private String bname;
	private String author;
	private double price;
	private double currPrice;
	private double discount;
	private String press;
	private String publishtime;
	private int edition;
	private int pageNum;
	private int wordNum;
	private String printtime;
	private int booksize;
	private String paper;
	private Category category;
	private String image_w;
	private String image_b;
	private int orderBy;
	
	public Book() {
		super();
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(double currPrice) {
		this.currPrice = currPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getWordNum() {
		return wordNum;
	}

	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}

	public String getPrinttime() {
		return printtime;
	}

	public void setPrinttime(String printtime) {
		this.printtime = printtime;
	}

	public int getBooksize() {
		return booksize;
	}

	public void setBooksize(int booksize) {
		this.booksize = booksize;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage_w() {
		return image_w;
	}

	public void setImage_w(String image_w) {
		this.image_w = image_w;
	}

	public String getImage_b() {
		return image_b;
	}

	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

    @Override
    public String toString() {
        return "Book{" +
                "bid='" + bid + '\'' +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", currPrice=" + currPrice +
                ", discount=" + discount +
                ", press='" + press + '\'' +
                ", publishtime='" + publishtime + '\'' +
                ", edition=" + edition +
                ", pageNum=" + pageNum +
                ", wordNum=" + wordNum +
                ", printtime='" + printtime + '\'' +
                ", booksize=" + booksize +
                ", paper='" + paper + '\'' +
                ", category=" + category +
                ", image_w='" + image_w + '\'' +
                ", image_b='" + image_b + '\'' +
                ", orderBy=" + orderBy +
                '}';
    }
}
