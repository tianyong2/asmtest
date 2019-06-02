package com.example.asmtest.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

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
