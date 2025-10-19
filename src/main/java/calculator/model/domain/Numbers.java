package calculator.model.domain;

import java.util.Collections;
import java.util.List;

public class Numbers {

    private final List<Long> numbers;

    public Numbers(List<Long> numbers) {
        this.numbers = Collections.unmodifiableList(numbers);
    }

    public long sum() {
        return numbers.stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}
