package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        InputNormalizer normalizer = new InputNormalizer();

        validator = new Validator(normalizer, extractor);
    }

    @Test
    @DisplayName("기본 구분자 테스트")
    void basicDelimiterTest() {
        String input = "1,2,3";

        assertThatCode(() -> validator.validate(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("기본 구분자가 두 개일 때")
    void basicDelimitersTest() {
        String input = "1,2:3";

        assertThatCode(() -> validator.validate(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("커스텀 구분자 테스트")
    void customDelimiterTest() {
        String input = "//;\\n1;2;3";

        assertThatCode(() -> validator.validate(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자 같이 사용")
    void basicAndCustomDelimiterTest() {
        String input = "//;\\n1;2,3";

        assertThatCode(() -> validator.validate(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("커스텀 구분자가 숫자일 때")
    void customDelimiterIsNumberTest() {
        String input = "//4\\n14243";

        assertThatCode(() -> validator.validate(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자가 아닌 경우 IllegalArgumentException")
    void notNumberTest() {
        String input = "1,a,3";

        assertThatThrownBy(() -> validator.validate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구분자가 연속으로 나오는 경우 IllegalArgumentException")
    void consecutiveDelimiterTest() {
        String input = "1,,2";

        assertThatThrownBy(() -> validator.validate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음수가 있는 경우 IllegalArgumentException")
    void negativeNumberTest() {
        String input = "1,-2,3";

        assertThatThrownBy(() -> validator.validate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("커스텀 구분자가 2자 이상인 경우 IllegalArgumentException")
    void longCustomDelimiterTest() {
        String input = "//;;\\n1;;2;;3";

        assertThatThrownBy(() -> validator.validate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("커스텀 구분자 형식이 잘못된 경우 IllegalArgumentException")
    void CustomDelimiterFormatIsInvalidTest() {
        String inputA = "//;/n1;2,3";
        String inputB = "//;\n1;2,3";
        String inputC = "//;\\\n1;2,3";

        assertThatThrownBy(() -> validator.validate(inputA)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> validator.validate(inputB)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> validator.validate(inputC)).isInstanceOf(IllegalArgumentException.class);
    }
}