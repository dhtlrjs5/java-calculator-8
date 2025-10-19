package calculator;

public class OutputView {

    private static final String RESULT_MESSAGE = "결과 : ";

    static void viewResult(int sum) {
        System.out.print(RESULT_MESSAGE + sum);
    }
}
