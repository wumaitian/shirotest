package com.ejiaofei.shiro;

import com.ejiaofei.shiro.common.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by dingxin on 2016/1/3.
 */
public class RoleTest {
    @Test
    public void testRole(){
        Subject user = ShiroUtil.login("classpath:shiro_role.ini", "ejiaofei", "123456");
        if(user.hasRole("role1")) {
            System.out.println("有role1角色");
        } else {
            System.out.println("没有role1角色");
        }
    }
}
