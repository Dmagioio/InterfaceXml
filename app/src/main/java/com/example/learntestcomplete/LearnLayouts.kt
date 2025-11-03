package com.example.learntestcomplete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learntestcomplete.databinding.ActivityLearnLayoutsBinding

class LearnLayouts : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLayoutsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLayoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}