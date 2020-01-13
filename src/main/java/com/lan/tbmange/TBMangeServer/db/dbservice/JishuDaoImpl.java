package com.lan.tbmange.TBMangeServer.db.dbservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import com.lan.tbmange.TBMangeServer.db.DBManager;
import com.lan.tbmange.TBMangeServer.db.dao.JishuDao;
import com.lan.tbmange.TBMangeServer.entity.Jishu;
import com.lan.tbmange.TBMangeServer.entity.Vote;
import com.lan.tbmange.TBMangeServer.entity.keFu;

public class JishuDaoImpl implements JishuDao {

	@Override
	public String addJishu(keFu keFu, Jishu jishu) {
		
		Object[] objects = {
				jishu.getQq(),
				jishu.getWx(),
				jishu.getYes(),
				jishu.getNo(),
				jishu.getInfo(),
				jishu.getLabel(),
				jishu.getSkill()
		};
		
		String sql = "insert into jishu(qq,wx,yes,no,info,label,skill) values(?,?,?,?,?)";
		int row = DBManager.executeUpdate(sql, objects);
		
		if(row>0) {
			return "ok";
		}
		return "error";

	}

	
	@Override
	public String supplyJishu(keFu keFu, Jishu jishu) {
		
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from jishu where qq=?";
	
			pst = conn.prepareStatement(sql);
			pst.setString(1, jishu.getQq());

			resultSet = pst.executeQuery();
			
			String info = "";
			String label = "";
			String skill = "";
			
			if(resultSet.next()) {
				
				info = resultSet.getString(6)+"\n\n"+jishu.getInfo();
				label = resultSet.getString(7)+"\n\n"+jishu.getLabel();
				skill = resultSet.getString(8)+"\n\n"+jishu.getSkill();
			}else {
				info = jishu.getInfo();
				label = jishu.getLabel();
				skill = jishu.getSkill();
			}
			

			Object[] objects = {
					
					jishu.getWx(),
					info,
					label,
					skill,
					jishu.getQq()
			};
			
			sql = "update jishu set wx=?,info=?,label=?,skill=? where qq=?";
			int row = DBManager.executeUpdate(sql, objects);
			
			if(row>0) {
				return "ok";
			}
			return "error";

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return "error other";
		
		
	}

	@Override
	public String voteJishu(Vote vote) {
		
		
		
		Object[] objects = {
				vote.getKefuname(),
				vote.getJishuqq(),
				vote.getYesorno(),
				vote.getLabel(),
				vote.getInfo()
		};
		
		String sql = "insert into vote(kefuname,jishuqq,yesorno,label,info) values(?,?,?,?,?)";
		int row = DBManager.executeUpdate(sql, objects);
		
		if(row>0) {
			
			Jishu jishu = getJishuByQq(vote.getJishuqq());
			int yes = jishu.getYes();
			int no = jishu.getNo();
			if(vote.getYesorno()==1) {
				yes+=1;
			}else {
				no+=1;
			}
			
			Object[] obs = {
					yes,
					no,
					vote.getJishuqq()
			};
			
			sql = "update jishu set yes=?,no=? where qq=?";
			row = DBManager.executeUpdate(sql, objects);
			
			if(row>0) {
				return "ok";
			}
			
		}
		return "error";
	}
	
	

	@Override
	public Jishu getJishuByQq(String qq) {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		Jishu jishu = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from jishu where qq=?";
	
			pst = conn.prepareStatement(sql);
			pst.setString(1, qq);

			resultSet = pst.executeQuery();
			
			
			if(resultSet.next()) {
				jishu = new Jishu();
				jishu.setQq(resultSet.getString(2));
				jishu.setWx(resultSet.getString(3));
				jishu.setYes(resultSet.getInt(4));
				jishu.setNo(resultSet.getInt(5));
				jishu.setInfo(resultSet.getString(6));
				jishu.setLabel(resultSet.getString(7));
				jishu.setSkill(resultSet.getString(8));
			}
			

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return jishu;
	}

	@Override
	public Jishu getJishuByWx(String wx) {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		Jishu jishu = null;
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from jishu where wx=?";
	
			pst = conn.prepareStatement(sql);
			pst.setString(1, wx);

			resultSet = pst.executeQuery();
			
			
			if(resultSet.next()) {
				jishu = new Jishu();
				jishu.setQq(resultSet.getString(2));
				jishu.setWx(resultSet.getString(3));
				jishu.setYes(resultSet.getInt(4));
				jishu.setNo(resultSet.getInt(5));
				jishu.setInfo(resultSet.getString(6));
				jishu.setLabel(resultSet.getString(7));
				jishu.setSkill(resultSet.getString(8));
			}
			

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return jishu;
	}

	@Override
	public Vector<Jishu> getJishuListBySkill(String skill) {
		
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		Vector<Jishu> jishus = new Vector<Jishu>();
		Vector<Jishu> jishusYes = new Vector<Jishu>();
		Vector<Jishu> jishusNo = new Vector<Jishu>();
		
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from jishu";
	
			pst = conn.prepareStatement(sql);

			resultSet = pst.executeQuery();
			
			while(resultSet.next()) {
				String s = resultSet.getString(8);
				
				if(StringUtils.containsIgnoreCase(s, skill)) {
					Jishu jishu = new Jishu();
					jishu.setQq(resultSet.getString(2));
					jishu.setWx(resultSet.getString(3));
					jishu.setYes(resultSet.getInt(4));
					jishu.setNo(resultSet.getInt(5));
					jishu.setInfo(resultSet.getString(6));
					jishu.setLabel(resultSet.getString(7));
					jishu.setSkill(resultSet.getString(8));
					
					if(jishu.getYes()>jishu.getNo()) {
						jishusYes.add(jishu);
					}else {
						jishusNo.add(jishu);
					}
					
				}
			}
			
			jishus.addAll(jishusYes);
			jishus.addAll(jishusNo);

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return jishus;
		
	}

	@Override
	public Vector<Jishu> getOnlyYesJishus() {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		Vector<Jishu> jishus = new Vector<Jishu>();
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from jishu";
	
			pst = conn.prepareStatement(sql);

			resultSet = pst.executeQuery();
			
			while(resultSet.next()) {
				int yes = resultSet.getInt(4);
				int no = resultSet.getInt(5);
				
				if(yes>0&&no==0) {
					Jishu jishu = new Jishu();
					jishu.setQq(resultSet.getString(2));
					jishu.setWx(resultSet.getString(3));
					jishu.setYes(resultSet.getInt(4));
					jishu.setNo(resultSet.getInt(5));
					jishu.setInfo(resultSet.getString(6));
					jishu.setLabel(resultSet.getString(7));
					jishu.setSkill(resultSet.getString(8));
					
					jishus.add(jishu);
					
				}
			}

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return jishus;
	}

	@Override
	public Vector<Jishu> getOnlyNoJishus() {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		Vector<Jishu> jishus = new Vector<Jishu>();
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from jishu";
	
			pst = conn.prepareStatement(sql);

			resultSet = pst.executeQuery();
			
			while(resultSet.next()) {
				int yes = resultSet.getInt(4);
				int no = resultSet.getInt(5);
				
				if(no>0&&yes==0) {
					Jishu jishu = new Jishu();
					jishu.setQq(resultSet.getString(2));
					jishu.setWx(resultSet.getString(3));
					jishu.setYes(resultSet.getInt(4));
					jishu.setNo(resultSet.getInt(5));
					jishu.setInfo(resultSet.getString(6));
					jishu.setLabel(resultSet.getString(7));
					jishu.setSkill(resultSet.getString(8));
					
					jishus.add(jishu);
					
				}
			}

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return jishus;
	}

	@Override
	public Vector<Jishu> getYesJishus() {
	
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		Vector<Jishu> jishus = new Vector<Jishu>();
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from jishu";
	
			pst = conn.prepareStatement(sql);

			resultSet = pst.executeQuery();
			
			while(resultSet.next()) {
				int yes = resultSet.getInt(4);
				int no = resultSet.getInt(5);
				
				if(yes>=no) {
					
					Jishu jishu = new Jishu();
					jishu.setQq(resultSet.getString(2));
					jishu.setWx(resultSet.getString(3));
					jishu.setYes(resultSet.getInt(4));
					jishu.setNo(resultSet.getInt(5));
					jishu.setInfo(resultSet.getString(6));
					jishu.setLabel(resultSet.getString(7));
					jishu.setSkill(resultSet.getString(8));
					
					jishus.add(jishu);
					
				}
			}

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return jishus;
	}

	@Override
	public Vector<Jishu> getNoJishus() {
		
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pst = null;
		
		Vector<Jishu> jishus = new Vector<Jishu>();
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from jishu";
	
			pst = conn.prepareStatement(sql);

			resultSet = pst.executeQuery();
			
			while(resultSet.next()) {
				int yes = resultSet.getInt(4);
				int no = resultSet.getInt(5);
				
				if(yes<=no) {
					
					Jishu jishu = new Jishu();
					jishu.setQq(resultSet.getString(2));
					jishu.setWx(resultSet.getString(3));
					jishu.setYes(resultSet.getInt(4));
					jishu.setNo(resultSet.getInt(5));
					jishu.setInfo(resultSet.getString(6));
					jishu.setLabel(resultSet.getString(7));
					jishu.setSkill(resultSet.getString(8));
					
					jishus.add(jishu);
					
				}
			}

		} catch (Exception a) {
			a.printStackTrace();
		}finally {
			DBManager.closeAll(null, pst, conn);
		}

		return jishus;
	}

}
