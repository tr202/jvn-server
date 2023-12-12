package ru.jvn;

import ru.jvn.TestClass.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        System.out.println(TestClass.static_test_class_field);
        //TestClass test_class = new TestClass();
        //TestClass.InnerTestClass innerTestClass = test_class.new InnerTestClass();
        InnerTestClass innerTestClass = new InnerTestClass();
        //System.out.println(test_class.getClass());
        //System.out.println(test_class.test_class_field);

    }


}