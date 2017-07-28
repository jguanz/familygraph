package com.neoworks.interviewtests.graph;

/**
 * Created by jguanz on 7/27/17.
 */
public class Person {
    String name;
    String email = "";
    int age;

    Person (String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String toString(){
        return String.format("name: %s, email: %s, age: %s", name, email, age);
    }
}
