package com.kaikeba.app.service;

import com.kaikeba.app.entity.Book;
import com.kaikeba.app.entity.Category;
import com.kaikeba.commons.PageBean;
import com.sun.tools.javac.code.BoundKind;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public interface IBookService {
    /**
     * 根据cid分页查询出指定页面的所有数据
     * @param category
     * @param pb
     * @return
     */
    List<Book> getBooksByCid(Category category,PageBean<Book> pb);

    /**
     * 根据cid查询出指定条件的所有的记录条数
     * @param category
     * @return
     */
    int countBooksByCid(Category category);

    /**
     * 根据出版社查询指定书籍信息
     * @param press
     * @param pb
     * @return
     */
    List<Book> getBooksByPress(String press,PageBean<Book> pb);

    /**
     * 根据出版社查询指定书籍的总记录条数
     * @param press
     * @return
     */
    int countBooksByPress(String press);

    /**
     * 根据书名查询相应的书籍信息
     * @param bname
     * @param pb
     * @return
     */
    List<Book> getBooksByBname(String bname,PageBean<Book> pb);

    /**
     * 根据书名查询指定书籍的总记录条数
     * @param bname
     * @return
     */
    int countBooksByBname(String bname);

    /**
     * 根据书的id查出指定书籍
     * @param bid
     * @return
     */
    Book showBookByBid(String bid);
}
