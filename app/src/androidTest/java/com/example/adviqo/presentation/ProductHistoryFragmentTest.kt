package com.example.adviqo.presentation

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.adviqo.R
import com.example.adviqo.di.launchFragmentInHiltsContainer
import com.example.adviqo.domain.model.Product
import com.example.adviqo.presentation.history.ProductHistoryFragment
import com.example.adviqo.presentation.productList.ProductListFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ProductHistoryFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testFragmentFactory: TestFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun test_recycler_is_visible_when_data_is_loaded_fromDatabase() {
        launchFragmentInHiltsContainer<ProductHistoryFragment>(
            fragmentFactory = testFragmentFactory
        ) {
            productHistoryAdapter.submitList(getListOfProduct())
        }
        onView(ViewMatchers.withId(R.id.productListRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
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
