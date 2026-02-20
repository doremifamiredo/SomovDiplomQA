package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static ru.iteco.fmhandroid.ui.data.CredHelper.faker;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsPage {
    public static final int allNewsCards = R.id.all_news_cards_block_constraint_layout;
    public static final int newsListViews = R.id.news_list_recycler_view;
    public static final ViewInteraction ctrlPnlTitle = onView(withText("Control panel"));
    public static final ViewInteraction titleNews = onView(allOf(withText("News"),
            withParent(withParent(withId(R.id.container_list_news_include)))));
    public static final ViewInteraction filterNewsBtn = onView(
            withId(R.id.filter_news_material_button));
    public static final ViewInteraction filterBtn = onView(
            withId(R.id.filter_button));
    public static final ViewInteraction filterTitle = onView(
            withId(R.id.filter_news_title_text_view));
    public static final ViewInteraction ctrlPnl = onView(
            withId(R.id.edit_news_material_button));
    public static final ViewInteraction addNewsBtn = onView(
            withId(R.id.add_news_image_view));
    public static final ViewInteraction creatingNewsTitle = onView(
            withId(R.id.custom_app_bar_title_text_view));
    public static final Matcher<View> categoryDropDown =
            withId(R.id.news_item_category_text_auto_complete_text_view);
    public static final Matcher<View> titleOfNewsField =
            withId(R.id.news_item_title_text_input_edit_text);
    public static final Matcher<View> publicDate =
            withId(R.id.news_item_publish_date_text_input_edit_text);
    public static final Matcher<View> publicTime =
            withId(R.id.news_item_publish_time_text_input_edit_text);
    public static final Matcher<View> descriptionField =
            withId(R.id.news_item_description_text_input_edit_text);
    public static final ViewInteraction saveBtn = onView(
            withId(R.id.save_button));
    public static final ViewInteraction cancelBtn = onView(
            withId(R.id.cancel_button));
    public static final ViewInteraction okBtn = onView(
            withId(android.R.id.button1));
    public static final ViewInteraction newsList = onView(
            withId(R.id.news_list_recycler_view));
    private static final ViewInteraction activeCheckBox = onView(
            withId(R.id.filter_news_inactive_material_check_box));
    private static final ViewInteraction notActiveCheckBox = onView(
            withId(R.id.filter_news_active_material_check_box));
    private static final int newsItemPublished = R.id.news_item_published_text_view;

    public enum Categories {
        Announcement("Объявление"),
        BDay("День рождения"),
        Salary("Зарплата"),
        ProfUnion("Профсоюз"),
        Fiesta("Праздник"),
        Massage("Массаж"),
        Gratitude("Благодарность"),
        NeedHelp("Нужна помощь");

        private final String ru;

        Categories(String name) {

            this.ru = name;
        }
    }

    public enum Status {
        ACTIVE("Active"),
        NOT_ACTIVE("Not active");
        String stat;

        Status(String stat) {
            this.stat = stat;
        }
    }

    public static void selectRandomCategory() {
        Allure.step("Выбираем случайную категорию");
        onView(categoryDropDown).perform(click());
        onData(anything())
                .inRoot(RootMatchers.isPlatformPopup())
                .atPosition(faker.random().nextInt(Categories.values().length))
                .perform(click());
    }

    public static String inputRandomTitle() {
        String title = faker.lorem().word();
        Allure.step("Вводим заголовок " + title);
        onView(titleOfNewsField).perform(replaceText(title), closeSoftKeyboard());
        return title;
    }

    public static void selectCurrentDate() {
        Allure.step("Выбираем текущую дату");
        onView(publicDate).perform(click());
        onView(withText("OK")).perform(click());
    }

    public static void selectCurrentTime() {
        Allure.step("Выбираем текущее время");
        onView(publicTime).perform(click());
        onView(withText("OK")).perform(click());
    }

    public static void inputRandomDescription() {
        Allure.step("Вводим сгенерированное описание");
        onView(descriptionField).perform(replaceText(
                faker.lorem().paragraph()), closeSoftKeyboard());
    }

    public static void searchNewsAndCheckIsDisplayed(String text) {
        Allure.step("Поиск новости с заголовком " + text);
        newsList.perform(scrollTo(hasDescendant(withText(text))));
        onView(allOf(
                withText(text),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
        )).check(matches(isDisplayed()));
    }

    public static void verifyDisplayedNewsStatus(Status expected) {
        List<String> actualStatuses = getNewsStatuses();

        for (String status : actualStatuses) {
            assertThat(status)
                    .isEqualTo(expected.stat);
        }

        Allure.step("Все " + actualStatuses.size() + " новостей имеют статус: " + expected.stat);
    }

    private static List<String> getNewsStatuses() {
        List<String> statuses = new ArrayList<>();

        onView(withId(newsListViews))
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return isAssignableFrom(RecyclerView.class);
                    }

                    @Override
                    public String getDescription() {
                        return "collect news statuses";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        RecyclerView recyclerView = (RecyclerView) view;

                        for (int i = 0; i < recyclerView.getChildCount(); i++) {
                            View itemView = recyclerView.getChildAt(i);
                            TextView statusView = itemView.findViewById(newsItemPublished);

                            if (statusView != null) {
                                statuses.add(statusView.getText().toString());
                            }
                        }
                    }
                });

        return statuses;
    }

    public static void selectStatus(Status status) {
        Allure.step("В фильтре новостей убираем чекбокс со статуса " + status.name());
        if (status == Status.NOT_ACTIVE) notActiveCheckBox.perform(click());
        else activeCheckBox.perform(click());
    }
}
