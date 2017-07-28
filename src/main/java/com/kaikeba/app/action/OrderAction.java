package com.kaikeba.app.action;

import com.kaikeba.app.entity.*;
import com.kaikeba.app.service.ICartItemService;
import com.kaikeba.app.service.IOrderService;
import com.kaikeba.app.utils.CommonUtils;
import com.kaikeba.app.utils.paymentUtils;
import com.kaikeba.commons.PageBean;
import com.kaikeba.commons.PropKit;
import com.kaikeba.commons.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/26.
 */
@Controller
@RequestMapping("/orderAction")
public class OrderAction {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICartItemService cartItemService;

    @RequestMapping("/listOrdersByUser")
    public String listOrdersByUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageBean<Order> pb = this.getpb(request);
        pb.setList(orderService.getOrdersByUid(user.getUid(), pb));
        pb.setTotalRecords(orderService.countOrderByUid(user.getUid()));
        request.setAttribute("pb",pb);
        return "forward:/jsps/order/list.jsp";
    }
    @RequestMapping("/saveOrder")
    public String saveOrder(@RequestParam String cartItemIds,@RequestParam String address,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Order order = new Order();
        order.setOid(CommonUtils.uuid());
        order.setAddress(address);
        order.setUser(user);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ordertime = dateFormat.format(new Date());
        order.setOrdertime(ordertime);
        order.setStatus(1);
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        List<CartItem> cartItems = cartItemService.loadCartItems(cartItemIds.split(","));
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemId(CommonUtils.uuid());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setBook(cartItem.getBook());
            orderItem.setBname(cartItem.getBook().getBname());
            orderItem.setCurrPrice(cartItem.getBook().getCurrPrice());
            orderItem.setImage_b(cartItem.getBook().getImage_b());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        BigDecimal totaldDecimal = new BigDecimal("0");
        for (OrderItem orderItem : orderItems) {
            totaldDecimal = totaldDecimal.add(new BigDecimal(orderItem.getSubtotal() + ""));
        }
        order.setTotal(totaldDecimal.doubleValue());
        // 给订单增加订单明细的数据
        order.setOrderItemList(orderItems);
        orderService.add(order);
        //删除购物车数据
        cartItemService.delCartItemByCartItemIds(cartItemIds.split(","));
        request.setAttribute("order", order);
        return "forward:/jsps/order/ordersucc.jsp";
    }

    @RequestMapping("/getOrderByOid")
    public String getOrderByOid(@RequestParam String oid,HttpServletRequest request){
        Order order = orderService.getOrderByOid(oid);
        request.setAttribute("order", order);
        return "forward:/jsps/order/desc.jsp";
    }

    @RequestMapping("/delOrder")
    public String delOrder(@RequestParam String oid ,HttpServletRequest request){
        orderService.delOrderByOid(oid);
        User user = (User) request.getSession().getAttribute("user");
        PageBean<Order> pb = this.getpb(request);
        pb.setList(orderService.getOrdersByUid(user.getUid(), pb));
        pb.setTotalRecords(orderService.countOrderByUid(user.getUid()));
        request.setAttribute("pb",pb);
        return "forward:/jsps/order/list.jsp";
    }

    @RequestMapping("/confirmReceipt")
    public String confirmReceipt(@RequestParam String oid,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        orderService.updateStatusByOid(oid);
        PageBean<Order> pb = this.getpb(request);
        pb.setList(orderService.getOrdersByUid(user.getUid(), pb));
        pb.setTotalRecords(orderService.countOrderByUid(user.getUid()));
        request.setAttribute("pb",pb);
        return "forward:/jsps/order/list.jsp";
    }

    //提交orderId和订单金额到支付页面
    @RequestMapping("/paymentPre")
    public String paymentPre(@RequestParam String oid,HttpServletRequest request){
        Order order = orderService.getOrderByOid(oid);
        request.setAttribute("order", order);
        return "forward:/jsps/order/pay.jsp";
    }
    @RequestMapping("/payment")
    public String payment(HttpServletRequest request,HttpServletResponse response){
        try {
            String p0_Cmd = "Buy";

            Properties properties = new Properties();
            InputStream is = OrderAction.class.getResourceAsStream("/mercmantInfo.properties");
            properties.load(is);

            String p1_MerId = properties.getProperty("p1_MerId");
            String p2_Order = request.getParameter("oid");
            String p3_Amt = "0.01";
            String p4_Cur = "CNY";
            String p5_Pid = "";
            String p6_Pcat = "";
            String p7_Pdesc = "";
            String p8_Url = properties.getProperty("responseURL");
            String p9_SAF = "";
            String pa_MP = "";
            String pd_FrpId = request.getParameter("yh");
            String pr_NeedResponse = "1";
            String keyValue = properties.getProperty("KeyValue");
            String hmac = paymentUtils.buildHmac(p0_Cmd, p1_MerId, p2_Order,
                    p3_Amt, p4_Cur, p5_Pid, p6_Pcat,
                    p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId,
                    pr_NeedResponse, keyValue);

            StringBuilder sb = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node");
            sb.append("?").append("p0_Cmd=").append(p0_Cmd);
            sb.append("&").append("p1_MerId=").append(p1_MerId);
            sb.append("&").append("p2_Order=").append(p2_Order);
            sb.append("&").append("p3_Amt=").append(p3_Amt);
            sb.append("&").append("p4_Cur=").append(p4_Cur);
            sb.append("&").append("p5_Pid=").append(p5_Pid);
            sb.append("&").append("p6_Pcat=").append(p6_Pcat);
            sb.append("&").append("p7_Pdesc=").append(p7_Pdesc);
            sb.append("&").append("p8_Url=").append(p8_Url);
            sb.append("&").append("p9_SAF=").append(p9_SAF);
            sb.append("&").append("pa_MP=").append(pa_MP);
            sb.append("&").append("pd_FrpId=").append(pd_FrpId);
            sb.append("&").append("pr_NeedResponse=").append(pr_NeedResponse);
            sb.append("&").append("hmac=").append(hmac);

            response.sendRedirect(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @RequestMapping("/back")
    public String back(HttpServletRequest request,HttpServletResponse response){
        //获取返回的数据
        try {
            String p1_MerId = request.getParameter("p1_MerId");
            String r0_Cmd = request.getParameter("p0_Cmd");
            String r1_Code = request.getParameter("r1_Code");
            String r2_TrxId = request.getParameter("r2_TrxId");
            String r3_Amt = request.getParameter("r3_Amt");
            String r4_Cur = request.getParameter("r4_Cur");
            String r5_Pid = request.getParameter("r5_Pid");
            String r6_Order = request.getParameter("r6_Order");
            String r7_Uid = request.getParameter("r7_Uid");
            String r8_MP = request.getParameter("r8_MP");
            String r9_BType = request.getParameter("r9_BType");
            String hmac = request.getParameter("hmac");

            Properties properties = new Properties();
            InputStream is = OrderAction.class.getResourceAsStream("/mercmantInfo.properties");
            properties.load(is);

            String keyValue = properties.getProperty("KeyValue");

            boolean bool = paymentUtils.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code,
                    r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order,
                    r7_Uid, r8_MP, r9_BType, keyValue);
            if(!bool){
                request.setAttribute("code", "error");
                request.setAttribute("msg", "无效的签名,支付失败!");
                return "forward:/jsps/msg.jsp";
            }

            if("1".equals(r1_Code)){
                orderService.updateStatusByOid(r6_Order,2);
                if(r9_BType.equals("1")){
                    request.setAttribute("code", "success");
                    request.setAttribute("msg", "支付成功！");
                    return "forward:/jsps/msg.jsp";
                }else if(r9_BType.equals("2")){
                    response.getWriter().write("success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "";
    }

    private PageBean<Order> getpb(HttpServletRequest request){
        PageBean<Order> pb = new PageBean<Order>();
        pb.setPageNumber(this.getPageNum(request));
        pb.setUrl(this.getUrl(request));
        pb.setPageSize(PropKit.use("pagesize.properties").getInt("order_pageSize"));
        return pb;
    }

    /**
     * 从请求流中获取前台传递过来的当前页的页码
     * @param request
     * @return
     */
    private int getPageNum(HttpServletRequest request){
        try {
            String pageNumber = request.getParameter("pageNumber");
            if(StrKit.notBlank(pageNumber)){
                //pageNumber不为空，说明当前不是第一页
                return Integer.parseInt(pageNumber);
            }else {
                //pageNumber为空，当前页为第一页
                return 1;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 动态获取前台传递过来的url
     * @param request
     * @return
     */
    private String getUrl(HttpServletRequest request){
        try {
            String uri = request.getRequestURI();// 得到的是：/ssm/bookAction/getBooksByCategoryId
            //request.getRequestURL(); //得到的是：http://localhost/ssm/bookAction/getBooksByCategoryId
            String queryString = request.getQueryString(); //得到请求地址 ? 后面的值
            //如果请求地址为：/ssm/bookAction/getBooksByCategoryId
            if(queryString == null){
                return uri;
            }else {
                //如果请求地址为：/ssm/bookAction/getBooksByCategoryId?pageNumber=2
                if(queryString.indexOf("pageNumber=") == 0){
                    return uri;
                }
                //如果请求地址为：/ssm/bookAction/getBooksByCategoryId?bname=java&pageNumber=2
                if(queryString.contains("&pageNumber=")){
                    return uri + "?" + queryString.substring(0,queryString.lastIndexOf("&pageNumber="));
                    //substring:前闭后开
                }
                //如果请求地址为：/ssm/bookAction/getBooksByCategoryId?bname=java(不需要分页)
                return uri + "?" + queryString;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
