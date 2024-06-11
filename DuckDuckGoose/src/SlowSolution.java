import java.util.*;

public class SlowSolution {

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
        for (int i = 0; i < possibleArrangements.size(); ++i) {
            String possibleArrangement = possibleArrangements.get(i);

            int lies = 0;

            int j = 0;

            while (j < questions.size() && lies <= numberOfLies) {
                Question question = questions.get(j);

                boolean bool = checkQuestion(possibleArrangement, question);

                if (bool != question.answerAliceHasGiven) {
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

    private static boolean checkWaterFowlAt(String possibleArrangement, int i, char t) {
        return possibleArrangement.charAt(i) == t;
    }

    private static boolean checkWaterFowlCount(String possibleArrangement, char t, int j) {
        int count = 0;

        for (int i = 0; i < possibleArrangement.length(); ++i) {
            if (possibleArrangement.charAt(i) == t) {
                ++count;
            }
        }

        return count == j;
    }

    private static boolean checkQuestion(String possibleArrangement, Question question) {

        if (question.logicalOperator == 1) {

            for (int i = 0; i < question.questionBobHasAsked.length; ++i) {

                if (!checkToken(possibleArrangement, question.questionBobHasAsked[i])) {
                    return false;
                }
            }

            return true;

        } else if (question.logicalOperator == 2) {

            for (int i = 0; i < question.questionBobHasAsked.length; ++i) {

                if (checkToken(possibleArrangement, question.questionBobHasAsked[i])) {
                    return true;
                }
            }

            return false;

        } else {
            return checkToken(possibleArrangement, question.questionBobHasAsked[0]);
        }
    }

    private static boolean checkToken(String possibleArrangement, String token) {

        String[] tokens = token.trim().split(" ");

        switch (tokens[0]) {
            case "bird":
                return checkWaterFowlAt(possibleArrangement, Integer.parseInt(tokens[1]) - 1, tokens[2].charAt(0));
            case "total":
                return checkWaterFowlCount(possibleArrangement, tokens[1].charAt(0), Integer.parseInt(tokens[2]));
            default:
                return false;
        }
    }

    public static class WaterFowls {
        private int[] arrangement = { 0x000, 0x000, 0x000, 0x000, 0x000, 0x000, 0x000, 0x000, 0x000, 0x000 };

        void addPossibility(int i, char c) {

            if (c == 'd') {
                arrangement[i] = arrangement[i] | 0x100;
            } else if (c == 'g') {
                arrangement[i] = arrangement[i] | 0x010;
            } else if (c == 's') {
                arrangement[i] = arrangement[i] | 0x001;
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

        String[] questionBobHasAsked = new String[10];
        int logicalOperator;                // 0 for 'none', 1 for 'and', and 2 for 'or'
        boolean answerAliceHasGiven;

        Question(String questionBobHasAsked, String answerAliceHasGiven) {

            if (questionBobHasAsked.contains("and")) {
                this.questionBobHasAsked = questionBobHasAsked.split("and");
                logicalOperator = 1;
            } else if (questionBobHasAsked.contains("or")) {
                this.questionBobHasAsked = questionBobHasAsked.split("or");
                logicalOperator = 2;
            } else {
                this.questionBobHasAsked[0] = questionBobHasAsked;
            }

            this.answerAliceHasGiven = answerAliceHasGiven.equals("yes");
        }
    }
}