package com.example.autoserviceandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingLocationActivity : AppCompatActivity() {

    private var selectedWorkshop: Workshop? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_location)
        
        // Back button
        findViewById<android.view.View>(R.id.ivBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Setup RecyclerView
        val rvWorkshops = findViewById<RecyclerView>(R.id.rvWorkshops)
        rvWorkshops.layoutManager = LinearLayoutManager(this)

        val dummyWorkshops = listOf(
            Workshop("1", "Bengkel Sejahtera Motor", "1.2 km", "4.8", "Jl. Ahmad Yani No. 12, Jakarta", R.drawable.bg_workshop),
            Workshop("2", "Bengkel AutoCare", "2.4 km", "4.6", "Jl. Sudirman No. 45, Jakarta", R.drawable.bg_workshop),
            Workshop("3", "Bengkel Prima Service", "3.1 km", "4.5", "Jl. Gatot Subroto No. 88, Jakarta", R.drawable.bg_workshop)
        )

        val adapter = WorkshopAdapter(dummyWorkshops) { workshop ->
            selectedWorkshop = workshop
        }
        rvWorkshops.adapter = adapter

        // Setup Lanjutkan Button
        findViewById<Button>(R.id.btnLanjutkan).setOnClickListener {
            if (selectedWorkshop != null) {
                // For now, just navigate to the next screen
                val intent = Intent(this, BookingConfirmationActivity::class.java)
                // You would typically pass the selected data via Intent extras here
                // intent.putExtra("WORKSHOP_NAME", selectedWorkshop?.name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Silakan pilih bengkel terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
