package com.example.asmtest.asm;

import com.example.asmtest.Inject;

import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.StringJoiner;

import sun.misc.Unsafe;

public class Utils {
    public static Class<?> defineClass(byte[] srcClassBytes) throws NoSuchFieldException, IllegalAccessException {
        return getUnsafe().defineClass(null, srcClassBytes, 0, srcClassBytes.length, Inject.class.getClassLoader(), null);
    }

    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
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

    public static String extractAccessFlags(int access) {
        StringJoiner j = new StringJoiner(" ").setEmptyValue("(package-private)");
        for (int remaining = access, bit; remaining != 0; remaining -= bit) {
            bit = Integer.lowestOneBit(remaining);
            switch (bit) {
                case Opcodes.ACC_PUBLIC:
                    j.add("public");
                    break;
                case Opcodes.ACC_PRIVATE:
                    j.add("private");
                    break;
                case Opcodes.ACC_PROTECTED:
                    j.add("protected");
                    break;
                case Opcodes.ACC_STATIC:
                    j.add("static");
                    break;
                case Opcodes.ACC_FINAL:
                    j.add("final");
                    break;
                case Opcodes.ACC_SYNCHRONIZED:
                    j.add("synchronzied");
                    break;
                case Opcodes.ACC_BRIDGE:
                    j.add("(bridge)");
                    break;
                case Opcodes.ACC_VARARGS:
                    j.add("(varargs)");
                    break;
                case Opcodes.ACC_NATIVE:
                    j.add("native");
                    break;
                case Opcodes.ACC_ABSTRACT:
                    j.add("abstract");
                    break;
                case Opcodes.ACC_STRICT:
                    j.add("strictfp");
                    break;
                case Opcodes.ACC_SYNTHETIC:
                    j.add("synthetic");
                    break;
                default:
            }
        }
        String decoded = j.toString();
        return decoded;
    }
}
