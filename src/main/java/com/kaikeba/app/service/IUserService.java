package com.kaikeba.app.service;

import com.kaikeba.app.entity.User;

/**
 * Created by Administrator on 2017/7/24.
 */
public interface IUserService {
    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    /**
     * 通过激活码修改客户状态
     * @param activationCode
     */
    void updateStatusByActivationCode(String activationCode);

    /**
     * 根据用户名和密码获取用户
     * @param loginname
     * @param loginpass
     * @return
     */
    User getUserByNameAndPass(String loginname,String loginpass);
}
