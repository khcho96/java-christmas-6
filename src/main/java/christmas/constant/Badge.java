package christmas.constant;

import java.util.Arrays;

public enum Badge {

    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0),
    ;

    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Badge from(int price) {
        return Arrays.stream(values())
                .filter(badge -> badge.price <= price)
                .findFirst()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
