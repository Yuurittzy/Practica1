package com.example.practica1_m4_yuritzy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practica1_m4_yuritzy.databinding.ActivityMainBinding
import com.example.practica1_m4_yuritzy.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundle(intent.extras)
    }

    private fun getBundle(bundle: Bundle?) {
        binding.result.text = bundle?.getString("RESULT")
    }

}