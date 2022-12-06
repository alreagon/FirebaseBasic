package com.zexceed.tugasakhirdot.activitties

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.zexceed.tugasakhirdot.databinding.ActivityInsertionBinding
import com.zexceed.tugasakhirdot.models.FirebaseModel
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*


class InsertionActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityInsertionBinding
    private lateinit var imageURI: Uri

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

        binding.btnSelectImage.setOnClickListener {
            selectImage()
        }

    }

    private fun selectImage() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {

            imageURI = data?.data!!
            binding.iVGambar.setImageURI(imageURI)

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
            btnSelectImage.isClickable = false
            val editTexts = listOf(etNamaMenu, etHargaMenu, etJumlahMenu)
            for (editText in editTexts) {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(
                        s: CharSequence,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        val et1 = etNamaMenu.text.toString().trim()
                        val et2 = etHargaMenu.text.toString().trim()
                        val et3 = etJumlahMenu.text.toString().trim()

                        btnSaveData.isClickable = et1.isNotEmpty()
                                && et2.isNotEmpty()
                                && et3.isNotEmpty()
                        btnSelectImage.isClickable = et1.isNotEmpty()
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

        } else {

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
            imageUpload()

        }

    }

    private fun imageUpload() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading file...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        //buat format image berdasarkan tanggal
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(imageURI).addOnCompleteListener {

            binding.iVGambar.setImageURI(null) //why null, c, kita udh upload imagenya
            Toast.makeText(this, "Successfully uploaded!", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()


        }.addOnFailureListener {
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this, "Failed uploaded!", Toast.LENGTH_SHORT).show()
        }

    }
}









