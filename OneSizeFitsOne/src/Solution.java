import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Solution {
    static Random prng = new Random();

    public static void main(String[] args) {
        // Create people/shirts collection
        ArrayList<Shirt> shirts = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        int n = prng.nextInt(1000) + 5000;
        for (int j = 0; j < n; j++) {
            shirts.add(new Shirt(0.01*j));
            persons.add(new Person(0.01*j));
        }

        Collections.shuffle(shirts);
        Collections.shuffle(persons);
        methodA(shirts,persons);

        Collections.shuffle(shirts);
        Collections.shuffle(persons);
        methodB(shirts,persons);
    }

    // 0(n^2)

    static void methodA(ArrayList<Shirt> shirts, ArrayList<Person> persons) {
        for (int i = 0; i < persons.size(); ++i) {
            for (int j = 0; j < shirts.size(); ++j) {
                if (persons.get(i).equals(shirts.get(j))) {
                    Shirt temp = shirts.get(i);
                    shirts.set(i, shirts.get(j));
                    shirts.set(j, temp);
                }
            }
        }
    }

    // O(n log n)

    static void methodB(ArrayList<Shirt> shirts, ArrayList<Person> persons) {
        quickSort(shirts, persons, 0, shirts.size() - 1);
    }

    private static void quickSort(ArrayList<Shirt> shirts, ArrayList<Person> people, int left, int right) {

        if (left >= right) {
            return;
        }

        Person randomPerson = people.get(right);

        int shirtPivotIndex = partitionShirts(shirts, randomPerson, left, right);

        Shirt shirtPivot = shirts.get(shirtPivotIndex);

        partitionPeople(people, shirtPivot, left, right);

        quickSort(shirts, people, left, shirtPivotIndex - 1);
        quickSort(shirts, people, shirtPivotIndex + 1, right);
    }

    private static int partitionShirts(ArrayList<Shirt> shirts, Person person, int left, int right) {
        int j = left;

        for (int i = left; i < right; ++i) {
            if (shirts.get(i).isTooSmallFor(person)) {
                swapShirts(shirts, i, j);
                ++j;
            } else if (shirts.get(i).equals(person)) {
                swapShirts(shirts, i, right);
                --i;
            }
        }

        swapShirts(shirts, j, right);

        return j;
    }

    private static int partitionPeople(ArrayList<Person> people, Shirt shirt, int left, int right) {
        int j = left;

        for (int i = left; i < right; i++)
        {
            if (people.get(i).isTooSmallFor(shirt)){
                swapPeople(people, j, i);
                ++j;
            } else if(people.get(i).equals(shirt)){
                swapPeople(people, i, right);
                --i;
            }
        }

        swapPeople(people, j, right);

        return j;
    }

    public static void swapPeople(ArrayList<Person> people, int i, int j) {
        Collections.swap(people, i, j);
    }

    public static void swapShirts(ArrayList<Shirt> shirts, int i, int j) {
        Collections.swap(shirts, i, j);
    }
}

class Item {
    protected double size;

    public Item(double size) { this.size = size; }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Item)) return false;
        Item that = (Item) other;
        return this.size == that.size;
    }
}

class Shirt extends Item {
    Shirt(double size) { super(size);};

    boolean isTooSmallFor(Person p) {
        return p.size > size;
    }
}

class Person extends Item {
    Person(double size) { super(size); };

    boolean isTooSmallFor(Shirt s) {
        return s.size > size;
    }
}