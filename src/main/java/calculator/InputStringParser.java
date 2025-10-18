package calculator;

import java.util.ArrayList;
import java.util.List;

public class InputStringParser {

    public static List<Integer> parseString(String str, String delimiter) {

        String[] tokens = str.split(delimiter);
        List<Integer> list = new ArrayList<>();

        for (String token : tokens) list.add(Integer.parseInt(token));

        return list;
    }
}
