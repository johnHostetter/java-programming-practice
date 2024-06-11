public interface SomeInterface {

    public void method1();

    default void defaultMethod() {
        System.out.println("doing default things here look away");
    }

    static void staticBoi() {
        System.out.println("you can call me anytime ;)");
    }
}
