package christmas.controller;

import christmas.dto.Result;
import christmas.service.ChristmasService;
import christmas.util.InputParser;
import christmas.util.Retry;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasController {

    private final ChristmasService christmasService;

    public ChristmasController(ChristmasService christmasService) {
        this.christmasService = christmasService;
    }

    public void run() {
        OutputView.printStartMessage();

        int day = Retry.retryUntilSuccess(() ->
                InputParser.parseDay(InputView.readDay())
        );

        Retry.retryUntilSuccess(() -> {
            Map<String, Integer> orderMenus = InputParser.parseMenus(InputView.readMenus());
            christmasService.registerCustomer(day, orderMenus);
        });

        Result result = christmasService.calculateResult();
    }
}

