package com.lan.tbmange.TBMangeServer.db.daoFactory;

import com.lan.tbmange.TBMangeServer.db.dao.JishuDao;
import com.lan.tbmange.TBMangeServer.db.dbservice.JishuDaoImpl;

/*
 * 技术信息dao层工厂类
 */
public class JishuDaoFactory {
	private static JishuDao jishuDao = null;

	public static JishuDao getJishuDao() {
		if (jishuDao == null) {
			synchronized (JishuDaoImpl.class) {
				if (jishuDao == null) {
					jishuDao = new JishuDaoImpl();
				}
			}
		}
		return jishuDao;
	}
}
