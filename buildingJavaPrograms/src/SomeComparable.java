public class SomeComparable implements Comparable {

    private int x;
    private int y;

    public SomeComparable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof SomeComparable) {
            SomeComparable thatComparable = (SomeComparable) o;

            if (this.x > thatComparable.x || this.y > thatComparable.y) {
                return 1;
            } else if (this.x == thatComparable.x && this.y == thatComparable.y) {
                return 0;
            } else {
                return -1;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        return String.valueOf(x) + ", " + String.valueOf(y);
    }
}
