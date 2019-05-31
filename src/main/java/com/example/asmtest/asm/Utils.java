package com.example.asmtest.asm;

import com.example.asmtest.Inject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class Utils {
    public static Class<?> defineClass(byte[] srcClassBytes) throws NoSuchFieldException, IllegalAccessException {
        return getUnsafe().defineClass(null, srcClassBytes, 0, srcClassBytes.length, Inject.class.getClassLoader(), null);
    }
    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe instance = (Unsafe) f.get(null);
        return instance;
    }
    public static byte[] getClassBytes(String path) throws IOException {
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        inputStream.read(bytes);
        return bytes;

    }

    public static void println(Object object) {
        System.out.println(object);
    }
}
