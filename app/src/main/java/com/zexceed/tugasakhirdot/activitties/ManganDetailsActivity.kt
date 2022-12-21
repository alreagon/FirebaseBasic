package com.zexceed.tugasakhirdot.activitties

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zexceed.tugasakhirdot.R
import com.zexceed.tugasakhirdot.databinding.ActivityManganDetailsBinding


class ManganDetailsActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityManganDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityManganDetailsBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)

        setContentView(R.layout.activity_mangan_details)
//        setValuesToViews()

//        binding.btnUpdate.setOnClickListener {
//            openUpdateDialog(
//                intent.getStringExtra("mgnId").toString(),
//                intent.getStringExtra("mgnNama").toString()
//            )
//        }

    }

//    private fun setValuesToViews() {
//
//
//        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
//        val view1 = layoutInflater.inflate(R.layout.bottomsheet, null)
//        val namaMgn = view1.findViewById<TextView>(R.id.tVMgnNamaBs)
//        val hargaMgn = view1.findViewById<TextView>(R.id.tVMgnHargaBs)
//        val deskMgn = view1.findViewById<TextView>(R.id.tVMgnDeskBs)
//        val imgMgn = view1.findViewById<ImageView>(R.id.iVGambarBs)
//
//        dialog.setCancelable(true)
//        dialog.setContentView(view1)
//        dialog.show()
//        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
//
//        namaMgn.text = intent.getStringExtra("mgnNama")
//        hargaMgn.text = intent.getStringExtra("mgnHarga")
//        deskMgn.text = intent.getStringExtra("mgnJumlah")
//        val iVDetail = intent.getStringExtra("mgnGambar")
//        Glide.with(this@ManganDetailsActivity).load(iVDetail).into(imgMgn)
//
//    }

//    private fun openUpdateDialog(mgnId: String?, mgnNama: String?) {
//
////        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
////        val view1 = layoutInflater.inflate(R.layout.bottomsheet, null)
////        val namaProduct = view1.findViewById<TextView>(R.id.namaProdukBtnSheet)
////        val hargaProduct = view1.findViewById<TextView>(R.id.hargaDetailBuyerBtnSheet)
////        val imgProduct = view1.findViewById<ImageView>(R.id.imageBtnsheet)
////
////        dialog.setCancelable(true)
////        dialog.setContentView(view1)
////        dialog.show()
////        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
//
//        val mDialog = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        val mDialogView = inflater.inflate(R.layout.update_dialog, null)
//
//        mDialog.setView(mDialogView)
//
//        val etNama = mDialogView.findViewById<EditText>(R.id.etMgnNama)
//        val etJumlah = mDialogView.findViewById<EditText>(R.id.etMgnJumlah)
//        val etHarga = mDialogView.findViewById<EditText>(R.id.etMgnHarga)
//        val buttonUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)
//
//
//    }

}