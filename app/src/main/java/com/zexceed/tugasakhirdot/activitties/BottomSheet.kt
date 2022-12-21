package com.zexceed.tugasakhirdot.activitties

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zexceed.tugasakhirdot.R
import com.zexceed.tugasakhirdot.databinding.FragmentBottomSheetBinding

class BottomSheet : BottomSheetDialogFragment() {

//    private lateinit var binding : FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view =  inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
//        val username = view.iv
        return inflater.inflate(R.layout.fragment_bottom_sheet,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setValuesToViews()

    }

//    private fun setValuesToViews() {
//
//
//        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
//        val view1 = layoutInflater.inflate(R.layout.fragment_bottom_sheet, null)
//        val namaMgn = view1.findViewById<TextView>(R.id.tVMgnNamaBsKot)
//        val hargaMgn = view1.findViewById<TextView>(R.id.tVMgnHargaBs)
//        val deskMgn = view1.findViewById<TextView>(R.id.tVMgnDeskBs)
//        val imgMgn = view1.findViewById<ImageView>(R.id.iVGambarBs)
//
//        dialog.setCancelable(true)
//        dialog.setContentView(view1)
//        dialog.show()
//        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
//
//        namaMgn.text = requireActivity().intent.getStringExtra("mgnNama")
////        namaMgn.text = intent.getStringExtra("mgnNama")
////        hargaMgn.text = intent.getStringExtra("mgnHarga")
////        deskMgn.text = intent.getStringExtra("mgnJumlah")
//        val iVDetail = requireActivity().intent.getStringExtra("mgnGambar")
//        Glide.with(requireContext()).load(iVDetail).into(imgMgn)
//
//    }

}