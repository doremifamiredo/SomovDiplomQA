package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPage {
    private static final ViewInteraction versionTitle = onView(
            withId(R.id.about_version_title_text_view));
    public static final ViewInteraction aboutBack = onView(
            withId(R.id.about_back_image_button));

    public static void checkVersionTitle() {
        Allure.step("Проверка заголовка Version на странице About");
        versionTitle.check(matches(isDisplayed()));
    }
}
