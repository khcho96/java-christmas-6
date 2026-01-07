package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.constant.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 정상_작동_테스트_1() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains(
                    "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n",
                    "<주문 메뉴>\n"
                            + "타파스 1개\n"
                            + "제로콜라 1개",

                    "<할인 전 총주문 금액>\n"
                            + "8,500원\n",

                    "<증정 메뉴>\n"
                            + "없음",

                    "<혜택 내역>\n"
                            + "없음",

                    "<총혜택 금액>\n"
                            + "0원\n",

                    "<할인 후 예상 결제 금액>\n"
                            + "8,500원\n",

                    "<12월 이벤트 배지>\n"
                            + "없음"
            );
        });
    }

    @Test
    void 정상_작동_테스트_2() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n",
                    "<주문 메뉴>\n"
                            + "티본스테이크 1개\n"
                            + "바비큐립 1개\n"
                            + "초코케이크 2개\n"
                            + "제로콜라 1개\n",

                    "<할인 전 총주문 금액>\n"
                            + "142,000원\n",

                    "<증정 메뉴>\n"
                            + "샴페인 1개\n",

                    "<혜택 내역>\n"
                            + "크리스마스 디데이 할인: -1,200원\n"
                            + "평일 할인: -4,046원\n"
                            + "특별 할인: -1,000원\n"
                            + "증정 이벤트: -25,000원\n",

                    "<총혜택 금액>\n"
                            + "-31,246원\n",

                    "<할인 후 예상 결제 금액>\n"
                            + "135,754원\n",

                    "<12월 이벤트 배지>\n"
                            + "산타"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "a"})
    void 날짜_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains(ErrorMessage.DAY_FORMAT_ERROR.getErrorMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스 1,제로콜라 1", "타파스-0,제로콜라-1", "타파스-1,제로콜라-1,타파스-2", "알리오올리오-5"})
    void 주문_형식_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains(ErrorMessage.MENU_FORMAT_ERROR.getErrorMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-10,제로콜라-11"})
    void 주문_개수_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains(ErrorMessage.MENU_MAX_ERROR.getErrorMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1"})
    void 주문_음료만_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException("3", input);
            assertThat(output()).contains(ErrorMessage.ONLY_DRINK_ERROR.getErrorMessage());
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
