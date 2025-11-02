package com.se;

import java.util.ArrayList;
import java.util.List;

public class Over extends Type{
	public Over(List<Long> l) {
		super(l);
	}

	@Override
	public List<Long> compress() {
		List<Long> res=new ArrayList<Long>();
		String temp="";
		int size=0;
		int i=0;
		this.len=count(this.list);
		while(i<list.size()) {
			String bin=padding(Long.toBinaryString(list.get(i)),len);
			size+=len;
			i++;
			if(size<32) {
				temp=temp+bin;
			}else {
				size-=32;
				temp=temp+bin.substring(0,len-size);
				res.add(Long.parseLong(temp,2));
				if(size==0){temp="";}else{temp=bin.substring(len-size,len);}
			}
		}
		if(temp.length()!=0)res.add(Long.parseLong(reversePadding(temp,32),2));
		return res;
	}

	@Override
	public List<Long> decompress(int n) {
		List<Long> res=new ArrayList<Long>();
		int size=0;
		int i=0;
		while(i<list.size()-1) {
			String bin=padding(Long.toBinaryString(list.get(i)),32);
			while(size<32-n) {
				res.add(Long.parseLong(bin.substring(size,n+size),2));
				size+=n;
			}
			size=size+n-32;
			if(size!=0) {
				res.add(Long.parseLong(bin.substring(32-n+size)+padding(Long.toBinaryString(list.get(i+1)),32).substring(0,size),2));
			}else {
				res.add(Long.parseLong(bin.substring(32-n,32),2));
			}
			i++;
		}
		String bin=padding(Long.toBinaryString(list.get(i)),32);
		while(size<32-n+1) {
			if(Long.parseLong(bin.substring(size,n+size),2)==0)break;
			res.add(Long.parseLong(bin.substring(size,n+size),2));
			size+=n;
		}
		return res;
	}

	@Override
	public long get(int n, int i) {
		int in=n*(i-1);
		String bin=padding(Long.toBinaryString(list.get(in/32)),32);
		if((in%32)+n<=32) {
			return Long.parseLong(bin.substring(in%32,(in%32)+n),2);
		}else {
			return Long.parseLong(bin.substring(in%32,32)+padding(Long.toBinaryString(list.get(in/32+1)),32).substring(0,in%32+n-32),2);
		}
	}

	@Override
	public List<Long> decompress(int n, int n2, int n3) {
		return null;	
	}
}
