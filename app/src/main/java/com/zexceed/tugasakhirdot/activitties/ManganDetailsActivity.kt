package com.zexceed.tugasakhirdot.activitties

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zexceed.tugasakhirdot.databinding.ActivityManganDetailsBinding


class ManganDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManganDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManganDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setValuesToViews()
    }

    private fun setValuesToViews() {
        binding.apply {
            tvMgnNama.text = intent.getStringExtra("mgnNama")
            tvMgnJumlah.text = intent.getStringExtra("mgnJumlah")
            tvMgnHarga.text = intent.getStringExtra("mgnHarga")
            val iVDetail = intent.getStringExtra("mgnGambar")
            Glide.with(this@ManganDetailsActivity).load(iVDetail).into(iVGambarDetail)

        }
    }
}