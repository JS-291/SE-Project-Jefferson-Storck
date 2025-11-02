package com.se;

import java.util.ArrayList;
import java.util.List;

public class Flow extends Type{
	private List<Integer> l;
	public Flow(List<Long> l) {
		super(l);
	}
	
	protected int count2(List<Long> t) {
		int res=reverseCount(t);
		l=new ArrayList<Integer>();
		for(int i=0;i<t.size();i++) {
			if(Long.toBinaryString(t.get(i)).length()>res) {
				if(Long.toBinaryString(t.get(i)).length()-res>4) {
					l.add(i);
				}else {
					res=Long.toBinaryString(t.get(i)).length();
					List<Integer>l2=new ArrayList<Integer>();
					int y=0;
					while(y<l.size()) {
						if(Long.toBinaryString(l.get(y)).length()-res>4) {
							res=Long.toBinaryString(t.get(y)).length();
							l2.addAll(l.subList(y,l.size()));
							y=0;
							l=l2;
							l2=new ArrayList<Integer>();
						}else {
							l2.add(y);
							y++;
						}
					}
				}
			}
		}
		return res;  
	}
	
	protected int reverseCount(List<Long> t) {
		int res=32;
		for(long i:t) {
			if(Long.toBinaryString(i).length()<res) {
				res=Long.toBinaryString(i).length();
			}
		}
		return res; 
	}
	
	@Override
	public List<Long> compress() {
		len=count2(list);
		try {
			if (len==32) {
				throw new Exception("Number out of bound");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		List<Long> res=new ArrayList<Long>();
		String temp="";
		int size=0;
		int i=0;
		while(i<list.size()) {
			if(l.contains(i)) {
				String bin="1"+padding(Integer.toBinaryString(i),len);
				size+=len+1;
				i++;
				if(size<32) {
					temp=temp+bin;
				}else {
					size-=32;
					temp=temp+bin.substring(0,len+1-size);
					res.add(Long.parseLong(temp,2));
					if(size==0){temp="";}else{temp=bin.substring(len+1-size,len+1);}
				}
			}else {
				String bin="0"+padding(Long.toBinaryString(list.get(i)),len);
				size+=len+1;
				i++;
				if(size<32) {
					temp=temp+bin;
				}else {
					size-=32;
					temp=temp+bin.substring(0,len+1-size);
					res.add(Long.parseLong(temp,2));
					if(size==0){temp="";}else{temp=bin.substring(len+1-size,len+1);}
				}
			}
		}
		List<Long> ls=new ArrayList<Long>();
		for(int k:l) {
			ls.add(list.get(k));
		}
		len=count(ls);
		for(long m:ls) {
			String bin=padding(Long.toBinaryString(m),len);
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
		return null;
	}

	@Override
	public long get(int n, int i) {
		return 0;
	}

	@Override
	public List<Long> decompress(int n, int n2, int n3) {
		List<Long> res=new ArrayList<Long>();
		int size=0;
		int i=0;
		while(i<(list.size()*32-n3*n2)/32) {
			String bin=padding(Long.toBinaryString(list.get(i)),32);
			while(size<32-n+1) {
				if(bin.substring(size,size+1).equals("0")) {
					res.add(Long.parseLong(bin.substring(size,n+size+1),2));
				}else {
					int index=list.size()*32-(n3-Integer.parseInt(bin.substring(size+1,size+n))*n2);
					String bin2=padding(Long.toBinaryString(list.get(index/32)),32);
					if((index%32)+n2>32) {
						res.add(Long.parseLong(bin2.substring(index%32)+padding(Long.toBinaryString(list.get(index/32+1)),32).substring(0,index%32+n2-32),2));
					}else {
						res.add(Long.parseLong(bin2.substring(index%32,index%32+n2)));
					}
				}
				size+=n+1;
			}
			size=size+n+1-32;
			if(size!=0) {
				if(bin.substring(size,size+1).equals("0")) {
					res.add(Long.parseLong(bin.substring(32-n+size)+padding(Long.toBinaryString(list.get(i+1)),32).substring(0,size),2));
				}else {
					int index=list.size()*32-(n3-Integer.parseInt(bin.substring(size+1)+padding(Long.toBinaryString(list.get(i+1)),32).substring(0,size+1+n-32))*n2);
					String bin2=padding(Long.toBinaryString(list.get(index/32)),32);
					if((index%32)+n2>32) {
						res.add(Long.parseLong(bin2.substring(index%32)+padding(Long.toBinaryString(list.get(index/32+1)),32).substring(0,index%32+n2-32),2));
					}else {
						res.add(Long.parseLong(bin2.substring(index%32,index%32+n2)));
					}
				}
			}else {
				if(bin.substring(32-n,32-n+1).equals("0")) {
					res.add(Long.parseLong(bin.substring(32-n,32),2));
				}else {
					int index=list.size()*32-(n3-Integer.parseInt(bin.substring(32-n+1,32))*n2);
					String bin2=padding(Long.toBinaryString(list.get(index/32)),32);
					if((index%32)+n2>32) {
						res.add(Long.parseLong(bin2.substring(index%32)+padding(Long.toBinaryString(list.get(index/32+1)),32).substring(0,index%32+n2-32),2));
					}else {
						res.add(Long.parseLong(bin2.substring(index%32,index%32+n2)));
					}
				}
			}
			i++;
		}
		return res;
	}

}
