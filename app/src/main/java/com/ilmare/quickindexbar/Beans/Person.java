package com.ilmare.quickindexbar.Beans;

import com.ilmare.quickindexbar.Utils.PinyinUtils;


public class Person implements  Comparable<Person> {

    private  String name;
    private String pingyin;

    public String getPingyin() {
        return pingyin;
    }

    public void setPingyin(String pingyin) {
        this.pingyin = pingyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.pingyin= PinyinUtils.getPinyin(name);
    }

    public Person(String name) {
        this.name = name;

        this.pingyin= PinyinUtils.getPinyin(name);
    }



    @Override
    public int compareTo(Person another) {
        return getPingyin().charAt(0)-another.getPingyin().charAt(0);
    }
}
