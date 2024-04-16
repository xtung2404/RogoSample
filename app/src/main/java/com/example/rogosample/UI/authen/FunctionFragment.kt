package com.example.rogosample.UI.authen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.adapter.FunctionAdapter
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentFunctionBinding
import com.example.rogosample.`object`.Function
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.auth.VerifyDefaultForgotPasswordMethod


class FunctionFragment : BaseFragment<FragmentFunctionBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_function

    private val functionAdapter by lazy {
        FunctionAdapter(onItemClick = {
            when(it) {
                Function.SIGNIN -> {
                    findNavController().navigate(R.id.signInFragment)
                }
                Function.SIGNUP -> {
                    findNavController().navigate(R.id.signUpFragment)
                }
                Function.FORGOTPASSWORD -> {
                    findNavController().navigate(R.id.forgotPasswordFragment)
                }
                else -> {
                    findNavController().navigate(R.id.signOutFragment)
                }
            }
        })
    }

    override fun initVariable() {
        super.initVariable()
        binding.rvFunction.adapter = functionAdapter
        functionAdapter.submitList(Function.getAuthenFuncs())
    }
}