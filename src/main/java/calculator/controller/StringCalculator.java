package calculator.controller;

import calculator.model.Calculator;
import calculator.view.InputHandler;
import calculator.view.OutputView;

//Controller
public class StringCalculator {

    private final Calculator calculator;
    private final InputHandler inputHandler;
    private final OutputView outputView;

    public StringCalculator(Calculator calculator, InputHandler inputHandler, OutputView outputView) {
        this.calculator = calculator;
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    /**
     * 문자열이 비어있으면 sum = 0
     */
    public void calculate() {

        String input = inputHandler.readInput();

        int sum = calculator.calculate(input);

        outputView.viewResult(sum);
    }

}