package com.kaikeba.app.service;

import com.kaikeba.app.dao.IOrderDao;
import com.kaikeba.app.entity.Order;
import com.kaikeba.commons.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public interface IOrderService {

    /**
     *根据uid查询订单信息
     * @param uid
     * @param pb
     * @return
     */
    List<Order> getOrdersByUid(String uid,PageBean<Order> pb);

    /**
     * 根据uid查询指定订单的总记录条数
     * @param uid
     * @return
     */
    int countOrderByUid(String uid);

    /**
     * 天假订单
     * @param order
     */
    void add(Order order);

    /**
     * 根据oid得到指定的order详细信息
     * @param oid
     * @return
     */
    Order getOrderByOid(String oid);

    /**
     * 删除订单
     */
    void delOrderByOid(String oid);

    /**
     * 确认收货
     * @param oid
     */
    void updateStatusByOid(String oid);
    void updateStatusByOid(String oid,int status);
}
