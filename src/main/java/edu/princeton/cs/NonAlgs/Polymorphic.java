package edu.princeton.cs.NonAlgs;

public class Polymorphic {

    public void printLL(){
        System.out.println("Parent");
    }
    public void printLL(String s){
        System.out.println("Child"+s);
    }
    public static void main(String... args){
        Polymorphic a = new Polymorphic();
        Polymorphic b = new PolymorphicChild();
        a.printLL();
        b.printLL();
        b.printLL(",sd");
    }
    // \\j a v a c Polymorphic . j a v a  生成 class 文件，然后营 idea打开就能看到编译后的代码了
    // 如果编码有问题 就这样 javac -encoding UTF-8 Polymorphic.java
    // 编译好后也可以用 javap -p -v  Polymorphic.class
    //命令说明是：输出附加信息
//    class文件的路径、最后修改时间、文件大小等
//    类的全路径、源（java）文件等
//            常量池
//    常量定义、值
//            构造方法
//    程序调用及执行逻辑（这个涉及的内容就比较多了）
//
//    总之，javap -v命令是很强大的一个命令！




}
class PolymorphicChild extends Polymorphic{
    @Override
    public void printLL() {
        System.out.println("Child");
    }


}