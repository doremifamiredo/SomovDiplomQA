package ru.iteco.fmhandroid.ui.test;

import static org.junit.Assert.assertTrue;
import static ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage.login;
import static ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage.textViewAuth;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.CredHelper;
import ru.iteco.fmhandroid.ui.data.Utils;
import ru.iteco.fmhandroid.ui.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Utils.waitDisplayed(AppBar.getAppBarFragmentMain());
    }

    @Test
    public void successfulAuthorization() {
        if (AppBar.isUserAuthorized()) AppBar.logout();
        Utils.checkTitle(textViewAuth, "Authorization");
        login(CredHelper.getValidCred());
        Utils.waitDisplayed(AppBar.getAuthImgBtn());
        assertTrue(AppBar.isUserAuthorized());
    }

    @Test
    public void unsuccessfulAuthorization() {
        if (AppBar.isUserAuthorized()) AppBar.logout();
        Utils.checkTitle(textViewAuth, "Authorization");
        login(CredHelper.getInvalidCred());
        Utils.checkTitle(textViewAuth, "Authorization");
    }

    @Test
    public void logout() {
        if (!AppBar.isUserAuthorized()) AuthorizationPage.login(CredHelper.getValidCred());
        AppBar.logout();
        Utils.checkTitle(textViewAuth, "Authorization");
    }
}
