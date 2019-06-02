package com.example.asmtest.unsafe;

import com.example.asmtest.MyClass;
import com.example.asmtest.asm.Utils;

import sun.misc.Unsafe;

public class Test {
    public static void main(String... args) {

    }

    /**
     * 测试boolean 实际是用int 保存
     * @throws Exception
     */
    private static void testBoolean() throws Exception {
        MyClass myClass = new MyClass();
        Unsafe unsafe = Utils.getUnsafe();
        long offset = unsafe.objectFieldOffset(MyClass.class.getDeclaredField("x"));
        unsafe.putInt(myClass, offset, 5);
        if (myClass.x) {
            Utils.println("true");
        }
        if (myClass.x == true) {
            Utils.println("true true");
        }
        Utils.println("----exit--");
    }
}
