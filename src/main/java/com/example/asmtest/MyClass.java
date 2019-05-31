package com.example.asmtest;


import com.example.asmtest.asm.MyClassVisitor;
import com.example.asmtest.asm.Utils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;


public class MyClass {
    public static void main(String[] args) throws Exception {

        String path = "D:\\test_project\\AutoTrackAppClick6\\temp\\Hello.class";
        MyClass myClass = new MyClass();
        byte[] srcBytes = Utils.getClassBytes(path);
        byte[] modified = myClass.asmOperator(srcBytes);
        Class clazz = Utils.defineClass(modified);
        Object instance = clazz.newInstance();
        Reflect reflect = new Reflect(instance.getClass());
        reflect.invoke("test", new Class[]{}, instance, new Object[]{});

    }


    public byte[] asmOperator(byte[] srcBytes) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new MyClassVisitor(classWriter);
        ClassReader reader = new ClassReader(srcBytes);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);
        return classWriter.toByteArray();
    }


}
