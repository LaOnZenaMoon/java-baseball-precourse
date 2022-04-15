package baseball.game;

import baseball.answer.Answer;
import baseball.global.AppUtils;
import baseball.question.Question;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class BaseballGame implements Game {

    private final Question question;
    private final Answer answer;


    public BaseballGame(Question question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }


    @Override
    public void start() {
        question.create();
    }

    @Override
    public void play() {
        System.out.print("숫자를 입력해주세요 : ");
        List<Integer> answers = (List<Integer>) answer.enter(Console.readLine());
        question.grade(answers);
    }

    @Override
    public boolean end() {
        return question.check();
    }

    @Override
    public boolean restart(final String enteredString) {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        AppUtils.validateLength(1, enteredString.length());
        AppUtils.validateDigit(enteredString.charAt(0));
        final int enteredInteger = Character.getNumericValue(enteredString.charAt(0));
        if (enteredInteger == 1) {
            return true;
        } else if (enteredInteger == 2) {
            return false;
        }
        return false;
    }
}
