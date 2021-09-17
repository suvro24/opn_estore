package com.saiful.opn_estore

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.saiful.opn_estore.ui.order_summary.OrderSummaryFragment
import com.saiful.opn_estore.ui.order_summary.OrderSummaryFragmentDirections
import com.saiful.opn_estore.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class OrderSummaryScreenTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        Dispatchers.setMain(Dispatchers.IO)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
    
    @Test
    fun testNavigationToOrderSuccessScreen() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<OrderSummaryFragment>(Bundle(), R.style.AppTheme) {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.orderSummaryScreen)
            Navigation.setViewNavController(requireView(), navController)

            (this as OrderSummaryFragment).navigateTo(OrderSummaryFragmentDirections.actionOrderSummaryScreenToOrderSuccessScreen())
        }

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.orderSuccessScreen)

    }

    @Test
    fun testNavigationOnBackPressed() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<OrderSummaryFragment>(Bundle(), R.style.AppTheme) {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.orderSummaryScreen)
            Navigation.setViewNavController(requireView(), navController)
        }

        navController.navigateUp()
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.storeScreen)

    }

}