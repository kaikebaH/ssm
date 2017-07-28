package com.kaikeba.app.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class IOrderDaoTest {
    @Autowired
    private IOrderDao orderDao;

    @Test
    public void testListOrderByUid() throws Exception {
        System.out.println(orderDao.listOrderByUid("FB09A821B29A4975A5F457C30366955D",0,4));
    }

    @Test
    public void testGetCountRowByUid() throws Exception {
        System.out.println(orderDao.getCountRowByUid("FB09A821B29A4975A5F457C30366955D"));
    }

    @Test
    public void testGetOrderItemByOid() throws Exception {
        System.out.println(orderDao.getOrderItemByOid("49127E9642C5474FA8F2139204DE571C"));
    }
}