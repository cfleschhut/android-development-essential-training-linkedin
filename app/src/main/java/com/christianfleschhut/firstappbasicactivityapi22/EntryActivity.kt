package com.christianfleschhut.firstappbasicactivityapi22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christianfleschhut.firstappbasicactivityapi22.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enterButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}