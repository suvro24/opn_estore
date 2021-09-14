package com.saiful.opn_estore.ui.order_summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderSummaryViewModel @Inject constructor(): ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Order Summary Fragment"
    }
    val text: LiveData<String> = _text
}