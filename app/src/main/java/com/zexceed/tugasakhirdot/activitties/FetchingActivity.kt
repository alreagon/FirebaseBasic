package com.zexceed.tugasakhirdot.activitties

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
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
                        override fun onItemClick(data: FirebaseModel,position: Int) {
                            val intent =
                                Intent(this@FetchingActivity, ManganDetailsActivity::class.java)

                            //put Extras
                            intent.putExtra("mgnId",mgnList[position].mgnId)
                            intent.putExtra("mgnNama", mgnList[position].mgnNama)
                            intent.putExtra("mgnJumlah", mgnList[position].mgnJumlah)
                            intent.putExtra("mgnHarga", mgnList[position].mgnHarga)

                            startActivity(intent)
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
}

