package com.example.messenger

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.messenger.NaverLoginManager.Companion.loginInstance
import com.example.messenger.NaverLoginManager.Companion.naverLogout

/**
 * @author bsgreentea
 */
class UserInfoActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var loginBtn: Button
    lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        init()
    }

    fun init() {
        loginBtn = findViewById(R.id.logout)
        loginBtn.setOnClickListener(this)

        logoutBtn = findViewById(R.id.leave)
        logoutBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.logout -> {
                naverLogout()
                makeToast("로그아웃되었습니다.")
                finish()
            }
            R.id.leave -> {
                DeleteTokenTask().execute()
                makeToast("탈퇴되었습니다.")
                finish()
            }
        }
    }

    class DeleteTokenTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            loginInstance()!!.logoutAndDeleteToken(MessengerApp.applicationContext())
            return null
        }

//        override fun onPostExecute(result: Void?) {
//            super.onPostExecute(result)
//            makeToast()
//        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}