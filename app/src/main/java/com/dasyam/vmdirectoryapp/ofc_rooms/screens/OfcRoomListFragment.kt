package com.dasyam.vmdirectoryapp.ofc_rooms.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dasyam.vmdirectoryapp.R
import com.dasyam.vmdirectoryapp.common.BaseFragment
import com.dasyam.vmdirectoryapp.databinding.FragmentRoomListBinding
import com.dasyam.vmdirectoryapp.ofc_rooms.FetchOfcRoomDetailsUseCase

class OfcRoomListFragment : BaseFragment() {

    private lateinit var viewBinding: FragmentRoomListBinding
    private lateinit var ofcRoomUseCase: FetchOfcRoomDetailsUseCase
    private val roomListAdapter = OfcRoomListAdapter()

    companion object {
        const val ROOM_BUNDLE = "room_bundle"

        fun newInstance(): OfcRoomListFragment {
            return OfcRoomListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentRoomListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEmployeeListAdapter()
        ofcRoomUseCase = getCompositionRoot().getFetchOfcRoomDetailsUseCase()
        initObservers()
        ofcRoomUseCase.fetchOfcRoomDetails()
        showProgressBar()
    }

    private fun initObservers() {
        ofcRoomUseCase.ofcRoomList.observe(viewLifecycleOwner, {
            roomListAdapter.bindRooms(it)
            hideProgressBar()
        })
        ofcRoomUseCase.ofcRoomUseCaseFailure.observe(viewLifecycleOwner, {
            roomListAdapter.bindRooms(emptyList())
            hideProgressBar()
        })
    }

    private fun initEmployeeListAdapter() {
        viewBinding.rvEmpList.layoutManager = GridLayoutManager(
            requireContext(),
            resources.getInteger(R.integer.recycler_span_count)
        )
        viewBinding.rvEmpList.adapter = roomListAdapter
    }

    private fun showProgressBar() {
        viewBinding.pbBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        viewBinding.pbBar.visibility = View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ROOM_BUNDLE, ArrayList(roomListAdapter.getRoomList()))
    }

}