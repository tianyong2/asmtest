package com.example.asmtest.asm.cv

import org.objectweb.asm.ClassVisitor

class RemoveDebugAdapter(api: Int, cv: ClassVisitor?) : ClassVisitor(api, cv) {
    override fun visitSource(source: String?, debug: String?) {
//        super.visitSource(source, debug)
        //没有覆写,相当于删除源文件名字
    }

    override fun visitOuterClass(owner: String?, name: String?, desc: String?) {
//        super.visitOuterClass(owner, name, desc)\
        //没有覆写,相当于删除外部类
    }

    override fun visitInnerClass(name: String?, outerName: String?, innerName: String?, access: Int) {
//        super.visitInnerClass(name, outerName, innerName, access)
        //没有覆写,相当于删除 内部类
    }
}