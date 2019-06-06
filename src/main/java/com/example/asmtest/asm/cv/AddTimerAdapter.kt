package com.example.asmtest.asm.cv

import com.example.asmtest.asm.mv.AddTimerMethodAdapter
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.ASM5

class AddTimerAdapter(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
    private var owner: String? = null
    private var isInterface = false
    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        cv.visit(version, access, name, signature, superName, interfaces)
        owner = name
        isInterface = (access and Opcodes.ACC_INTERFACE) != 0
    }

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        val mv = cv.visitMethod(access, name, desc, signature, exceptions)
        if (mv != null && !isInterface && !name.equals("<init>") && owner != null) {
            return AddTimerMethodAdapter(Opcodes.ASM5, mv, owner!!)
        }
        return mv;
    }

    override fun visitEnd() {
        if (!isInterface) {
            val fv = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "timer",
                    "J", null, null)
            if (fv != null) {
                fv.visitEnd()
            }
            cv.visitEnd()

        }
    }
}