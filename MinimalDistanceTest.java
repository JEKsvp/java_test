import java.util.Objects;

public class MinimalDistanceTest {

    public static void main(String[] args) {
        try {
            assertEquals(1, MinimalDistance.minimalDistance("aaaaa", "aabaa"));
            assertEquals(2, MinimalDistance.minimalDistance("aaaaa", "aaba"));
            assertEquals(5, MinimalDistance.minimalDistance("aaaaa", ""));
            assertEquals(0, MinimalDistance.minimalDistance("", ""));
            assertEquals(0, MinimalDistance.minimalDistance("aaa", "aaa"));
            assertEquals(5, MinimalDistance.minimalDistance("", "aaaaa"));
            assertEquals(4, MinimalDistance.minimalDistance("aababababa", "aaaaaaa"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void assertEquals(Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            throw new IllegalStateException("Test failed. Expected: " + expected + ", actual: " + actual);
        }
    }

}
