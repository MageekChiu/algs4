package edu.princeton.cs.other;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者消费者问题
 * @author Mageek Chiu
 * @date 2018/3/22 0022:10:53
 */
public class CP {

    static final int TIMES = 50;

    static class Producer implements Runnable {

        public Producer(StorageQueue<Integer> storageQueue) {
            this.storageQueue = storageQueue;
        }

        private StorageQueue<Integer> storageQueue;


        @Override
        public void run() {
            int i = 0;
            while (i<TIMES){
                try {
                    System.out.println("生产："+i);
                    storageQueue.produce(i++);
                    Thread.sleep((long) (Math.random()*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer implements Runnable{

        public Consumer(StorageQueue<Integer> storageQueue) {
            this.storageQueue = storageQueue;
        }

        private StorageQueue<Integer> storageQueue;

        @Override
        public void run() {
            int i = 0;
            while (i<TIMES) {
                try {
                    System.out.println("消费："+storageQueue.comsume());
                    i++;
                    Thread.sleep((long) (Math.random()*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class StorageQueue<T>{
        private final int MAX_VOL = 100;
        private LinkedBlockingQueue<T> queue = new LinkedBlockingQueue<>(MAX_VOL);

        public T comsume() throws InterruptedException {
            return  queue.take();//自动阻塞
        }

        public void produce(T t) throws InterruptedException {
            queue.put(t);//自动阻塞
        }

    }

    public static void main(String ...arg){
        StorageQueue<Integer> storageQueue = new StorageQueue<>();
        Thread consumer = new Thread(new Consumer(storageQueue));
        Thread producer = new Thread(new Producer(storageQueue));

        consumer.start();
        producer.start();
    }




}
