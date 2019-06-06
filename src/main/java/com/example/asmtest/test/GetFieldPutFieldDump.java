package com.example.asmtest.test;

import java.util.*;

import org.objectweb.asm.*;

public class GetFieldPutFieldDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "com/example/asmtest/test/GetFieldPutField", null, "java/lang/Object", null);

        {
            fv = cw.visitField(ACC_PUBLIC, "f", "Ljava/lang/String;", null, null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitLdcInsn("");
            mv.visitFieldInsn(PUTFIELD, "com/example/asmtest/test/GetFieldPutField", "f", "Ljava/lang/String;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "com/example/asmtest/test/GetFieldPutField", "f", "Ljava/lang/String;");
            mv.visitFieldInsn(PUTFIELD, "com/example/asmtest/test/GetFieldPutField", "f", "Ljava/lang/String;");
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 1);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
            mv.visitLdcInsn("1");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitFieldInsn(PUTFIELD, "com/example/asmtest/test/GetFieldPutField", "f", "Ljava/lang/String;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
