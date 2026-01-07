package christmas.domain;

import christmas.constant.Badge;
import christmas.constant.Event;
import java.util.EnumMap;
import java.util.Map;

public class EventResult {

    private final Map<Event, Integer> eventResult;

    public EventResult() {
        this.eventResult = new EnumMap<>(Event.class);
    }

    public EventResult(Event event) {
        this.eventResult = new EnumMap<>(Event.class);
        eventResult.put(event, 0);
    }

    public void addEvent(Event event, int price) {
        eventResult.put(event, price);
    }

    public String getPresentEvent() {
        if (eventResult.containsKey(Event.PRESENT)) {
            return "샴페인 1개";
        }
        return "없음";
    }

    public Map<Event, Integer> getEventResult() {
        return eventResult;
    }

    public int getTotalEventPrice() {
        return eventResult.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalEventPriceWithoutPresent() {
        return eventResult.keySet().stream()
                .filter(event -> !event.equals(Event.PRESENT))
                .map(eventResult::get)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public String getBadge() {
        return Badge.from(getTotalEventPrice()).getName();
    }
}
