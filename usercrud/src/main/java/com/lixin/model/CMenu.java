package com.lixin.model;

public class CMenu {
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	private  int  cid;
	
	private String cname;
	
	private  int  fcid;

	public int getFcid() {
		return fcid;
	}

	public void setFcid(int fcid) {
		this.fcid = fcid;
	}

}
