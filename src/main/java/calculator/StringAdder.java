package calculator;

import java.util.List;

public class StringAdder {

    public static int addParsedNumber(List<Integer> parsed) {

        int sum = 0;

        for (int parsedNumber : parsed) {
            sum += parsedNumber;
        }

        return sum;
    }
}
