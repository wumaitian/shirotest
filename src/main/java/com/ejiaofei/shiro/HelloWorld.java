package com.ejiaofei.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dingxin on 2016/1/3.
 */
public class HelloWorld {
    static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    public static void main(String[] args) {
        //　读取配置文件，初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取SecurityManager实例
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到当前执行的用户
        Subject user = SecurityUtils.getSubject();
        //创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("ejiaofei","123456");
        try {
            user.login(token);
            logger.info("登陆成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            logger.error("登陆失败");
        }
        user.logout();
    }
}
