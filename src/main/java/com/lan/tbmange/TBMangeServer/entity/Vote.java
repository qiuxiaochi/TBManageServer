package com.lan.tbmange.TBMangeServer.entity;

public class Vote {

	String kefuname = "";                   //客服名
	String jishuqq = "";                  //技术qq
	int yesorno = 1;                     //1代表支持   -1代表反对
	String label = "";                       //标签
	String info = "";                      // 客服投票时给的理由
	
	
	public Vote() {
		
	}
	
	public Vote(String kefuname, String jishuqq, int yesorno, String label, String info) {
		super();
		this.kefuname = kefuname;
		this.jishuqq = jishuqq;
		this.yesorno = yesorno;
		this.label = label;
		this.info = info;
	}
	
	public String getKefuname() {
		return kefuname;
	}
	public void setKefuname(String kefuname) {
		this.kefuname = kefuname;
	}
	public String getJishuqq() {
		return jishuqq;
	}
	public void setJishuqq(String jishuqq) {
		this.jishuqq = jishuqq;
	}
	public int getYesorno() {
		return yesorno;
	}
	public void setYesorno(int yesorno) {
		this.yesorno = yesorno;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	

	
}
