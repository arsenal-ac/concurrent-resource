package com.yqc.basic.aqs;

import java.util.concurrent.atomic.AtomicReference;

public class ClhSpinLock {
    private final ThreadLocal<Node> prev;
    private final ThreadLocal<Node> node;
    private final AtomicReference<Node> tail = new AtomicReference<>(new Node());

    public ClhSpinLock() {
        this.node = ThreadLocal.withInitial(() -> new Node());
        this.prev = ThreadLocal.withInitial(() -> null);
    }

    public void lock() {
        final Node node = this.node.get();  //��ȡ��ǰ�̵߳�Node����
        node.locked = true;
        // һ��CAS�������ɽ���ǰ�̶߳�Ӧ�Ľڵ���뵽�����У�
        // ����ͬʱ�����ǰ�̽ڵ�����ã�Ȼ����ǵȴ�ǰ���ͷ���
        Node pred = this.tail.getAndSet(node);    //��ԭ�ӵķ�ʽ������ֵ�����ҷ��ؾ�ֵ
        this.prev.set(pred);
        while (pred.locked) {// ��������
        }
    }

    public void unlock() {
        final Node node = this.node.get();
        node.locked = false;
        this.node.set(this.prev.get());
    }

    private static class Node {
        private volatile boolean locked;
    }

    public static void main(String[] args) throws InterruptedException {
        final ClhSpinLock lock = new ClhSpinLock();
        lock.lock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getId() + " acquired the lock!");
                lock.unlock();
            }).start();
            Thread.sleep(100);
        }

        System.out.println("main thread unlock!");
        lock.unlock();
    }
}
