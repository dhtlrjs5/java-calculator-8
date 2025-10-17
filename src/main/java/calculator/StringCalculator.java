package calculator;

public class StringCalculator {

    /**
     * 고려사항: 수가 엄청 크다면?
     */
    public static void calculate() {
        String input = InputHandler.readInput();
        if (input.isEmpty()) {
            System.out.println("0");
            return;
        }

        Validator.validate(input);
    }
}
