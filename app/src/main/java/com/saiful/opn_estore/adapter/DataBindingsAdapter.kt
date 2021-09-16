package com.saiful.opn_estore.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.saiful.opn_estore.data.model.ParentListItemModel

@BindingAdapter("app:setItems")
fun setItems(recyclerView: RecyclerView, items: List<ParentListItemModel>?) {
    val adapter = (recyclerView.adapter as ParentListAdapter)
    adapter.submitList(items)
    adapter.notifyDataSetChanged()
}

@BindingAdapter("imageFromUrl")
fun imageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, qty:Int) {
    println(qty)
    view.visibility = if (qty==0) {
        View.VISIBLE
    } else {
        View.GONE
    }
}