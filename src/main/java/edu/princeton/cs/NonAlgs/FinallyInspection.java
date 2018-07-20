package edu.princeton.cs.NonAlgs;

import static java.lang.System.out;

public class FinallyInspection {
    public static int aa(int a,int b){
        int result = 2;
        try {
            result = a/b;
        }catch (Exception e){
            return result;
        }finally {
            result = 1;
            out.println("dsa "+result);
        }
        return result;
    }
//    javac FinallyInspection.java
//    javap -c FinallyInspection.class
//    可以看出 dsa 出现了了3遍

    public static void main(String... args){
        out.println(aa(1,0));
    }
}
