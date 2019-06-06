package com.example.asmtest.asm.mv

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class RemoveNopAdapter(api: Int, mv: MethodVisitor?) : MethodVisitor(api, mv) {

    override fun visitInsn(opcode: Int) {
        if(opcode==Opcodes.NOP){
            mv.visitInsn(opcode)
        }else{
            //ignore invalid instruction
        }
    }
}