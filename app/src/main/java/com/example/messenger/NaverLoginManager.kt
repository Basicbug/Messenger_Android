package com.example.messenger

import android.widget.Toast
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

/**
 * @author bsgreentea
 */
class NaverLoginManager {

    companion object {
        private val OAUTH_CLIENT_ID = "nJg0Pj0f2rEBCBQSZe6s"
        private val OAUTH_CLIENT_SECRET = "2Ca8FHXsj4"
        private val context = MessengerApp.applicationContext()
        private var loginInstance: OAuthLogin? = null

        fun loginInstance(): OAuthLogin? {
            if (loginInstance == null) {
                loginInstance = OAuthLogin.getInstance()
                loginInstance!!.init(
                    context,
                    OAUTH_CLIENT_ID,
                    OAUTH_CLIENT_SECRET,
                    R.string.app_name.toString()
                )
            }
            return loginInstance
        }

        fun naverLogout() {
            loginInstance()!!.logout(context)
        }

        val naverLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {

            override fun run(success: Boolean) {

                if (success) {
                    val accessToken = loginInstance()!!.getAccessToken(context).toString()
                    val refreshToken = loginInstance()!!.getRefreshToken(context).toString()
                    val loginState = loginInstance()!!.getState(context).toString()
                    Toast.makeText(context, "로그인되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    val errorCode: String = loginInstance()!!.getLastErrorCode(context).code
                    val descCode: String = loginInstance()!!.getLastErrorDesc(context)
                    Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}