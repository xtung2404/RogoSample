package com.example.rogosample.UI.device.ir

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.AcControlAdapter
import com.example.rogosample.adapter.DeviceSpinnerAdapter
import com.example.rogosample.adapter.GroupSpinnerAdapter
import com.example.rogosample.adapter.IrCodeAdapter
import com.example.rogosample.adapter.LearnIrDeviceAdapter
import com.example.rogosample.adapter.ManufacturerSpinnerAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.base.getDeviceName
import com.example.rogosample.databinding.FragmentLearnIrAcBinding
import com.example.rogosample.`object`.AcControlItem
import com.example.rogosample.`object`.DeviceLearnIr
import com.example.rogosample.`object`.IrCode
import com.example.rogosample.`object`.getIrCodeFanName
import com.example.rogosample.`object`.getIrCodeTVName
import rogo.iot.module.rogocore.basesdk.callback.RequestCallback
import rogo.iot.module.rogocore.basesdk.callback.SuccessStatus
import rogo.iot.module.rogocore.basesdk.define.IoTIrCodeFan
import rogo.iot.module.rogocore.basesdk.define.IoTIrCodeTV
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.CheckIrHubInfoCallback
import rogo.iot.module.rogocore.sdk.callback.LearnIrCallback
import rogo.iot.module.rogocore.sdk.callback.SuccessCallback
import rogo.iot.module.rogocore.sdk.entity.IoTDevice
import rogo.iot.module.rogocore.sdk.entity.IoTGroup
import rogo.iot.module.rogocore.sdk.entity.IoTIrProtocolInfo
import rogo.iot.module.rogocore.sdk.entity.IoTIrRemote

class LearnIrAcFragment : BaseFragment<FragmentLearnIrAcBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_learn_ir_ac

    private val irDeviceList = arrayListOf<IoTDevice>()
    private var protocolInfo: IoTIrProtocolInfo? = null
    private var acMode: Int = 0
    private var fanSpeed: Int = 0
    private var tempMax: Int = 30
    private var tempMin: Int = 16
    private var currentTemp: Int = 24
    private var isOn: Boolean = false
    private var ioTIrRemote: IoTIrRemote?= null
    private var acModeList = mutableListOf<AcControlItem>()
    private var fanSpeedList = mutableListOf<AcControlItem>()
    private val iRDeviceAdapter by lazy {
        DeviceSpinnerAdapter(requireContext(), irDeviceList)
    }
    private val setUpRemoteDialog by lazy {
        Dialog(requireContext())
    }

    private val groupSpinnerAdapter by lazy {
        GroupSpinnerAdapter(requireContext(), SmartSdk.groupHandler().all.toMutableList())
    }

    private lateinit var manufacturerSpinnerAdapter: ManufacturerSpinnerAdapter

    private val acModeAdapter by lazy {
        AcControlAdapter(isChecked = {
            if(it.second) {
                acMode = it.first
                SmartSdk.learnIrDeviceHandler().testAcIrRemote(
                    (binding.spinnerHub.selectedItem as IoTDevice).uuid,
                    ioTIrRemote!!.rid,
                    isOn,
                    acMode,
                    currentTemp,
                    fanSpeed,
                    SuccessStatus {

                    }
                )
            } else {

            }
        })
    }

    private val fanSpeedAdapter by lazy {
        AcControlAdapter(isChecked = {
            if(it.second) {
                fanSpeed = it.first
                SmartSdk.learnIrDeviceHandler().testAcIrRemote(
                    (binding.spinnerHub.selectedItem as IoTDevice).uuid,
                    ioTIrRemote!!.rid,
                    isOn,
                    acMode,
                    currentTemp,
                    fanSpeed,
                    SuccessStatus {

                    }
                )
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setUpRemoteDialog.window?.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        setUpRemoteDialog.setContentView(R.layout.dialog_set_up_remote)
        super.onCreate(savedInstanceState)
    }

    override fun initVariable() {
        super.initVariable()
        binding.apply {
            toolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.txtTitle.text = resources.getString(R.string.add_ir_remote)
            spinnerHub.adapter = iRDeviceAdapter
            rvAcMode.adapter = acModeAdapter
            rvFanSpeed.adapter = fanSpeedAdapter
            setUpDialogSetUpRemote()
            manufacturerSpinnerAdapter =
                ManufacturerSpinnerAdapter(requireContext(),
                    getDeviceName.getClassInfo().entries.filter { data ->
                        SmartSdk.learnIrDeviceHandler()
                            .getSupportManufacturers(DeviceLearnIr.AIRCON.type)
                            .contains(
                                data.value
                            )
                    }.toList()
                )
            spinnerManufacture.adapter = manufacturerSpinnerAdapter

        }
    }

    override fun initAction() {
        super.initAction()
        binding.apply {
            irDeviceList.clear()
            /*
            * Check available Hub devices such as : FPT Box, IR Device...
            * */
            SmartSdk.learnIrDeviceHandler().checkIrHubAvailable(object : CheckIrHubInfoCallback {
                override fun onIrDeviceInfo(devId: String?, irSupport: Int, protcolSupport: Int) {
                    irDeviceList.add(SmartSdk.deviceHandler().get(devId))
                    iRDeviceAdapter.notifyDataSetChanged()
                }
            })

            /*
            * Select available buttons like the remote
            * */
            spinnerHub.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    lnAc.visibility = View.VISIBLE
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    lnAc.visibility = View.GONE
                }
            }

            spinnerManufacture.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    parent?.let {
                        lnAcControl.visibility = View.GONE
                        fanSpeedList.clear()
                        acModeList.clear()
                        SmartSdk.learnIrDeviceHandler().getIrRemotes(
                            (it.getItemAtPosition(position) as Map.Entry<String, Int>).value,
                            DeviceLearnIr.AIRCON.type,
                            object : RequestCallback<Collection<IoTIrRemote>> {
                                override fun onSuccess(p0: Collection<IoTIrRemote>?) {
                                    p0?.let { remoteList ->
                                        ioTIrRemote = remoteList.first()
                                        SmartSdk.learnIrDeviceHandler().preloadTestIrRemote(
                                            (it.getItemAtPosition(position) as Map.Entry<String, Int>).value,
                                            DeviceLearnIr.AIRCON.type,
                                            ioTIrRemote!!.rid,
                                            SuccessStatus {status ->
                                                if (status) {
                                                    lnAcControl.visibility = View.VISIBLE
                                                    acModeList = AcControlItem.getAcModeList().filter { acMode ->
                                                        remoteList.first().modes.contains(
                                                            acMode.itemId
                                                        )
                                                    }.toMutableList()
                                                    acModeList.first().isChecked = true
                                                    acModeAdapter.submitList(acModeList)
                                                    fanSpeedList = AcControlItem.getFanSpeedList().filter { fanSpeed ->
                                                        remoteList.first().fans.contains(
                                                            fanSpeed.itemId
                                                        )
                                                    }.toMutableList()
                                                    fanSpeedList.first().isChecked = true
                                                    fanSpeedAdapter.submitList(fanSpeedList)
                                                    tempMin = remoteList.first().tempRange[0]
                                                    tempMax = remoteList.first().tempRange[1]
                                                    currentTemp = tempMax
                                                } else {
                                                    lnAcControl.visibility = View.GONE
                                                }
                                            }
                                        )
                                    }
                                }

                                override fun onFailure(p0: Int, p1: String?) {

                                }
                            }
                        )
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

            btnMinus.setOnClickListener{
                if(currentTemp > tempMin) {
                    currentTemp--
                }
                SmartSdk.learnIrDeviceHandler().testAcIrRemote(
                    (spinnerHub.selectedItem as IoTDevice).uuid,
                    ioTIrRemote!!.rid,
                    isOn,
                    acMode,
                    currentTemp,
                    fanSpeed,
                    SuccessStatus {

                    }
                )
            }

            btnOn.setOnClickListener {
                isOn = true
                SmartSdk.learnIrDeviceHandler().testAcIrRemote(
                    (spinnerHub.selectedItem as IoTDevice).uuid,
                    ioTIrRemote!!.rid,
                    isOn,
                    acMode,
                    currentTemp,
                    fanSpeed,
                    SuccessStatus {

                    }
                )
            }

            btnOff.setOnClickListener {
                isOn = false
                SmartSdk.learnIrDeviceHandler().testAcIrRemote(
                    (spinnerHub.selectedItem as IoTDevice).uuid,
                    ioTIrRemote!!.rid,
                    isOn,
                    acMode,
                    currentTemp,
                    fanSpeed,
                    SuccessStatus {

                    }
                )
            }

            btnPlus.setOnClickListener {
                if(currentTemp < tempMax) {
                    currentTemp++
                }
                SmartSdk.learnIrDeviceHandler().testAcIrRemote(
                    (spinnerHub.selectedItem as IoTDevice).uuid,
                    ioTIrRemote!!.rid,
                    isOn,
                    acMode,
                    currentTemp,
                    fanSpeed,
                    SuccessStatus {

                    }
                )
            }

            /*
            * Get codes that users want to learn
            * */
            btnConfirm.setOnClickListener {
                setUpRemoteDialog.show()
            }
        }
    }

    /*
    * To learn a code
    * Para 1: Hub device 's UUID
    * Para 2: Code(Int Type)
    */

    private fun setUpDialogSetUpRemote() {
        val btnContinueSetUp = setUpRemoteDialog.findViewById<AppCompatButton>(R.id.btn_continue)
        val groupSpinner = setUpRemoteDialog.findViewById<Spinner>(R.id.spinner_group)
        val lnManufacturer = setUpRemoteDialog.findViewById<LinearLayout>(R.id.ln_manufacturer)
        val edtDeviceName = setUpRemoteDialog.findViewById<EditText>(R.id.edt_device_name)
        lnManufacturer.visibility = View.GONE
        groupSpinner.adapter = groupSpinnerAdapter
        btnContinueSetUp.setOnClickListener {
            setUpRemoteDialog.dismiss()
            dialogLoading.show()
        }
        setUpRemoteDialog.setCanceledOnTouchOutside(true)
        val window = setUpRemoteDialog.window ?: return
        window.setGravity(Gravity.BOTTOM)
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}