package com.example.rogosample.UI.device

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.DeviceSpinnerAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentDeleteDeviceBinding
import rogo.iot.module.rogocore.basesdk.callback.RequestCallback
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.IoTDevice

class DeleteDeviceFragment : BaseFragment<FragmentDeleteDeviceBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_delete_device

    private val deviceSpinnerAdapter by lazy {
        DeviceSpinnerAdapter(requireContext(), SmartSdk.deviceHandler().all.toMutableList())
    }

    override fun initVariable() {
        super.initVariable()
        binding.apply {
            toolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.txtTitle.text = resources.getString(R.string.edit_device)
            spinnerDevice.adapter = deviceSpinnerAdapter
        }
    }

    override fun initAction() {
        super.initAction()
        binding.apply {
            /*
            * Delete a device, pass the device's UUID
            * */
            btnDeleteDevice.setOnClickListener {
                SmartSdk.deviceHandler().delete(
                    (spinnerDevice.selectedItem as IoTDevice).uuid,
                    object : RequestCallback<Boolean> {
                        override fun onSuccess(p0: Boolean?) {
                            p0?.let {
                                showNoti(R.string.delete_success)
                            }
                        }

                        override fun onFailure(p0: Int, p1: String?) {
                            p1?.let {
                                showNoti(it)
                            }
                        }

                    }
                )
            }
        }
    }
}