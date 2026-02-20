package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {
    public static final ViewInteraction allNews = onView(withId(R.id.all_news_text_view));

    private static final ViewInteraction fragmentMain = onView(withId(
            R.id.container_list_news_include_on_fragment_main));

    public static void clickAllNews() {
        Allure.step("Нажатие на кнопку 'Все новости'");
        allNews.check(matches(isDisplayed()));
        allNews.perform(click());
    }

    public static void checkMainNews() {
        Allure.step("Проверка видимости новостей на главной странице");
        fragmentMain.check(matches(isDisplayed()));
    }
}