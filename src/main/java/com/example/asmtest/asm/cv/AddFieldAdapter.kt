package com.example.asmtest.asm.cv

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor

class AddFieldAdapter(api: Int,
                      private val classVisitor: ClassVisitor,
                      val fAcc: Int,
                      val fName: String,
                      val fDesc: String) : ClassVisitor(api, classVisitor) {
    private var isFieldPresent = false;//是否已经存在
    override fun visitField(access: Int, name: String?, desc: String?, signature: String?, value: Any?): FieldVisitor {
        if (name == fName) {
            isFieldPresent = true;
        }
        return classVisitor.visitField(access, name, desc, signature, value)
    }

    override fun visitEnd() {
        if (!isFieldPresent) {//如果类中不存在该变量,再添加
            val fv = classVisitor.visitField(fAcc, fName, fDesc, null, null);
            fv?.visitEnd()
        }
        classVisitor.visitEnd()
    }

}