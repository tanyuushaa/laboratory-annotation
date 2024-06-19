package com.example;

import com.example.annotation.MinAge;
import com.example.annotation.GenerateClass;

@GenerateClass(className = "GeneratedPerson")
public class Person {
    private String name;

    @MinAge(18)
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}
