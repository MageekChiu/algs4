package edu.princeton.cs.other;

import java.util.concurrent.TimeUnit;

/**
 * @author Mageek Chiu
 * @date 2018/3/4 0004:15:47
 */
public class Counter {
//    private static  boolean stop ;
    private static volatile boolean stop ;
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stop) {
                    i++;
                }
            }
        } );
        t.start();

        TimeUnit.MILLISECONDS .sleep(5);
        stop = true;
    }
}