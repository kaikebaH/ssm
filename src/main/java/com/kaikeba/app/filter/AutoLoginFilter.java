package com.kaikeba.app.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.kaikeba.app.entity.User;
import com.kaikeba.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 实现自动登录功能
 */
public class AutoLoginFilter implements Filter {
    @Autowired
    private IUserService userService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		if(req.getSession() == null || req.getSession().getAttribute("user") == null){
			Cookie[] cookies = req.getCookies();
			Cookie findCook = null;
			if(cookies != null){
				for(Cookie c : cookies ){
					if("autoLogin".equals(c.getName())){
						findCook = c;
						break;
					}
				}
			}
			if(findCook != null){
				String str = URLDecoder.decode(findCook.getValue(),"utf-8");
				String loginname = str.split(":")[0];
				String loginpass = str.split(":")[1];
                //如果需要在这里对service 进行注入，需要执行以下代码，不然service为null
                ServletContext servletContext = req.getSession().getServletContext();
                XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
                if(cxt != null && cxt.getBean("userService") != null && userService == null){
                    userService = (IUserService)cxt.getBean("userService");
                    User user = userService.getUserByNameAndPass(loginname,loginpass);
                    if(user != null){
                        req.getSession().setAttribute("user", user);
                    }
                }
			}
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

    }

	
}
