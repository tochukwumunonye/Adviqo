package com.example.adviqo.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adviqo.databinding.ProductHistoryItemBinding
import com.example.adviqo.domain.model.Product

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Product] along with [ProductVisitedViewHolder]
 * @param listener which will receive callback when item is clicked.
 */

class ProductHistoryAdapter : ListAdapter<Product, ProductHistoryAdapter.ProductHistoryViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHistoryViewHolder {
        val binding =
            ProductHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHistoryViewHolder, position: Int) {
        val currentProduct = getItem(position)

        if( currentProduct != null) {
            holder.bind(currentProduct)
        }
    }

    inner class ProductHistoryViewHolder(private val binding: ProductHistoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            Glide.with(binding.root.context)
                .load(item.thumbnail)
                .centerCrop()
                .into(binding.productThumbnail)

            binding.productPrice.text = item.price.toString()
            binding.productTitle.text = item.title
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.price == newItem.price
            }
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }
        }
    }
}
