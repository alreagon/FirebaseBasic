package com.zexceed.tugasakhirdot.activitties

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.*
import com.zexceed.tugasakhirdot.R
import com.zexceed.tugasakhirdot.adapters.manganAdapter
import com.zexceed.tugasakhirdot.databinding.ActivityFetchingBinding
import com.zexceed.tugasakhirdot.models.FirebaseModel

class FetchingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFetchingBinding
    private lateinit var mgnList: ArrayList<FirebaseModel>
    private lateinit var dbMgn: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFetchingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setData()
        getManganData()

    }

    private fun setData() {

        binding.apply {
            rvFetching.layoutManager = LinearLayoutManager(this@FetchingActivity)
            rvFetching.setHasFixedSize(true)

        }
        mgnList = arrayListOf<FirebaseModel>()
    }

    private fun getManganData() {
        binding.rvFetching.visibility = View.GONE
        binding.pbFetching.visibility = View.VISIBLE

        dbMgn = FirebaseDatabase.getInstance().getReference("Mangan")

        dbMgn.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mgnList.clear()
                if (snapshot.exists()) {
                    for (mgnSnap in snapshot.children) {
                        val mgnData = mgnSnap.getValue(FirebaseModel::class.java)
                        mgnList.add(mgnData!!)
                    }
                    val mAdapter = manganAdapter(mgnList)
                    binding.rvFetching.adapter = mAdapter

                    mAdapter.setOnClickListener(object : manganAdapter.onItemClickListener {
                        override fun onItemClick(data: FirebaseModel, position: Int) {
                            val intent =
                                Intent(this@FetchingActivity, BottomSheetKotlin::class.java)

//                            val intent = Intent(this@FetchingActivity, dialogAction::)

                            //put Extras
                            intent.putExtra("mgnId", mgnList[position].mgnId)
                            intent.putExtra("mgnNama", mgnList[position].mgnNama)
                            intent.putExtra("mgnHarga", mgnList[position].mgnHarga)
                            intent.putExtra("mgnDesk", mgnList[position].mgnDesk)
                            intent.putExtra("mgnGambar", mgnList[position].mgnImage)


//                            val bottomFragment = BottomSheet()
//                            bottomFragment.show(supportFragmentManager,"TAG")
//                            binding.rvFetching.visibility = View.VISIBLE

//                            BottomSheetBehavior.from(findViewById(R.id.myBottomSheet)).state =
//                                BottomSheetBehavior.STATE_HIDDEN
                            startActivity(intent)

//                            BottomSheetBehavior.from(findViewById(R.id.myBottomSheet)).state =
//                                BottomSheetBehavior.STATE_EXPANDED

//                            dialogAction()
                        }
                    })

                    binding.rvFetching.visibility = View.VISIBLE
                    binding.pbFetching.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

//    private fun dialogAction() {
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
//        binding.apply {
//            namaMgn.text = intent.getStringExtra("mgnNama")
//            hargaMgn.text = intent.getStringExtra("mgnHarga")
//            deskMgn.text = intent.getStringExtra("mgnJumlah")
//            val iVDetail = intent.getStringExtra("mgnGambar")
//            Glide.with(this@FetchingActivity).load(iVDetail).into(imgMgn)
//        }
//    }
}

