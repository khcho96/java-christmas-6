package christmas.service;

import christmas.domain.Customer;
import christmas.domain.EventHandler;
import christmas.domain.EventResult;
import christmas.dto.Result;
import java.util.Map;

public class ChristmasService {

    private Customer customer;

    public void registerCustomer(int day, Map<String, Integer> orderMenus) {
        customer = Customer.of(day, orderMenus);
    }

    public Result calculateResult() {
        EventHandler eventHandler = new EventHandler();

        EventResult eventResult = eventHandler.adjustEvent(customer);

        return new Result(customer, eventResult);
    }
}
