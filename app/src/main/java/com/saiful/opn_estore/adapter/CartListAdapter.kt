package com.saiful.opn_estore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.databinding.ListItemCartBinding

class CartListAdapter() : ListAdapter<Product, CartListAdapter.CartProductViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        return CartProductViewHolder(
            ListItemCartBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        holder.bind(
            getItem(position) as Product
        )

    }

    class CartProductViewHolder(private val binding: ListItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.apply {
                product = item
                executePendingBindings()
            }
        }
    }


    interface OnAddRemoveProductClickListener {
        fun onAddProduct(item: Product)
        fun onRemoveProduct(item: Product)
    }

    private class DiffCallback : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem


        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {

            return oldItem == newItem
        }
    }
}

