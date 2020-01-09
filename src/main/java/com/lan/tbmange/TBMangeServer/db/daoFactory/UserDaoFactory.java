package com.lan.tbmange.TBMangeServer.db.daoFactory;

import com.lan.tbmange.TBMangeServer.db.dao.UserDao;
import com.lan.tbmange.TBMangeServer.db.dbservice.UserDaoImpl;

public class UserDaoFactory {

	private static UserDao userDao = null;

	public static UserDao getUserDao() {
		if (userDao == null) {
			synchronized (UserDaoImpl.class) {
				if (userDao == null) {
					userDao = new UserDaoImpl();
				}
			}
		}
		return userDao;
	}

}
