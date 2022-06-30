package com.example.adviqo.presentation.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adviqo.databinding.ProductListItemBinding
import com.example.adviqo.domain.model.Product
/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Product] along with [ProductListViewHolder]
 * @param listener which will receive callback when item is clicked.
 */

class ProductListAdapter(
    private val listener :OnItemClickListener
) : ListAdapter<Product, ProductListAdapter.ProductListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding =
            ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val currentProduct = getItem(position)

        if(currentProduct != null) {
            holder.bind(currentProduct)
        }
    }
    inner class ProductListViewHolder(private val binding: ProductListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                binding.root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION) {
                        val item = getItem(position)
                        if(item != null) {
                            listener.onItemClick(item)
                        }
                    }
                }
            }
        }
        fun bind(item: Product) {
            Glide.with(binding.root.context)
                .load(item.thumbnail)
                .centerCrop()
                .into(binding.productThumbnail)

            binding.productPrice.text = item.price.toString()
            binding.productTitle.text = item.title
        }
    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
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