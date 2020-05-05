package com.example.messenger.ui.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.messenger.R
import com.example.messenger.databinding.ActivityUserInfoBinding
import com.example.messenger.ui.sample.helper.LoginActionHelper

/**
 * @author bsgreentea
 */
class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityUserInfoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user_info)

        binding.loginActionHelper = LoginActionHelper()
        binding.userInfoActivity = this
    }
}