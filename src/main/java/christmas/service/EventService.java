package christmas.service;

import christmas.domain.OrderDate;
import christmas.dto.EventDto;

public class EventService {

    // TODO: 도메인 객체 인스턴스 변수로 저장
    private OrderDate orderDate;

    public void registerDate(int date) {
        orderDate = OrderDate.from(date);
    }

    public EventDto calculateEvent() {
        return null;
    }
}
