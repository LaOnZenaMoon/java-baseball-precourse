package baseball.question;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Computer implements Question<List<Integer>> {

    protected List<Integer> questions;
    private boolean answerIsCorrect;
    private int strike;
    private int ball;


    @Override
    public void create() {
        questions = new ArrayList<>();
        while (questions.size() < 3) {
            addQuestion(Randoms.pickNumberInRange(1, 9));
        }
        this.answerIsCorrect = false;
        resetStrikeAndBallCount();
    }

    @Override
    public void grade(List<Integer> answers) {
        validateQuestions();
        validateQuestionsLength(questions.size());

        if (compareQuestionsAndAnswers(answers)) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            answerIsCorrect = true;
        }
    }

    @Override
    public boolean check() {
        resetStrikeAndBallCount();
        return answerIsCorrect;
    }

    public List<Integer> getQuestions() {
        return questions;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    private void addQuestion(int randomInteger) {
        if (!questions.contains(randomInteger)) {
            questions.add(randomInteger);
        }
    }

    private boolean compareQuestionsAndAnswers(List<Integer> answers) {
        for (int i = 0; i < answers.size(); i++) {
            boolean isStrike = checkStrike(questions.get(i), answers.get(i));
            strike += isStrike ? 1 : 0;
            ball += !isStrike && checkBall(answers.get(i)) ? 1 : 0;
        }

        writeResult();
        return strike == 3;
    }

    private void writeResult() {
        StringBuilder result = new StringBuilder();
        if (ball > 0) result.append(String.format("%d볼", ball));
        if (strike > 0 && ball > 0) result.append(" ");
        if (strike > 0) result.append(String.format("%d스트라이크", strike));
        if (strike == 0 && ball == 0) result.append("낫싱");
        System.out.println(result);
    }

    private boolean checkBall(Integer answer) {
        return questions.contains(answer);
    }

    private boolean checkStrike(Integer question, Integer answer) {
        return question.equals(answer);
    }

    private void validateQuestions() {
        if (questions == null) {
            throw new IllegalStateException("questions are not created.");
        }
    }

    private void validateQuestionsLength(int length) {
        if (length == 3) {
            return;
        }

        throw new IllegalStateException("Questions' length is not a 3 digit number.");
    }

    private void resetStrikeAndBallCount() {
        this.strike = 0;
        this.ball = 0;
    }

}
