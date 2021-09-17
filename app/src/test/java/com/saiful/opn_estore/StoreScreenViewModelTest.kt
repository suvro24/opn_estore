package com.saiful.opn_estore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saiful.opn_estore.ui.store.StoreViewModel
import com.saiful.opn_estore.utils.FakeRepository
import com.saiful.opn_estore.utils.getOrAwaitValue
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class StoreScreenViewModelTest {


    private lateinit var storeViewModel: StoreViewModel
    private lateinit var fakeRepository: FakeRepository

//    @Mock
//    private var repo: DefaultRepository = mock()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        fakeRepository = FakeRepository()
        storeViewModel = StoreViewModel(fakeRepository)
    }

    @Test
    fun isInitialStoreIsNull() {


        storeViewModel.storeInfo = fakeRepository.getStores().

        assertNotNull(store)

    }

}