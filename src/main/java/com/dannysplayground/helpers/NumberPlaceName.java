package com.dannysplayground.helpers;

import java.util.HashMap;

public class NumberPlaceName {
	
private HashMap<Integer, String> numPlaceNames = new HashMap<Integer, String>(); 
	
	public NumberPlaceName() {
		numPlaceNames.put(2,"Hundred");
		numPlaceNames.put(3,"Thousand");
		numPlaceNames.put(6,"Million");
		numPlaceNames.put(9,"Billion");
		numPlaceNames.put(12,"Trillion");
		numPlaceNames.put(15,"Quadrillion");
		numPlaceNames.put(18,"Quintillion");
	}

	public String getNumberPlaceName(int key) {
		return numPlaceNames.get(key);
	}
	
	public HashMap<Integer, String> getNumPlaces() {
		return this.numPlaceNames;
	}

}
