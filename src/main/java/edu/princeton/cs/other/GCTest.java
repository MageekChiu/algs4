package edu.princeton.cs.other;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Mageek Chiu
 * @date 2018/4/6 0006:21:39
 */
public class GCTest {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {

        byte[] byte1 = new byte[2 * _1M];
        byte[] byte2 = new byte[2 * _1M];
        byte[] byte3 = new byte[2 * _1M];


//        System.out.println("return:"+a());
//        finally:2
//        return:1



    }

    private static int a(){
        int aa = 1;
        try {
            return aa;
        }finally {
            aa = 2;
            System.out.println("finally:"+aa);
        }

    }
}
