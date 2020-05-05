package com.example.messenger

import android.util.Log
import android.widget.Toast
import com.example.messenger.constants.AppInfoConstants
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.ObservableConverter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * @author bsgreentea
 */

object NaverLoginManager : OAuthLoginHandler() {

    private const val OAUTH_CLIENT_ID = "nJg0Pj0f2rEBCBQSZe6s"
    private const val OAUTH_CLIENT_SECRET = "2Ca8FHXsj4"
    private val context = MessengerApp.applicationContext()
    private var loginInstance = OAuthLogin.getInstance().apply {
        init(
            context,
            OAUTH_CLIENT_ID,
            OAUTH_CLIENT_SECRET,
            AppInfoConstants.AppName
        )
    }

    fun loginInstance(): OAuthLogin {
        return loginInstance
    }

    fun naverLogout() {
        loginInstance?.logout(context)
    }

    override fun run(success: Boolean) {
        if (success) {
            val accessToken = loginInstance?.getAccessToken(context).toString()
            val refreshToken = loginInstance?.getRefreshToken(context).toString()
            val loginState = loginInstance?.getState(context).toString()
            Toast.makeText(context, "로그인되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            val errorCode = loginInstance?.getLastErrorCode(context)?.code.toString()
            val descCode = loginInstance?.getLastErrorDesc(context).toString()
            Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteToken() {

//        Observable.just("1")
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(getObserver())

        Schedulers.newThread().createWorker().schedule {
            loginInstance.logoutAndDeleteToken(context)
        }
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                Toast.makeText(context, "탈퇴되었습니다", Toast.LENGTH_SHORT).show()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                loginInstance.logoutAndDeleteToken(context)
                val errorCode = loginInstance?.getLastErrorCode(context)?.code.toString()
                val descCode = loginInstance?.getLastErrorDesc(context).toString()
                Log.d("delete_error", errorCode + " / " + descCode)
            }

            override fun onError(e: Throwable) {
//                Toast.
            }
        }
    }
}