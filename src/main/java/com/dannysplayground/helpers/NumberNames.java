package com.dannysplayground.helpers;

import java.util.HashMap;

public class NumberNames {
	
	private HashMap<Integer, String> numNames = new HashMap<Integer, String>(); 
	
	public NumberNames() {
		numNames.put(0,"Zero");
		numNames.put(1,"One");
		numNames.put(2,"Two");
		numNames.put(3,"Three");
		numNames.put(4,"Four");
		numNames.put(5,"Five");
		numNames.put(6,"Six");
		numNames.put(7,"Seven");
		numNames.put(8,"Eight");
		numNames.put(9,"Nine");
		numNames.put(10,"Ten");
		numNames.put(11,"Eleven");
		numNames.put(12,"Twelve");
		numNames.put(13,"Thirteen");
		numNames.put(14,"Fourteen");
		numNames.put(15,"Fifteen");
		numNames.put(16,"Sixteen");
		numNames.put(17,"Seventeen");
		numNames.put(18,"Eighteen");
		numNames.put(19,"Nineteen");
	}

	public String getNumberName(int key) {
		return numNames.get(Math.abs(key));
	}

}
