package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import java.util.LinkedHashMap;
import java.util.Map;

public class Customer {

    private final Map<Menu, Integer> orderMenus;
    private final int day;

    private Customer(Map<Menu, Integer> orderMenus, int day) {
        this.orderMenus = orderMenus;
        this.day = day;
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

    public int getTotalPrice() {
        return orderMenus.keySet().stream()
                .map(menu -> menu.getPrice() * orderMenus.get(menu))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getDay() {
        return day;
    }

    public Map<Menu, Integer> getMainMenuOrders() {
        Map<Menu, Integer> mainMenus = new LinkedHashMap<>();

        for (Menu menu : orderMenus.keySet()) {
            if (menu.isMain()) {
                mainMenus.put(menu, orderMenus.get(menu));
            }
        }

        return mainMenus;
    }

    public Map<Menu, Integer> getDissertMenuOrders() {
        Map<Menu, Integer> dissertMenus = new LinkedHashMap<>();

        for (Menu menu : orderMenus.keySet()) {
            if (menu.isDissert()) {
                dissertMenus.put(menu, orderMenus.get(menu));
            }
        }

        return dissertMenus;
    }
}
