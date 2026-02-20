package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.CredHelper;

public class AuthorizationPage {

    private enum Fields {
        Login,
        Password;
    }

    private final static ViewInteraction buttonSingIn = onView(withId(R.id.enter_button));
    public final static ViewInteraction textViewAuth = onView(withText("Authorization"));

    private static void inputInField(Fields field, String value) {
        Allure.step("Ввод в поле " + field.name());
        ViewInteraction inputInFieldLogin = onView(withHint(field.name()));
        inputInFieldLogin.perform(replaceText(value));
        closeSoftKeyboard();
    }

    private static void pressButton() {
        Allure.step("Нажатие на кнопку входа");
        buttonSingIn.check(matches(isDisplayed()));
        buttonSingIn.perform(click());
    }

    public static void login(CredHelper.Credentials cred) {
        inputInField(Fields.Login, cred.login);
        inputInField(Fields.Password, cred.password);
        pressButton();
    }
}
