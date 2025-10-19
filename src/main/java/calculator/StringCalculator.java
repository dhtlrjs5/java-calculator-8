package calculator;

import java.util.List;

//Controller
public class StringCalculator {

    private static final String EMPTY_INPUT_ZERO = "0";

    public static void calculate() {
        String input = InputHandler.readInput();
        if (input.isEmpty()) {
            System.out.println(EMPTY_INPUT_ZERO);
            return;
        }

        String delimiter = DelimiterExtractor.extractDelimiter(input);
        String origin = Validator.validate(input);
        if (origin.isEmpty()) {
            System.out.println(EMPTY_INPUT_ZERO);
            return;
        }

        Numbers numbers = InputStringParser.parseString(origin, delimiter);
        int sum = numbers.sum();
        OutputView.viewResult(sum);
    }
}
