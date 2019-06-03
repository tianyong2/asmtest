package com.example.asmtest.asm.util

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.util.CheckClassAdapter
import org.objectweb.asm.util.TraceClassVisitor
import java.io.PrintWriter

//TraceClassVisitor 使用,将类变成->
// class version 51.0 (51)
// access flags 0x21
/**

public class com/example/asmtest/Hello {

// compiled from: Hello.java

// access flags 0x1
public <init>()V
L0
LINENUMBER 3 L0
ALOAD 0
INVOKESPECIAL java/lang/Object.<init> ()V
RETURN
L1
LOCALVARIABLE this Lcom/example/asmtest/Hello; L0 L1 0
MAXSTACK = 1
MAXLOCALS = 1

// access flags 0x1
public test()V
L0
LINENUMBER 6 L0
GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
LDC "method test executed"
INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
L1
LINENUMBER 7 L1
RETURN
L2
LOCALVARIABLE this Lcom/example/asmtest/Hello; L0 L2 0
MAXSTACK = 2
MAXLOCALS = 1

// access flags 0x1
public toString()Ljava/lang/String;
L0
LINENUMBER 11 L0
LDC "hello to string"
ARETURN
L1
LOCALVARIABLE this Lcom/example/asmtest/Hello; L0 L1 0
MAXSTACK = 1
MAXLOCALS = 1
}

 */

//CheckClassAdapter

//ASMifier 是 TraceClassVisitor 的一种替代后端
//命令行工具
// java -cp D:\asm-lib\asm-5.1.jar;D:\asm-lib\asm-util-5.1.jar org.objectweb.asm.util.ASMifier java.lang.Runnable

//回告诉我们如何用代码生成Runnable类

fun main(args: Array<String>) {
    val cw = ClassWriter(0)
    val tcv = TraceClassVisitor(cw, PrintWriter(System.out))
    val cca = CheckClassAdapter(tcv)
//    cca.visit()
}
