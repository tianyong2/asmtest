package com.example.asmtest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflect {
    private Constructor constructor;
    private Class clazz;

    public Reflect(Class clazz, Class<?>... parameterTypes) throws NoSuchMethodException {
        this.clazz = clazz;
        constructor = clazz.getDeclaredConstructor(parameterTypes);
    }


    public Object invoke(String methodName, Class[] parameterTypes, Object instance, Object[] parameters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(instance, parameters);
    }

    public Object invokeStatic(String methodName, Class[] parameterTypes, Object[] parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invoke(methodName, parameterTypes, null, parameters);
    }

    public Object getField(String fieldName, Object instance) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }

    public Object getStaticField(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return getField(fieldName, null);
    }

    public void setField(String fieldName, Object instance, Object newField) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, newField);
    }

    public void setStaticField(String fieldName, Object newField) throws NoSuchFieldException, IllegalAccessException {
        setField(fieldName, null, newField);
    }


}
