package ru.iteco.fmhandroid.ui.test;


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
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Utils.waitDisplayed(AppBar.getAppBarFragmentMain());
    }

    @Test
    public void allNews() {
        if (!AppBar.isUserAuthorized()) AuthorizationPage.login(CredHelper.getValidCred());
        Utils.checkAndClick(MainPage.allNews, "Нажатие на кнопку All news");
        Utils.waitDisplayed(NewsPage.allNewsCards);
        Utils.checkTitle(NewsPage.titleNews, "News");
    }
}
