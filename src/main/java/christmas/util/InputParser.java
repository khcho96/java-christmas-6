package christmas.util;

import java.util.LinkedHashMap;
import java.util.Map;

public final class InputParser {

    private static final String FIRST_DELIMITER = ",";
    private static final String SECOND_DELIMITER = "-";

    private InputParser() {
    }

    public static int parseDay(String rawInput) {
        rawInput = rawInput.strip();

        Validator.validateDayFormat(rawInput);

        return NumberConvertor.convertToNumber(rawInput);
    }

    public static Map<String, Integer> parseMenus(String rawInput) {
        rawInput = rawInput.strip();

        Validator.validateMenuFormat(rawInput);

        Map<String, Integer> menus = new LinkedHashMap<>();
        String[] split = rawInput.split(FIRST_DELIMITER);
        for (String s : split) {
            String[] split1 = s.strip().split(SECOND_DELIMITER);
            String menu = split1[0].strip();
            int qty = NumberConvertor.convertToNumber(split1[1].strip());

            Validator.validateMenuDuplication(menus, menu);
            menus.put(menu, qty);
        }

        return menus;
    }
}
