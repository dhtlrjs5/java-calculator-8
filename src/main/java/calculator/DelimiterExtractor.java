package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterExtractor {

    public static String extractDelimiter(String input) {
        String delimiter = ",|:";

        Pattern pattern = Pattern.compile("^//(.)\\n");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            delimiter += "|" + Pattern.quote(matcher.group(1));
        }

        return delimiter;
    }
}
