package calculator.view;

public class OutputView {

    private static final String RESULT_MESSAGE = "결과 : ";

    public void viewResult(long sum) {
        System.out.print(RESULT_MESSAGE + sum);
    }
}
