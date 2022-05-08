package com.example.chatmekotlin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatmekotlin.fragment.GetUserNumber
import com.example.chatmekotlin.R
import com.example.chatmekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, GetUserNumber())
            .commit()
    }
}