package com.example.asmtest.asm.cv

import com.example.asmtest.asm.Utils
import org.objectweb.asm.*

/**
 * 生命周期
 * visit->visitSource? -> visitOuterClass? -> (visitAnnotation | visitAttribute) *
 * -> ( visitInnerClass | visitField | visitMethod) *
 * -> visitEnd
 * 说明:
 * ? 表示最多一次访问
 * * 表示任意多次访问
 */
class LifeCircleVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv), Opcodes {

    /**
     * version: java 版本号
     */
    override fun visit(version: Int, access: Int, name: String, signature: String?, superName: String, interfaces: Array<String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        val interfacesString = interfaces?.joinToString() ?: ""
        Utils.println("=======================================================================================================")
        Utils.println("visit(version = $version ;access = ${Utils.extractAccessFlags(access)} ; name = $name ; signature = $signature ; superName = $superName ; interfaces = $interfacesString)")

    }


    override fun visitSource(source: String, debug: String?) {
        super.visitSource(source, debug)
        Utils.println("source=$source ; debug = $debug \n\n")
    }

    override fun visitOuterClass(owner: String, name: String, desc: String) {
        super.visitOuterClass(owner, name, desc)
        Utils.println("visitOuterClass(owner = $owner ; name = $name ; desc = $desc)")
    }

    override fun visitAnnotation(desc: String, visible: Boolean): AnnotationVisitor {
        Utils.println("visitAnnotation(desc = $desc ; visible = $visible ;)")
        return super.visitAnnotation(desc, visible)
    }

    override fun visitAttribute(attr: Attribute) {
        super.visitAttribute(attr)
        Utils.println("visitAttribute(${attr.type})")
    }

    override fun visitInnerClass(name: String, outerName: String, innerName: String, access: Int) {
        super.visitInnerClass(name, outerName, innerName, access)
        Utils.println("visitInnerClass(name = $name ; outerName = $outerName ; innerName = $innerName ; access = ${Utils.extractAccessFlags(access)})")
    }

    override fun visitField(access: Int, name: String, desc: String, signature: String, value: Any): FieldVisitor {
        Utils.println("visitField(access = ${Utils.extractAccessFlags(access)} ; name = $name ; desc = $desc ; signature = $signature ; value = $value)")
        return super.visitField(access, name, desc, signature, value)

    }

    override fun visitMethod(access: Int, name: String, desc: String, signature: String?, exceptions: Array<String>?): MethodVisitor {
        Utils.println("visitMethod(access = ${Utils.extractAccessFlags(access)} ; name = $name ; desc = $desc ; signature = $signature ; exceptions = ${exceptions?.joinToString()
                ?: ""})")
        return super.visitMethod(access, name, desc, signature, exceptions)
    }

    override fun visitEnd() {
        super.visitEnd()
        Utils.println("visitEnd()");
        Utils.println("=======================================================================================================")
    }
}
