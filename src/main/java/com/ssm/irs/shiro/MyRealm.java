package com.ssm.irs.shiro;

import com.ssm.irs.mapper.AdminMapper;
import com.ssm.irs.pojo.Admin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm{

  private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

  @Autowired
  private AdminMapper adminMapper;

  //authenticationToken.getPrincipal();授权SimpleAuthorizationInfo
  //principalCollection.getPrimaryPrincipal();认证SimpleAuthercationInfo


  /**
   * 授权方法
   * @param principalCollection
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    //获取当前用户在数据库中配置得权限
    Admin admin= (Admin)principalCollection.getPrimaryPrincipal();
    logger.info("获取到Admin的信息"+admin.getUsername());
    List<String> list= adminMapper.selectByPerms(admin.getUsername());
    if(CollectionUtils.isEmpty(list)){
      //将list数据里的空字符串移除掉
      for(int i = 0;i<list.size();i++){
        if("".equals(list.get(i))){
          list.remove(i);

        }
      }
    }
    logger.info("list数据大小"+list);

    //把用户权限放到shiro中
    SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();

    Set<String> permsSessions = new HashSet<String>();

    permsSessions.addAll(list);

    sazi.setStringPermissions(permsSessions);
    return sazi;
  }

  /**
   * 认证方法
   * @param authenticationToken
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    //从数据库获取到登录名进行认证
    String userName = (String)authenticationToken.getPrincipal();

    Admin admin=adminMapper.selectByUserName(userName);
    if(ObjectUtils.isEmpty(admin)){
      return null;
    }
    //将用户名得信息放到shiro的SimpleAuthenticationInfo中  "myRealm"
    SimpleAuthenticationInfo saci=new SimpleAuthenticationInfo(admin,admin.getPassword(),getName());
    return saci;

  }
}
