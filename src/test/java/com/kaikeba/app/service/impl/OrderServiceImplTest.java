package com.kaikeba.app.service.impl;

import com.kaikeba.app.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class OrderServiceImplTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void testGetOrdersByUid() throws Exception {

    }
}