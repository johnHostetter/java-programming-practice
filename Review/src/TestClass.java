import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TestClass {

    private int num;
    private double doubleNum;
    private String someWord;

    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("###000.000");
        String string = decimalFormat.format(1241.9785);
        System.out.println(string);

        DecimalFormat scientificFormat = new DecimalFormat("##0.##E0");
        String scientificNotation = scientificFormat.format(12345);
        System.out.println(scientificNotation);

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        int z = x + y;
        System.out.println(z);

        assert z >= 0; // if true, the following code will run, else the program ends

        String word = scanner.next();
        String sentence = scanner.nextLine();

        System.out.println(word);
        System.out.println(sentence);

        String firstWord = scanner.next();
        String secondWord = scanner.next();
        scanner.nextLine();

        if (firstWord.equals(secondWord)) {
            System.out.println("both words are equal");
        } else if (firstWord.equalsIgnoreCase(secondWord)) {
            System.out.println("both words are equal ignoring case");
        } else {
            System.out.println("words not equal");
        }

        TestClass testClass = new TestClass();

        System.out.println(testClass.randomMethod());

        Derived derived = new Derived();

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.size();
        arrayList.add("Hello");
        arrayList.get(0);
        arrayList.isEmpty();
        arrayList.remove(0);
        arrayList.clear();

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 1);
        hashMap.get("A");
        hashMap.size();
        hashMap.keySet();
        hashMap.

        System.exit(0);
    }

    // constructor

    public TestClass() {
        num = 2;
        doubleNum = 3.14;
        someWord = "test";
    }

    // getters and setters

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    // more methods

    public String randomMethod() {
        return someWord + " " + num;
    }
}

class Base implements Interface {
    protected int someVariable;

    @Override
    public void method() {
        // do nothing
    }
}

class Derived extends Base {
    public Derived() {
        someVariable = 2;
    }
}

interface Interface {
    public void method();
}
