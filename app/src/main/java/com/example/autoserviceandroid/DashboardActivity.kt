package com.example.autoserviceandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var llNavHome: View
    private lateinit var llNavBooking: View
    private lateinit var llNavHistory: View
    private lateinit var llNavProfile: View

    private lateinit var ivNavHome: ImageView
    private lateinit var ivNavBooking: ImageView
    private lateinit var ivNavHistory: ImageView
    private lateinit var ivNavProfile: ImageView

    private lateinit var tvNavHome: TextView
    private lateinit var tvNavBooking: TextView
    private lateinit var tvNavHistory: TextView
    private lateinit var tvNavProfile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initViews()
        setupClickListeners()

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            updateBottomNavUI(0)
        }
    }

    private fun initViews() {
        llNavHome = findViewById(R.id.llNavHome)
        llNavBooking = findViewById(R.id.llNavBooking)
        llNavHistory = findViewById(R.id.llNavHistory)
        llNavProfile = findViewById(R.id.llNavProfile)

        ivNavHome = findViewById(R.id.ivNavHome)
        ivNavBooking = findViewById(R.id.ivNavBooking)
        ivNavHistory = findViewById(R.id.ivNavHistory)
        ivNavProfile = findViewById(R.id.ivNavProfile)

        tvNavHome = findViewById(R.id.tvNavHome)
        tvNavBooking = findViewById(R.id.tvNavBooking)
        tvNavHistory = findViewById(R.id.tvNavHistory)
        tvNavProfile = findViewById(R.id.tvNavProfile)
    }

    private fun setupClickListeners() {
        llNavHome.setOnClickListener {
            loadFragment(HomeFragment())
            updateBottomNavUI(0)
        }

        llNavBooking.setOnClickListener {
            // Booking opens a new activity flow, not a fragment
            val intent = Intent(this, BookingTimeActivity::class.java)
            startActivity(intent)
        }

        llNavHistory.setOnClickListener {
            loadFragment(HistoryFragment())
            updateBottomNavUI(2)
        }

        llNavProfile.setOnClickListener {
            loadFragment(ProfileFragment())
            updateBottomNavUI(3)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun updateBottomNavUI(selectedIndex: Int) {
        val colorPrimary = ContextCompat.getColor(this, R.color.primary_blue)
        val colorGrey = ContextCompat.getColor(this, R.color.text_grey)

        // Reset all to grey
        ivNavHome.setColorFilter(colorGrey)
        tvNavHome.setTextColor(colorGrey)
        
        ivNavBooking.setColorFilter(colorGrey)
        tvNavBooking.setTextColor(colorGrey)
        
        ivNavHistory.setColorFilter(colorGrey)
        tvNavHistory.setTextColor(colorGrey)
        
        ivNavProfile.setColorFilter(colorGrey)
        tvNavProfile.setTextColor(colorGrey)

        // Set selected to primary blue
        when (selectedIndex) {
            0 -> {
                ivNavHome.setColorFilter(colorPrimary)
                tvNavHome.setTextColor(colorPrimary)
            }
            2 -> {
                ivNavHistory.setColorFilter(colorPrimary)
                tvNavHistory.setTextColor(colorPrimary)
            }
            3 -> {
                ivNavProfile.setColorFilter(colorPrimary)
                tvNavProfile.setTextColor(colorPrimary)
            }
        }
    }
}