package com.example.asmtest.asm.mv

import com.example.asmtest.asm.Utils
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor


abstract class PatternMethodAdapter(api: Int, mv: MethodVisitor?) : MethodVisitor(api, mv) {
    companion object {
        const val SEEN_NOTHING = 0
    }

    protected var state = SEEN_NOTHING

    /**
     * 再访问 操作符 指令
     */
    override fun visitInsn(opcode: Int) {
        Utils.println("visitInsn opcode = $opcode")
        visitInsn();
        mv.visitInsn(opcode)
    }

    /**
     * 先 访问 操作数 指令
     */
    override fun visitIntInsn(opcode: Int, operand: Int) {
        Utils.println("visitIntInsn opcode = $opcode ;operand =$operand")
        visitInsn()
        mv.visitIntInsn(opcode, operand)
    }


    override fun visitFrame(type: Int, nLocal: Int, local: Array<out Any>?, nStack: Int, stack: Array<out Any>?) {
        Utils.println("visitFrame type = $type ;nLocal =$nLocal ,nStack = $nStack")
        visitInsn()
        mv.visitFrame(type, nLocal, local, nStack, stack)
    }

    override fun visitLabel(label: Label?) {
        Utils.println("visitLabel")
        visitInsn()
        mv.visitLabel(label)
    }

    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
        Utils.println("visitMaxs maxStack= $maxStack; maxLocals= $maxLocals")
        visitInsn()
        mv.visitMaxs(maxStack, maxLocals)
    }

    abstract fun visitInsn();

}