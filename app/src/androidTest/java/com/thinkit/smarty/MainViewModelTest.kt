package com.thinkit.smarty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.thinkit.smarty.di.*
import com.thinkit.smarty.enums.ViewModelState
import com.thinkit.smarty.repositories.UserRepository
import com.thinkit.smarty.viewmodels.MainViewModel
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
class MainViewModelTest {
    private val instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var mainViewModel: MainViewModel
    lateinit var kodein: Kodein
    lateinit var sharedPreferencesProvider: SharedPreferencesProvider


    @Before
    fun setup() {

        kodein = Kodein.lazy {
            bind() from singleton { instrumentationContext }
            bind() from singleton { SharedPreferencesProvider(instance()) }
            bind() from singleton { ResourcesProvider(instance()) }
            import(viewModelModule)
            import(repositoriesModule)
            import(roomModule)
        }

        val resourcesProvider: ResourcesProvider by kodein.instance()
        val sharedPreferencesProvider: SharedPreferencesProvider by kodein.instance()
        val userRepository: UserRepository by kodein.instance()
        this.sharedPreferencesProvider = sharedPreferencesProvider
        mainViewModel = MainViewModel(
            sharedPreferencesProvider = sharedPreferencesProvider,
            resourcesProvider = resourcesProvider,
            userRepository = userRepository
        )


    }


    @Test
    fun validNameTest() =
        runBlocking {
            mainViewModel.name = "Oussama"
            mainViewModel.validateName()
            assertEquals(ViewModelState.SUCCESS, mainViewModel.loginState.asFlow().first())

        }

    @Test
    fun invalidNameTest() =
        runBlocking {
            mainViewModel.name = ""
            mainViewModel.validateName()
            assertEquals(ViewModelState.ERROR, mainViewModel.loginState.asFlow().first())
        }

}