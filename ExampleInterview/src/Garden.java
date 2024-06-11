public class Garden {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] list = {1, 2, 1, 2, 1, 2, 1};

        System.out.println(solution.solution(list));
    }
}

class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8

        int maximalRecord = 0;          // of all the combinations, this is the best possible
        int maximalAttempt = 0;         // this is a tally for the attempt to beat the record

        int firstSelection = A[0];
        int secondSelection = A[1];

        int nextSelection = 0;

        for (int i = 0; i < A.length; ++i) {
            nextSelection = A[i];

            if (nextSelection == firstSelection || nextSelection == secondSelection) {
                ++maximalAttempt;
            } else {
                // we have encountered a fruit of a different type,
                // try selecting this fruit now as our first selection

                if (maximalAttempt > maximalRecord) {
                    // but before we reset our attempt, if our attempt resulted in breaking the record, update it
                    maximalRecord = maximalAttempt;
                }

                firstSelection = nextSelection;
                maximalAttempt = 0; // we are attempting to beat max from scratch
            }
        }

        if (maximalAttempt > maximalRecord) {
            // but before we reset our attempt, if our attempt resulted in breaking the record, update it
            maximalRecord = maximalAttempt;
        }

        return maximalRecord;
    }
}