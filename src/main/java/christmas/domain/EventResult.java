package christmas.domain;

import christmas.constant.Event;
import java.util.EnumMap;
import java.util.Map;

public class EventResult {

    private final Map<Event, Integer> eventResult;

    public EventResult() {
        this.eventResult = new EnumMap<>(Event.class);
    }

    public void addEvent(Event event, int price) {
        eventResult.put(event, price);
    }
}
