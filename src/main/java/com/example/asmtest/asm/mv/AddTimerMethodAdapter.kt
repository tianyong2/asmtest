package com.example.asmtest.asm.mv

import com.sun.org.apache.bcel.internal.generic.ATHROW
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class AddTimerMethodAdapter(api: Int, mv: MethodVisitor?, private val owner: String) : MethodVisitor(api, mv) {

    override fun visitCode() {
        mv.visitCode()
        mv.visitFieldInsn(Opcodes.ACC_STATIC, owner, "time", "J")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false)
        mv.visitInsn(Opcodes.LSUB)
        mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J")
    }

    override fun visitInsn(opcode: Int) {
        if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
            mv.visitFieldInsn(Opcodes.ACC_STATIC, owner, "time", "J")
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
                    "currentTimeMillis", "()J", false)
            mv.visitInsn(Opcodes.LSUB)
            mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J")
        }
        mv.visitInsn(opcode)
    }

    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
        mv.visitMaxs(maxStack + 4, maxLocals)
    }

}