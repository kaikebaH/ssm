package com.kaikeba.app.action;

import com.kaikeba.app.entity.Book;
import com.kaikeba.app.entity.CartItem;
import com.kaikeba.app.entity.User;
import com.kaikeba.app.service.IBookService;
import com.kaikeba.app.service.ICartItemService;
import com.kaikeba.app.utils.CommonUtils;
import org.apache.ibatis.javassist.bytecode.LineNumberAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
@Controller
@Scope("prototype")
@RequestMapping("/cartItemAction")
public class CartItemAction {
    @Autowired
    private ICartItemService cartItemService;
    @Autowired
    private IBookService bookService;

    @RequestMapping("/mycart")
    public String mycart(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> list = cartItemService.getCartByUid(user.getUid());
        request.setAttribute("clist", list);
        return "forward:/jsps/cart/list.jsp";
    }

    @RequestMapping("/saveCartItem")
    public String saveCartItem(@ModelAttribute CartItem cartItem,@RequestParam String bid, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //没有登录
        if(user == null){
            return "forward:/jsps/user/login.jsp";
        }
        CartItem findCartItem = cartItemService.getCartItemByBidAndUid(bid,user.getUid());
        if(findCartItem != null){
            //修改购买数量
            cartItemService.updateQuantityByCartItemId(findCartItem);
        } else {
            //添加到购物车
            findCartItem = new CartItem();
            findCartItem.setCartItemId(CommonUtils.uuid());
            findCartItem.setQuantity(cartItem.getQuantity());
            findCartItem.setOrderBy(22);
            findCartItem.setUser(user);
            Book book = bookService.showBookByBid(bid);
            findCartItem.setBook(book);
            cartItemService.addCart(findCartItem);
        }
        List<CartItem> list = cartItemService.getCartByUid(user.getUid());
        request.setAttribute("clist", list);
        return "forward:/jsps/cart/list.jsp";
    }

    @RequestMapping("/removeCartItemByCid")
    public String removeCartItemByCid(@RequestParam String cartItemId,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        cartItemService.deleteCartItem(cartItemId);
        List<CartItem> list = cartItemService.getCartByUid(user.getUid());
        request.setAttribute("clist", list);
        return "forward:/jsps/cart/list.jsp";
    }
    @RequestMapping("/removeCartItemsByCids")
    public String removeCartItemsByCids(@RequestParam String cids,HttpServletRequest request){
        String[] cidsArray = cids.split(",");
        User user = (User) request.getSession().getAttribute("user");
        cartItemService.delCartItemByCartItemIds(cidsArray);
        List<CartItem> list = cartItemService.getCartByUid(user.getUid());
        request.setAttribute("clist", list);
        return "forward:/jsps/cart/list.jsp";
    }

    //有bagug
    @ResponseBody
    @RequestMapping("/removeCartItemByCidAjax")
    public List<CartItem> removeCartItemByCidAjax(@RequestParam String cartItemId,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        cartItemService.deleteCartItem(cartItemId);
        List<CartItem> list = cartItemService.getCartByUid(user.getUid());
        return  list;
    }
    @ResponseBody
    @RequestMapping("/minusQuantityByCidAjax")
    public List<CartItem> minusQuantityByCidAjax(@RequestParam String cartItemId,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        cartItemService.updateQuantityByCartItemId(cartItemId);
        List<CartItem> list = cartItemService.getCartByUid(user.getUid());
        return  list;
    }
    @ResponseBody
    @RequestMapping("/plusQuantityByCidAjax")
    public List<CartItem> plusQuantityByCidAjax(@ModelAttribute CartItem cartItem,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        cartItemService.updateQuantityByCartItemId(cartItem);
        List<CartItem> list = cartItemService.getCartByUid(user.getUid());
        return  list;
    }
    @RequestMapping("/listCartItemsByCids")
    public String listCartItemsByCids(@RequestParam String cartItemIds,HttpServletRequest request){
        String[] cartItemIdsStrings = cartItemIds.split(",");
        List<CartItem> cartItems = cartItemService.loadCartItems(cartItemIdsStrings);
        request.setAttribute("cartItemIds", cartItemIds);
        request.setAttribute("clist", cartItems);
        return "forward:/jsps/cart/showitem.jsp";
    }
}
