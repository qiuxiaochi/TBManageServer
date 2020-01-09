package com.lan.tbmange.TBMangeServer.db.dbservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lan.tbmange.TBMangeServer.db.DBManager;
import com.lan.tbmange.TBMangeServer.db.dao.UserDao;
import com.lan.tbmange.TBMangeServer.entity.keFu;


public class UserDaoImpl implements UserDao {

	@Override
	public String login(keFu keFu) {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "select password from kefu where kefuname=?";
	
			pst = conn.prepareStatement(sql);
			pst.setString(1, keFu.getUsername());

			resultSet = pst.executeQuery();
			
			if(resultSet.next()) {
				
				String password = resultSet.getString(1);
				if(!password.equals(keFu.getPassword())) {
					return "password error";
				}
				return "ok";
				
			}else {
				return "username error";
			}

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return "error other";
		
	}

	
	@Override
	public String UpdatePassword(keFu keFu, String newpassword) {
		
		String rsString = userValidate(keFu);
		if(!rsString.equals("ok")) {
			return "oldpassword error";
		}
		
		Object[] objects = {
				newpassword,
				keFu.getUsername()
		};
		
		String sql = "udpate kefu set password=? where kefuname=?";
		
		int row = DBManager.executeUpdate(sql, objects);
		
		if(row>0) {
			return "ok";
		}
		return "error";

	}


	@Override
	public String userValidate(keFu keFu) {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "select password from kefu where kefuname=?";
	
			pst = conn.prepareStatement(sql);
			pst.setString(1, keFu.getUsername());

			resultSet = pst.executeQuery();
			
			if(resultSet.next()) {
				
				String password = resultSet.getString(1);
				if(!password.equals(keFu.getPassword())) {
					return "password error";
				}
				return "ok";
				
			}else {
				return "username error";
			}

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return "error other";
	}

}
