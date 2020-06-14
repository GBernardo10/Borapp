package com.br.bora.app

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LayoutLoginTest  {

    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<AuthActivity> = ActivityTestRule(AuthActivity::class.java)

    @Before
    fun initValidString() {
        stringToBetyped = "Espresso"
    }

    @Test
    fun checkInput(){
        Espresso.onView(ViewMatchers.withText(R.string.login))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
