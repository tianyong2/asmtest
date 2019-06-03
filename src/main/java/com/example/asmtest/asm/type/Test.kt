package com.example.asmtest.asm.type

import org.objectweb.asm.Type
import java.lang.reflect.Constructor
import java.lang.reflect.Method

class Test {

    fun test1() {
        println(Type.INT_TYPE.descriptor) // I
    }

    /**
     *String.class => java/lang/String
     */
    fun getClassName(clazz: Class<Any>): String = Type.getType(clazz).internalName

    fun getMethodName(method: Method): String = Type.getType(method).internalName
    fun getConstructorName(constructor: Constructor<Any>): String = Type.getType(constructor).internalName

    /**
     * String.class => Ljava/lang/String;
     */
    fun getClassDesc(clazz: Class<Any>): String = Type.getType(clazz).descriptor

    fun getMethodDesc(method: Method): String = Type.getType(method).descriptor
    fun getConstructorDesc(constructor: Constructor<Any>): String = Type.getType(constructor).descriptor

    fun test() {
        Type.getArgumentTypes("(I)V").forEach {
            println(it.descriptor)
        }
        println(Type.getReturnType("(I)V").descriptor)
    }

}

fun main(args: Array<String>) {
    val test = Test()
    test.test()

}