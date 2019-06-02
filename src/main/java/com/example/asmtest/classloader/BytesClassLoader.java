package com.example.asmtest.classloader;

public class BytesClassLoader extends ClassLoader {
    private String className;
    private byte[] bytes;

    public BytesClassLoader(ClassLoader classLoader, String className, byte[] bytes) {
        super(classLoader);
        this.className = className;
        this.bytes = bytes;
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        if (className != null && className.equals(s)) {
            return defineClass(s, bytes, 0, bytes.length);
        }
        return super.findClass(s);
    }
}
