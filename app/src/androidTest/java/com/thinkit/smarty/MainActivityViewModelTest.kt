package com.thinkit.smarty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.thinkit.smarty.di.SharedPreferencesProvider
import com.thinkit.smarty.enums.Navigation
import com.thinkit.smarty.viewmodels.MainActivityViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityViewModelTest {
    private val instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var  activityViewModel: MainActivityViewModel
    lateinit var kodein: Kodein
    lateinit var sharedPreferencesProvider:SharedPreferencesProvider





    @Before
    fun setup() {

        kodein = Kodein.lazy {
            bind() from singleton { instrumentationContext }
            bind() from singleton { SharedPreferencesProvider(instance()) }

        }


        val sharedPreferencesProvider: SharedPreferencesProvider by kodein.instance()
        this.sharedPreferencesProvider = sharedPreferencesProvider
        activityViewModel = MainActivityViewModel(sharedPreferencesProvider = sharedPreferencesProvider)


    }


    @Test
    fun navigateToMainTest() =
        runBlocking {
            sharedPreferencesProvider.insertBoolean(SharedPreferencesProvider.USER_LOGGED_IN, false)
            assertEquals(Navigation.MAIN, activityViewModel.navigation.asFlow().first())

        }

    @Test
    fun navigateToHomeTest() =
        runBlocking {
            sharedPreferencesProvider.insertBoolean(SharedPreferencesProvider.USER_LOGGED_IN, true)
            assertEquals(Navigation.HOME, activityViewModel.navigation.asFlow().first())
        }

}