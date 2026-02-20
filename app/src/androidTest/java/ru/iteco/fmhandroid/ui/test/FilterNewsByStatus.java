package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.data.Utils.checkAndClick;
import static ru.iteco.fmhandroid.ui.data.Utils.checkTitle;
import static ru.iteco.fmhandroid.ui.data.Utils.waitDisplayed;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.Status;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.ctrlPnl;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.filterBtn;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.filterNewsBtn;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.filterTitle;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.selectStatus;
import static ru.iteco.fmhandroid.ui.pageObject.NewsPage.verifyDisplayedNewsStatus;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Utils;
import ru.iteco.fmhandroid.ui.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@RunWith(Parameterized.class)
public class FilterNewsByStatus {

    private Status status;

    public FilterNewsByStatus(Status status) {
        this.status = status;
    }

    @Parameterized.Parameters
    public static Iterable<Status> data() {
        return Arrays.asList(Status.values());
    }

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        waitDisplayed(AppBar.getAppBarFragmentMain());
    }

    @Test
    public void filterNewsByStatusTest() {

        Log.d("RUNNER_CHECK", InstrumentationRegistry.getInstrumentation().getClass().getName());
        AppBar.clickMenu(AppBar.Buttons.News);
        checkAndClick(ctrlPnl, "Переходим в панель управления новостями");
        checkAndClick(filterNewsBtn, "Нажимаем фильтр новостей");
        checkTitle(filterTitle, "Filter news");
        selectStatus(status);
        checkAndClick(filterBtn, "Выполняем фильтрацию");
        Utils.waitDisplayed(NewsPage.newsListViews);
        verifyDisplayedNewsStatus(status);
    }
}
