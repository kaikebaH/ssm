package com.kaikeba.app.action;

import com.kaikeba.app.entity.User;
import com.kaikeba.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/24.
 */
@Controller
@Scope("prototype")
@RequestMapping("/userAction")
public class UserAction {
    @Autowired
    private IUserService userService;

    @RequestMapping("/regist")
    public String regist(@ModelAttribute User user,HttpServletRequest request){
        Map<String,String> errors = new HashMap<String,String>();
        if(user.getLoginname() == null || user.getLoginname().trim().length() == 0){
            errors.put("loginname", "用户名不能为空！");
        }else if(!(user.getLoginname().length() >= 3 && user.getLoginname().length() <= 20)){
            errors.put("loginname", "长度在3到20之间！");
        }

        if(user.getLoginpass() == null || user.getLoginpass().trim().length() == 0){
            errors.put("loginpass", "密码不能为空！");
        }else if(!(user.getLoginpass().length() >= 3 && user.getLoginpass().length() <= 10)){
            errors.put("loginpass", "长度在3到10之间！");
        }

        if(user.getReloginpass() == null || user.getReloginpass().trim().length() == 0){
            errors.put("reloginpass", "确认密码不能为空！");
        }else if(!(user.getReloginpass().equals(user.getLoginpass()))){
            errors.put("reloginpass", "两次密码不一致！");
        }

        if(user.getEmail() == null || user.getEmail().trim().length() == 0){
            errors.put("email", "邮箱不能为空！");
        }

        if(user.getVerifyCode() == null || user.getVerifyCode().trim().length() == 0){
            errors.put("verifyCode", "验证码不能为空！");
        }


        String code = user.getVerifyCode();
        String imgCode = (String) request.getSession().getAttribute("vCode");
        if(!code.equalsIgnoreCase(imgCode)){
            errors.put("verifyCode", "验证码错误！");
        }
        if(errors.size() != 0){
            request.setAttribute("errors",errors);
            return "forward:/jsps/user/regist.jsp";
        }
        //存入数据库
        try {
            userService.register(user);
            request.setAttribute("code", "success");
            request.setAttribute("msg", "注册成功，请到邮箱激活！");
            return "forward:/jsps/msg.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/activation")
    public String activation(@RequestParam String activationCode, HttpServletRequest request){
        try {
            userService.updateStatusByActivationCode(activationCode);
            request.setAttribute("code", "success");
            request.setAttribute("msg", "激活成功，可以进行登录了！");
            return "forward:/jsps/msg.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute User user,HttpServletRequest request,HttpServletResponse response){
        User findUser = userService.getUserByNameAndPass(user.getLoginname(), user.getLoginpass());
        Map<String,String> errors = new HashMap<String,String>();
        if(user.getLoginname() == null || user.getLoginname().trim().length() == 0){
            errors.put("loginname", "用户名不能为空！");
        }else if(!(user.getLoginname().length() >= 3 && user.getLoginname().length() <= 20)){
            errors.put("loginname", "长度在3到20之间！");
        }

        if(user.getLoginpass() == null || user.getLoginpass().trim().length() == 0){
            errors.put("loginpass", "密码不能为空！");
        }else if(!(user.getLoginpass().length() >= 3 && user.getLoginpass().length() <= 10)){
            errors.put("loginpass", "长度在3到10之间！");
        }
        if(user.getVerifyCode() == null || user.getVerifyCode().trim().length() == 0){
            errors.put("verifyCode", "验证码不能为空！");
        }
        String imgCode = (String) request.getSession().getAttribute("vCode");
        String code = user.getVerifyCode();

        if(!code.equalsIgnoreCase(imgCode)){
            errors.put("verifyCode", "验证码错误！");
        }


        if(errors.size() != 0){
            request.setAttribute("errors",errors);
            return "forward:/jsps/user/login.jsp";
        }

        if(findUser == null){
            //用户名或密码错误,转发到登录页
            request.setAttribute("msg","用户名或密码错误");
            return "forward:/jsps/user/login.jsp";
        }

        if(findUser.getStatus() == 0){
            request.setAttribute("msg","账户未激活，请先激活");
            return "forward:/jsps/user/login.jsp";
        }
        //七天内自动登录
        if("true".equals(request.getParameter("autoLogin"))){
            try {
                Cookie autoCookieC = new Cookie("autoLogin", URLEncoder.encode(user.getLoginname() + ":" + user.getLoginpass(), "utf-8"));
                autoCookieC.setPath("/ssm");
                autoCookieC.setMaxAge(3600 * 24 * 7);
                response.addCookie(autoCookieC);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
       }
        request.getSession().setAttribute("user", findUser);
        return "redirect:/index.jsp";
    }

    @RequestMapping("/quit")
    public String quit(HttpServletRequest request,HttpServletResponse response){
        if(request.getSession(false) != null){
            request.getSession().invalidate();//第一种方式
            //request.getSession().removeAttribute("user");  第二种方式
            //删除自动登录cookie
            Cookie autoLoginC = new Cookie("autoLogin", "");
            autoLoginC.setPath("/ssm");
            autoLoginC.setMaxAge(0);
            response.addCookie(autoLoginC);
        }
        return "redirect:/jsps/user/login.jsp";
    }
}
