/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.android.singleton;

/**
 * Description:
 *
 * @author zhaozp
 * @since 2016-04-22
 */
public class Singleton {
    private String name = "default";
    public String getName() {
        return name;
    }

    //1---lazy initialization thread-unsafety
//    private static Singleton instance = null;
//    private Singleton(){
//        name = "thread-unsafety";
//    }
//    public static Singleton getInstance() {
//        if(singleton == null)
//            instance = new Singleton();
//        return instance;
//    }

    //2---lazy initialization double-checked thread-safety
//    private static volatile Singleton instance = null;
//    private Singleton(){
//        name = "double-checked thread-safety";
//    }
//
//    public static Singleton getInstance(){
//        if (instance == null ){
//            synchronized (Singleton.class){
//                if (instance == null) {
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }

    //3---eager initialization thread-safety  1
//    private static Singleton instance = new Singleton();
//    private Singleton(){
//        name = "eager initialization thread-safety  1";
//    }
//
//    public static Singleton getInstance(){
//        return instance;
//    }

    //4---eager initialization thread-safety  2
//    private static Singleton instance  = null;
//    private Singleton(){
//        name = "eager initialization thread-safety  2";
//    }
//
//    static {
//        instance = new Singleton();
//    }
//    public Singleton getInstance(){
//        return instance;
//    }

    //5---static inner class thread-safety
    private static class SingletonHolder{
        public static final Singleton instance = new Singleton();
    }

}
