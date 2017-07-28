package com.kaikeba.app.service.impl;

import com.kaikeba.app.dao.IBookDao;
import com.kaikeba.app.entity.Book;
import com.kaikeba.app.entity.Category;
import com.kaikeba.app.service.IBookService;
import com.kaikeba.commons.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Service
public class BookServiceImpl implements IBookService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IBookDao bookDao;
    @Override
    public List<Book> getBooksByCid(Category category,PageBean<Book> pb) {
        try {
            int pageSize = pb.getPageSize();
            int startPage = (pb.getPageNumber() - 1) * pageSize;
            return bookDao.listBooksByCid(category,startPage,pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public int countBooksByCid(Category category) {
        try {
            return bookDao.getCountRowByCid(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public List<Book> getBooksByPress(String press, PageBean<Book> pb) {
        try {
            int pageSize = pb.getPageSize();
            int startPage = (pb.getPageNumber() - 1) * pageSize;
            return bookDao.listBooksByPress(press, startPage, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public int countBooksByPress(String press) {
        try {
            return bookDao.getCountRowByPress(press);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public List<Book> getBooksByBname(String bname, PageBean<Book> pb) {
        try {
            int pageSize = pb.getPageSize();
            int startPage = (pb.getPageNumber() - 1) * pageSize;
            return bookDao.listBooksByBname(bname, startPage, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public int countBooksByBname(String bname) {
        try {
            return bookDao.getCountRowByBname(bname);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public Book showBookByBid(String bid) {
        try {
            return bookDao.getBookByBid(bid);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw  new RuntimeException(e.getMessage(),e);
        }
    }
}
