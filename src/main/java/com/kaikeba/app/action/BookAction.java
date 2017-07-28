package com.kaikeba.app.action;

import com.kaikeba.app.entity.Book;
import com.kaikeba.app.entity.Category;
import com.kaikeba.app.service.IBookService;
import com.kaikeba.commons.PageBean;
import com.kaikeba.commons.PropKit;
import com.kaikeba.commons.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Controller
@Scope("prototype")
@RequestMapping("/bookAction")
public class BookAction {
    @Autowired
    private IBookService bookService;

    @RequestMapping("/getBooksByCategoryId")
    public String getBooksByCategoryId(@ModelAttribute Category category,HttpServletRequest request){
        PageBean<Book> pb = this.getpb(request);
        //显示每页的书籍
        pb.setList(bookService.getBooksByCid(category,pb));
        //符合条件的记录总条数
        pb.setTotalRecords(bookService.countBooksByCid(category));
        request.setAttribute("pb",pb);
        return "forward:/jsps/book/list.jsp";
    }

    @RequestMapping("/listBooksByPress")
    public String listBooksByPress(@RequestParam String press,HttpServletRequest request){
        PageBean<Book> pb = this.getpb(request);
        pb.setList(bookService.getBooksByPress(press, pb));
        pb.setTotalRecords(bookService.countBooksByPress(press));
        request.setAttribute("pb",pb);
        return "forward:/jsps/book/list.jsp";
    }

    @RequestMapping("/listBooksByBname")
    public String listBooksByBname(@RequestParam String bname,HttpServletRequest request){
        PageBean<Book> pb = this.getpb(request);
        pb.setList(bookService.getBooksByBname(bname, pb));
        pb.setTotalRecords(bookService.countBooksByBname(bname));
        request.setAttribute("pb",pb);
        return "forward:/jsps/book/list.jsp";
    }

    @RequestMapping("/getBookByBid")
    public String getBookByBid(@RequestParam String bid,HttpServletRequest request){
        Book book = bookService.showBookByBid(bid);
        request.setAttribute("book",book);
        return "forward:/jsps/book/desc.jsp";
    }

    private PageBean<Book> getpb(HttpServletRequest request){
        PageBean<Book> pb = new PageBean<Book>();
        pb.setPageNumber(this.getPageNum(request));
        pb.setUrl(this.getUrl(request));
        pb.setPageSize(PropKit.use("pagesize.properties").getInt("pagesize"));
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
