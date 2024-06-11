public class SubClass extends SuperClass implements SomeInterface {
    public SubClass() {

    }

    @Override
    public void someMethod() {
        System.out.println("here i am in sub class");
    }

    public void callBigBoi() {
        super.someMethod();
    }

    @Override
    public void method1() {
        System.out.println("implementing this in sub");
    }
}
