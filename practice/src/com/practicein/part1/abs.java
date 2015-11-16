package com.practicein.part1;

abstract class abs
{
abstract void method1();
void method2()
{
System.out.println("this is real method");
}
}
class B extends abs
{
void method1()
{
System.out.println("B is execution of method1"); }
}
class demo
{
public static void main(String arg[])
{
B b=new B();
b.method1();
b.method2();
}
}

