package com.example.adviqo.presentation

import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.adviqo.presentation.productList.ProductListFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import com.example.adviqo.R
import com.example.adviqo.di.launchFragmentInHiltsContainer
import com.example.adviqo.domain.model.Product
import com.example.adviqo.presentation.productList.ProductListAdapter
import com.example.adviqo.presentation.productList.ProductListFragmentDirections
import org.mockito.Mockito
import org.mockito.Mockito.verify


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ProductListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testFragmentFactory: TestFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun test_recycler_is_visible_when_data_is_loaded() {
        launchFragmentInHiltsContainer<ProductListFragment>(
            fragmentFactory = testFragmentFactory
        ) {
            productListAdapter.submitList(getListOfProduct())
        }
        onView(withId(R.id.productListRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun click_on_recyclerViewholder_to_navigate_to_detail_screen() {
        val product = getListOfProduct()
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltsContainer<ProductListFragment>(
            fragmentFactory = testFragmentFactory
        ){
            productListAdapter.submitList(product)
            androidx.navigation.Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.productListRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ProductListAdapter.ProductListViewHolder>(
                0, click()
            )
        )
        verify(navController).navigate(
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(product[0])
        )
    }

    @Test
    fun click_visit_button_to_go_to_historyscreen() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltsContainer<ProductListFragment>(
            fragmentFactory = testFragmentFactory
        ) {
            androidx.navigation.Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.visitButton)).perform(click())
        verify(navController).navigate(
            ProductListFragmentDirections.actionProductListFragmentToProductHistoryFragment()
        )
    }

    private fun getListOfProduct(): List<Product> {
        return listOf(
            Product(
                id = "book1",
                price = 2.3,
                thumbnail = "https://book.com",
                title = "macBook pro"
            ),
            Product(
                id = "book2",
                price = 2.5,
                thumbnail = "https://book2.com",
                title = "macBook pro2"
            )
        )
    }


}