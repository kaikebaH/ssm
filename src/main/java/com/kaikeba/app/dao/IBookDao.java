package com.kaikeba.app.dao;

import com.kaikeba.app.entity.Book;
import com.kaikeba.app.entity.Category;
import com.kaikeba.commons.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public interface IBookDao {
    /**
     * 根据cid分页查询出指定页面的所有数据
     * @param category
     * @param startPage 第几页
     * @param pageSize  一个显示的记录条数
     * @return
     */
    List<Book> listBooksByCid(@Param("category")Category category,@Param("startPage")int startPage,@Param("pageSize")int pageSize);

    /**
     * 根据cid查询出指定条件的所有的记录条数
     * @param category
     * @return
     */
    int getCountRowByCid(@Param("category")Category category);

    /**
     * 根据出版社查询指定书籍信息
     * @param press
     * @param startPage
     * @param pageSize
     * @return
     */
    List<Book> listBooksByPress(@Param("press")String press,@Param("startPage")int startPage,@Param("pageSize")int pageSize);

    /**
     * 根据出版社查询指定书籍的总记录条数
     * @param press
     * @return
     */
    int getCountRowByPress(String press);

    /**
     * 根据书名查询相应的书籍信息
     * @param bname
     * @param startPage
     * @param pageSize
     * @return
     */
    List<Book> listBooksByBname(@Param("bname")String bname, @Param("startPage")int startPage, @Param("pageSize")int pageSize);

    /**
     * 根据书名查询指定书籍的总记录条数
     * @param bname
     * @return
     */
    int getCountRowByBname(@Param("bname")String bname);

    /**
     * 根据书的id查出指定书籍
     * @param bid
     * @return
     */
    Book getBookByBid(String bid);
}
