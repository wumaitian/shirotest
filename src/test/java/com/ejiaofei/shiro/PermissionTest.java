package com.ejiaofei.shiro;

import com.ejiaofei.shiro.common.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by dingxin on 2016/1/3.
 */
public class PermissionTest {
    @Test
    public void testPermisson() {
        Subject user = ShiroUtil.login("classpath:shiro_permission.ini", "dingxin", "123");
        if(user.isPermitted("user:update")) {
            System.out.println("有update 权限");
        } else {
            System.out.println("没有update权限");
        }
    }
}
