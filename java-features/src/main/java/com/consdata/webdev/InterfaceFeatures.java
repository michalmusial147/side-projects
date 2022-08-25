package com.consdata.webdev;

public interface InterfaceFeatures {
    void visit();

    void sayHello();

    // Java 8
    public static String getInfoStatic() {
        return null;
    }

    // Java 8
    public default String getInfoDefault() {
        return info();
    }

    // Java 9
    private String info() {
        return null;
    }
}
