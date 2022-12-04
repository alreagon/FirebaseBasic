package com.zexceed.tugasakhirdot.activitties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.zexceed.tugasakhirdot.adapters.manganAdapter
import com.zexceed.tugasakhirdot.databinding.ActivityFetchingBinding
import com.zexceed.tugasakhirdot.models.FirebaseModel

class FetchingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFetchingBinding
    private lateinit var mgnList : ArrayList<FirebaseModel>
    private lateinit var dbMgn : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFetchingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvFetching.layoutManager = LinearLayoutManager(this)
        binding.rvFetching.setHasFixedSize(true)

        mgnList = arrayListOf<FirebaseModel>()

        getManganData()

    }

    private fun getManganData() {
        binding.rvFetching.visibility = View.GONE
        binding.pbFetching.visibility = View.VISIBLE

        dbMgn = FirebaseDatabase.getInstance().getReference("Mangan")

        dbMgn.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mgnList.clear()
                if (snapshot.exists()){
                    for (mgnSnap in snapshot.children){
                        val mgnData = mgnSnap.getValue(FirebaseModel::class.java)
                        mgnList.add(mgnData!!)
                    }
                    val mAdapter = manganAdapter(mgnList)
                    binding.rvFetching.adapter = mAdapter

                    binding.rvFetching.visibility = View.VISIBLE
                    binding .pbFetching.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })







    }
}