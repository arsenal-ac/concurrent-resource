package com.yqc.bankTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private final double[] accounts;
	Lock bankLock;
	Condition condition;

	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = initialBalance;
		}
		bankLock = new ReentrantLock();
		condition = bankLock.newCondition();
	}

	public void transfer(int from, int to, double amount) throws InterruptedException {
		bankLock.lock();
		try {
			while ((accounts[from] - amount) < amount) {
				condition.await();
			}
			System.out.printf("%10.2f from %d to %d ", amount, from, to);
			accounts[to] += amount;
			System.out.printf("Total Balance:%10.2f%n", getTotalBalance());
			condition.signalAll();
		} finally {
			bankLock.unlock();
		}
	}

	public double getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;
			for (double a : accounts) {
				sum += a;
			}
			return sum;
		} finally {
			bankLock.unlock();
		}
	}

	public int size() {
		return accounts.length;
	}
}
