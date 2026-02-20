package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.data.Utils.checkAndClick;

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
import ru.iteco.fmhandroid.ui.pageObject.AboutPage;
import ru.iteco.fmhandroid.ui.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.LoveIsAllPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavigatingTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Utils.waitDisplayed(AppBar.getAppBarFragmentMain());
        if (!AppBar.isUserAuthorized()) AuthorizationPage.login(CredHelper.getValidCred());
    }

    @Test
    public void goToNews() {
        AppBar.clickMenu(AppBar.Buttons.News);
        Utils.checkTitle(NewsPage.titleNews, "News");
    }

    @Test
    public void goFromNewsToMain() {
        AppBar.clickMenu(AppBar.Buttons.News);
        AppBar.clickMenu(AppBar.Buttons.Main);
        MainPage.checkMainNews();
    }

    @Test
    public void goFromMainToAbout() {
        AppBar.clickMenu(AppBar.Buttons.About);
        AboutPage.checkVersionTitle();
    }

    @Test
    public void goFromAboutToMain() {
        AppBar.clickMenu(AppBar.Buttons.About);
        checkAndClick(AboutPage.aboutBack, "Нажимаем кнопку Back");
        MainPage.checkMainNews();
    }

    @Test
    public void goFromNewsToAbout() {
        AppBar.clickMenu(AppBar.Buttons.News);
        AppBar.clickMenu(AppBar.Buttons.About);
        AboutPage.checkVersionTitle();
    }

    @Test
    public void goToLoveIsAll() {
        AppBar.clickButterfly();
        Utils.checkTitle(LoveIsAllPage.missionTitle, "Love is all");
    }
}
