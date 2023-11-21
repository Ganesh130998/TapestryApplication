package org.pages;

import org.apache.tapestry5.annotations.Property;
public class IndividualEmployee {

    @Property
    private int id;
    @Property
    private String name;
    @Property
    private int age;
    @Property
    private String address;

    void onActivate(int id,String name,int age,String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    Object[] onPassivate() {
        return new Object[]{id,name,age,address};
    }
}
