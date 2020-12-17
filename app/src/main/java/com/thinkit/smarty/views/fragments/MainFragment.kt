package com.thinkit.smarty.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.thinkit.smarty.R
import com.thinkit.smarty.databinding.FragmentMainBinding
import com.thinkit.smarty.enums.ViewModelState
import com.thinkit.smarty.utils.viewModel
import com.thinkit.smarty.viewmodels.MainViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein


class MainFragment : Fragment(), KodeinAware {

    /**
     * kodein instance used for dependency injection
     */
    override val kodein by kodein()

    /**
     * login view model
     * @see MainViewModel
     */
    private val viewModel: MainViewModel by viewModel()

    /**
     * generated data binding for view fragment_login.xml
     */
    private lateinit var dataBinding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        dataBinding.viewModel = viewModel

        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginState.observe(viewLifecycleOwner, Observer { viewModelState ->
            onLoginStateChange(viewModelState)

        })

    }

    private fun onLoginStateChange(state: ViewModelState) {

        when (state) {
            ViewModelState.SUCCESS -> {
                parentFragmentManager.commit {
                    replace(R.id.main_container, HomeFragment())
                }
            }

            ViewModelState.ERROR -> {
                Toast.makeText(activity, viewModel.errorMessage, Toast.LENGTH_SHORT).show()
            }


        }

    }
}