package com.example.asmtest;


import com.example.asmtest.asm.MyClassVisitor;
import com.example.asmtest.asm.Utils;
import com.example.asmtest.classloader.BytesClassLoader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import sun.misc.Unsafe;


public class MyClass {
    public boolean x = false;

    public static void main(String[] args) throws Exception {
        MyClass myClass = new MyClass();
        myClass.testAsm();

    }

    private void testAsm() throws Exception {
        String path = "D:\\test_project\\AutoTrackAppClick6\\temp\\Hello.class";

        byte[] srcBytes = Utils.getClassBytes(path);
        byte[] modified = asmOperator(srcBytes);
        Class clazz = getClassByBytes(modified);
        Object instance = clazz.newInstance();
        Reflect reflect = new Reflect(instance.getClass());
        reflect.invoke("test", new Class[]{}, instance, new Object[]{});
    }

    private Class getClassByBytes(byte[] bytes) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        //1 unsafe 加载类时 不会执行构造函数
//        Class clazz=Utils.defineClass(bytes);

        //2
        String className = "com.example.asmtest.Hello";
        ClassLoader classLoader = new BytesClassLoader(Inject.class.getClassLoader(), className, bytes);
        Class clazz = classLoader.loadClass(className);
        return clazz;
    }


    private byte[] asmOperator(byte[] srcBytes) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new MyClassVisitor(classWriter);
        ClassReader reader = new ClassReader(srcBytes);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);
        return classWriter.toByteArray();
    }


}
