package com.example.messenger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton

/**
 * @author bsgreentea
 */

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: OAuthLoginButton
    lateinit var testButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        testButton = findViewById(R.id.test_button)
        testButton.setOnClickListener {
            startActivityForResult(Intent(this, UserInfoActivity::class.java), 1)
        }
    }

    // for the test
    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
    }

    fun init() {
        loginButton = findViewById(R.id.button_login)
        loginButton.setOAuthLoginHandler(NaverLoginManager.naverLoginHandler)
    }

//    fun login() {
//        loginInstance.startOauthLoginActivity(this@LoginActivity, naverLoginHandler)
//    }
}