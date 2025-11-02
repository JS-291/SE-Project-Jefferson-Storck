package com.se;

import java.util.List;

public abstract class Type {
	protected List<Long> list;
	protected int len;
	
	public Type(List<Long> l) {
		list=l;
	}
	
	protected int count(List<Long> t) {
		int res=0;
		for(long i:t) {
			if(Long.toBinaryString(i).length()>res) {
				res=Long.toBinaryString(i).length();
			}
		}
		return res;  
	}
	
	protected String padding(String s,int n) {
		String res=s;
		while(res.length()<n) {
			res="0"+res;
		}
		return res;
	}
	
	protected String reversePadding(String s,int n) {
		String res=s;
		while(res.length()<n) {
			res=res+"0";
		}
		return res;
	}
	
	public abstract List<Long> compress();
	public abstract List<Long> decompress(int n);
	public abstract List<Long> decompress(int n, int n2, int n3);
	public abstract long get(int n,int i);
}
