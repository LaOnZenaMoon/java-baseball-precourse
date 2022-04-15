package baseball.answer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private final User user;


    public UserTest() {
        this.user = new User();
    }


    @DisplayName("사용자 입력 실패(길이) 테스트")
    @ParameterizedTest(name = "{index}. {displayName} 입력값={0}")
    @ValueSource(strings = {"", "1", "12", "1234"})
    void testUserEnterWrongLengthValue(final String enteredString) {
        // Given
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> user.enter(enteredString));
    }

    @DisplayName("사용자 입력 실패(비숫자 포함) 테스트")
    @ParameterizedTest(name = "{index}. {displayName} 입력값={0}")
    @ValueSource(strings = {"12a", "12!", "12ㄱ", "ㄱㄴㄷ", "aㄱ$", "  ㄱ"})
    void testUserEnterNonDigitValue(final String enteredString) {
        // Given
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> user.enter(enteredString));
    }

    @DisplayName("사용자 입력 성공 테스트")
    @ParameterizedTest(name = "{index}. {displayName} 입력값={0}")
    @ValueSource(strings = {"123", "459", "234", "369"})
    void testUserEnterValueSuccessfully(final String enteredString) {
        // Given
        // When
        List<Integer> enter = user.enter(enteredString);

        // Then
        assertEquals(3, enter.size());
    }

}