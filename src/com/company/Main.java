package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ReflectionTest reflectionTest = new ReflectionTest();

        //1
        System.out.println(reflectionTest.s);
        Field field = reflectionTest.getClass().getDeclaredField("s");
        field.set(reflectionTest, "ABCDE");
        System.out.println(reflectionTest.s);

        //2
        objectFieldsValues(reflectionTest);
    }

    public static void objectFieldsValues(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] field = clazz.getDeclaredFields();
            for (Field f : field) {
                if (!f.isAnnotationPresent(Ignore.class)) {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    int modifier = f.getModifiers();
                    if (Modifier.isPublic(modifier)) {
                        System.out.print("public | ");
                    } else if (Modifier.isPrivate(modifier)) {
                        System.out.print("private | ");
                    } else if (Modifier.isProtected(modifier)) {
                        System.out.print("protected | ");
                    } else {
                        System.out.print("default | ");
                    }
                    System.out.print(f.getName() + " | ");
                    System.out.println(f.get(object));
                }
            }
    }


}
