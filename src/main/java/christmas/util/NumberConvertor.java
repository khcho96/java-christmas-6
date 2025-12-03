package christmas.util;

import static christmas.constant.ErrorMessage.DATE_ERROR;

public final class NumberConvertor {

    public static Integer convertToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_ERROR.getErrorMessage());
        }
    }
}
