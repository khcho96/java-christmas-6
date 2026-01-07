package christmas.view;

import christmas.constant.Event;
import christmas.constant.Menu;
import christmas.domain.Customer;
import christmas.domain.EventResult;
import christmas.dto.Result;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void print() {
    }

    public static void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printResult(Result result) {
        Customer customer = result.customer();
        EventResult eventResult = result.eventResult();

        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n", customer.getDay());

        System.out.println("<주문 메뉴>");
        Map<Menu, Integer> orderMenus = customer.getOrderMenus();
        for (Menu menu : orderMenus.keySet()) {
            System.out.printf("%s %d개", menu.getName(), orderMenus.get(menu));
        }
        System.out.println();

        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n\n", customer.getTotalPrice());

        System.out.println("<증정 메뉴>");
        System.out.printf("%s\n\n", eventResult.getPresentEvent());

        System.out.println("<혜택 내역>");
        Map<Event, Integer> events = eventResult.getEventResult();
        for (Event event : events.keySet()) {
            if (event.equals(Event.NONE)) {
                System.out.println("없음\n");
                continue;
            }
            System.out.printf("%s: -%,d원\n", event.getName(), events.get(event));
        }
        System.out.println();

        System.out.println("<총혜택 금액>");
        System.out.printf("-%,d원\n\n", eventResult.getTotalEventPrice());

        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n\n", customer.getTotalPrice() - eventResult.getTotalEventPriceWithoutPresent());

        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventResult.getBadge());
    }
}
