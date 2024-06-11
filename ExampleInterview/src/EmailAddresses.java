//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class EmailAddresses {
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//
//        String string = "test+1";
//
//        int cutoffIndex = string.indexOf('+');
//
//        System.out.println(string.subSequence(0, cutoffIndex));
//
//        String[] list = { "y@example.com", "y@example.com",  "a.b@example.com", "ab+1@example.com" };
//
//        System.out.println(solution.solution(list));
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
//    public int solution(String[] L) {
//        // write your code in Java SE 8
//
//        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
//
//        String[] tokens;
//
//        for (int i = 0; i < L.length; ++i) {
//
//            StringBuilder stringBuilder = new StringBuilder();
//
//            // consider the rules for each email address
//
//            String emailAddress = L[i];
//
//            // all the rules only apply to the local name, which we can get by splitting at the '@' where we are guaranteed there is only one
//
//            tokens = emailAddress.split("@");
//
//            // the local name will be the characters before '@'
//
//            String originalLocalName = tokens[0];
//
//            String domainAddress = tokens[1];
//
//            // 1. remove every character after '+' including '+' in the local name
//
//            tokens = originalLocalName.split("\\+");
//
//            // we can only use the first part of the tokens since those are the characters that are valid in the local name
//
//            String reducedLocalName = tokens[0];
//
//            // 2. ignore '.' in the local name
//
//            if (reducedLocalName.contains(".")) {
//                tokens = reducedLocalName.split("\\.");
//
//                for (String token : tokens) {
//                    stringBuilder.append(token);
//                }
//
//            } else {
//                stringBuilder.append(reducedLocalName);
//            }
//
//            String localName = stringBuilder.toString();
//
//            StringBuilder finalBuild = new StringBuilder();
//
//            finalBuild.append(localName);
//            finalBuild.append("@");
//            finalBuild.append(domainAddress);
//
//            String key = finalBuild.toString();
//
//            if (!hashMap.containsKey(key)) {
//                hashMap.put(key, new ArrayList<>());
//            }
//
//            hashMap.get(key).add(emailAddress);
//        }
//
//        int numberOfDuplicates = 0;
//
//        for (String key : hashMap.keySet()) {
//            if (hashMap.get(key).size() > 1) {
//                ++numberOfDuplicates;
//            }
//        }
//
//        return numberOfDuplicates;
//    }
//}