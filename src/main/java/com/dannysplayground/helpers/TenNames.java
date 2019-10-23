package com.dannysplayground.helpers;

import java.util.HashMap;

public class TenNames {

	private HashMap<Integer, String> tenNames = new HashMap<Integer, String>(); 
	
	public TenNames() {
		tenNames.put(2,"Twenty");
		tenNames.put(3,"Thirty");
		tenNames.put(4,"Forty");
		tenNames.put(5,"Fifty");
		tenNames.put(6,"Sixty");
		tenNames.put(7,"Seventy");
		tenNames.put(8,"Eighty");
		tenNames.put(9,"Ninety");
	}

	public String getTenName(int key) {
		return tenNames.get(Math.abs(key));
	}

}
