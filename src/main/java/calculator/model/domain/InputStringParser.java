package calculator.model.domain;

import java.util.ArrayList;
import java.util.List;

public class InputStringParser {

    public Numbers parseString(String str, String delimiter) {

        String[] tokens = str.split(delimiter);
        List<Long> list = new ArrayList<>();

        for (String token : tokens) list.add(Long.parseLong(token));

        return new Numbers(list);
    }
}
