package com.example.chatmekotlin.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.chatmekotlin.R
import com.example.chatmekotlin.databinding.ActivitySplashScreenBinding
import com.example.chatmekotlin.util.AppUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId

class SplashScreen : AppCompatActivity() {

    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var appUtil: AppUtil
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        appUtil = AppUtil()

        Handler().postDelayed({

            if (firebaseAuth?.currentUser == null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                FirebaseInstanceId.getInstance().instanceId
                    .addOnCompleteListener(OnCompleteListener {
                        if (it.isSuccessful) {
                            val token = it.result?.token
                            val databaseReference =
                                FirebaseDatabase.getInstance().getReference("Users")
                                    .child(appUtil.getUID()!!)

                            val map: MutableMap<String, Any> = HashMap()
                            map["token"] = token!!
                            databaseReference.updateChildren(map)
                        }
                    })
                startActivity(Intent(this, DashBoard::class.java))
                finish()
            }

        }, 3000)
    }
}