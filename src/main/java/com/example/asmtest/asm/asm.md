### new  ClassWriter(flag)

1. flag ==0 : 表明需要手动计算栈帧大小、本地变量和操作数栈的大小；
2. flag ==ClassWriter.COMPUTE_MAXS :) 慢10%; 需要自己计算栈帧大小，但本地变量与操作数已自动计算好，当然也可以调用visitMaxs方法，只不过不起作用，参数会被忽略；
3. flag ==ClassWriter.COMPUTE_FRAMES :慢2倍; 栈帧本地变量和操作数栈都自动计算，不需要调用visitFrame和visitMaxs方法，即使调用也会被忽略。

### 生命周期
1. visit->visitSource? -> visitOuterClass? -> (visitAnnotation | visitAttribute) *
 -> ( visitInnerClass | visitField | visitMethod) *
 -> visitEnd

2. 说明:
 ? 表示最多一次访问
 * 表示任意多次访问


### asm如何用代码生成一个类
1. 比如要生成java.lang.Runnable
2. 使用asmifier 执行命令
     //参考 http://yangbolin.cn/2014/07/27/how-to-use-asmifier/
     //进入目录 dir=D://test
     1. copy Bean.java to dir ,and  javac Bean.java ,generate Bean.class
     2.  java -cp  .;D:\asm-lib\asm-5.1.jar;D:\asm-lib\asm-util-5.1.jar org.objectweb.asm.util.ASMifier Bean >BeanDump.java


3. 查看结果
4. 或者使用TraceClassVisitor