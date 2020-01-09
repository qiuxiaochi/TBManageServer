package com.lan.tbmange.TBMangeServer.verticleServer.service.jsMgService;

import java.util.Vector;

import com.lan.tbmange.TBMangeServer.entity.Jishu;
import com.lan.tbmange.TBMangeServer.entity.Vote;
import com.lan.tbmange.TBMangeServer.entity.keFu;

/*
 * 	技术信息管理
 */
public interface IJsMgService {

	String addJishu(keFu keFu,Jishu jishu);                 //添加技术信息
	String supplyJishu(keFu keFu,Jishu jishu);              //补充技术信息
	String voteJishu(Vote vote);                            //某客服给技术投票和贴标签
	
	
	Jishu getJishuByQq(String qq);            //根据技术的qq获得技术信息
	Jishu getJishuByWx(String wx);            //根据技术的wx获得技术信息
	
	Vector<Jishu> getJishuListBySkill(String Skill);       //获得会某技术语言的所有技术
	
	Vector<Jishu> getOnlyYesJishus();                   //获得只有好评的技术
	Vector<Jishu> getOnlyNoJishus();                   //获得只有差评的技术
	
	Vector<Jishu> getYesJishus();                   //获得好评票数多于差评票数的技术
	Vector<Jishu> getNoJishus();                   //获得差评票数多于好评票数的技术
	
	
	
}
