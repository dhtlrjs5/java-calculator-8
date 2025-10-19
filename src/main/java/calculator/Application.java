package calculator;

import calculator.controller.StringCalculator;
import calculator.model.Calculator;
import calculator.model.domain.DelimiterExtractor;
import calculator.model.domain.InputNormalizer;
import calculator.model.domain.InputStringParser;
import calculator.model.domain.Validator;
import calculator.view.InputHandler;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        //input, output 객체 생성
        InputHandler inputHandler = new InputHandler();
        OutputView outputView = new OutputView();

        //Model 객체 생성
        DelimiterExtractor delimiterExtractor = new DelimiterExtractor();
        InputNormalizer inputNormalizer = new InputNormalizer();
        Validator validator = new Validator(inputNormalizer, delimiterExtractor);
        InputStringParser inputStringParser = new InputStringParser();
        Calculator calculator = new Calculator(validator, inputStringParser, delimiterExtractor);

        //Controller 객체 생성
        StringCalculator stringCalculator = new StringCalculator(calculator, inputHandler, outputView);

        //실행
        stringCalculator.calculate();
    }
}