package dev.wallacegs.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.wallacegs.flixter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}