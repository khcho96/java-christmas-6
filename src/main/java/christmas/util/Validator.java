package christmas.util;

import christmas.constant.ErrorMessage;
import java.util.Map;

public final class Validator {

    private static final String NUMBER_FORMAT = "[1-9]|[12]\\d|3[01]";
    private static final String MENU_FORMAT = "^([가-힣]+-[1-9]\\d*) *(, *([가-힣]+-[1-9]\\d*) *)*$";

    private Validator() {}

    public static void validateDayFormat(String input) {
        if (!input.matches(NUMBER_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.DAY_FORMAT_ERROR.getErrorMessage());
        }
    }

    public static void validateMenuFormat(String input) {
        if (!input.matches(MENU_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_FORMAT_ERROR.getErrorMessage());
        }
    }

    public static void validateMenuDuplication(Map<String, Integer> menus, String menu) {
        if (menus.containsKey(menu)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_FORMAT_ERROR.getErrorMessage());
        }
    }
}
