package edu.princeton.cs.NonAlgs;

import java.io.FileWriter;
import java.io.IOException;

public class IOTester {

    public static void main(String... args){
        String fileName="D:\\IOTester.txt";
        try {
                FileWriter writer=new FileWriter(fileName);
                writer.write("IOTester");
                writer.close();
        } catch (IOException e){
                e.printStackTrace();
        }
    }
}
