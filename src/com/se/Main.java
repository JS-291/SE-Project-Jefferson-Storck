package com.se;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		try {
			List<Long> list;
			BufferedReader b=new BufferedReader(new FileReader(args[0]));
			list=Arrays.asList(b.readLine().split(" ")).stream().map(x->Long.parseLong(x)).collect(Collectors.toList());
			b.close();
			for(long n:list) {
				if(n<0||n>4294967295l) {
					throw new Exception("Number out of bound");
				}
			}
			BitPacking bit=new BitPacking(list,args[1]);
			if(args[2].equals("-c")) {
				long start=System.nanoTime();
				bit.compress();
				long end=System.nanoTime();
				System.out.print("Time elapsed :");
				System.out.println(Math.round(end-start/1000000.0)+"ms");
			}else if(args[2].equals("-d")) {
				Scanner scan = new Scanner(System.in);
				System.out.println("Length :");
				long start=System.nanoTime();
				bit.decompress(scan.nextInt());
				long end=System.nanoTime();
				System.out.print("Time elapsed :");
				System.out.println(Math.round(end-start/1000000.0)+"ms");
				scan.close();
			}else if(args[2].equals("-g")) {
				Scanner scan = new Scanner(System.in);
				System.out.println("Length :");
				int l=scan.nextInt();
				System.out.println("Index :");
				long start=System.nanoTime();
				System.out.println(bit.get(l,scan.nextInt()));
				long end=System.nanoTime();
				System.out.print("Time elapsed :");
				System.out.println(Math.round(end-start/1000000.0)+"ms");
				scan.close();
			}else {
				throw new Exception("Invalid argument");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}	
