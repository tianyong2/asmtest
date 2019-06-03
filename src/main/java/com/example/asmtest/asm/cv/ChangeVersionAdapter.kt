package com.example.asmtest.asm.cv

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

class ChangeVersionAdapter(api: Int, cv: ClassVisitor?) : ClassVisitor(api, cv) {

    companion object {
        /**
         * 优化后
         *
         * 执行这一优化后，由于 ChangeVersionAdapter 没有转换任何方法，所以代码的
        速度可以达到之前代码的两倍。对于转换部分或全部方法的常见转换，这一速度ᨀ升幅度可能要
        小一些，但仍然是很可观的：实际上在 10%到 20%的量级。遗憾的是，这一优化需要将原类中
        定义的所有常量都复制到转换后的类中。对于那些增加字段、方法或指令的转换来说，这一点不
        成问题，但对于那些要移除或重命名许多类成员的转换来说，这一优化将导致类文件大于未优化
        时的情况。因此，建议仅对“增加性”转换应用这一优化。
         */
        fun test1(bytes: ByteArray) {
            val cr = ClassReader(bytes)
            val cw = ClassWriter(cr, 0)
            val changeVersionAdapter = ChangeVersionAdapter(Opcodes.ASM5, cw)
            cr.accept(changeVersionAdapter, 0)
        }

        /**
         *原来得
         */
        fun test2(bytes: ByteArray) {
            val cr = ClassReader(bytes)
            val cw = ClassWriter(0)
            val changeVersionAdapter = ChangeVersionAdapter(Opcodes.ASM5, cw)
            cr.accept(changeVersionAdapter, 0)


        }

    }

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
//        super.visit(version, access, name, signature, superName, interfaces)
        this.cv.visit(Opcodes.V1_5, access, name, signature, superName, interfaces)
    }

}
