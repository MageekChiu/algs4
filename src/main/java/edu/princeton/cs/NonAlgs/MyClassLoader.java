package edu.princeton.cs.NonAlgs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {//不调用super()并指定parent，这里的parent就是null，就可以破坏 双亲委托

    }

    @Override
    /**
     * 所以要破坏双亲委托的方法就是重写loadClass，自定义的这个方法不实现双亲委托的逻辑即可
     */
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class klass = null;
        try {
            klass = findLoadedClass(name); //检查该类是否已经被装载。
            if (klass != null) {
                return klass;
            }

            byte[] bs = getClassBytes(name);//从一个特定的信息源寻找并读取该类的字节。
            if (bs != null && bs.length > 0) {
                klass = defineClass(name, bs, 0, bs.length);
            }
            if (klass == null) { //如果读取字节失败，则试图从JDK的系统API中寻找该类。
                klass = findSystemClass(name);
            }
            if (resolve && klass != null) {
                resolveClass(klass);
            }
        } catch (IOException e) {
            throw new ClassNotFoundException(e.toString());
        }
//        System.out.println("-klass == " + klass);
        return klass;
    }

    private byte[] getClassBytes(String className) throws IOException {
        String path = System.getProperty("java.class.path") + File.separator;
        path += className.replace('.', File.separatorChar) + ".class";
//        System.out.println("--"+path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;   //如果查找失败，则放弃查找。捕捉这个异常主要是为了过滤JDK的系统API。
        }
        byte[] bs = new byte[fis.available()];
        fis.read(bs);
        return bs;
    }

    public static void main(java.lang.String... args){
        MyClassLoader loader = new MyClassLoader();
        Class c;
        try {
            c = loader.loadClass("edu.princeton.cs.NonAlgs.MyString", false);
            Object o = c.newInstance();
//            System.out.println(o);
            Method m = c.getMethod("show");
            m.invoke(o);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}



