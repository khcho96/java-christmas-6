package christmas.domain;

import christmas.constant.Event;
import christmas.constant.Menu;
import java.util.List;
import java.util.Map;

public class EventHandler {

    public EventResult adjustEvent(Customer customer) {
        if (!isEventPossible(customer)) {
            return new EventResult();
        }

        EventResult eventResult = new EventResult();
        List<Event> events = Event.from(customer.getDay());
        for (Event event : events) {
            adjustEachEvent(customer, event, eventResult);
        }

        return eventResult;
    }

    private boolean isEventPossible(Customer customer) {
        return customer.getTotalPrice() >= 10_000;
    }

    private void adjustEachEvent(Customer customer, Event event, EventResult eventResult) {
        if (event.equals(Event.CHRISTMAS_D_DAY)) {
            adjustChristmasEvent(customer, eventResult);
            return;
        }

        if (event.equals(Event.WEEKDAYS)) {
            adjustWeekDaysEvent(customer, eventResult);
            return;
        }

        if (event.equals(Event.WEEKENDS)) {
            adjustWeekendsEvent(customer, eventResult);
            return;
        }

        if (event.equals(Event.SPECIAL)) {
            adjustSpecialEvent(eventResult);
            return;
        }

        adjustPresentEvent(customer, eventResult);
    }

    private void adjustChristmasEvent(Customer customer, EventResult eventResult) {
        eventResult.addEvent(Event.CHRISTMAS_D_DAY, 1000 + 100 * (customer.getDay() - 1));
    }

    private void adjustWeekDaysEvent(Customer customer, EventResult eventResult) {
        Map<Menu, Integer> dissertMenus = customer.getDissertMenuOrders();
        if (!dissertMenus.isEmpty()) {
            int totalQty = dissertMenus.values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            eventResult.addEvent(Event.WEEKDAYS, totalQty * 2023);
        }
    }

    private void adjustWeekendsEvent(Customer customer, EventResult eventResult) {
        Map<Menu, Integer> mainMenus = customer.getMainMenuOrders();
        if (!mainMenus.isEmpty()) {
            int totalQty = mainMenus.values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            eventResult.addEvent(Event.WEEKENDS, totalQty * 2023);
        }
    }

    private void adjustSpecialEvent(EventResult eventResult) {
        eventResult.addEvent(Event.SPECIAL, 1000);
    }

    private void adjustPresentEvent(Customer customer, EventResult eventResult) {
        if (customer.getTotalPrice() >= 120_000) {
            eventResult.addEvent(Event.PRESENT, Menu.CHAMPAGNE.getPrice());
        }
    }
}
