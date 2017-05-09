package com.yqc.beforePractice.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * �򵥵Ļ���ϵͳ
 * 2015.10.27
 * @author Yqngqc
 *
 */
public class CacheDemo {
	private Map<String,Object> cache=new HashMap<>();
	public static void main(String[] args) {
		
	}
	private ReadWriteLock rw=new ReentrantReadWriteLock();
	public /*synchronized*/ Object getData(String key){
		rw.readLock().lock();
		Object value=null;
		try {
			if (value == null) {
				rw.readLock().unlock();
				rw.writeLock().lock();
				try {
					if (value == null) {
						value = "aaa"; // ʵ����ȥ���ݿ��ѯ
					}
				} finally {
					rw.writeLock().unlock();
				}
				rw.readLock().lock();
			}
			value = cache.get(key);
		} finally {
			rw.readLock().unlock();
		}
		return value;
	}
}
