package com.example.learntestcomplete

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learntestcomplete.databinding.ActivityLearnLayoutsBinding

class LearnLayouts : AppCompatActivity() {

    private lateinit var binding: ActivityLearnLayoutsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearnLayoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val users = listOf(
            User("Alice", "alice@gmail.com"),
            User("Bob", "bob@gmail.com"),
            User("Charlie", "charlie@gmail.com"),
            User("Diana", "diana@gmail.com")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = UserAdapter(users)
    }
}
