package com.example.asmtest.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import static com.example.asmtest.asm.Utils.println;


public class MyMethodVisitor extends AdviceAdapter {
    private final static String SDK_API_CLASS = "com/example/asmtest/Inject";
    private String name;
    private MethodVisitor mv;

    protected MyMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
        super(Opcodes.ASM5, mv, access, name, desc);
        this.name = name;
        this.mv = mv;

    }

    @Override
    protected void onMethodExit(int i) {
        super.onMethodExit(i);
        if ("test".equals(name)) {
            println("insert method");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, SDK_API_CLASS, "inject", "(Ljava/lang/Object;)V", false);
        }
    }
}
