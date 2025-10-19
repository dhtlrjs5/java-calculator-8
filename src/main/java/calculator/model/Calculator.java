package calculator.model;

import calculator.model.domain.DelimiterExtractor;
import calculator.model.domain.InputStringParser;
import calculator.model.domain.Numbers;
import calculator.model.domain.Validator;

public class Calculator {

    private static final int ZERO = 0;

    private final Validator validator;
    private final InputStringParser inputStringParser;
    private final DelimiterExtractor delimiterExtractor;

    public Calculator(Validator validator, InputStringParser inputStringParser, DelimiterExtractor delimiterExtractor) {
        this.validator = validator;
        this.inputStringParser = inputStringParser;
        this.delimiterExtractor = delimiterExtractor;
    }

    public int calculate(String input) {

        if (input.isEmpty()) {
            return ZERO;
        }

        //구분자 추출
        String delimiter = delimiterExtractor.extractDelimiter(input);
        //유효성 검사
        String origin = validator.validate(input);

        if (origin.isEmpty()) {
            return ZERO;
        }

        Numbers numbers = inputStringParser.parseString(origin, delimiter);

        return numbers.sum();
    }
}
