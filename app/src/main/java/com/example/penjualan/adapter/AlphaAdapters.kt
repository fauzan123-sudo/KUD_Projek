package com.example.penjualan.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualan.R
import kotlinx.android.synthetic.main.item_kategori_obat.view.*

class AlphaAdapters{



}

//class CustomAdapter(var context: Context, var flowerName: Array<String>, var image: IntArray) :
//    BaseAdapter() {
//    private var inflater: LayoutInflater? = null
//    override fun getCount(): Int {
//        return flowerName.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return flowerName[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getView(position: Int, view: View, parent: ViewGroup): View {
//        if (inflater == null)
//            inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val imageView = view.imgKategori
//        val textView = view.txtKategori
//        imageView.setImageResource(image[position])
//        textView.text = flowerName[position]
//        return view
//    }
//}