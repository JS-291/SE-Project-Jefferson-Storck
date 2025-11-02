package com.se;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class BitPacking {
	private List<Long> tab;
	private Type type;
	
	public BitPacking(List<Long> t, String s) {
		tab=t;
		type=Factory.build(s,t);
	}
	
	public void compress() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("Out.txt", "UTF-8");
			for(long i:type.compress()) {
				writer.print(i);
				writer.print(" ");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void decompress(int n) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("Out.txt", "UTF-8");
			for(long i:type.decompress(n)) {
				writer.print(i);
				writer.print(" ");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void decompress(int n,int n2,int n3) {
		type.decompress(n,n2,n3);
	}
	
	public long get(int n, int i) {
		return type.get(n, i);
	}
}
