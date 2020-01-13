package com.deepoove.testpie.target;

public class User {

    private long phone;
    private String name;
    private int age;

    public User() {}

    public User(long phone) {
        this.phone = phone;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [phone=" + phone + ", name=" + name + ", age=" + age + "]";
    }

}
