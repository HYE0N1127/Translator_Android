package com.hyeonbin.translator_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyeonbin.translator_android.R
import com.hyeonbin.translator_android.databinding.ActivityMainBinding
import com.hyeonbin.translator_android.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}