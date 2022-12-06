package com.zexceed.tugasakhirdot.activitties

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zexceed.tugasakhirdot.databinding.ActivityManganDetailsBinding

class ManganDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityManganDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManganDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setValuesToViews()
    }

    private fun setValuesToViews() {
//        binding.tvMgnId.text = intent.getStringExtra("mgnId")
        binding.tvMgnNama.text = intent.getStringExtra("mgnNama")
        binding.tvMgnJumlah.text = intent.getStringExtra("mgnJumlah")
        binding.tvMgnHarga.text = intent.getStringExtra("mgnHarga")
    }
}