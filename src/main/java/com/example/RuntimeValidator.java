package com.example;

import com.example.annotation.MinAge;

import java.lang.reflect.Field;

public class RuntimeValidator {
    public static void validate(Object obj) throws IllegalAccessException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(MinAge.class)) {
                MinAge minAge = field.getAnnotation(MinAge.class);
                field.setAccessible(true);
                int value = (int) field.get(obj);
                if (value < minAge.value()) {
                    throw new IllegalArgumentException("Age should be at least " + minAge.value());
                }
            }
        }
    }

    public static void main(String[] args) {
        Person person = new Person("John", 17);
        try {
            validate(person);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
