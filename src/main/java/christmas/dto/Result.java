package christmas.dto;

import christmas.domain.Customer;
import christmas.domain.EventResult;

public record Result(Customer customer, EventResult eventResult) {
}
