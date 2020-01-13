package com.lan.tbmange.TBMangeServer.entity;

/*
 * 	技术信息
 */
public class Jishu {

	
	String qq = "";
	String wx = "";
	int yes = 0;                   //支持数量
	int no = 0;                   //否定数量
	String info = "";                //其他信息
	String label = "";                //标签
	String skill = "";              //会的技术语言
	
	
	public Jishu() {
		
	}
	
	public Jishu(String qq, String wx, int yes, int no, String info, String label, String skill) {
		super();
		this.qq = qq;
		this.wx = wx;
		this.yes = yes;
		this.no = no;
		this.info = info;
		this.label = label;
		this.skill = skill;
		
	}
	
	public Jishu(String qq) {
		this.qq =  qq;
	}
	
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
	}
	public int getYes() {
		return yes;
	}
	public void setYes(int yes) {
		this.yes = yes;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	
	
	
}
