package baseball.global;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    private AppUtils() {
    }

    public static void validateLength(int source, int target) {
        if (source == target) {
            return;
        }

        throw new IllegalArgumentException(String.format("Please enter a %d digit number.", source));
    }

    public static void validateDigit(char item) {
        if (!Character.isDigit(item)) {
            throw new IllegalArgumentException("Please enter digit numbers.");
        }
    }

    public static List<Integer> makeStringToIntegerList(final String enteredString) {
        AppUtils.validateLength(3, enteredString.length());

        List<Integer> answerList = new ArrayList<>();
        for (char item : enteredString.toCharArray()) {
            AppUtils.validateDigit(item);
            answerList.add(Character.getNumericValue(item));
        }

        return answerList;
    }

}
