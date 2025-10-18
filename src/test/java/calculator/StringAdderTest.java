package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StringAdderTest {

    @Test
    @DisplayName("빈 문자열")
    void emptyStringTest() {
        //given
        String inputString = "";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        //when
        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        //then
        assertThat(sum).isEqualTo(0);
    }

    @Test
    @DisplayName("단일 숫자 문자열")
    void singleNumberTest() {
        String inputString = "5";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(5);
    }

    @Test
    @DisplayName("쉼표로 구분된 숫자")
    void commaSeparatedTest() {
        String inputString = "1,2,3";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("콜론으로 구분된 숫자")
    void colonSeparatedTest() {
        String inputString = "1:2:3:4";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(10);
    }

    @Test
    @DisplayName("쉼표와 콜론 혼합")
    void mixedDelimiterTest() {
        String inputString = "1,2:3";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자")
    void customSemicolonTest() {
        String inputString = "//;\n1;2;3";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자 파이프")
    void customPipeTest() {
        String inputString = "//|\n4|5|6";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(15);
    }

    @Test
    @DisplayName("단일 숫자 + 커스텀 구분자")
    void singleNumberCustomDelimiterTest() {
        String inputString = "//;\n7";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(7);
    }

    @Test
    @DisplayName("특수문자 커스텀 구분자")
    void specialCharacterDelimiterTest() {
        String inputString = "//^\n1^2^3";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자 + 빈 문자열")
    void customDelimiterAndEmptyInputTest() {
        String inputString = "//;\n";
        String delimiter = DelimiterExtractor.extractDelimiter(inputString);

        List<Integer> parsedString = InputStringParser.parseString(inputString, delimiter);
        int sum = StringAdder.addParsedNumber(parsedString);

        assertThat(sum).isEqualTo(0);
    }
}
