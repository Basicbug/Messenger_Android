package com.example.messenger.ui.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.messenger.NaverLoginManager
import com.example.messenger.R
import com.example.messenger.databinding.ActivityLoginBinding
import com.example.messenger.ui.sample.helper.LoginActionHelper
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton

/**
 * @author bsgreentea
 */

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: OAuthLoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginActionHelper = LoginActionHelper()
        binding.context = this

        initView()
    }

    private fun initView() {
        loginButton = findViewById(R.id.button_login)
        loginButton.setOAuthLoginHandler(NaverLoginManager)
    }

    // for the test
    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
    }

//    fun login() {
//        loginInstance.startOauthLoginActivity(this@LoginActivity, naverLoginHandler)
//    }
}