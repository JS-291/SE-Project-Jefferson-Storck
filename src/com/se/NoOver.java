package com.se;

import java.util.ArrayList;
import java.util.List;

public class NoOver extends Type{
	public NoOver(List<Long> l) {
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
			if(size<=32) {
				temp=temp+bin;
				i++;
			}else {
				size=0;
				res.add(Long.parseLong(reversePadding(temp,32),2));
				temp="";
			}
		}
		if(temp.length()>0) {
			res.add(Long.parseLong(reversePadding(temp,32),2));
		}
		return res;
	}

	@Override
	public List<Long> decompress(int n) {
		List<Long> res=new ArrayList<Long>();
		int size=0;
		int i=0;
		while(i<list.size()) {
			String bin=padding(Long.toBinaryString(list.get(i)),32);
			while(size<32-n+1) {
				if(Long.parseLong(bin.substring(size,n+size),2)==0)break;
				res.add(Long.parseLong(bin.substring(size,n+size),2));
				size+=n;
			}
			i++;size=0;
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
			return Long.parseLong(padding(Long.toBinaryString(list.get(in/32+1)),32).substring(0,n),2);
		}
	}

	@Override
	public List<Long> decompress(int n, int n2, int n3) {
		return null;
	}

}
