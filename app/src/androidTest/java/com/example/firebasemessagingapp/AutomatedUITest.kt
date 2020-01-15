package com.example.firebasemessagingapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.firebasemessagingapp.view.MainActivity
import org.junit.Rule
import org.junit.Test

class AutomatedUITest {
    val toType = "This is a test"

    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testType(){
        Espresso.onView(withId(R.id.profile_image))
            .perform(click())

        Espresso.onView(withId(R.id.user_tv)).check(matches(withText(toType)))

    }

}