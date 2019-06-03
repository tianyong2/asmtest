package com.example.asmtest.asm.cv

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor

class RemoveMethodAdapter(api: Int, cv: ClassVisitor?,
                          val methodName: String, val methodDesc: String)
    : ClassVisitor(api, cv) {

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {

        if (name == methodName && desc == methodDesc) {//相当于删除 methodName 方法
            return null;
        }
        return super.visitMethod(access, name, desc, signature, exceptions)
    }

}