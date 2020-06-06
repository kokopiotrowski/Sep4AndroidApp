package com.example.sep4androidapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.sep4androidapp.Firebase.Firebase_Login;
import com.example.sep4androidapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Firebase_LoginTest {

    @Rule
    public ActivityTestRule< Firebase_Login > mActivityTestRule = new ActivityTestRule<>(Firebase_Login.class);

    @Test
    public void firebase_LoginTest() {
        ViewInteraction supportVectorDrawablesButton = onView(
                allOf(withText("Sign in with Google"),
                        childAtPosition(
                                withId(R.id.btn_holder),
                                2),
                        isDisplayed()));
        supportVectorDrawablesButton.perform(click());
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
