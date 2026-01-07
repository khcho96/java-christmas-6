package christmas;

import christmas.controller.ChristmasController;
import christmas.service.ChristmasService;

public class Application {
    public static void main(String[] args) {
        ChristmasService christmasService = new ChristmasService();
        ChristmasController christmasController = new ChristmasController(christmasService);
        christmasController.run();
    }
}
