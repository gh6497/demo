package cn.cat.springmvc.demo.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;


public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	//密码比较的方法   token代表用户在界面输入的用户名和密码     info代表从数据库中得到加密数据
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//1.向下转型 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		Object credentials = info.getCredentials();
		String pwd = new String(upToken.getPassword());
		return credentials.equals(pwd);
	}

	
}
