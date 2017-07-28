package com.kaikeba.app.dao;

import com.kaikeba.app.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/7/24.
 */
public interface IUserDao {
    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    /**
     * 通过激活码得到对应用户信息
     * @param activationCode
     * @return
     */
    User getUserByActivationCode(String activationCode);

    /**
     * 修改用户状态
     * @param activationCode
     */
    void  updateStatusByActivationCode(String activationCode);

    /**
     * 根据用户名和密码获取用户
     * @param loginname
     * @param loginpass
     * @return
     */
    User getUserByNameAndPass(@Param("loginname")String loginname,@Param("loginpass")String loginpass);
}
