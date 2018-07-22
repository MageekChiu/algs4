package edu.princeton.cs.NonAlgs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 两线程交替打印
public class TakeTurns {
    public static volatile int a = 0;

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition single = lock.newCondition();
    private static Condition plural = lock.newCondition();
    private static int state = 1;

    public static void main(String...args){

        // 不能严格实现交替
//        Semaphore semaphore = new Semaphore(1);
//
//        Thread thread1 = new Thread(() -> {
//            while (a<100)
//                try {
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName()+":"+(++a));
//                    semaphore.release();
//                    Thread.sleep(40);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//        },"a");
//
//        Thread thread2 = new Thread(() -> {
//            while (a<100)
//                try {
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName()+":"+(++a));
//                    semaphore.release();
//                    Thread.sleep(30);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//        },"b");
//
//        thread1.start();thread2.start();



        // 这个可以实现每个线程交替加1，但是打印出来的顺序不能保证
//        Thread thread1 = new Thread(() -> {
//            while (atomicInteger.get()<100)
//                if (atomicInteger.get()%2==0){
//                    System.out.println(Thread.currentThread().getName()+":"+(atomicInteger.incrementAndGet()));
//                }
//        },"a");
//
//        Thread thread2 = new Thread(() -> {
//            while (atomicInteger.get()<100)
//                if (atomicInteger.get()%2==1){
//                    System.out.println(Thread.currentThread().getName()+":"+(atomicInteger.incrementAndGet()));
//                }
//        },"b");
//
//        thread1.start();thread2.start();

        // 这个可以严格交替打印
        Thread thread1 = new Thread(() -> {
            while (a<100){
                lock.lock();
                try {
                    if (state!=1)
                        single.await();//等待是单数
                    System.out.println(Thread.currentThread().getName()+":"+(++a));
                    plural.signal();//现在是双数了
                    state = 2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }

        },"a");

        Thread thread2 = new Thread(() -> {
            while (a<100){
                lock.lock();
                try {
                    if (state!=2)
                        plural.await();//等待是双数
                    System.out.println(Thread.currentThread().getName()+":"+(++a));
                    single.signal();//现在是单数了
                    state = 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }

        },"b");

        thread1.start();thread2.start();

    }
}


