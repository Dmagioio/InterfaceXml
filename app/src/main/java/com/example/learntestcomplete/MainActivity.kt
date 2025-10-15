package com.example.learntestcomplete

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learntestcomplete.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


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

                while (isActive) {
                    delay(1000L)
                    timerValue++
                    binding.tvTimer.text = timerValue.toString()
                }
            }

            val fadeInAnimator = ObjectAnimator.ofFloat(binding.tvReplacementWord, "alpha", 0f, 1f)
            fadeInAnimator.duration = 1000
            fadeInAnimator.start()

//            val slideUpFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_and_fade_in)
//            binding.tvReplacementAndroid.startAnimation(slideUpFadeInAnimation)
//
//            val inflater = layoutInflater
//            val layout = inflater.inflate(R.layout.custom_toast, null)
//
//            val toast = Toast(applicationContext)
//            toast.duration = Toast.LENGTH_SHORT
//            toast.view = layout
//            toast.show()

            Snackbar.make(binding.root, "Файл видалено", Snackbar.LENGTH_LONG)
                .setAction("Відмінити") {
                    Toast.makeText(this, "Відмінено", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
        
        Toast.makeText(this, "Вітаємо в нашому додатку!", Toast.LENGTH_LONG).show()
    }
}
