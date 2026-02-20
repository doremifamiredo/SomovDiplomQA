package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class LoveIsAllPage {
    public static final ViewInteraction missionTitle = onView(
            withId(R.id.our_mission_title_text_view));

}
