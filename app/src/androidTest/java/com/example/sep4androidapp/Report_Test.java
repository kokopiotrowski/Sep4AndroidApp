package com.example.sep4androidapp;


import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.sep4androidapp.Firebase.Firebase_Login;
import com.example.sep4androidapp.MainActivity;
import com.example.sep4androidapp.R;
import com.google.android.material.navigation.NavigationView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Report_Test {

    @Rule
    public ActivityTestRule< Firebase_Login > mActivityTestRule = new ActivityTestRule<>(Firebase_Login.class);

    @Test
    public void report_Test() {
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

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("open"), isDisplayed()));
        appCompatImageButton.perform(click());

        //onView(allOf(withId(R.id.nav_view),isDisplayed())).perform(click());
        /*
        onView(allOf(withId(R.id.nav_view),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(withId(R.id.itemReports)));

        onView(allOf(withId(R.id.nav_view), isDisplayed())).perform(swipeUp());


                onView(
                allOf(withClassName(is("com.google.android.material.internal.NavigationMenuItemView")), isDisplayed()));
        navigationMenuItemView.perform(click());


        ViewInteraction navigationMenuItemView = onView(allOf(withId(R.id.nav_view), withEffectiveVisibility(VISIBLE))).perform(click());

        onView(allOf(withId(R.id.nav_view), isDisplayed())).perform(scrollTo(hasDescendant(withText("Report"))), click());

        onView(allOf(withId(R.id.nav_view), hasFocus())).perform(NavigationViewActions.navigateTo(2));

        onView(allOf(instanceOf(MainActivity.class), withId(R.id.itemReports))).check(matches(isDisplayed()));

         ViewInteraction navigationMenuItemView =onView(
                allOf(withClassName(is("com.google.android.material.internal.NavigationMenuItemView")), isDisplayed()));
        navigationMenuItemView.perform(click(NavigationViewActions.navigateTo(2)));



*/

        ViewInteraction navigationMenuItemView =onView(
                allOf(withClassName(is("com.google.android.material.internal.NavigationMenuItemView")), isDisplayed()));
        navigationMenuItemView.perform(click());





    }
}
