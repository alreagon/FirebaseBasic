package com.zexceed.tugasakhirdot.activitties

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.zexceed.tugasakhirdot.databinding.ActivityInsertionBinding
import com.zexceed.tugasakhirdot.models.FirebaseModel
import java.text.SimpleDateFormat
import java.util.*


class InsertionActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityInsertionBinding
    private lateinit var imageURI: Uri
    private var storageReferenceDude: StorageReference =
        FirebaseStorage.getInstance().getReference()

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

        if (requestCode == 100 && resultCode == RESULT_OK && data !== null) {

            imageURI = data.data!!
            binding.iVGambar.setImageURI(imageURI)
        }
    }

    private fun saveManganData() {
        //getting values
        val mgnNama = binding.etNamaMenu.text.toString()
        val mgnHarga = binding.etHargaMenu.text.toString()
        val mgnDesk = binding.etDeskMenu.text.toString()
//        val mgnIv = binding.iVDeskripsiInsert

        binding.apply {
            if (mgnNama.isEmpty()) {
                etNamaMenu.error = "Nama jgn kosong!"
            }
            if (mgnHarga.isEmpty()) {
                etHargaMenu.error = "Harga jgn kosong!"
            }
            if (mgnHarga.isEmpty()) {
                etDeskMenu.error = "Deskripsi jgn kosong!"
            }

            btnSaveData.isClickable = false
            btnSelectImage.isClickable = false
            val editTexts = listOf(etNamaMenu, etHargaMenu, etDeskMenu)
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
                        val et3 = etDeskMenu.text.toString().trim()

                        btnSaveData.isClickable = et1.isNotEmpty()
                                && et2.isNotEmpty()
                                && et3.isNotEmpty()
                        btnSelectImage.isClickable = et1.isNotEmpty()
                                && et2.isNotEmpty()
                                && et3.isNotEmpty()



//                        mgnIv.text = s.toString()
//                        var size = mgnIv.textSize
//                        size /= 3
//                        editTexts[1].textSize = size
//                        arrayOf(editText)[1].textSize = size
//                        editText.textSize = size

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

        if (mgnHarga.isEmpty() || mgnDesk.isEmpty() || mgnHarga.isEmpty()) {
            Toast.makeText(this, "Data gk boleh kosong!", Toast.LENGTH_SHORT).show()

        } else {

            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Uploading file...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            //buat format image berdasarkan tanggal
            val formatter = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
            val now = Date()
            val fileName = formatter.format(now)
//            val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

            val jadiLink: StorageReference =
                storageReferenceDude.child(fileName + "." + getFileExtension(imageURI))
            //storageReferenceDude.child(System.currentTimeMillis().toString() + "." + getFileExtension(imageURI))
            //storageReferenceDude.child("images/" + UUID.randomUUID().toString())
            jadiLink.putFile(imageURI)
                .addOnSuccessListener {
                    jadiLink.downloadUrl.addOnSuccessListener { uri ->

                        val mgnId = dbRef.push().key!!
                        val mangan =
                            FirebaseModel(mgnId, mgnNama, mgnHarga, mgnDesk, uri.toString())

                        //ngebuat child dari id nya
                        dbRef.child(mgnId).setValue(mangan)
                            .addOnCompleteListener {
                                Toast.makeText(
                                    this,
                                    "Data inserted successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.etNamaMenu.text!!.clear()
                                binding.etHargaMenu.text!!.clear()
                                binding.etDeskMenu.text!!.clear()

                            }.addOnFailureListener { err ->
                                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }
//                        val firebaseModelId = dbRef.push().key
//                        val firebaseModel = FirebaseModel(uri.toString())
//                        dbRef.child(mgnId!!).setValue(mangan)

                        binding.iVGambar.setImageURI(null) //why null, c, kita udh upload imagenya
//                        Toast.makeText(this, "Successfully image uploaded!", Toast.LENGTH_SHORT).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }.addOnFailureListener {
                    if (progressDialog.isShowing) progressDialog.dismiss()
                    Toast.makeText(this, "Failed image uploaded!", Toast.LENGTH_SHORT).show()
                }

        }

    }


    private fun getFileExtension(mUri: Uri): String? {
        val cr = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(mUri))
    }
}









