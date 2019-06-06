package com.example.asmtest.asm.mv

import com.example.asmtest.asm.Utils
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * 模式匹配操作
 * 删除 ALOAD 0 ALOAD 0 GETFIELD  f PUTFIELD f 序列 ,其实就是删除 f=f 语句
 */
class RemoveGetFieldPutFieldAdapter(api: Int, mv: MethodVisitor?) : PatternMethodAdapter(api, mv) {
    companion object {
        const val SEEN_ALOAD_0 = 1;
        const val SEEN_ALOAD_0ALOAD_0 = 2;
        const val SEEN_ALOAD_0ALOAD_0GETFIELD = 3;
    }

    protected var fieldOwner: String? = ""
    protected var fieldName: String? = ""
    protected var fieldDesc: String? = ""
    override fun visitVarInsn(opcode: Int, `var`: Int) {
        Utils.println("visitVarInsn opcode= $opcode; `var` = ${`var`}")
        when (state) {
            SEEN_NOTHING -> {
                //so -> s1
                if (opcode == Opcodes.ALOAD && `var` == 0) {
                    state = SEEN_ALOAD_0;
                    return
                }
            }
            SEEN_ALOAD_0 -> {
                // s1->s2
                if (opcode == Opcodes.ALOAD && `var` == 0) {
                    state == SEEN_ALOAD_0ALOAD_0
                    return
                }

            }
            SEEN_ALOAD_0ALOAD_0 -> {
                // s2->s2
                if (opcode == Opcodes.ALOAD && `var` == 0) {
                    mv.visitVarInsn(Opcodes.ALOAD, 0)
                    return
                }
            }
        }
        visitInsn()
        mv.visitVarInsn(opcode, `var`)
    }

    override fun visitFieldInsn(opcode: Int, owner: String?, name: String, desc: String?) {
        Utils.println("visitFieldInsn opcode= $opcode; owner = $owner ,name = $name; desc =$desc")
        when (state) {
            SEEN_ALOAD_0ALOAD_0 -> {
                //s2-s3
                if (opcode == Opcodes.GETFIELD) {
                    state == SEEN_ALOAD_0ALOAD_0GETFIELD
                    fieldOwner = owner
                    fieldName = name
                    fieldDesc = desc
                    return
                }
            }
            SEEN_ALOAD_0ALOAD_0GETFIELD -> {
                //s3-s0
                if (opcode == Opcodes.PUTFIELD && name == fieldName) {
                    state = SEEN_NOTHING
                    return

                }
            }
        }
        visitInsn()
        mv.visitFieldInsn(opcode, owner, name, desc)
    }

    override fun visitInsn() {
        when (state) {
            SEEN_ALOAD_0 -> {
                mv.visitVarInsn(Opcodes.ALOAD, 0)
            }
            SEEN_ALOAD_0ALOAD_0 -> {
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
            }
            SEEN_ALOAD_0ALOAD_0GETFIELD -> {
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, fieldOwner, fieldName, fieldDesc)
            }
        }
        state = SEEN_NOTHING

    }
}