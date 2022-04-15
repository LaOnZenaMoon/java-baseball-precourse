package baseball.question;

import baseball.answer.User;
import baseball.global.AppUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    private final Computer computer;
    private final User user;


    public ComputerTest() {
        this.computer = new Computer();
        this.user = new User();
    }


    @DisplayName("문제 생성 검증 테스트")
    @RepeatedTest(value = 30, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void testCreateWrongQuestionsIsExist(RepetitionInfo repetitionInfo) {
        // Given
        // When
        computer.create();

        // Then
        final List<Integer> questions = computer.getQuestions();
        assertEquals(3, questions.size());
        final Integer firstItem = questions.get(0);
        final Integer secondItem = questions.get(1);
        final Integer thirdItem = questions.get(2);

        assertTrue(!firstItem.equals(secondItem) && !firstItem.equals(thirdItem)
                && !secondItem.equals(thirdItem));
        assertFalse(computer.check());
    }

    @DisplayName("채점 검증 테스트(낫싱)")
    @Test
    void testCompareQuestionsAndAnswersNothing() {
        // Given
        final String enteredQuestions = "123";
        final String enteredAnswers = "456";

        HackedComputer hackedComputer = createHackedComputerAndHackTheQuestion(enteredQuestions);

        // When
        playTheGame(enteredAnswers, hackedComputer);

        // Then
        assertEquals(0, hackedComputer.getStrike());
        assertEquals(0, hackedComputer.getBall());
        assertFalse(hackedComputer.check());
    }

    @DisplayName("채점 검증 테스트(1볼)")
    @Test
    void testCompareQuestionsAndAnswers1Ball() {
        // Given
        final String enteredQuestions = "124";
        final String enteredAnswers = "456";

        HackedComputer hackedComputer = createHackedComputerAndHackTheQuestion(enteredQuestions);

        // When
        playTheGame(enteredAnswers, hackedComputer);

        // Then
        assertEquals(0, hackedComputer.getStrike());
        assertEquals(1, hackedComputer.getBall());
        assertFalse(hackedComputer.check());
    }

    @DisplayName("채점 검증 테스트(2볼)")
    @Test
    void testCompareQuestionsAndAnswers2Ball() {
        // Given
        final String enteredQuestions = "124";
        final String enteredAnswers = "452";

        HackedComputer hackedComputer = createHackedComputerAndHackTheQuestion(enteredQuestions);

        // When
        playTheGame(enteredAnswers, hackedComputer);

        // Then
        assertEquals(0, hackedComputer.getStrike());
        assertEquals(2, hackedComputer.getBall());
        assertFalse(hackedComputer.check());
    }

    @DisplayName("채점 검증 테스트(3볼)")
    @Test
    void testCompareQuestionsAndAnswers3Ball() {
        // Given
        final String enteredQuestions = "124";
        final String enteredAnswers = "412";

        HackedComputer hackedComputer = createHackedComputerAndHackTheQuestion(enteredQuestions);

        // When
        playTheGame(enteredAnswers, hackedComputer);

        // Then
        assertEquals(0, hackedComputer.getStrike());
        assertEquals(3, hackedComputer.getBall());
        assertFalse(hackedComputer.check());
    }

    @DisplayName("채점 검증 테스트(1볼 1스트라이크)")
    @Test
    void testCompareQuestionsAndAnswers1Ball1Strike() {
        // Given
        final String enteredQuestions = "124";
        final String enteredAnswers = "254";

        HackedComputer hackedComputer = createHackedComputerAndHackTheQuestion(enteredQuestions);

        // When
        playTheGame(enteredAnswers, hackedComputer);

        // Then
        assertEquals(1, hackedComputer.getStrike());
        assertEquals(1, hackedComputer.getBall());
        assertFalse(hackedComputer.check());
    }

    @DisplayName("채점 검증 테스트(2볼 1스트라이크)")
    @Test
    void testCompareQuestionsAndAnswers1Ball2Strike() {
        // Given
        final String enteredQuestions = "124";
        final String enteredAnswers = "421";

        HackedComputer hackedComputer = createHackedComputerAndHackTheQuestion(enteredQuestions);

        // When
        playTheGame(enteredAnswers, hackedComputer);

        // Then
        assertEquals(1, hackedComputer.getStrike());
        assertEquals(2, hackedComputer.getBall());
        assertFalse(hackedComputer.check());
    }

    @DisplayName("채점 검증 테스트(2스트라이크)")
    @Test
    void testCompareQuestionsAndAnswers2Strike() {
        // Given
        final String enteredQuestions = "124";
        final String enteredAnswers = "324";

        HackedComputer hackedComputer = createHackedComputerAndHackTheQuestion(enteredQuestions);

        // When
        playTheGame(enteredAnswers, hackedComputer);

        // Then
        assertEquals(2, hackedComputer.getStrike());
        assertEquals(0, hackedComputer.getBall());
        assertFalse(hackedComputer.check());
    }

    @DisplayName("채점 검증 테스트(3스트라이크)")
    @Test
    void testCompareQuestionsAndAnswers3Strike() {
        // Given
        final String enteredQuestions = "124";
        final String enteredAnswers = "124";

        HackedComputer hackedComputer = createHackedComputerAndHackTheQuestion(enteredQuestions);

        // When
        playTheGame(enteredAnswers, hackedComputer);

        // Then
        assertEquals(3, hackedComputer.getStrike());
        assertEquals(0, hackedComputer.getBall());
        assertTrue(hackedComputer.check());
    }

    private void playTheGame(String enteredAnswers, HackedComputer hackedComputer) {
        List<Integer> answers = user.enter(enteredAnswers);
        hackedComputer.grade(answers);
    }

    private HackedComputer createHackedComputerAndHackTheQuestion(String enteredQuestions) {
        HackedComputer hackedComputer = new HackedComputer();
        hackedComputer.create();
        hackedComputer.hackTheQuestion(enteredQuestions);
        return hackedComputer;
    }

    private class HackedComputer extends Computer {

        public void hackTheQuestion(final String hackedString) {
            this.questions = AppUtils.makeStringToIntegerList(hackedString);
        }

    }

}