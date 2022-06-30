package com.example.adviqo.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.adviqo.presentation.history.ProductHistoryFragment
import com.example.adviqo.presentation.productList.ProductListFragment
import com.example.adviqo.presentation.productdetail.ProductDetailFragment
import javax.inject.Inject


class TestFragmentFactory @Inject constructor(
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ProductListFragment::class.java.name -> ProductListFragment()
            ProductDetailFragment::class.java.name -> ProductDetailFragment()
            ProductHistoryFragment::class.java.name -> ProductHistoryFragment()
            else -> super.instantiate(classLoader, className)
        }
    }

}