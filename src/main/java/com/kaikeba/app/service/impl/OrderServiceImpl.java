package com.kaikeba.app.service.impl;

import com.kaikeba.app.dao.IOrderDao;
import com.kaikeba.app.entity.Order;
import com.kaikeba.app.entity.OrderItem;
import com.kaikeba.app.service.IOrderService;
import com.kaikeba.commons.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Order> getOrdersByUid(String uid, PageBean<Order> pb) {
        int pageSize = pb.getPageSize();
        int startPage = (pb.getPageNumber() - 1) * pageSize;
        return orderDao.listOrderByUid(uid, startPage, pageSize);
    }

    @Override
    public int countOrderByUid(String uid) {
        return orderDao.getCountRowByUid(uid);
    }

    @Transactional
    @Override
    public void add(Order order) {
        orderDao.add(order);
        //保存订单明细信息
        List<OrderItem> orderItems = order.getOrderItemList();
        for(OrderItem orderItem : orderItems){
            orderDao.saveOrderItem(orderItem);
        }
    }

    @Override
    public Order getOrderByOid(String oid) {
        Order order = orderDao.getOrderByOid(oid);
        List<OrderItem> orderItems = orderDao.getOrderItemByOid(oid);
        order.setOrderItemList(orderItems);
        return order;
    }

    @Transactional
    @Override
    public void delOrderByOid(String oid) {
        orderDao.delOrderItemByOid(oid);
        orderDao.delOrderByOid(oid);
    }

    @Override
    public void updateStatusByOid(String oid) {
        orderDao.updateStatusByOid(oid);
    }

    @Override
    public void updateStatusByOid(String oid, int status) {
        orderDao.updateByOid(oid,status);
    }
}
