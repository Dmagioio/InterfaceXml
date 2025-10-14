package com.example.learntestcomplete

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learntestcomplete.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            binding.tvReplacementWord.text = "Dood job!"
            binding.tvReplacementAndroid.text = "Dood job!"

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

            val slideUpFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_and_fade_in)
            binding.tvReplacementAndroid.startAnimation(slideUpFadeInAnimation)
        }
    }
}
