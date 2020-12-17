package com.thinkit.smarty.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.thinkit.smarty.R
import com.thinkit.smarty.enums.Navigation
import com.thinkit.smarty.utils.viewModel
import com.thinkit.smarty.viewmodels.MainActivityViewModel
import com.thinkit.smarty.views.fragments.HomeFragment
import com.thinkit.smarty.views.fragments.MainFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val mainViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.navigation.observe(this, { navigation ->
            navigateTo(navigation = navigation)
        })


    }

    /**
     * function to properly change fragment according to navigation value
     */
    private fun navigateTo(navigation: Navigation) {
        when (navigation) {
            Navigation.HOME -> changeFragment(HomeFragment())
            Navigation.MAIN -> changeFragment(MainFragment())
        }
    }


    /**
     * helper function to easily change fragment
     * @param fragment Fragment
     */
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.main_container, fragment)
        }
    }


}