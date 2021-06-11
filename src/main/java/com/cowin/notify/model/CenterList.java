package com.cowin.notify.model;

import java.util.ArrayList;
import java.util.List;

public class CenterList {
	private List<Center> centers;
	
	public CenterList() {
		centers = new ArrayList<Center>();
	}

	public List<Center> getCenters() {
		return centers;
	}

	public void setCenters(List<Center> centers) {
		this.centers = centers;
	}
	

}
