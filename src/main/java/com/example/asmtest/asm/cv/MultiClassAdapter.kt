package com.example.asmtest.asm.cv

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes

class MultiClassAdapter(private val cvs: Array<ClassVisitor>)
    : ClassVisitor(Opcodes.ASM5) {

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        cvs.forEach {
            it.visit(version, access, name, signature, superName, interfaces)
        }
    }

}