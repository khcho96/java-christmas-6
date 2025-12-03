package christmas.view;

import christmas.dto.EventDto;
import christmas.dto.OrderedMenuDto;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String XXX_MESSAGE = "";

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printResult(OrderedMenuDto orderedMenuDto, EventDto eventDto) {
        // TODO: DTO 에서 값 가져오기
        System.out.println();
        // TODO: 결과 출력
    }


    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
