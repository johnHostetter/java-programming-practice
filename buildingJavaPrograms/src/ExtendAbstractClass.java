public class ExtendAbstractClass<T> extends AbstractBoi {

    private T someData;

    public ExtendAbstractClass() {
    }

    @Override
    public void abstractMethod() {
        System.out.println("i am in the class extending the abstract");
    }

    @Override
    public void doTheThing() {
        System.out.println("this method is required by me due to abstract implementing interface");
    }

    public void changeT(T newData) {
        this.someData = newData;
    }

    @Override
    public String toString() {
        if (someData instanceof String) {
            return "this is storing a string: " + someData;
        } else if (someData instanceof Integer) {
            return "This is storing an integer: " + someData;
        } else {
            return "cant display data";
        }
    }
}
