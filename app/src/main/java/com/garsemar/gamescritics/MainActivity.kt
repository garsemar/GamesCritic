package com.garsemar.gamescritics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GamesCritics)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, ListFragment())
            setReorderingAllowed(true)
            addToBackStack("name") // name can be null
            commit()
        }

    }
}