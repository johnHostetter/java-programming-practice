import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Chapter1.demo();
        SuperClass superClass = new SuperClass();
        SubClass subClass = new SubClass();

        superClass.someMethod();
        subClass.someMethod();
        subClass.callBigBoi();

        String someString = "hello world";

        if (someString instanceof String) {
            System.out.println(someString);
        }

        if (subClass instanceof SuperClass) {
            System.out.println("a sub class is an instance of its parent class");
        }

        SomeInterface.staticBoi();

        subClass.method1();

        ExtendAbstractClass<String> extendAbstractClass = new ExtendAbstractClass<>();

        extendAbstractClass.changeT("hello world");

        extendAbstractClass.abstractMethod();
        extendAbstractClass.doTheThing();
        System.out.println(extendAbstractClass.toString());

        // array lists

        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        arrayList.add(2);

        arrayList.remove(0);
        arrayList.add(0, 4);

        traverse(arrayList);

        arrayList.clear();
        System.out.println(arrayList.isEmpty());

        arrayList.add(0, 5);
        arrayList.set(0, 0);

        arrayList.add(5);
        arrayList.add(10);
        arrayList.add(12);
        arrayList.add(-1);
        arrayList.add(5);

        traverse(arrayList);

        // searching with array lists

        System.out.println(arrayList.contains(-1));

        System.out.println(arrayList.indexOf(5));
        System.out.println(arrayList.lastIndexOf(5));

        // init array list alt way

//        ArrayList<String> words = new ArrayList<>(
//                List.of("hello", "world"));

        // wrappers

        Integer integer = new Integer(38);

        integer.intValue();

        // boxing --> primitive to a wrapped obj

        // unboxing --> wrapped obj to primitive

        // Collections.sort() can be used to sort array lists, elements need to implement Comparable interface

        Collections.sort(arrayList);

        traverse(arrayList);

        ArrayList<SomeComparable> comparables = new ArrayList<>();

        comparables.add(new SomeComparable(4, 4));
        comparables.add(new SomeComparable(1, 2));
        Collections.sort(comparables);
        traverse1(comparables);
    }

    public static void traverse(ArrayList<Integer> arrayList) {
        for (Object obj :
                arrayList) {
            System.out.println(obj.toString());
        }
    }

    public static void traverse1(ArrayList<SomeComparable> arrayList) {
        for (SomeComparable com :
                arrayList) {
            System.out.println(com.toString());
        }
    }
}
