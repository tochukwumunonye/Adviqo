package com.example.adviqo.presentation.productList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adviqo.R
import com.example.adviqo.databinding.FragmentProductListBinding
import com.example.adviqo.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment: Fragment(R.layout.fragment_product_list), ProductListAdapter.OnItemClickListener {

   val productListAdapter = ProductListAdapter(this)

    private val viewModel: ProductListViewModel by viewModels()

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProductListBinding.bind(view)

        setUpRecyclerView()

        binding.searchButton.setOnClickListener {
            val text = binding.editText.text.toString()
            viewModel.search(text)
        }

        binding.visitButton.setOnClickListener {
            val action = ProductListFragmentDirections.actionProductListFragmentToProductHistoryFragment()
            findNavController().navigate(action)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when(viewState) {
                        is ProductListViewState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            productListAdapter.submitList(viewState.product)
                        }
                        is ProductListViewState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is ProductListViewState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                viewState.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onItemClick(product: Product) {
        viewModel.insertProductInDatabase(product)
        val action = ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(product)
        findNavController().navigate(action)
    }

    private fun setUpRecyclerView() {
        binding.productListRecyclerView.apply {
            adapter = productListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
