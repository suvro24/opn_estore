package com.saiful.opn_estore

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.saiful.opn_estore.ui.store.StoreFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class StoreScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationToInOrderSummaryScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<StoreFragment>(Bundle(), R.style.AppTheme) {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.orderSummaryScreen)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.go_to_order)).perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.orderSummaryScreen)

    }

}