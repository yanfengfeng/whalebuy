package com.android.whalebuy.been;

import java.io.Serializable;

public class PopWindow implements Serializable{
	
	private int id;
	private String netName;
	private String phone;
	
	public PopWindow(String netName, String phone) {
		super();
		this.netName = netName;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "PopWindow [netName=" + netName + ", phone=" + phone + "]";
	}

}
