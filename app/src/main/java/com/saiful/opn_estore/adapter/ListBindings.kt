package com.saiful.opn_estore.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saiful.opn_estore.data.model.ParentListItemModel

@BindingAdapter("app:setItems")
fun setItems(recyclerView: RecyclerView, items: List<ParentListItemModel>?) {
    (recyclerView.adapter as ParentListAdapter).submitList(items)
}