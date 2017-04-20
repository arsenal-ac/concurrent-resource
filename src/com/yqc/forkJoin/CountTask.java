package com.yqc.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = -4433078506168360476L;
	private static final int THRESHOLD = 2;// ��ֵ
	private int start;
	private int end;

	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// ��������㹻С�ͼ�������
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {   //��������㹻�£���ֱ�Ӽ�������
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			// ���������ڷ�ֵ���ͷ��ѳ��������������
			int middle = (start + end) / 2;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);
			// ִ��������
			leftTask.fork();
			rightTask.fork();
			// �ȴ�������ִ���꣬���õ�����
			int leftResult = (int) leftTask.join();
			int rightResult = (int) rightTask.join();
			// �ϲ�������
			sum = leftResult + rightResult;
		}
		return sum;
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// ����һ���������񣬸������1+2+3+4
		CountTask task = new CountTask(1, 4);
		// ִ��һ������
		Future<Integer> result = forkJoinPool.submit(task);
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		}
	}
}