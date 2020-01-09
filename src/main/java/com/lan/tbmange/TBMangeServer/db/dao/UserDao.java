package com.lan.tbmange.TBMangeServer.db.dao;

import com.lan.tbmange.TBMangeServer.entity.keFu;

/*
 * 	用户dao
 */
public interface UserDao {

	String login(keFu keFu);                               //登录
	String userValidate(keFu keFu);                         //获得某账号是否有效 即账号和面膜是否匹对
	String UpdatePassword(keFu keFu,String newpassword);   //修改密码
	
}
