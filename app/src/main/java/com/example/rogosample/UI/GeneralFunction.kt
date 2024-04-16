package com.example.rogosample.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.FunctionAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentGeneralFunctionBinding
import com.example.rogosample.`object`.Function
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.define.IoTSmartType

class GeneralFunction : BaseFragment<FragmentGeneralFunctionBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_general_function

    private val groupFunctionAdapter by lazy {
        FunctionAdapter(onItemClick = {
            when (it) {
                Function.GETLISTGROUP -> {
                    val bundle = bundleOf("isVirtualGroup" to false)
                    findNavController().navigate(R.id.groupFragment, bundle)
                }

                Function.ADDGROUP -> {
                    findNavController().navigate(R.id.addGroupFragment)
                }

                Function.EDITGROUP -> {
                    findNavController().navigate(R.id.editGroupFragment)
                }

                Function.DELETEGROUP -> {
                    findNavController().navigate(R.id.deleteGroupFragment)
                }

                else -> {
                    val bundle = bundleOf("isVirtualGroup" to true)
                    findNavController().navigate(R.id.groupFragment, bundle)
                }
            }
        })
    }

    private val deviceFunctionAdapter by lazy {
        FunctionAdapter(onItemClick = {
            when (it) {
                Function.GETLISTDEVICE -> {
                    findNavController().navigate(R.id.deviceFragment)
                }

                Function.ADDDEVICE -> {
                    findNavController().navigate(R.id.addDeviceFragment)
                }

                Function.EDITDEVICE -> {
                    findNavController().navigate(R.id.editDeviceFragment)
                }

                else -> {
                    findNavController().navigate(R.id.deleteDeviceFragment)
                }
            }
        })
    }

    private val smartFunctionAdapter by lazy {
        FunctionAdapter(onItemClick = {
            when (it) {
                Function.SMARTSCENARIO -> {
                    val bundle = bundleOf("smartType" to IoTSmartType.TYPE_SCENE)
                    findNavController().navigate(R.id.smartSceneFragment, bundle)
                }

                Function.SMARTSCHEDULE -> {
                    val bundle = bundleOf("smartType" to IoTSmartType.TYPE_SCHEDULE)
                    findNavController().navigate(R.id.smartSceneFragment, bundle)
                }

                else -> {
                    val bundle = bundleOf("smartType" to IoTSmartType.TYPE_AUTOMATION)
                    findNavController().navigate(R.id.smartSceneFragment, bundle)
                }
            }
        })
    }


    override fun initVariable() {
        super.initVariable()
        binding.apply {
            toolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun initAction() {
        super.initAction()
        binding.apply {
            rvGroupFunction.adapter = groupFunctionAdapter
            groupFunctionAdapter.submitList(Function.getGroupFuncs())

            rvDeviceFunction.adapter = deviceFunctionAdapter
            deviceFunctionAdapter.submitList(Function.getDeviceFuncs())

            rvSmartFunction.adapter = smartFunctionAdapter
            smartFunctionAdapter.submitList(Function.getSMARTFuncs())
        }
    }
}