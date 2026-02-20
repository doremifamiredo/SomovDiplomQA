package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AppBar {
    private static final int appBarFragmentMain = R.id.container_custom_app_bar_include_on_fragment_main;
    private static final int authImgBtn = R.id.authorization_image_button;
    private static final ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    private static final ViewInteraction butterflyButton = onView(withId(R.id.our_mission_image_button));

    public static int getAppBarFragmentMain() {
        return appBarFragmentMain;
    }

    public static int getAuthImgBtn() {
        return authImgBtn;
    }

    public enum Buttons {
        Main,
        News,
        About;
    }

    public static void logout() {
        Allure.step("Выход из приложения");
        ViewInteraction buttonProfile = onView(withId(authImgBtn));
        buttonProfile.perform(click());
        ViewInteraction logOut = onView(withText("Log out"));
        logOut.check(matches(isDisplayed())).perform(click());
    }

    public static boolean isUserAuthorized() {
        Allure.step("Проверка авторизации пользователz");
        try {
            final boolean[] isVisible = {false};
            onView(withId(authImgBtn))
                    .check((view, noViewFoundException) -> {
                        if (noViewFoundException != null) {
                            throw noViewFoundException;
                        }
                        assertThat(view.getVisibility())
                                .isEqualTo(View.VISIBLE);
                        isVisible[0] = true;
                    });

            return isVisible[0];
        } catch (NoMatchingViewException | AssertionError e) {
            return false;
        }
    }

    public static void clickMenu(Buttons button) {
        Allure.step("Переход на страницу " + button.name());
        menuButton.perform(click());
        onView(withText(button.name())).perform(click());
    }

    public static void clickButterfly() {
        Allure.step("Клик по бабочке");
        butterflyButton.perform(click());
    }
}
