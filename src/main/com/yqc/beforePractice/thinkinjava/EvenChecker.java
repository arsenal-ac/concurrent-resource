package com.yqc.beforePractice.thinkinjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable{
	private IntGenerator generator;
	private final int id;
	
	public EvenChecker(IntGenerator g,int ident){
		generator=g;
		id=ident;
	}
	@Override
	public void run() {
		while(!generator.isCanceled()){
			int val=generator.next();
			if(val%2!=0){
				System.out.println(val+" not event!");
				generator.cancel();
			}
		}
	}

	public static void test(IntGenerator gp,int count){
		System.out.println("Press Control_c to exit");
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<count;i++)
			exec.execute(new EvenChecker(gp,i));
		exec.shutdown();
	}
	
	public static void test(IntGenerator gp){
		test(gp,10);
	}
}
