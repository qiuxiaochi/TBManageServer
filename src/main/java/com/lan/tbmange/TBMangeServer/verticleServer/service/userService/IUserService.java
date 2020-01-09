package com.lan.tbmange.TBMangeServer.verticleServer.service.userService;

import com.lan.tbmange.TBMangeServer.entity.keFu;

public interface IUserService {

	
	String login(keFu keFu);                                     //登录
	String updatePassword(keFu keFu,String newpassword);         //修改密码
	
}
