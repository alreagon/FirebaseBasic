package com.zexceed.tugasakhirdot.activitties

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.zexceed.tugasakhirdot.databinding.ActivityInsertionBinding
import com.zexceed.tugasakhirdot.models.FirebaseModel


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

        binding.apply {
            if (mgnNama.isEmpty()) {
                etNamaMenu.error = "Nama jgn kosong!"
            }
            if (mgnJumlah.isEmpty()) {
                etJumlahMenu.error = "Jumlah jgn kosong!"
            }
            if (mgnHarga.isEmpty()) {
                etHargaMenu.error = "Harga jgn kosong!"
            }

            btnSaveData.isClickable = false
            val editTexts = listOf(etNamaMenu, etHargaMenu, etJumlahMenu)
            for (editText in editTexts) {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        val et1 = etNamaMenu.text.toString().trim()
                        val et2 = etHargaMenu.text.toString().trim()
                        val et3 = etJumlahMenu.text.toString().trim()

                        btnSaveData.isClickable = et1.isNotEmpty()
                                && et2.isNotEmpty()
                                && et3.isNotEmpty()

                    }

                    override fun beforeTextChanged(
                        s: CharSequence, start: Int, count: Int, after: Int
                    ) {

                    }

                    override fun afterTextChanged(
                        s: Editable
                    ) {
                    }
                })
            }

        }

        if (mgnHarga.isEmpty() || mgnJumlah.isEmpty() || mgnHarga.isEmpty()) {
            Toast.makeText(this, "Data gk boleh kosong!", Toast.LENGTH_SHORT).show()

        }else{

            val mgnId = dbRef.push().key!!
            val mangan = FirebaseModel(mgnId, mgnNama, mgnJumlah, mgnHarga)

            //ngebuat child dari id nya
            dbRef.child(mgnId).setValue(mangan)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()

                    binding.etNamaMenu.text!!.clear()
                    binding.etJumlahMenu.text!!.clear()
                    binding.etHargaMenu.text!!.clear()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
        }


    }
}









