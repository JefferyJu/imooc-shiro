package com.imooc.dao;

import com.imooc.vo.User;

import java.util.List;

/**
 * @author: JefferyJu
 * @date: 2018/5/9
 */

public interface UserDao {
    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 通过用户名获取权限
     *
     * @param userName
     * @return
     */
    List<String> queryRolesByUserName(String userName);
}
