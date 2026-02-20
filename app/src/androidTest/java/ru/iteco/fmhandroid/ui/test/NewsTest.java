package ru.iteco.fmhandroid.ui.test;


import static ru.iteco.fmhandroid.ui.data.Utils.checkAndClick;
import static ru.iteco.fmhandroid.ui.data.Utils.checkTitle;
import static ru.iteco.fmhandroid.ui.data.Utils.waitDisplayed;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.addNewsBtn;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.cancelBtn;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.creatingNewsTitle;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.ctrlPnl;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.ctrlPnlTitle;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.filterNewsBtn;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.filterTitle;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.inputRandomDescription;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.inputRandomTitle;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.okBtn;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.saveBtn;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.searchNewsAndCheckIsDisplayed;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.selectCurrentDate;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.selectCurrentTime;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.selectRandomCategory;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageObject.AppBar;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        waitDisplayed(AppBar.getAppBarFragmentMain());
        AppBar.clickMenu(AppBar.Buttons.News);
    }

    @Test
    public void cancelFilter() {
        checkAndClick(filterNewsBtn, "Нажимаем фильтр новостей");
        checkTitle(filterTitle, "Filter news");
        checkAndClick(cancelBtn, "Нажимаем отмену фильтрации");
    }

    @Test
    public void createNews() {
        checkAndClick(ctrlPnl, "Переходим в панель управления новостями");
        checkAndClick(addNewsBtn, "Нажимаем кнопку добавления новости");
        checkTitle(creatingNewsTitle, "Creating");
        selectRandomCategory();
        String title = inputRandomTitle();
        selectCurrentDate();
        selectCurrentTime();
        inputRandomDescription();
        checkAndClick(saveBtn, "Нажимаем кнопку Save");
        searchNewsAndCheckIsDisplayed(title);
    }

    @Test
    public void cancelNewsCreation() {
        checkAndClick(ctrlPnl, "Переходим в панель управления новостями");
        checkAndClick(addNewsBtn, "Нажимаем кнопку добавления новости");
        checkTitle(creatingNewsTitle, "Creating");
        checkAndClick(cancelBtn, "Нажимаем кнопку Cancel");
        checkAndClick(okBtn, "Нажимаем кнопку Ok");
        checkTitle(ctrlPnlTitle, "Control panel");
    }
}
