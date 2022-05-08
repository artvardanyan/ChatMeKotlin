package com.example.chatmekotlin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chatmekotlin.fragment.ChatFragment
import com.example.chatmekotlin.fragment.ContactFragment
import com.example.chatmekotlin.fragment.ProfileFragment
import com.example.chatmekotlin.R
import com.example.chatmekotlin.databinding.ActivityDashBoardBinding
import com.example.chatmekotlin.databinding.ActivityEditNameBindingImpl
import com.example.chatmekotlin.util.AppUtil
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoard : AppCompatActivity() {

    private var fragment: Fragment? = null
    private lateinit var appUtil: AppUtil
    private lateinit var binding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appUtil = AppUtil()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.dashboardContainer, ChatFragment()).commit()
            binding.bottomChip.setItemSelected(R.id.btnChat)
        }

        binding.bottomChip.setOnItemSelectedListener { id ->
            when (id) {
                R.id.btnChat -> {
                    fragment = ChatFragment()
                }

                R.id.btnProfile -> {
                    fragment = ProfileFragment();
                }

                R.id.btnContact -> fragment = ContactFragment()
            }

            fragment.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.dashboardContainer, fragment!!)
                    .commit()
            }
        }

    }

    override fun onPause() {
        super.onPause()
        appUtil.updateOnlineStatus("offline")
    }

    override fun onResume() {
        super.onResume()
        appUtil.updateOnlineStatus("online")
    }

}



