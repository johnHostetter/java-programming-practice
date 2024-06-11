//public class Demo {
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.solution("2-4A0r7-4k", 3));
//    }
//}
//
//// you can also use imports, for example:
//// import java.util.*;
//
//// you can write to stdout for debugging purposes, e.g.
//// System.out.println("this is a debug message");
//
//class Solution {
//    public String solution(String S, int K) {
//        // write your code in Java SE 8
//
//        int remainderToAdd = K;
//        StringBuilder stringBuilder = new StringBuilder();
//
//        // the best way to approach this is to work backwards since the first group in the string can have any # of chars
//
//        for (int i = (S.length() - 1); i >= 0; --i) {
//
//            // all characters to be added must be in upper case form
//
//            char currentCharacter = Character.toUpperCase(S.charAt(i));
//
//            // keep a tally of how many more characters we can add before needing to start a new group
//
//            if (remainderToAdd <= 0) {
//                stringBuilder.insert(0, '-');
//                remainderToAdd = K;
//            }
//
//            if (currentCharacter != '-') {
//                // we can ignore all dashes
//                stringBuilder.insert(0,currentCharacter);
//                --remainderToAdd;
//            }
//        }
//
//        return stringBuilder.toString();
//    }
//}