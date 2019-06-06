### new  ClassWriter(flag)

1. flag ==0 : 不会自动计算任何东西。必须自行计算帧、局部变
              量与操作数栈的大小
2. flag ==ClassWriter.COMPUTE_MAXS :) 慢10%; ，将为你计算局部变量
                                            与操作数栈部分的大小。还是必须调用 visitMaxs，但可以使用任何参数：它们将被
                                            忽略并重新计算。使用这一选项时，仍然必须自行计算这些帧
3. flag ==ClassWriter.COMPUTE_FRAMES :慢2倍; 一切都是自动计算。
                                           不再需要调用 visitFrame，但仍然必须调用 visitMaxs（参数将被忽略并重新计
                                           算）。

### ClassVisitor 生命周期
1. visit->visitSource? -> visitOuterClass? -> (visitAnnotation | visitAttribute) *
 -> ( visitInnerClass | visitField | visitMethod) *
 -> visitEnd

2. 说明:
 ? 表示最多一次访问
 * 表示任意多次访问

### MethodVisitor 生命周期
1. visitAnnotationDefault?
(visitAnnotation|visitParameterAnnotation|visitAttribute)*
(visitCode
    (visitTryCatchBlock|visitLabel|visitFrame|visitXxxInsn|visitLocalVariable|visitLineNumber)*
visitMaxs)?
visitEnd

### asm如何用代码生成一个类
1. 比如要生成java.lang.Runnable
2. 使用asmifier 执行命令
     //参考 http://yangbolin.cn/2014/07/27/how-to-use-asmifier/
     //进入目录 dir=D://test
     1. copy Bean.java to dir ,and  javac Bean.java ,generate Bean.class
     2.  java -cp  .;D:\asm-lib\asm-5.1.jar;D:\asm-lib\asm-util-5.1.jar org.objectweb.asm.util.ASMifier Bean >BeanDump.java


3. 查看结果
4. 或者使用TraceClassVisitor