package com.kaikeba.app.service.impl;

import com.kaikeba.app.dao.IUserDao;
import com.kaikeba.app.entity.User;
import com.kaikeba.app.service.IUserService;
import com.kaikeba.app.utils.CommonUtils;
import com.kaikeba.app.utils.Mail;
import com.kaikeba.app.utils.MailUtils;
import com.kaikeba.commons.PropKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by Administrator on 2017/7/24.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public void register(User user) {
        try {
            user.setUid(CommonUtils.uuid());
            user.setStatus(0);
            user.setActivationCode(CommonUtils.uuid());
            userDao.register(user);

            String from = PropKit.use("email_template.properties").get("from");
            String subject = PropKit.use("email_template.properties").get("subject");
            String content = PropKit.use("email_template.properties").get("content");
            String host = PropKit.use("email_template.properties").get("host");
            String username = PropKit.use("email_template.properties").get("username");
            String password = PropKit.use("email_template.properties").get("password");
            Session session = MailUtils.createSession(host, username, password);
            content = MessageFormat.format(content, user.getActivationCode());
            Mail mail = new Mail(from,user.getEmail(),subject,content);
            MailUtils.send(session, mail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatusByActivationCode(String activationCode) {
        User user = userDao.getUserByActivationCode(activationCode);
        if(user == null){
            throw new RuntimeException("激活码不存在，请检查您的激活码!");
        }

        if(user.getStatus() != 0){
            throw new RuntimeException("您已激活，可以登录了！");
        }

        userDao.updateStatusByActivationCode(activationCode);
        user.setStatus(1);
    }

    @Override
    public User getUserByNameAndPass(String loginname, String loginpass) {
        return userDao.getUserByNameAndPass(loginname,loginpass);
    }
}
