package calculator;

//Controller
public class StringCalculator {

    public static void calculate() {

        String input = InputHandler.readInput();

        int sum = 0;

        /**
         * 문자열이 비어있으면 sum = 0
         */
        if (!input.isEmpty()) {
            String delimiter = DelimiterExtractor.extractDelimiter(input);
            String origin = Validator.validate(input);

            if (!origin.isEmpty()) {
                Numbers numbers = InputStringParser.parseString(origin, delimiter);
                sum = numbers.sum();
            }
        }

        OutputView.viewResult(sum);
    }
}
