package com.thinkit.smarty.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.thinkit.smarty.R
import com.thinkit.smarty.adapters.RoomsListAdapter
import com.thinkit.smarty.databinding.FragmentHomeBinding
import com.thinkit.smarty.utils.viewModel
import com.thinkit.smarty.viewmodels.HomeViewModel
import com.thinkit.smarty.viewmodels.MainViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein


class HomeFragment : Fragment(), KodeinAware {

    /**
     * kodein instance used for dependency injection
     */
    override val kodein by kodein()

    /**
     * login view model
     * @see MainViewModel
     */
    private val viewModel: HomeViewModel by viewModel()

    /**
     * generated data binding for view fragment_home.xml
     */
    private lateinit var dataBinding: FragmentHomeBinding
    lateinit var roomsListAdapter: RoomsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        dataBinding.viewModel = viewModel
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRoomsListRecyclerView()

        viewModel.rooms.observe(viewLifecycleOwner, { roomsWithDevices ->
            roomsListAdapter.apply {
                this.roomsWithDevices = roomsWithDevices
                notifyDataSetChanged()
            }

        })

    }

    /**
     * setting recylerView with RoomsListAdapter
     */
    private fun setupRoomsListRecyclerView() {
        roomsListAdapter = RoomsListAdapter(roomsWithDevices = emptyList())
        dataBinding.recyclerRooms.apply {
            layoutManager = LinearLayoutManager(activity)
            /* in case we want to show only 4 items in recycler view

                  layoutManager = object :LinearLayoutManager(activity) {
                 override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                     if (lp != null) {
                         lp.height = height / 4
                     };
                     return true
                 }
             }
              I didn't opt for this solution because i tried to take advantage of the screen height and show more items

             */
            adapter = roomsListAdapter
        }
    }
}