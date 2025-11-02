package com.se;

import java.util.List;

public class Factory {
	public static Type build(String s,List<Long>l){
		try {
			if(s.equals("-o")) {
				return new Over(l);
			}
			if(s.equals("-no")) {
				return new NoOver(l);
			}
			if(s.equals("-fl")) {
				return new Flow(l);
			}
			throw new Exception("Invalid argument");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
	}
}

