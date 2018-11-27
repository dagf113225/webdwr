package com.lixin.model;

public class FMenu {
	
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public CMenu[] getCmenus() {
		return cmenus;
	}

	public void setCmenus(CMenu[] cmenus) {
		this.cmenus = cmenus;
	}

	private  int  fid;
	
	private String fname;
	
	//一个FMenu一级菜单关联多个子菜单CMenu
	private CMenu[]  cmenus;

}
