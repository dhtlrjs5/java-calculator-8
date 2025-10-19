package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterExtractor {

    private static final String DEFAULT_DELIMITER = "[,:]";
    private static final String CUSTOM_DELIMITER_PATTERN = "^//(.)\\\\n";

    public String extractDelimiter(String input) {
        String delimiter = DEFAULT_DELIMITER;

        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_PATTERN, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            delimiter = delimiter.substring(0, delimiter.length() - 1) + Pattern.quote(matcher.group(1)) + "]";
        }

        return delimiter;
    }
}
