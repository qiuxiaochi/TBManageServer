package com.lan.tbmange.TBMangeServer.verticleServer.service.jsMgService;

import java.util.Vector;

import com.lan.tbmange.TBMangeServer.db.daoFactory.JishuDaoFactory;
import com.lan.tbmange.TBMangeServer.entity.Jishu;
import com.lan.tbmange.TBMangeServer.entity.Vote;
import com.lan.tbmange.TBMangeServer.entity.keFu;

public class JsMgServiceImpl implements IJsMgService {

	@Override
	public String addJishu(keFu keFu, Jishu jishu) {
		
		return JishuDaoFactory.getJishuDao().addJishu(keFu, jishu);
	}
	
	
	@Override
	public String voteJishu(Vote vote) {
		
		return JishuDaoFactory.getJishuDao().voteJishu(vote);
	}


	@Override
	public String supplyJishu(keFu keFu, Jishu jishu) {
		// TODO Auto-generated method stub
		return JishuDaoFactory.getJishuDao().supplyJishu(keFu, jishu);
	}


	@Override
	public Jishu getJishuByQq(String qq) {
		// TODO Auto-generated method stub
		return JishuDaoFactory.getJishuDao().getJishuByQq(qq);
	}


	@Override
	public Jishu getJishuByWx(String wx) {
		// TODO Auto-generated method stub
		return JishuDaoFactory.getJishuDao().getJishuByWx(wx);
	}


	@Override
	public Vector<Jishu> getJishuListBySkill(String Skill) {
		// TODO Auto-generated method stub
		return JishuDaoFactory.getJishuDao().getJishuListBySkill(Skill);
	}


	@Override
	public Vector<Jishu> getOnlyYesJishus() {
		// TODO Auto-generated method stub
		return JishuDaoFactory.getJishuDao().getOnlyYesJishus();
	}


	@Override
	public Vector<Jishu> getOnlyNoJishus() {
		// TODO Auto-generated method stub
		return JishuDaoFactory.getJishuDao().getOnlyNoJishus();
	}


	@Override
	public Vector<Jishu> getYesJishus() {
		// TODO Auto-generated method stub
		return JishuDaoFactory.getJishuDao().getYesJishus();
	}


	@Override
	public Vector<Jishu> getNoJishus() {
		// TODO Auto-generated method stub
		return JishuDaoFactory.getJishuDao().getNoJishus();
	}

}
