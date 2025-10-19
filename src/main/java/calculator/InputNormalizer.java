package calculator;

import java.util.regex.Pattern;

import static calculator.ErrorMessageConstants.INVALID_CUSTOM_DELIMITER;

public class InputNormalizer {

    private static final String NUMBER_START_PATTERN = "^\\d.*";
    private static final String CUSTOM_DELIMITER_PATTERN = "^//.\\\\n.*";

    /**
     * 커스텀 구분자 형식이 올바른 형식인지 확인
     * 커스텀 구분자를 지정하는 부분을 제거하고 return
     */
    public String extractOriginAndValidateDelimiterFormat(String input) {

        //기본 구분자 사용시 return
        if (input.matches(NUMBER_START_PATTERN)) {
            return input;
        }

        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_PATTERN);
        if (pattern.matcher(input).matches()) {
            int idx = input.indexOf('n');
            if (idx == -1) {
                throw new IllegalArgumentException(INVALID_CUSTOM_DELIMITER);
            }

            return input.substring(idx + 1);
        }

        throw new IllegalArgumentException(INVALID_CUSTOM_DELIMITER);
    }
}