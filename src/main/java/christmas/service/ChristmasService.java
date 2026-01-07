package christmas.service;

import christmas.domain.Customer;
import java.util.Map;

public class ChristmasService {

    private Customer customer;

    public void registerCustomer(int day, Map<String, Integer> orderMenus) {
        customer = Customer.of(day, orderMenus);
    }
}
