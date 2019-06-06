package com.example.asmtest.asm.cv

import com.example.asmtest.asm.mv.RemoveNopAdapter
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class RemoveNopClassAdapter(api: Int, cv: ClassVisitor?) : ClassVisitor(api, cv) {
    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        var mv = cv.visitMethod(access, name, desc, signature, exceptions)
        if (mv != null) {
            mv = RemoveNopAdapter(Opcodes.ASM5, mv)
        }
        return mv
    }
}