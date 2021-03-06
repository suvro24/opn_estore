package com.saiful.opn_estore

import android.os.Bundle
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.saiful.opn_estore.databinding.FragmentStoreBinding
import com.saiful.opn_estore.ui.store.StoreFragment
import com.saiful.opn_estore.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class StoreScreenTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun testNavigationToOrderSummaryScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        var binding:FragmentStoreBinding? = null
        launchFragmentInHiltContainer<StoreFragment>(Bundle(), R.style.AppTheme) {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.storeScreen)
            Navigation.setViewNavController(requireView(), navController)

            binding = DataBindingUtil.findBinding(this.view!!)
        }

        binding!!.goToOrder.visibility = View.VISIBLE

        onView(withId(R.id.go_to_order)).perform(click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.orderSummaryScreen)

    }

}