package com.example.asmtest;


import com.example.asmtest.test.GetFieldPutField;
import com.example.asmtest.test.cv.MyClassVisitor;
import com.example.asmtest.asm.Utils;
import com.example.asmtest.classloader.BytesClassLoader;
import com.example.asmtest.test.Inject;
import com.example.asmtest.test.cv.RemoveGetFieldPutFieldVisitor;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.FileOutputStream;
import java.io.PrintWriter;


public class MyClass {
    public boolean x = false;

    public static void main(String[] args) throws Exception {
        MyClass myClass = new MyClass();
        myClass.testAsm();
//        myClass.trace();

    }

    private void testAsm() throws Exception {
        String className = GetFieldPutField.class.getSimpleName();
        String path = "D:\\test\\" + className + ".class";
        String destPath = "D:\\test_project\\AutoTrackAppClick6\\AsmTest\\src\\main\\java\\com\\example\\asmtest\\test\\" + className + ".class";

        byte[] srcBytes = Utils.getClassBytes(path);
        byte[] modified = asmOperator(srcBytes);
        FileOutputStream outputStream = new FileOutputStream(destPath);
        outputStream.write(modified);

//        Class clazz = getClassByBytes(modified);
//        Object instance = clazz.newInstance();
//        Reflect reflect = new Reflect(instance.getClass());
//        reflect.invoke("test", new Class[]{}, instance, new Object[]{});
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
//        ClassVisitor visitor = new MyClassVisitor(classWriter);
        ClassVisitor visitor = new RemoveGetFieldPutFieldVisitor(classWriter);
        ClassReader reader = new ClassReader(srcBytes);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);
        return classWriter.toByteArray();
    }

    private void trace() throws Exception {
        String className = GetFieldPutField.class.getSimpleName();
        String destPath = "D:\\test_project\\AutoTrackAppClick6\\AsmTest\\src\\main\\java\\com\\example\\asmtest\\test\\" + className + ".class";
        byte[] srcBytes = Utils.getClassBytes(destPath);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassVisitor visitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));
        ClassReader reader = new ClassReader(srcBytes);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);
    }


}
