package com.example.learntestcomplete

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.with
import androidx.lifecycle.lifecycleScope
import com.example.learntestcomplete.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {

            binding.tvReplacementWord.text = "Good job!"
            binding.tvReplacementAndroid.text = "Good job!"

            lifecycleScope.launch {
                var timerValue = binding.tvTimer.text.toString().toIntOrNull() ?: 0

                while (timerValue < 1000) {
                    delay(1000L)
                    timerValue++
                    binding.tvTimer.text = timerValue.toString()
                }
            }

            val fadeInAnimator = ObjectAnimator.ofFloat(binding.tvReplacementWord, "alpha", 0f, 1f)
            fadeInAnimator.duration = 1000
            fadeInAnimator.start()

            val slideUpFadeInAnimation =
                AnimationUtils.loadAnimation(this, R.anim.slide_up_and_fade_in)
            binding.tvReplacementAndroid.startAnimation(slideUpFadeInAnimation)

            binding.imageView.setImageResource(R.drawable.cat)

        }

        binding.btnClick2.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.dog)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ExampleFragment())
            .commit()

        binding.imageView.setOnClickListener {
            val imageView = binding.imageView
            Glide.with(this)
                .asGif()
                .load(R.drawable.cat2)
                .into(imageView)
        }
    }
}