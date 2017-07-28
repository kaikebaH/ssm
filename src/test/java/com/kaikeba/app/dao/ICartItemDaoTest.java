package com.kaikeba.app.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class ICartItemDaoTest {

    @Autowired
    private ICartItemDao cartItemDao;

    @Test
    public void testGetCartByUid() throws Exception {
        System.out.println(cartItemDao.getCartByUid("32DB3700D2564254982BC58B0E4D95BC"));
    }

    @Test
    public void testLoadCartItems() throws Exception {
        List<String> a = new ArrayList<String>();
        a.add("161CC73026EA49358B3F875F80E41D31");
        a.add("CB86EDEF39BE4AD7BD62E8D7DF84AA83");
        System.out.println(cartItemDao.loadCartItems(a));
    }
}