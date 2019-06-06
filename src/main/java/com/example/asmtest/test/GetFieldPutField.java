package com.example.asmtest.test;

public class GetFieldPutField {
    public String f = "";

    public void test() {
        f = f;
        int a = 0;
        f = a + "1";
    }
}
