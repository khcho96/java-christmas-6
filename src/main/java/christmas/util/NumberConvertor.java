package christmas.util;

import christmas.constant.ErrorMessage;

public final class NumberConvertor {

    public static int convertToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.DAY_FORMAT_ERROR.getErrorMessage());
        }
    }
}
