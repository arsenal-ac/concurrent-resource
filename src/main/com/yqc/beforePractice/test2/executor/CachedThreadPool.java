package com.yqc.beforePractice.test2.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.yqc.basic.execute.executor.LiftOff;

/**
 * ����Executors�����������߳����� �̳߳�
 * 
 * @author yangqc
 * 
 */
public class CachedThreadPool {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new LiftOff());
		exec.shutdown();
	}
}
