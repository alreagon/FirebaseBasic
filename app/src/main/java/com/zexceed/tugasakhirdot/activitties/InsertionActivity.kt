package com.zexceed.tugasakhirdot.activitties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.zexceed.tugasakhirdot.models.FirebaseModel
import com.zexceed.tugasakhirdot.databinding.ActivityInsertionBinding

class InsertionActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityInsertionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //initialization
        dbRef = FirebaseDatabase.getInstance().getReference("Mangan")

        binding.btnSaveData.setOnClickListener {
            saveManganData()
        }


    }

    private fun saveManganData() {
        //getting values
        val mgnNama = binding.etNamaMenu.text.toString()
        val mgnJumlah = binding.etJumlahMenu.text.toString()
        val mgnHarga = binding.etHargaMenu.text.toString()


        if (mgnNama.isEmpty()) {
            binding.etNamaMenu.error = "Nama jgn kosong!"
        }
        if (mgnJumlah.isEmpty()) {
            binding.etJumlahMenu.error = "Jumlah jgn kosong!"
        }
        if (mgnHarga.isEmpty()) {
            binding.etHargaMenu.error = "Harga jgn kosong!"
        }

        val mgnId = dbRef.push().key!!

        val mangan = FirebaseModel(mgnId, mgnNama, mgnJumlah, mgnHarga)

        //ngebuat shild dari id nya
        dbRef.child(mgnId).setValue(mangan)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()

                binding.etNamaMenu.text.clear()
                binding.etJumlahMenu.text.clear()
                binding.etHargaMenu.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
            }

    }


}









