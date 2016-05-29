package com.android.objectpoolpattern;

import java.util.ArrayList;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-29
 */
public class Reusable {

    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public ArrayList<String> h;
    public ArrayList<String> i;
    public ArrayList<String> j;
    public ArrayList<String> k;
    public ArrayList<String> l;


    public Reusable(){
        h = new ArrayList<>();
        i = new ArrayList<>();
        j = new ArrayList<>();
        k = new ArrayList<>();
        l = new ArrayList<>();
    }
}
