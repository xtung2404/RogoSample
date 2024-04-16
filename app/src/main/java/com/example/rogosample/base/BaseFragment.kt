package com.example.rogosample.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.rogosample.R

abstract class BaseFragment<VB: ViewDataBinding>: Fragment() {
    protected lateinit var binding: VB
    abstract val layoutId: Int
    private val dialog by lazy {
        Dialog(requireContext())
    }
    protected val dialogLoading by lazy {
        Dialog(requireContext())
    }

    private lateinit var txtAnnounce: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        dialog.window?.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        dialog.setContentView(R.layout.layout_dialog_noti)
        dialogLoading.window?.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        dialogLoading.setContentView(R.layout.layout_dialog_loading)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        initView()
        initAction()
        setUpDialogNoti()
        setUpDialogLoading()
    }

    open fun initVariable(){}

    open fun initView(){}

    open fun initAction(){}

    fun notify(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showNoti(message: Int) {
        txtAnnounce.text = resources.getString(message)
        dialog.show()
    }

    fun showNoti(message: String) {
        txtAnnounce.text = message
        dialog.show()
    }

    fun dismissNoti() {
        dialog.dismiss()
    }


    private fun setUpDialogNoti() {
        val btnCancel = dialog.findViewById<AppCompatButton>(R.id.btn_ok)
        txtAnnounce = dialog.findViewById<TextView>(R.id.txt_announce)
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCanceledOnTouchOutside(true)
        val window = dialog.window ?: return
        window.setGravity(Gravity.BOTTOM)
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun setUpDialogLoading() {
        dialogLoading.setCanceledOnTouchOutside(false)
        val window = dialogLoading.window ?: return
        window.setGravity(Gravity.BOTTOM)
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog.cancel()
    }
}