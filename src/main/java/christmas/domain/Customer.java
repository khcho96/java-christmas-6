package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import java.util.LinkedHashMap;
import java.util.Map;

public class Customer {

    private Map<Menu, Integer> orderMenus;
    private final int date;

    public Customer(Map<Menu, Integer> orderMenus, int date) {
        this.orderMenus = orderMenus;
        this.date = date;
    }

    public static Customer of(int day, Map<String, Integer> orderMenus) {

        Map<Menu, Integer> menus = new LinkedHashMap<>();
        for (String menuName : orderMenus.keySet()) {
            Menu menu = Menu.from(menuName);
            menus.put(menu, orderMenus.get(menuName));
        }

        validateOnlyDrink(menus);
        validateMenuMaxCount(menus);

        return new Customer(menus, day);
    }

    private static void validateOnlyDrink(Map<Menu, Integer> menus) {
        if (menus.keySet().stream().allMatch(Menu::isDrink)) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINK_ERROR.getErrorMessage());
        }
    }

    private static void validateMenuMaxCount(Map<Menu, Integer> menus) {
        int orderMenuCount = menus.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        if (orderMenuCount > 20) {
            throw new IllegalArgumentException(ErrorMessage.MENU_MAX_ERROR.getErrorMessage());
        }
    }
}
