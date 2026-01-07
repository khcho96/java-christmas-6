package christmas.util;

import christmas.view.OutputView;
import java.util.function.Supplier;

public final class Retry {

    private Retry() {}

    public static <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    public static void retryUntilSuccess(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}
