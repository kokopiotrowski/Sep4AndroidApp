package com.example.sep4androidapp;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.sep4androidapp.Firebase.Firebase_Login;
import com.example.sep4androidapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FirstFragment {

    @Rule
    public ActivityTestRule< Firebase_Login > mActivityTestRule = new ActivityTestRule<>(Firebase_Login.class);

    @Test
    public void uI_test() {
        ViewInteraction supportVectorDrawablesButton = onView(
                allOf(withId(R.id.email_button), withText("Sign in with email"), isDisplayed()));
        supportVectorDrawablesButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.email), isDisplayed()));
        textInputEditText.perform(replaceText("email@email.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_next), withText("Next"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        textInputEditText2.perform(replaceText("password"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button_done), withText("Sign in"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription("Temp"), isDisplayed()));
        tabView.perform(click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("Hum"), isDisplayed()));
        tabView2.perform(click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("CO2"), isDisplayed()));
        tabView3.perform(click());
    }
}
