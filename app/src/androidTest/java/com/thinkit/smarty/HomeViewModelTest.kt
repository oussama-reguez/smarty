package com.thinkit.smarty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.thinkit.smarty.di.*
import com.thinkit.smarty.repositories.RoomRepository
import com.thinkit.smarty.repositories.UserRepository
import com.thinkit.smarty.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
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
class HomeViewModelTest {
    private val instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var  viewModel: HomeViewModel
    lateinit var kodein: Kodein
    lateinit var sharedPreferencesProvider:SharedPreferencesProvider





    @Before
    fun setup(){

        kodein = Kodein.lazy {
            bind() from singleton { instrumentationContext }
            bind() from singleton { SharedPreferencesProvider(instance()) }
            bind() from singleton { ResourcesProvider(instance()) }
            import(viewModelModule)
            import(repositoriesModule)
            import(roomModule)
        }

        val roomRepository: RoomRepository by kodein.instance()
        val userRepository: UserRepository by kodein.instance()
        val resourcesProvider: ResourcesProvider by kodein.instance()
        val sharedPreferencesProvider: SharedPreferencesProvider by kodein.instance()
        this.sharedPreferencesProvider = sharedPreferencesProvider
        viewModel = HomeViewModel(roomRepository = roomRepository, userRepository = userRepository, resourcesProvider = resourcesProvider)


    }


    @Test
    fun areRoomsPopulatedTest() =
            runBlocking {

                val rooms = viewModel.rooms.asFlow().first()
                assertNotEquals(0, rooms.size)

            }



}