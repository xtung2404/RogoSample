package com.example.rogosample.UI.group

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.GroupAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentGroupBinding
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.IoTGroup

class GroupFragment : BaseFragment<FragmentGroupBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_group

    private val groupAdapter by lazy {
        GroupAdapter()
    }
    private var isVirtualGroup: Boolean = false

    override fun initVariable() {
        super.initVariable()
        arguments?.let {
            isVirtualGroup = it.getBoolean("isVirtualGroup")
        }
        binding.apply {
            toolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            if(isVirtualGroup) {
                toolbar.txtTitle.text = resources.getString(R.string.virtual_group)
            } else {
                toolbar.txtTitle.text = resources.getString(R.string.get_list_group)
            }
            rvGroup.adapter = groupAdapter
        }
    }

    override fun initAction() {
        super.initAction()
        binding.apply {
            btnGetList.setOnClickListener {
                /*
                * To get the avaiable groups
                * */
                lnGroups.visibility = View.VISIBLE
                var list = mutableListOf<IoTGroup>()
                if(isVirtualGroup) {
                    list = SmartSdk.groupHandler().groupCtls.toMutableList()
                } else {
                    list = SmartSdk.groupHandler().all.toMutableList()
                }
                if(list.isNullOrEmpty()) {
                    lnEmpty.visibility = View.VISIBLE
                    rvGroup.visibility = View.GONE
                } else {
                    rvGroup.visibility = View.VISIBLE
                    lnEmpty.visibility = View.GONE
                    groupAdapter.submitList(list)
                }
            }
        }
    }
}