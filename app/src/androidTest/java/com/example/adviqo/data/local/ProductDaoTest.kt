package com.example.adviqo.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.adviqo.data.local.entities.ProductEntity
import com.example.adviqo.domain.model.Product
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ProductDaoTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ProductDatabase

    private lateinit var dao: ProductDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.productDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertProduct() = runTest {
        val productEntity = ProductEntity("book", 2.0, "http://book", "macbook", 1)
        dao.insertProduct(productEntity)

        val products = dao.returnLastProducts()
        assertThat(productEntity.id).isEqualTo(products[0].id)
        assertThat(productEntity.price).isEqualTo(products[0].price)
        assertThat(productEntity.title).isEqualTo(products[0].title)
        assertThat(productEntity.thumbnail).isEqualTo(products[0].thumbnail)
    }

    @Test
    fun return_Latest_Five_products_From_Database() = runTest {
        val productEntity1 =  ProductEntity(
            id = "book1",
            price = 2.3,
            thumbnail = "https://book.com",
            title = "macBook pro1"
        );
        dao.insertProduct(productEntity1)

        val productEntity2 =  ProductEntity(
            id = "book2",
            price = 2.3,
            thumbnail = "https://book.com",
            title = "macBook pro2"
        );
        dao.insertProduct(productEntity2)

        val productEntity3 =  ProductEntity(
            id = "book3",
            price = 2.3,
            thumbnail = "https://book.com",
            title = "macBook pro3"
        );
        dao.insertProduct(productEntity3)

        val productEntity4 =  ProductEntity(
            id = "book4",
            price = 2.3,
            thumbnail = "https://book.com",
            title = "macBook pro4"
        );
        dao.insertProduct(productEntity4)

        val productEntity5 =  ProductEntity(
            id = "book5",
            price = 2.3,
            thumbnail = "https://book.com",
            title = "macBook pro5"
        );
        dao.insertProduct(productEntity5)

        val productEntity6 =  ProductEntity(
            id = "book6",
            price = 2.3,
            thumbnail = "https://book.com",
            title = "macBook pro6"
        );
        dao.insertProduct(productEntity6)

        val productEntity7 =  ProductEntity(
            id = "book7",
            price = 2.3,
            thumbnail = "https://book.com",
            title = "macBook pro7"
        );
        dao.insertProduct(productEntity7)

        val products = dao.returnLastProducts()
        assertThat(products.size).isEqualTo(5)

        assertThat(products[0].id).contains("book7")

    }

}
