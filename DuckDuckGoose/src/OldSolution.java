import java.io.*;
import java.util.*;

public class OldSolution {

    // constraints:
    // 1 <= g <= 20
    // 1 <= q <= 20
    // 0 <= n <= q
    // 1 <= i <= 10
    // 0 <= j <= 10
    // at most 9 logical operators in a given q

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        // 1. get number of games, g

        Scanner scanner = new Scanner(System.in);
        int numberOfGames = scanner.nextInt();

        // 2. skip the empty line

        scanner.nextLine();

        // 3. begin traversing through the games

        for (int i = 0; i < numberOfGames; ++i) {

            ArrayList<Question> questions = new ArrayList<>();
            ArrayList<WaterFowl> waterFowls = new ArrayList<>();

            waterFowls.add(new WaterFowl());
            waterFowls.add(new WaterFowl());
            waterFowls.add(new WaterFowl());
            waterFowls.add(new WaterFowl());
            waterFowls.add(new WaterFowl());

            waterFowls.add(new WaterFowl());
            waterFowls.add(new WaterFowl());
            waterFowls.add(new WaterFowl());
            waterFowls.add(new WaterFowl());
            waterFowls.add(new WaterFowl());

            // 4. get the current game's number of questions, q, Bob has asked,
            // and the number of lies, n, Alice has told

            int numberOfQuestions = scanner.nextInt();
            int numberOfLies = scanner.nextInt();

            // 5. next line

            scanner.nextLine();

            // 6. there will now be q number of line pairs,
            // first line will be the question Bob has asked,
            // and the second line will be the answer Alice has given

            for (int j = 0; j < numberOfQuestions; ++j) {
                String questionBobHasAsked = scanner.nextLine();
                String answerAliceHasGiven = scanner.nextLine();
                Question question = new Question(questionBobHasAsked, answerAliceHasGiven);
                questions.add(question);
                understandQuestionAsked(question, waterFowls, numberOfQuestions, numberOfLies);
            }

            System.out.println(waterFowlsToString(waterFowls));
        }
    }

    public static void understandQuestionAsked(Question question, ArrayList<WaterFowl> waterFowls, int numberOfQuestions, int numberOfLies) {

        String[] tokens;

        if (question.questionBobHasAsked.contains("and")) {
            tokens = question.questionBobHasAsked.split("and");

            // assume what Alice said is true

            for (int i = 0; i < tokens.length; ++i) {

                String currentToken = tokens[i];

                readToken(waterFowls, currentToken, numberOfQuestions, numberOfLies);

            }

            // TODO: implement assume what Alice said is false

        } else if (question.questionBobHasAsked.contains("or")) {
            tokens = question.questionBobHasAsked.split("or");

            // TODO: implement questions with or logical operator(s)
        } else {

            String currentToken = question.getQuestionBobHasAsked();

            readToken(waterFowls, currentToken, numberOfQuestions, numberOfLies);
        }
    }

    private static void readToken(ArrayList<WaterFowl> waterFowls, String currentToken, int numberOfQuestions, int numberOfLies) {
        if (currentToken.contains("bird")) {
            String[] tokens1 = currentToken.trim().split(" ");

            // first element of split tokens is going to be type of question

            String questionType = tokens1[0];

            // second element of split tokens is going to be location of bird

            int location = Integer.parseInt(tokens1[1]) - 1;

            // third element of split tokens is going to be type of bird

            String birdType = tokens1[2];

            if (birdType.equals("d")) {
                if (numberOfLies == numberOfQuestions) {
                    waterFowls.get(location).setDuck(false);
                    waterFowls.get(location).setGoose(true);
                    waterFowls.get(location).setSwan(true);
                } else {
                    waterFowls.get(location).setDuck(true);
                    waterFowls.get(location).setGoose(false);
                    waterFowls.get(location).setSwan(false);
                }
            } else if (birdType.equals("g")) {
                if (numberOfLies == numberOfQuestions) {
                    waterFowls.get(location).setDuck(true);
                    waterFowls.get(location).setGoose(false);
                    waterFowls.get(location).setSwan(true);
                } else {
                    waterFowls.get(location).setDuck(false);
                    waterFowls.get(location).setGoose(true);
                    waterFowls.get(location).setSwan(false);
                }
            } else {
                if (numberOfLies == numberOfQuestions) {
                    waterFowls.get(location).setDuck(true);
                    waterFowls.get(location).setGoose(true);
                    waterFowls.get(location).setSwan(false);
                } else {
                    waterFowls.get(location).setDuck(false);
                    waterFowls.get(location).setGoose(false);
                    waterFowls.get(location).setSwan(true);
                }
            }
        } else if (currentToken.contains("total")) {
            // TODO: implement question about total
        }
    }

    public static String waterFowlsToString(ArrayList<WaterFowl> waterFowls) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < waterFowls.size(); ++i) {
            stringBuilder.append(waterFowls.get(i).toString());

            if (i + 1 < waterFowls.size()) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }

    public static class WaterFowl {

        private boolean isDuck;
        private boolean isGoose;
        private boolean isSwan;

        WaterFowl() {
            isDuck = true;
            isGoose = true;
            isSwan = true;
        }

        WaterFowl(boolean isDuck, boolean isGoose, boolean isSwan) {
            this.isDuck = isDuck;
            this.isGoose = isGoose;
            this.isSwan = isSwan;
        }

        @Override
        public String toString() {

            StringBuilder stringBuilder = new StringBuilder();

            if (isDuck) {
                stringBuilder.append("d");
            }

            if (isGoose) {
                stringBuilder.append("g");
            }

            if (isSwan) {
                stringBuilder.append("s");
            }

            return stringBuilder.toString();
        }

        public boolean isDuck() {
            return isDuck;
        }

        public void setDuck(boolean duck) {
            isDuck = duck;
        }

        public boolean isGoose() {
            return isGoose;
        }

        public void setGoose(boolean goose) {
            isGoose = goose;
        }

        public boolean isSwan() {
            return isSwan;
        }

        public void setSwan(boolean swan) {
            isSwan = swan;
        }
    }

    public static class Question {

        private String questionBobHasAsked;
        private boolean answerAliceHasGiven;

        Question(String questionBobHasAsked, String answerAliceHasGiven) {
            this.questionBobHasAsked = questionBobHasAsked;
            this.answerAliceHasGiven = answerAliceHasGiven.equals("yes");
        }

        public String getQuestionBobHasAsked() {
            return questionBobHasAsked;
        }

        public void setQuestionBobHasAsked(String questionBobHasAsked) {
            this.questionBobHasAsked = questionBobHasAsked;
        }

        public boolean isAnswerAliceHasGiven() {
            return answerAliceHasGiven;
        }

        public void setAnswerAliceHasGiven(boolean answerAliceHasGiven) {
            this.answerAliceHasGiven = answerAliceHasGiven;
        }
    }
}