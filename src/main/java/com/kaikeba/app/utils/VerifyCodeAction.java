package com.kaikeba.app.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/verifyCodeAction")
public class VerifyCodeAction {

    @RequestMapping("/verifyCode")
	public String verifyCode(HttpServletRequest request,HttpServletResponse response) {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();//获取一次性验证码图片
		// 该方法必须在getImage()方法之后来调用
//		System.out.println(vc.getText());//获取图片上的文本
		try {
			VerifyCode.output(image, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}//把图片写到指定流中
		
		// 把文本保存到session中，为LoginServlet验证做准备
		request.getSession().setAttribute("vCode", vc.getText());
		return "";
	}
}
