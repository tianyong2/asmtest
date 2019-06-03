package com.example.asmtest.asm.cv;

import com.example.asmtest.asm.Utils;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EmptyVisitor extends ClassVisitor implements Opcodes {
    public EmptyVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        Utils.println(" empty : visitMethod = " + name);
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
