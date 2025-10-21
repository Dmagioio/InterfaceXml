package com.example.learntestcomplete

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.learntestcomplete.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import coil.load


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.load("https://www.nylabone.com/-/media/project/oneweb/nylabone/images/dog101/10-intelligent-dog-breeds/golden-retriever-tongue-out.jpg?h=430&w=710&hash=7FEB820D235A44B76B271060E03572C7")

        binding.imageView.alpha = 0f
        val fadeInAnimator = ObjectAnimator.ofFloat(binding.imageView, "alpha", 0f, 1f)
        fadeInAnimator.duration = 1000
        fadeInAnimator.start()

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

            binding.imageView.alpha = 0f
            val fadeInAnimator2 = ObjectAnimator.ofFloat(binding.imageView, "alpha", 0f, 1f)
            fadeInAnimator2.duration = 1000
            fadeInAnimator2.start()

        }

        binding.btnClick2.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.dog)
            binding.imageView.alpha = 0f
            val fadeInAnimator = ObjectAnimator.ofFloat(binding.imageView, "alpha", 0f, 1f)
            fadeInAnimator.duration = 1000
            fadeInAnimator.start()
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