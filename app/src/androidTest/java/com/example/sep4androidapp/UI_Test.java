package com.example.sep4androidapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.sep4androidapp.Firebase.Firebase_Login;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UI_Test {

    @Rule
    public ActivityTestRule< Firebase_Login > mActivityTestRule = new ActivityTestRule<>(Firebase_Login.class);

    @Test
    public void uI_Test() {
        ViewInteraction supportVectorDrawablesButton = onView(
                allOf(withId(R.id.email_button), withText("Sign in with email"),
                        childAtPosition(
                                allOf(withId(R.id.btn_holder),
                                        childAtPosition(
                                                withId(R.id.container),
                                                0)),
                                0)));
        supportVectorDrawablesButton.perform(scrollTo(), click());

        ViewInteraction textInputLayout = onView(
                allOf(withId(R.id.email_layout),
                        childAtPosition(
                                allOf(withId(R.id.email_top_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                1)));
        textInputLayout.perform(scrollTo(), click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_layout),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("email@email.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_next), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.email_top_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button_done), withText("Sign in"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.password_layout),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("password"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button_done), withText("Sign in"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.randomFactButton),
                        childAtPosition(
                                withParent(withId(R.id.viewpager_id)),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0)));
        appCompatSpinner.perform(scrollTo(), click());

        ViewInteraction switch_ = onView(
                allOf(withId(R.id.deviceSwitch),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        switch_.perform(scrollTo(), click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription("Temp"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout_id),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("Hum"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout_id),
                                        0),
                                2),
                        isDisplayed()));
        tabView2.perform(click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("CO2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout_id),
                                        0),
                                3),
                        isDisplayed()));
        tabView3.perform(click());

        ViewInteraction tabView4 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tablayout_id),
                                        0),
                                0),
                        isDisplayed()));
        tabView4.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0)));
        appCompatSpinner2.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("open"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction spinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));

        ViewInteraction switch_2 = onView(
                allOf(withId(R.id.deviceSwitch),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        switch_2.check(matches(isDisplayed()));

        ViewInteraction switch_3 = onView(
                allOf(withId(R.id.deviceSwitch),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        switch_3.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.randomFactButton),
                        childAtPosition(
                                withParent(withId(R.id.viewpager_id)),
                                1),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("TEMP"),
                        childAtPosition(
                                allOf(withContentDescription("Temp"),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("TEMP")));

        ViewInteraction textView2 = onView(
                allOf(withText("TEMP"),
                        childAtPosition(
                                allOf(withContentDescription("Temp"),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withText("HUM"),
                        childAtPosition(
                                allOf(withContentDescription("Hum"),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                2)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("HUM")));

        ViewInteraction textView4 = onView(
                allOf(withText("HUM"),
                        childAtPosition(
                                allOf(withContentDescription("Hum"),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                2)),
                                0),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction textView5 = onView(
                allOf(withText("CO2"),
                        childAtPosition(
                                allOf(withContentDescription("CO2"),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                3)),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("CO2")));

        ViewInteraction textView6 = onView(
                allOf(withText("CO2"),
                        childAtPosition(
                                allOf(withContentDescription("CO2"),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                3)),
                                0),
                        isDisplayed()));
        textView6.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("open"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.reportLastWeekRadioButton), withText("Last Week"),
                        childAtPosition(
                                allOf(withId(R.id.lastReportRadioGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.twojaStara),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatSpinner3.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.lastSleepRadioButton), withText("Last sleep"),
                        childAtPosition(
                                allOf(withId(R.id.lastReportRadioGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatRadioButton2.perform(click());

        ViewInteraction appCompatRadioButton3 = onView(
                allOf(withId(R.id.reportLastWeekRadioButton), withText("Last Week"),
                        childAtPosition(
                                allOf(withId(R.id.lastReportRadioGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatRadioButton3.perform(click());

        ViewInteraction appCompatRadioButton4 = onView(
                allOf(withId(R.id.reportLastMonthRadioButton), withText("Last Month"),
                        childAtPosition(
                                allOf(withId(R.id.lastReportRadioGroup),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatRadioButton4.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("open"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction navigationMenuItemView2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView2.perform(click());

        ViewInteraction switch_4 = onView(
                allOf(withId(R.id.roomsSwitch),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()));
        switch_4.perform(click());

        ViewInteraction switch_5 = onView(
                allOf(withId(R.id.roomsSwitch),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()));
        switch_5.perform(click());

        ViewInteraction switch_6 = onView(
                allOf(withId(R.id.roomsSwitch),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                0),
                        isDisplayed()));
        switch_6.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.leadToSetUpButton),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.fragment_container),
                                                0)),
                                1),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.deviceId),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("fake_device9"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.newRoomName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.newRoomName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("n"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.deviceId), withText("fake_device9"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.deviceId), withText("fake_device9"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.deviceId), withText("fake_device9"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("fake_device"));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.deviceId), withText("fake_device"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText7.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.deviceId), withText("fake_device"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText8.perform(click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.newRoomName), withText("n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("new"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.newRoomName), withText("new"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.deviceId), withText("fake_device"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("fake_device5"));

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.deviceId), withText("fake_device5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText12.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.setupDevice), withText("Create device"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0)));
        appCompatSpinner4.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatCheckedTextView3.perform(click());

        ViewInteraction switch_7 = onView(
                allOf(withId(R.id.deviceSwitch),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        switch_7.perform(scrollTo(), click());

        ViewInteraction textView7 = onView(
                allOf(withId(android.R.id.text1), withText("new"),
                        childAtPosition(
                                allOf(withId(R.id.spinner),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("new")));

        ViewInteraction textView8 = onView(
                allOf(withId(android.R.id.text1), withText("new"),
                        childAtPosition(
                                allOf(withId(R.id.spinner),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView8.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("open"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction navigationMenuItemView3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        4),
                        isDisplayed()));
        navigationMenuItemView3.perform(click());

        ViewInteraction spinner2 = onView(
                allOf(withId(R.id.preferencesSpinner),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(androidx.appcompat.widget.LinearLayoutCompat.class),
                                        0),
                                0),
                        isDisplayed()));
        spinner2.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.temperatureImage),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.tempText), withText("Temperature"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("Temperature")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.tempText), withText("Temperature"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView10.check(matches(isDisplayed()));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.TempMinTextView), withText("Minimum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView11.check(matches(withText("Minimum:")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.TempMinTextView), withText("Minimum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView12.check(matches(isDisplayed()));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.TempMaxTextView), withText("Maximum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                4),
                        isDisplayed()));
        textView13.check(matches(withText("Maximum:")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.TempMaxTextView), withText("Maximum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                4),
                        isDisplayed()));
        textView14.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.minTempEditText), withText("17.0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                3),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.humText), withText("Humidity"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView15.check(matches(withText("Humidity")));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.humText), withText("Humidity"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView16.check(matches(isDisplayed()));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.HumMinTextView), withText("Minimum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView17.check(matches(withText("Minimum:")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.HumMinTextView), withText("Minimum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView18.check(matches(isDisplayed()));

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.HemMaxTextView), withText("Maximum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                4),
                        isDisplayed()));
        textView19.check(matches(withText("Maximum:")));

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.HemMaxTextView), withText("Maximum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                4),
                        isDisplayed()));
        textView20.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.co2Image),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView21 = onView(
                allOf(withId(R.id.co2Text), withText("CO2"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView21.check(matches(withText("CO2")));

        ViewInteraction textView22 = onView(
                allOf(withId(R.id.co2Text), withText("CO2"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView22.check(matches(isDisplayed()));

        ViewInteraction textView23 = onView(
                allOf(withId(R.id.Co2MinTextView), withText("Minimum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView23.check(matches(withText("Minimum:")));

        ViewInteraction textView24 = onView(
                allOf(withId(R.id.Co2MinTextView), withText("Minimum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView24.check(matches(isDisplayed()));

        ViewInteraction textView25 = onView(
                allOf(withId(R.id.Co2MaxTextView), withText("Maximum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                4),
                        isDisplayed()));
        textView25.check(matches(withText("Maximum:")));

        ViewInteraction textView26 = onView(
                allOf(withId(R.id.Co2MaxTextView), withText("Maximum:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                4),
                        isDisplayed()));
        textView26.check(matches(isDisplayed()));

        ViewInteraction frameLayout = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.fragment_container),
                                0),
                        3),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction viewGroup = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.< View >instanceOf(androidx.appcompat.widget.LinearLayoutCompat.class),
                                2),
                        0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction appCompatSpinner5 = onView(
                allOf(withId(R.id.preferencesSpinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.LinearLayoutCompat")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatSpinner5.perform(click());

        DataInteraction appCompatCheckedTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView4.perform(click());

        ViewInteraction appCompatSpinner6 = onView(
                allOf(withId(R.id.preferencesSpinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.LinearLayoutCompat")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatSpinner6.perform(click());

        DataInteraction appCompatCheckedTextView5 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatCheckedTextView5.perform(click());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.minTempEditText), withText("17.0"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText13.perform(replaceText("0"));

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.minTempEditText), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText14.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.MaxTempEditText), withText("22.0"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText15.perform(replaceText("2"));

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.MaxTempEditText), withText("2"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText16.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.minHumEditText), withText("20"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText17.perform(replaceText("30"));

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.minHumEditText), withText("30"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText18.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.MaxHumEditText), withText("50"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText19.perform(replaceText("40"));

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.MaxHumEditText), withText("40"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText20.perform(closeSoftKeyboard());

        pressBack();

        pressBack();

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.MaxCo2EditText), withText("600"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText21.perform(replaceText("900"));

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.MaxCo2EditText), withText("900"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText22.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.buttonSave), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.LinearLayoutCompat")),
                                        4),
                                0),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatSpinner7 = onView(
                allOf(withId(R.id.preferencesSpinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.LinearLayoutCompat")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatSpinner7.perform(click());

        DataInteraction appCompatCheckedTextView6 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatCheckedTextView6.perform(click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.minTempEditText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText23.perform(replaceText("10"), closeSoftKeyboard());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.MaxTempEditText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText24.perform(replaceText("12"), closeSoftKeyboard());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.minHumEditText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText25.perform(replaceText("40"), closeSoftKeyboard());

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.MaxHumEditText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText26.perform(replaceText("50"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.MaxCo2EditText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText27.perform(replaceText("900"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.buttonSave), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.LinearLayoutCompat")),
                                        4),
                                0),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatSpinner8 = onView(
                allOf(withId(R.id.preferencesSpinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.LinearLayoutCompat")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatSpinner8.perform(click());

        DataInteraction appCompatCheckedTextView7 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView7.perform(click());

        ViewInteraction appCompatSpinner9 = onView(
                allOf(withId(R.id.preferencesSpinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.LinearLayoutCompat")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatSpinner9.perform(click());

        DataInteraction appCompatCheckedTextView8 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatCheckedTextView8.perform(click());

        ViewInteraction appCompatSpinner10 = onView(
                allOf(withId(R.id.preferencesSpinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.appcompat.widget.LinearLayoutCompat")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatSpinner10.perform(click());

        DataInteraction appCompatCheckedTextView9 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView9.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("open"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction navigationMenuItemView4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        5),
                        isDisplayed()));
        navigationMenuItemView4.perform(click());

        ViewInteraction textView27 = onView(
                allOf(withId(R.id.textView), withText("Welcome! Please enter your device id, and the name of the room, where you have placed your device!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        textView27.check(matches(withText("Welcome! Please enter your device id, and the name of the room, where you have placed your device!")));

        ViewInteraction textView28 = onView(
                allOf(withId(R.id.textView), withText("Welcome! Please enter your device id, and the name of the room, where you have placed your device!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        textView28.check(matches(isDisplayed()));

        ViewInteraction textView29 = onView(
                allOf(withId(R.id.deviceIdtext), withText("Device id:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView29.check(matches(withText("Device id:")));

        ViewInteraction textView30 = onView(
                allOf(withId(R.id.deviceIdtext), withText("Device id:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView30.check(matches(isDisplayed()));

        ViewInteraction textView31 = onView(
                allOf(withId(R.id.newRoomtext), withText("New room's name:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        textView31.check(matches(withText("New room's name:")));

        ViewInteraction textView32 = onView(
                allOf(withId(R.id.newRoomtext), withText("New room's name:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        textView32.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.setupDevice),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView33 = onView(
                allOf(withId(R.id.availableDevices), withText("fake_device6\nfake_device7\nfake_device8\n"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.view.ViewGroup.class),
                                        6),
                                1),
                        isDisplayed()));
        textView33.check(matches(withText("fake_device6 fake_device7 fake_device8 ")));

        ViewInteraction textView34 = onView(
                allOf(withId(R.id.textView2), withText("Currently available devices"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.view.ViewGroup.class),
                                        6),
                                0),
                        isDisplayed()));
        textView34.check(matches(withText("Currently available devices")));

        ViewInteraction textView35 = onView(
                allOf(withId(R.id.textView2), withText("Currently available devices"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.view.ViewGroup.class),
                                        6),
                                0),
                        isDisplayed()));
        textView35.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("open"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction navigationMenuItemView5 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        6),
                        isDisplayed()));
        navigationMenuItemView5.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.factRecyclerView),
                        childAtPosition(
                                withId(R.id.nestedScrollView),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction viewGroup2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.custom),
                                childAtPosition(
                                        withId(R.id.customPanel),
                                        0)),
                        0),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));

        ViewInteraction textView36 = onView(
                allOf(withId(R.id.factsTipsTextView), withText("Tips AND Facts"),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.fragment_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView36.check(matches(withText("Tips AND Facts")));

        ViewInteraction viewGroup3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.factRecyclerView),
                                0),
                        0),
                        isDisplayed()));
        viewGroup3.check(matches(isDisplayed()));

        ViewInteraction scrollView = onView(
                allOf(withId(R.id.nestedScrollView),
                        childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        childAtPosition(
                                                withId(R.id.fragment_container),
                                                0)),
                                1),
                        isDisplayed()));
        scrollView.check(matches(isDisplayed()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.factRecyclerView),
                        childAtPosition(
                                allOf(withId(R.id.nestedScrollView),
                                        childAtPosition(
                                                withId(R.id.constraintLayout),
                                                1)),
                                0),
                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));

        ViewInteraction viewGroup4 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.factRecyclerView),
                                0),
                        0),
                        isDisplayed()));
        viewGroup4.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withContentDescription("open"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction navigationMenuItemView6 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        7),
                        isDisplayed()));
        navigationMenuItemView6.perform(click());

        ViewInteraction appCompatSpinner11 = onView(
                allOf(withId(R.id.deviceSpinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0)));
        appCompatSpinner11.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView10 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatCheckedTextView10.perform(click());

        ViewInteraction appCompatSpinner12 = onView(
                allOf(withId(R.id.spinnerSleepData),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner12.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView11 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView11.perform(click());

        ViewInteraction appCompatSpinner13 = onView(
                allOf(withId(R.id.spinnerSleepData),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner13.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView12 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView12.perform(click());

        ViewInteraction appCompatSpinner14 = onView(
                allOf(withId(R.id.spinnerSleepData),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner14.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView13 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(3);
        appCompatCheckedTextView13.perform(click());

        ViewInteraction appCompatSpinner15 = onView(
                allOf(withId(R.id.spinnerSleepData),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner15.perform(scrollTo(), click());

        ViewInteraction listView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                0),
                        0),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));

        ViewInteraction spinner3 = onView(
                allOf(withId(R.id.deviceSpinner),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        spinner3.check(matches(isDisplayed()));

        ViewInteraction textView37 = onView(
                allOf(withId(android.R.id.text1), withText("2020/6/2-2020/6/2"),
                        childAtPosition(
                                allOf(withId(R.id.sleepSpinner),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView37.check(matches(isDisplayed()));

        ViewInteraction textView38 = onView(
                allOf(withId(android.R.id.text1), withText("2020/6/2-2020/6/2"),
                        childAtPosition(
                                allOf(withId(R.id.sleepSpinner),
                                        childAtPosition(
                                                IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView38.check(matches(withText("2020/6/2-2020/6/2")));

        ViewInteraction textView39 = onView(
                allOf(withId(R.id.averageTemperature), withText("Average temperature:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView39.check(matches(withText("Average temperature:")));

        ViewInteraction textView40 = onView(
                allOf(withId(R.id.averageHumidity), withText("Average humidity:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView40.check(matches(withText("Average humidity:")));

        ViewInteraction textView41 = onView(
                allOf(withId(R.id.averageHumidity), withText("Average humidity:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView41.check(matches(isDisplayed()));

        ViewInteraction viewGroup5 = onView(
                allOf(withId(R.id.lineChart),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.LinearLayout.class),
                                        3),
                                0),
                        isDisplayed()));
        viewGroup5.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withContentDescription("open"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

        ViewInteraction navigationMenuItemView7 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        1),
                        isDisplayed()));
        navigationMenuItemView7.perform(click());

        ViewInteraction textView42 = onView(
                allOf(withId(R.id.tvCurrentTemperature), withText("Temperature"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView42.check(matches(withText("Temperature")));

        ViewInteraction textView43 = onView(
                allOf(withId(R.id.tvCurrentCo2), withText("CO2"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView43.check(matches(withText("CO2")));

        ViewInteraction textView44 = onView(
                allOf(withId(R.id.tvExpectedCo2), withText("Expected:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView44.check(matches(withText("Expected:")));

        ViewInteraction textView45 = onView(
                allOf(withId(R.id.tvExpectedCo2), withText("Expected:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView45.check(matches(isDisplayed()));

        ViewInteraction textView46 = onView(
                allOf(withId(R.id.tvExpectedHumidity), withText("Expected:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView46.check(matches(withText("Expected:")));

        ViewInteraction textView47 = onView(
                allOf(withId(R.id.tvExpectedHumidity), withText("Expected:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.< View >instanceOf(android.widget.FrameLayout.class),
                                        0),
                                2),
                        isDisplayed()));
        textView47.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.randomFactButton),
                        childAtPosition(
                                withParent(withId(R.id.viewpager_id)),
                                1),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.randomFactButton),
                        childAtPosition(
                                withParent(withId(R.id.viewpager_id)),
                                1),
                        isDisplayed()));
        imageButton4.check(doesNotExist());

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.randomFactButton),
                        childAtPosition(
                                withParent(withId(R.id.viewpager_id)),
                                1),
                        isDisplayed()));
        imageButton5.check(doesNotExist());
    }

    private static Matcher< View > childAtPosition(
            final Matcher< View > parentMatcher, final int position) {

        return new TypeSafeMatcher< View >() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
