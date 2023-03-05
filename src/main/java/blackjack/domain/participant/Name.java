package blackjack.domain.participant;

import java.util.regex.Pattern;

public class Name {

    private static final Pattern NAME_FORMAT = Pattern.compile("[a-zA-Z]+");
    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 5;
    private static final String DEALER_NAME = "딜러";
    private static final String NAME_FORMAT_ERROR_MESSAGE = "사람 이름은 영문자만 가능합니다.";
    private static final String NAME_LENGTH_ERROR_MESSAGE = "참가자 이름은 2글자 이상 5글자 이하만 가능합니다.";

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        validateFormat(value);
        validateLength(value);
    }

    private void validateFormat(final String value) {
        if (isNotDealerName(value) && isNotEnglish(value)) {
            throw new IllegalArgumentException(NAME_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean isNotDealerName(final String value) {
        return !DEALER_NAME.equals(value);
    }

    private boolean isNotEnglish(final String value) {
        return !NAME_FORMAT.matcher(value).matches();
    }

    private void validateLength(final String value) {
        if (isLessLength(value) || isExceedLength(value)) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE);
        }
    }

    private boolean isLessLength(final String value) {
        return NAME_MIN_LENGTH > value.length();
    }

    private boolean isExceedLength(final String value) {
        return NAME_MAX_LENGTH < value.length();
    }

    public String getValue() {
        return value;
    }
}
