package com.lan.tbmange.TBMangeServer.verticleServer.service.userService;

import com.lan.tbmange.TBMangeServer.db.daoFactory.UserDaoFactory;
import com.lan.tbmange.TBMangeServer.entity.keFu;

public class UserServiceImpl implements IUserService {

	@Override
	public String login(keFu keFu) {
		return UserDaoFactory.getUserDao().login(keFu);
	}

	@Override
	public String updatePassword(keFu keFu,String newpassword) {
		return UserDaoFactory.getUserDao().UpdatePassword(keFu, newpassword);
	}

	
		
}
