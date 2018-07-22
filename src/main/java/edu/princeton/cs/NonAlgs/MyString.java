package edu.princeton.cs.NonAlgs;

import java.util.concurrent.atomic.AtomicInteger;

class MyString{//叫String会使得本包下面的其他类都无法使用 java 原生String
    public char[] chars = new char[100];
    private AtomicInteger index = new AtomicInteger(0);

    public MyString(char c){
        chars[index.getAndIncrement()] = c;
    }

    public MyString(){
        chars[index.getAndIncrement()] = 'd';
        chars[index.getAndIncrement()] = 'e';
        chars[index.getAndIncrement()] = 'f';
        chars[index.getAndIncrement()] = 'a';
        chars[index.getAndIncrement()] = 'u';
        chars[index.getAndIncrement()] = 'l';
        chars[index.getAndIncrement()] = 't';
    }

    public void show(){
        System.out.println("show begin");
        for (char aChar : chars) {
            System.out.print(aChar+",");
        }
        System.out.println();
    }
}


