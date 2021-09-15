package com.saiful.opn_estore.adapter

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saiful.opn_estore.data.model.ParentListItemModel

@BindingAdapter("app:items")
fun setItems(recyclerView: RecyclerView, items: List<ParentListItemModel>?) {
    (recyclerView.adapter as ParentListAdapter).submitList(items)
}