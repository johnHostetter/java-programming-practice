public abstract class AbstractBoi extends SubClass implements SomeInterface, AnotherInterface {

    private int x;

    public AbstractBoi() {
        this.x = 0;
    }

    public abstract void abstractMethod();

    public void codeWrittenAlready() {
        System.out.println("some code here in the abstract class");
    }

    public void incrementX() {
        ++this.x;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
