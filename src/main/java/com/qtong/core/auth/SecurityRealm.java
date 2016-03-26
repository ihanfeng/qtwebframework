package com.qtong.core.auth;

import com.qtong.core.model.User;
import com.qtong.core.service.BaseService;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;


/**
 * Created by ZML on 2015/4/22.
 */
public class SecurityRealm extends AuthorizingRealm {

	private BaseService baseService;

	@Resource
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {

		String username = (String) principalCollection.fromRealm(getName())
				.iterator().next();

		if (username != null) {

			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

			authorizationInfo.setRoles(baseService.getUserRoleNames(username));

			authorizationInfo.setStringPermissions(baseService
					.getUserPermissions(username));
			return authorizationInfo;

		}
		return null;
	}


	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		User user = baseService.queryUniqueUser(token.getUsername());
		if (user == null) {
			throw new UnknownAccountException("该账户不存在");
		}
		if (!user.isEnable()) {
			throw new LockedAccountException("该账户禁止登录");
		}

        if(user.isExpired()){
            throw new AccountException("该账户已过期");
        }

        if(!user.isActived()){
            throw new AccountException("该账户尚未激活");
        }

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUsername(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getSalt()),// salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}
}
