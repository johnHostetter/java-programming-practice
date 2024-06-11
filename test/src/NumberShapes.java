public class NumberShapes {
    public static void main(String[] args) {
        class Number {
            int number;

            public boolean isSquare() {

                double squareRoot = Math.sqrt(number);

                if ((squareRoot == Math.floor(squareRoot)) && (!Double.isInfinite(squareRoot)))
                    return true;
                else
                    return false;
            }

            public boolean isTriangular() {

                int x = 1;

                int triangularNumber = 1;

                while (triangularNumber < number) {

                    x++;

                    triangularNumber += x;

                }

                if (triangularNumber == number)
                    return true;
                else
                    return false;
            }
        }

        Number myNumber = new Number();

        myNumber.number = 6;

        System.out.println(myNumber.isTriangular());
    }
}