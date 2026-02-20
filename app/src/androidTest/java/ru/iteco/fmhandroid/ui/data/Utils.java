package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;

public class Utils {
    static int waitMillis = 15000;

    public static void waitDisplayed(final int viewId) {
        Espresso.onView(isRoot()).perform(getViewAction(viewId));
    }

    private static ViewAction getViewAction(final int viewId) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> to be displayed during " + waitMillis + " millis.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                performWaitDisplayed(uiController, view, viewId, getDescription());
            }
        };
    }

    private static void performWaitDisplayed(UiController uiController, View view, int viewId, String description) {
        uiController.loopMainThreadUntilIdle();
        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + waitMillis;
        do {
            for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                if (child.getId() == viewId && child.isShown()) {
                    return;
                }
            }
            uiController.loopMainThreadForAtLeast(50);
        } while (System.currentTimeMillis() < endTime);

        throw new PerformException.Builder()
                .withActionDescription(description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(new TimeoutException())
                .build();
    }

    public static void checkTitle(ViewInteraction view, String text) {
        Allure.step("Проверка заголовка " + text);
        view.check(matches(withText(text)));
    }

    public static void checkAndClick(ViewInteraction view, String step) {
        Allure.step(step);
        view.check(matches(isDisplayed()));
        view.perform(click());
    }
}
