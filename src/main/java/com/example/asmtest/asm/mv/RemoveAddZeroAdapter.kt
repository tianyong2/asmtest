package com.example.asmtest.asm.mv

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * 模式匹配 操作
 * 删除指令序列 ICONST_0 IADD ,+ 0 相当于无用操作
 */
class RemoveAddZeroAdapter(api: Int, mv: MethodVisitor?) : PatternMethodAdapter(api, mv) {

    companion object {
        const val SEEN_ICONST_0 = 1
    }

    override fun visitInsn(opcode: Int) {
        if (state == SEEN_ICONST_0) {
            if (opcode == Opcodes.IADD) {
                state = SEEN_NOTHING;
                return
            }
        }
        visitInsn()
        if (opcode == Opcodes.ICONST_0) {
            state = SEEN_ICONST_0;
            return
        }
        mv.visitInsn(opcode)
    }

    override fun visitInsn() {
        if (state == SEEN_ICONST_0) {
            mv.visitInsn(Opcodes.ICONST_0)
        }
        state = SEEN_NOTHING
    }
}