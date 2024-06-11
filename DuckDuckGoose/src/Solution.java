import java.util.*;

public class Solution {

    public static void main(String[] args) {

        // get number of games, g

        Scanner scanner = new Scanner(System.in);
        int numberOfGames = scanner.nextInt();

        // skip the empty line

        scanner.nextLine();

        // begin traversing through the games

        ArrayList<String> answers = new ArrayList<>();

        // get all the possible arrangements that the birds could be in

        ArrayList<String> possibleArrangements = generateStrings("dgs", 10);

        for (int i = 0; i < numberOfGames; ++i) {

            WaterFowls waterFowls = new WaterFowls();

            ArrayList<Question> questions = new ArrayList<>();

            // get the current game's number of questions, q, Bob has asked,
            // and the number of lies, n, Alice has told

            int numberOfQuestions = scanner.nextInt();
            int numberOfLies = scanner.nextInt();

            scanner.nextLine();

            // there will now be q number of line pairs,
            // first line will be the question Bob has asked,
            // and the second line will be the answer Alice has given

            for (int j = 0; j < numberOfQuestions; ++j) {
                String questionBobHasAsked = scanner.nextLine();
                String answerAliceHasGiven = scanner.nextLine();
                Question question = new Question(questionBobHasAsked, answerAliceHasGiven);
                questions.add(question);
            }

            // see if Bob's question and Alice's answer match the possible arrangement

            checkPossibleArrangements(waterFowls, possibleArrangements, questions, numberOfLies);

            answers.add(waterFowls.toString());
        }

        for (String answer : answers) {
            System.out.println(answer);
        }
    }

    private static ArrayList<String> generateStrings(String alphabet, int n) {
        ArrayList<String> result = new ArrayList<>();

        helper(alphabet, n, "", result);

        return result;
    }

    private static void helper(String alphabet, int n, String prefix, ArrayList<String> result) {
        if (n == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < alphabet.length(); ++i) {
                char c = alphabet.charAt(i);
                helper(alphabet, n - 1, prefix + c, result);
            }
        }
    }

    private static void checkPossibleArrangements(WaterFowls waterFowls, ArrayList<String> possibleArrangements, ArrayList<Question> questions, int numberOfLies) {
        for (String possibleArrangement : possibleArrangements) {
            int lies = 0;

            int j = 0;

            while (j < questions.size() && lies <= numberOfLies) {
                Question question = questions.get(j);

                if (question.isLie(possibleArrangement)) {
                    ++lies;
                }

                ++j;
            }

            if (lies == numberOfLies) {

                for (j = 0; j < possibleArrangement.length(); ++j) {
                    waterFowls.addPossibility(j, possibleArrangement.charAt(j));
                }
            }
        }
    }

    public static class WaterFowls {
        private int[] arrangement = { 0x000, 0x000, 0x000, 0x000, 0x000, 0x000, 0x000, 0x000, 0x000, 0x000 };

        void addPossibility(int i, char c) {
            switch (c) {
                case 'd':
                    arrangement[i] = arrangement[i] | 0x100;
                    break;
                case 'g':
                    arrangement[i] = arrangement[i] | 0x010;
                    break;
                case 's':
                    arrangement[i] = arrangement[i] | 0x001;
                    break;
            }
        }

        @Override
        public String toString() {

            StringBuilder stringBuilder = new StringBuilder();

            for (int integer : arrangement) {

                if ((integer & 0x100) == 0x100) {
                    stringBuilder.append('d');
                }

                if ((integer & 0x010) == 0x010) {
                    stringBuilder.append('g');
                }

                if ((integer & 0x001) == 0x001) {
                    stringBuilder.append('s');
                }

                stringBuilder.append(" ");
            }

            return stringBuilder.toString().trim();
        }
    }

    public static class Question {

        ArrayList<IPredicate> predicates = new ArrayList<>();
        int logicalOperator;                // 0 for 'none', 1 for 'and', and 2 for 'or'
        boolean answerAliceHasGiven;

        Question(String questionBobHasAsked, String answerAliceHasGiven) {

            if (questionBobHasAsked.contains("and")) {
                for (String predicate : questionBobHasAsked.split("and")) {
                    if (predicate.contains("bird")) {
                        this.predicates.add(new SelectBirdPredicate(predicate, answerAliceHasGiven));
                    } else if (predicate.contains("total")) {
                        this.predicates.add(new TotalBirdPredicate(predicate, answerAliceHasGiven));
                    }
                }
                logicalOperator = 1;
            } else if (questionBobHasAsked.contains("or")) {
                for (String predicate : questionBobHasAsked.split("or")) {
                    if (predicate.contains("bird")) {
                        this.predicates.add(new SelectBirdPredicate(predicate, answerAliceHasGiven));
                    } else if (predicate.contains("total")) {
                        this.predicates.add(new TotalBirdPredicate(predicate, answerAliceHasGiven));
                    }
                }
                logicalOperator = 2;
            } else {
                if (questionBobHasAsked.contains("bird")) {
                    this.predicates.add(new SelectBirdPredicate(questionBobHasAsked, answerAliceHasGiven));
                } else if (questionBobHasAsked.contains("total")) {
                    this.predicates.add(new TotalBirdPredicate(questionBobHasAsked, answerAliceHasGiven));
                }
            }

            this.answerAliceHasGiven = answerAliceHasGiven.equals("yes");
        }

        boolean isLie(String arrangement) {
            return isValid(arrangement) != answerAliceHasGiven;
        }

        private boolean isValid(String arrangement) {
            switch (logicalOperator) {
                case 0:
                    if (!predicates.isEmpty()) {
                        return predicates.get(0).isValid(arrangement);
                    }
                    break;
                case 1:
                    if (!predicates.isEmpty()) {
                        for (IPredicate predicate : predicates) {
                            if (!predicate.isValid(arrangement)) {
                                return false;
                            }
                        }

                        return true;
                    }
                    break;
                case 2:
                    if (!predicates.isEmpty()) {
                        for (IPredicate predicate : predicates) {
                            if (predicate.isValid(arrangement)) {
                                return true;
                            }
                        }

                        return false;
                    }
                    break;
            }

            return false;
        }
    }

    public interface IPredicate {
        boolean isValid(String arrangement);
    }

    public static class SelectBirdPredicate implements IPredicate {
        int index;
        char birdType;

        SelectBirdPredicate(String question, String answer){
            String tokens[] = question.trim().split(" ");
            index = Integer.parseInt(tokens[1]) - 1;
            birdType = tokens[2].charAt(0);
        }

        @Override
        public boolean isValid(String arrangement) {
            return (arrangement.charAt(index) == birdType);
        }
    }

    public static class TotalBirdPredicate implements IPredicate {
        int total;
        char birdType;

        TotalBirdPredicate(String question, String answer){
            String tokens[] = question.trim().split(" ");
            total = Integer.parseInt(tokens[2]);
            birdType = tokens[1].charAt(0);
        }

        @Override
        public boolean isValid(String arrangement) {
            int count = 0;

            for (int i = 0; i < arrangement.length(); ++i) {
                if (arrangement.charAt(i) == birdType) {
                    ++count;
                }
            }

            return (count == total);
        }
    }
}