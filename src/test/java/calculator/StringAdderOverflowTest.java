package calculator;

import calculator.model.Calculator;
import calculator.model.domain.DelimiterExtractor;
import calculator.model.domain.InputNormalizer;
import calculator.model.domain.InputStringParser;
import calculator.model.domain.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringAdderOverflowTest {

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
    @DisplayName("Integer.MAX_VALUE를 초과하는 값의 합계 테스트 (long 검증)")
    void sumOverIntegerMaxValueTest() {
        //given
        String input = "100000000000,5";
        long expectedResult = 100_000_000_005L;

        //when
        long sum = calculate(input);

        //then
        assertThat(sum).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("커스텀 구분자가 포함된 입력에서 Integer.MAX_VALUE를 초과하는 값의 합계 테스트")
    void sumOverIntegerMaxValueWithCustomDelimiterTest() {
        //given
        String input = "//;\\n100000000000,5;3";
        long expectedResult = 100_000_000_008L;

        //when
        long sum = calculate(input);

        //then
        assertThat(sum).isEqualTo(expectedResult);
    }
}
