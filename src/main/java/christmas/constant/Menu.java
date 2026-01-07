package christmas.constant;

import java.util.Arrays;

public enum Menu {

    SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    SALAD("시저샐러드", 8_000, Category.APPETIZER),

    STEAK("티본스테이크", 55_000, Category.MAIN),
    LEAP("바비큐립", 54_000, Category.MAIN),
    SEA_PASTA("해산물파스타", 35_000, Category.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN),

    CAKE("초코케이크", 15_000, Category.DISSERT),
    ICE_CREAM("아이스크림", 5_000, Category.DISSERT),

    COKE("제로콜라", 3_000, Category.DRINK),
    WINE("레드와인", 60_000, Category.DRINK),
    CHAMPAGNE("샴페인", 25_000, Category.DRINK),
    ;
    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Menu from(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MENU_FORMAT_ERROR.getErrorMessage()));
    }

    public boolean isMain() {
        return this.category.equals(Category.MAIN);
    }

    public boolean isDissert() {
        return this.category.equals(Category.DISSERT);
    }

    public boolean isDrink() {
        return this.category.equals(Category.DRINK);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
