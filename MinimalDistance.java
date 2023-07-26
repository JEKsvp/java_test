public class MinimalDistance {

    public static void main(String[] args) {
        minimalDistance(args[0], args[1]);
    }

    public static int minimalDistance(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();
        if (word1Length == 0 || word2Length == 0) {
            return calculateEasily(word1, word2, word1Length, word2Length);
        }
        int[][] dp = createDpArray(word1Length, word2Length);

        for (int i = 1; i <= word1Length; i++) {
            for (int j = 1; j <= word2Length; j++) {
                dp[i][j] = defineDistanceToCurrentPoint(word1, word2, dp, i, j);
            }
        }

        int distance = dp[word1Length][word2Length];
        System.out.println(distance);
        printTransformationSteps(word1, word2, word1Length, word2Length, dp, distance);
        return distance;
    }

    private static int calculateEasily(String word1, String word2, int word1Length, int word2Length) {
        int distance = Math.max(word1Length, word2Length);
        System.out.println(distance);
        printEasyTransformationSteps(word1, word2);
        return distance;
    }

    private static void printEasyTransformationSteps(String word1, String word2) {
        if (word1.length() > 0) {
            printCharByChar(word1);
        } else if (word2.length() > 0) {
            printCharByChar(word2);
        }
    }

    private static void printCharByChar(String word) {
        String[] curWord = word.split("");
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println();
        for (String s : curWord) {
            stringBuilder.append(s);
            System.out.println(stringBuilder);
        }
    }

    private static void printTransformationSteps(String word1, String word2, int word1Length, int word2Length,
            int[][] dp, int distance) {
        String[] curWord = word2.split("");
        print(curWord);
        int curJ = word2Length;
        int curI = word1Length;
        while (distance > 0) {
            int deletion = dp[curI][curJ - 1];
            int insertion = dp[curI - 1][curJ];
            int substitution = dp[curI - 1][curJ - 1];
            if (substitution < distance) {
                curWord[curJ - 1] = Character.toString(word1.charAt(curI - 1));
                curI--;
                curJ--;
                distance = substitution;
                print(curWord);
            } else if (deletion < distance) {
                curWord[curJ - 1] = "";
                curJ--;
                distance = deletion;
                print(curWord);
            } else if (insertion < distance) {
                curWord = insertIntoArray(curWord, curJ, Character.toString(word1.charAt(curI - 1)));
                curI--;
                distance = insertion;
                print(curWord);
            } else {
                curI--;
                curJ--;
            }
        }
    }

    private static void print(String[] curWord) {
        System.out.println(String.join("", curWord));
    }

    public static String[] insertIntoArray(String[] arr, int index, String newItem) {
        String[] result = new String[arr.length + 1];
        for (int i = 0; i < index; i++) {
            result[i] = arr[i];
        }
        result[index] = newItem;
        for (int i = index + 1; i < result.length; i++) {
            result[i] = arr[i - 1];
        }
        return result;
    }

    private static int defineDistanceToCurrentPoint(String word1, String word2, int[][] dp, int i, int j) {
        int deletion = dp[i][j - 1] + 1;
        int insertion = dp[i - 1][j] + 1;
        int substitution;
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            substitution = dp[i - 1][j - 1];
        } else {
            substitution = dp[i - 1][j - 1] + 1;
        }
        return Math.min(Math.min(deletion, insertion), substitution);
    }

    private static int[][] createDpArray(int word1Length, int word2Length) {
        int[][] dp = new int[word1Length + 1][word2Length + 1];

        for (int i = 0; i <= word1Length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2Length; j++) {
            dp[0][j] = j;
        }
        return dp;
    }
}
