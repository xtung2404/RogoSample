package com.example.rogosample.base

//import com.google.firebase.auth.AuthCredential
import rogo.iot.module.rogocore.basesdk.auth.method.IAuthForgotMethod
import rogo.iot.module.rogocore.basesdk.auth.method.IAuthSignInMethod
import rogo.iot.module.rogocore.basesdk.auth.method.IAuthSignUpMethod

sealed class SignInMethod: IAuthSignInMethod {
//    class signInWithCredential(var credential: AuthCredential): SignInMethod()

    class signInWithEmail(var email: String, password: String): SignInMethod()

}

sealed class SignUpMethod: IAuthSignUpMethod {
    class SignUpMethod(var email: String, var password: String): com.example.rogosample.base.SignUpMethod()
}
sealed class ForgotPasswordMethod: IAuthForgotMethod {
    class ForgotPasswordMethod(var email: String): com.example.rogosample.base.ForgotPasswordMethod()
}