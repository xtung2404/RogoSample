package com.example.rogosample.UI.device.ir

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.AcControlAdapter
import com.example.rogosample.adapter.AddRemoteTypeSpinnerAdapter
import com.example.rogosample.adapter.DeviceSpinnerAdapter
import com.example.rogosample.adapter.GroupSpinnerAdapter
import com.example.rogosample.adapter.ManufacturerSpinnerAdapter
import com.example.rogosample.adapter.TimeSpinnerAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.base.getDeviceName
import com.example.rogosample.databinding.FragmentLearnIrAcBinding
import com.example.rogosample.`object`.AcControlItem
import com.example.rogosample.`object`.DeviceLearnIr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rogo.iot.module.rogocore.basesdk.callback.RequestCallback
import rogo.iot.module.rogocore.basesdk.callback.SuccessStatus
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.callback.CheckIrHubInfoCallback
import rogo.iot.module.rogocore.sdk.callback.SuccessCallback
import rogo.iot.module.rogocore.sdk.define.ir.IoTIrPrtc
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
    private val addRemoteTypeList = arrayListOf(
        R.string.raw,
        R.string.protocol
    )
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

    private lateinit var protocolAdapter: TimeSpinnerAdapter
    private val acModeAdapter by lazy {
        AcControlAdapter(isChecked = {
            when(binding.spinnerAddType.selectedItem as Int) {
                R.string.raw -> {
                    if(it.second) {
                        acMode = it.first
                        for(item in acModeList) {
                            item.isChecked = item.itemId == it.first
                        }
                        testCmdRaw()
                    } else {
                        acMode = it.first
                        for(item in acModeList) {
                            item.isChecked = item.itemId == it.first
                        }
                    }
                    notifyChange()
                }
                else -> {

                }
            }
        })
    }

    fun notifyChange() {
        binding.rvAcMode.post {
            acModeAdapter.notifyDataSetChanged()
        }
    }

    fun notifyFanChange() {
        binding.rvFanSpeed.post {
            fanSpeedAdapter.notifyDataSetChanged()
        }
    }

    private val fanSpeedAdapter by lazy {
        AcControlAdapter(isChecked = {
            when(binding.spinnerAddType.selectedItem as Int) {
                R.string.raw -> {
                    if(it.second) {
                        fanSpeed = it.first
                        for(item in fanSpeedList) {
                            item.isChecked = item.itemId == it.first
                        }
                        testCmdRaw()
                    } else {
                        fanSpeed = it.first
                        for(item in fanSpeedList) {
                            item.isChecked = item.itemId == it.first
                        }
                    }
                    notifyFanChange()
                }
                else -> {

                }
            }

        })
    }

    private val addRemoteTypeSpinnerAdapter by lazy {
        AddRemoteTypeSpinnerAdapter(
            requireContext(),
            addRemoteTypeList)
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
            spinnerAddType.adapter = addRemoteTypeSpinnerAdapter
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

            spinnerAddType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    parent?.let {
                        when((it.getItemAtPosition(position) as Int)) {
                            R.string.protocol -> {
                                lnProtocol.visibility = View.VISIBLE
                            }
                            else -> {
                                lnProtocol.visibility = View.GONE
                            }
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

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
                        when(spinnerAddType.selectedItem as Int) {
                            R.string.protocol -> {
                                protocolAdapter = TimeSpinnerAdapter(
                                    requireContext(),
                                    IoTIrPrtc.getPrtcAcByManufacturer((it.getItemAtPosition(position) as Map.Entry<String, Int>).value).toList()
                                )
                            }
                            else -> {
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
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

            btnMinus.setOnClickListener{
                if(currentTemp > tempMin) {
                    currentTemp--
                }
                when(spinnerAddType.selectedItem as Int) {
                    R.string.raw -> {
                        testCmdRaw()
                    }
                    else -> {
                        testCmdProtocol()
                    }
                }
            }

            btnOn.setOnClickListener {
                isOn = true
                when(spinnerAddType.selectedItem as Int) {
                    R.string.raw -> {
                        testCmdRaw()
                    }
                    else -> {
                        testCmdProtocol()
                    }
                }
            }

            btnOff.setOnClickListener {
                isOn = false
                when(spinnerAddType.selectedItem as Int) {
                    R.string.raw -> {
                        testCmdRaw()
                    }
                    else -> {
                        testCmdProtocol()
                    }
                }
            }

            btnPlus.setOnClickListener {
                if(currentTemp < tempMax) {
                    currentTemp++
                }
                when(spinnerAddType.selectedItem as Int) {
                    R.string.raw -> {
                        testCmdRaw()
                    }
                    else -> {
                        testCmdProtocol()
                    }
                }
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
            when(binding.spinnerAddType.selectedItem as Int) {
                R.string.raw -> {
                    addRemoteRaw(
                        edtDeviceName.text.toString(),
                        (groupSpinner.selectedItem as IoTGroup).uuid
                    )
                }
                R.string.protocol -> {
                    addRemoteProtocol(
                        edtDeviceName.text.toString(),
                        (groupSpinner.selectedItem as IoTGroup).uuid
                    )
                }
            }
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

    private fun testCmdRaw() {
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

    private fun testCmdProtocol() {
        binding.apply {
            SmartSdk.learnIrDeviceHandler().testAcIrPrtc(
                (spinnerHub.selectedItem as IoTDevice).uuid,
                (spinnerProtocol.selectedItem as Int),
                isOn,
                acMode,
                currentTemp,
                fanSpeed
            )
        }
    }

    private fun addRemoteRaw(deviceName: String, groupUuid: String) {
        SmartSdk.learnIrDeviceHandler().addIrRemote(
            (binding.spinnerHub.selectedItem as IoTDevice).uuid,
            ioTIrRemote,
            deviceName,
            groupUuid,
            object : SuccessCallback<IoTDevice> {
                override fun onFailure(errorCode: Int, message: String?) {
                    dialogLoading.dismiss()
                    message?.let {
                        showNoti(it)
                    }
                }

                override fun onSuccess(item: IoTDevice?) {
                    dialogLoading.dismiss()
                    showNoti(R.string.add_success)
                }

            }
        )
    }

    private fun addRemoteProtocol(deviceName: String, groupUuid: String) {
        dialogLoading.show()
        protocolInfo?.irp = binding.spinnerProtocol.selectedItem as Int
        protocolInfo?.manufacturer = (binding.spinnerManufacture.selectedItem as Map.Entry<String, Int>).value
        protocolInfo?.deviceType = DeviceLearnIr.AIRCON.type

        ioTIrRemote?.tempRange = intArrayOf(tempMax, tempMin)
        for(item in fanSpeedList) {
            if(item.isChecked == true) {
                ioTIrRemote?.fans?.plus(item.itemId)
            }
        }
        for(item in acModeList) {
            if(item.isChecked == true) {
                ioTIrRemote?.modes?.plus(item.itemId)
            }
        }
        SmartSdk.learnIrDeviceHandler().addAcIrRemote(
            (binding.spinnerHub.selectedItem as IoTDevice).uuid,
            deviceName,
            groupUuid,
            protocolInfo,
            ioTIrRemote!!,
            object: SuccessCallback<IoTDevice> {
                override fun onFailure(errorCode: Int, message: String?) {
                    dialogLoading.dismiss()
                    message?.let {
                        showNoti(it)
                    }
                }

                override fun onSuccess(item: IoTDevice?) {
                    dialogLoading.dismiss()
                    showNoti(R.string.add_success)
                }

            }
        )
    }
}