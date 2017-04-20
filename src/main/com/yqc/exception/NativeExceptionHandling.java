package com.yqc.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NativeExceptionHandling {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		try {
			exec.execute(new ExceptionThread());  //������쳣ʱ���񲻵���
		} catch (RuntimeException e) {
			System.out.println("Exception has been handled!");
		}
	}
}
