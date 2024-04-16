package com.example.rogosample.UI.authen

import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.rogosample.R
import com.example.rogosample.base.BaseFragment
import com.example.rogosample.databinding.FragmentSignInBinding
import rogo.iot.module.rogocore.basesdk.ILogR
import rogo.iot.module.rogocore.basesdk.auth.callback.AuthRequestCallback
import rogo.iot.module.rogocore.sdk.SmartSdk
import rogo.iot.module.rogocore.sdk.entity.auth.SignInDefaultEmailPhoneMethod

class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_sign_in

    override fun initVariable() {
        super.initVariable()
        binding.apply {
            toolbar.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            toolbar.txtTitle.text = resources.getString(R.string.sign_in)
            edtUsername.setText("nx1415")
            edtEmail.setText("nhacxuan1415@gmail.com")
            edtPassword.setText("123456")
        }
    }

    override fun initAction() {
        super.initAction()
        binding.apply {
            /*
            * sign in with email, password, username
            * */
            btnSignIn.setOnClickListener {
                dialogLoading.show()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val username = edtUsername.text.toString()
                if(email.isEmpty() || password.isEmpty()) {
                    showNoti(R.string.check_your_information)
                } else {
                    SmartSdk.signIn(SignInDefaultEmailPhoneMethod(username, email, password), object : AuthRequestCallback{
                        override fun onSuccess() {
                            dialogLoading.dismiss()
                            lnOptions.visibility = View.VISIBLE
                        }

                        override fun onFailure(p0: Int, p1: String?) {
                            dialogLoading.dismiss()
                            p1?.let {
                                showNoti(it)
                            }
                        }
                    })
                }
            }
            btnToLocation.setOnClickListener {
                findNavController().navigate(R.id.locationFunctionFragment)
            }
            btnToSignOut.setOnClickListener {
                findNavController().navigate(R.id.signOutFragment)
            }
        }
    }
}