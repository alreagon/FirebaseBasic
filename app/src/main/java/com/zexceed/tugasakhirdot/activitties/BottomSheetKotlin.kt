package com.zexceed.tugasakhirdot.activitties

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zexceed.tugasakhirdot.R


class BottomSheetKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_bottom_sheet_kotlin)

//        setTheme(R.style.AppTheme_Dialog)

//        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//        window.decorView.post {
//            val bottomBar =
//                BottomSheetBehavior.from(findViewById(R.id.myBottomSheet))
//            bottomBar.setState(BottomSheetBehavior.STATE_HIDDEN)
//        }


        val view: View? = findViewById(R.id.rooooot)

        if (view != null) {
            // Access the view object here
            val layoutParams = view.layoutParams
        } else {
            // View is null, do something else
            dialogAction()
        }


//        val bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.rooooot))
//        bottomSheetBehavior.isHideable = true
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
//        bottomSheetBehavior.peekHeight = 0



    }


    private fun dialogAction() {

        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
//        val dialog = BottomSheetDialog(this, R.style.AppTheme_Dialog)
        val view1 = layoutInflater.inflate(R.layout.activity_bottom_sheet_kotlin, null)
        val namaMgn = view1.findViewById<TextView>(R.id.tVMgnNamaBsKotlin)
        val hargaMgn = view1.findViewById<TextView>(R.id.tVMgnHargaBsKotlin)
        val deskMgn = view1.findViewById<TextView>(R.id.tVMgnDeskBsKotlin)
        val imgMgn = view1.findViewById<ImageView>(R.id.iVGambarBsKotlin)

        dialog.setCancelable(true)
        dialog.setContentView(view1)
        dialog.show()
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.clearFlags(LayoutParams.MATCH_PARENT)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation

        namaMgn.text = intent.getStringExtra("mgnNama")
        hargaMgn.text = intent.getStringExtra("mgnHarga")
        deskMgn.text = intent.getStringExtra("mgnDesk")
        val iVDetail = intent.getStringExtra("mgnGambar")
        Glide.with(this).load(iVDetail).into(imgMgn)
//        dialog.dismiss()


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        BottomSheetBehavior.from(findViewById(R.id.myBottomSheet)).state =
            BottomSheetBehavior.STATE_HIDDEN
    }


    // this will work depending on the behaviour you want for your users
//    override fun onResume() {
//        super.onResume()
//        BottomSheetBehavior.from(findViewById(R.id.myBottomSheet)).state = BottomSheetBehavior.STATE_HIDDEN
//    }


    // This is enough for you, according to your description
//    override fun onStart() {
//        super.onStart()
//        BottomSheetBehavior.from(findViewById(R.id.myBottomSheet)).state =
//            BottomSheetBehavior.STATE_HIDDEN
//    }

    // this will work depending on the behaviour you want for your users
    override fun onRestart() {
        super.onRestart()
        // BottomSheetBehavior.from(myBottomSheet).state = BottomSheetBehavior.STATE_HIDDEN
    }
}


