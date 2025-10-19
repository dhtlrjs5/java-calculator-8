package calculator;

import calculator.model.Calculator;
import calculator.model.domain.DelimiterExtractor;
import calculator.model.domain.InputNormalizer;
import calculator.model.domain.InputStringParser;
import calculator.model.domain.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringAdderTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        InputNormalizer normalizer = new InputNormalizer();
        Validator validator = new Validator(normalizer, extractor);
        InputStringParser parser = new InputStringParser();

        this.calculator = new Calculator(validator, parser, extractor);
    }

    private long calculate(String inputString) {
        return calculator.calculate(inputString);
    }

    @Test
    @DisplayName("단일 숫자 문자열")
    void singleNumberTest() {
        String inputString = "5";
        long sum = calculate(inputString);
        assertThat(sum).isEqualTo(5);
    }

    @Test
    @DisplayName("쉼표로 구분된 숫자")
    void commaSeparatedTest() {
        String inputString = "1,2,3";
        long sum = calculate(inputString);
        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("콜론으로 구분된 숫자")
    void colonSeparatedTest() {
        String inputString = "1:2:3:4";
        long sum = calculate(inputString);
        assertThat(sum).isEqualTo(10);
    }

    @Test
    @DisplayName("쉼표와 콜론 혼합")
    void mixedDelimiterTest() {
        String inputString = "1,2:3";
        long sum = calculate(inputString);
        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자")
    void customSemicolonTest() {
        String inputString = "//;\\n1;2;3";
        long sum = calculate(inputString);
        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자 파이프")
    void customPipeTest() {
        String inputString = "//|\\n4|5|6";
        long sum = calculate(inputString);
        assertThat(sum).isEqualTo(15);
    }

    @Test
    @DisplayName("단일 숫자 + 커스텀 구분자")
    void singleNumberCustomDelimiterTest() {
        String inputString = "//;\\n7";
        long sum = calculate(inputString);
        assertThat(sum).isEqualTo(7);
    }

    @Test
    @DisplayName("특수문자 커스텀 구분자")
    void specialCharacterDelimiterTest() {
        String inputString = "//^\\n1^2^3";
        long sum = calculate(inputString);
        assertThat(sum).isEqualTo(6);
    }
}
