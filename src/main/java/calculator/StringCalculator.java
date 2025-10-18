package calculator;

import java.util.List;

public class StringCalculator {

    /**
     * 고려사항: 수가 엄청 크다면?
     * controller
     */
    public static void calculate() {
        String input = InputHandler.readInput();
        if (input.isEmpty()) {
            System.out.println("0");
            return;
        }

        String delimiter = DelimiterExtractor.extractDelimiter(input);
        String origin = Validator.validate(input);
        List<Integer> parsedString = InputStringParser.parseString(origin, delimiter);

    }
}
