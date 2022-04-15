package baseball.answer;

import baseball.global.AppUtils;

import java.util.List;

public class User implements Answer<List<Integer>> {

    @Override
    public List<Integer> enter(final String enteredString) {
        return AppUtils.makeStringToIntegerList(enteredString);
    }

}
