package com.example.asmtest.test.cv

import com.example.asmtest.asm.mv.RemoveGetFieldPutFieldAdapter
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class RemoveGetFieldPutFieldVisitor(cv: ClassVisitor?) : ClassVisitor(Opcodes.ASM5, cv) {

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        val mv = super.visitMethod(access, name, desc, signature, exceptions)
        return RemoveGetFieldPutFieldAdapter(Opcodes.ASM5, mv)
    }

}