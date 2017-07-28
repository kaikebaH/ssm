package com.kaikeba.app.dao;

import com.kaikeba.app.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class IBookDaoTest {
    @Autowired
    private IBookDao bookDao;

    @Test
    public void testListBooksByCid() throws Exception {
        Category category = new Category();
        category.setCid("5F79D0D246AD4216AC04E9C5FAB3199E");
        int start = 0;
        int pageSize = 2;
        System.out.println(bookDao.listBooksByCid(category, start, pageSize));
    }

    @Test
    public void testGetCountRowByCid() throws Exception {
        Category category = new Category();
        category.setCid("5F79D0D246AD4216AC04E9C5FAB3199E");
        System.out.println(bookDao.getCountRowByCid(category));
    }
}