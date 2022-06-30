package com.example.adviqo.application

import javax.inject.Inject
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.adviqo.presentation.productList.ProductListFragment
import com.example.adviqo.presentation.productdetail.ProductDetailFragment

class FragmentFactory @Inject constructor(): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ProductListFragment::class.java.name -> ProductListFragment()
            ProductDetailFragment::class.java.name -> ProductDetailFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}
