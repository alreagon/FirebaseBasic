package com.zexceed.tugasakhirdot.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zexceed.tugasakhirdot.databinding.ListItemBinding
import com.zexceed.tugasakhirdot.models.FirebaseModel

class manganAdapter(private val mgnList: ArrayList<FirebaseModel>) :
    RecyclerView.Adapter<manganAdapter.ViewHolder>() {

    class ViewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val manganResponse = mgnList[position]
        val nama = manganResponse.mgnNama
        val jumlah = manganResponse.mgnJumlah
        val harga = manganResponse.mgnHarga

        Log.d("adapter", "nama mangan Adapter : $nama")
        holder.binding.apply {
            etHargaMenu.text = harga
            etJumlahMenu.text = jumlah
            etNamaMenu.text = nama
        }
    }

    override fun getItemCount() = mgnList.size

//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    }

}