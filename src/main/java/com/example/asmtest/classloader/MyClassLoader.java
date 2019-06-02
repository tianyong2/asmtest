package com.example.asmtest.classloader;

import com.example.asmtest.asm.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyClassLoader extends ClassLoader {
    private Map<String, String> classMap;

    public MyClassLoader(ClassLoader classLoader) {
        this(classLoader, null);
    }

    public MyClassLoader(ClassLoader classLoader, Map<String, String> classMap) {
        super(classLoader);
        if (classMap == null) {
            this.classMap = new HashMap<>();
        } else {
            this.classMap = classMap;
        }
    }

    public void addClass(String name, String path) {
        classMap.put(name, path);
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        if (classMap.containsKey(s)) {
            try {
                byte[] srcBytes = Utils.getClassBytes(classMap.get(s));
                return defineClass(s, srcBytes, 0, srcBytes.length);
            } catch (IOException e) {
            } catch (ClassFormatError error) {

            }
        }
        return super.findClass(s);
    }
}
