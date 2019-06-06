package com.example.asmtest.test.cv;

import com.example.asmtest.asm.Utils;
import com.example.asmtest.asm.cv.LifeCircleVisitor;
import com.example.asmtest.asm.mv.MyMethodVisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor implements Opcodes {
    public MyClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, new LifeCircleVisitor(Opcodes.ASM5, classVisitor));
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
        Utils.println("MyClassVisitor visitMethod = " + name + "\n\n");
        return new MyMethodVisitor(visitor, access, name, desc);
    }
}
