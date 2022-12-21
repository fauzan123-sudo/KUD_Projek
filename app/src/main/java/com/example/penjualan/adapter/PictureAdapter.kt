package com.example.penjualan.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.penjualan.R
//import com.facebook.shimmer.Shimmer
//import com.facebook.shimmer.ShimmerDrawable
//import kotlinx.android.synthetic.main.item_barang.view.*
//import kotlinx.android.synthetic.main.item_pics.view.*
//import kotlinx.android.synthetic.main.row_layout.view.*
//
//class PictureAdapter(private val context : Context) : RecyclerView.Adapter<PictureAdapter.PicsViewHolder>(){
//
//    val limit = 3
//    var dataPic = emptyList<PicsResponseItem>()
//
//    inner class PicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
//
////    private val differCallback = object : DiffUtil.ItemCallback<PicsResponseItem>() {
////        override fun areItemsTheSame(oldItem: PicsResponseItem, newItem: PicsResponseItem): Boolean {
////            return oldItem.id == newItem.id
////        }
////
////        override fun areContentsTheSame(oldItem: PicsResponseItem, newItem: PicsResponseItem): Boolean {
////            return oldItem == newItem
////        }
////    }
////
////    val differ = AsyncListDiffer(this, differCallback)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PicsViewHolder(
//        LayoutInflater.from(parent.context).inflate(
//            R.layout.item_pics,
//            parent,
//            false
//        )
//    )
//    override fun getItemCount() =  dataPic.size
//
//    override fun onBindViewHolder(holder: PicsViewHolder, position: Int) {
////        holder.itemView.ivImage.load(dataPic.download_url)
////        var pic = dataPic[position]
////        holder.itemView.iv_image.load(dataPic[position].download_url){
////
////        }
//        val shimmer = Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
//            .setDuration(1000) // how long the shimmering animation takes to do one full sweep
//            .setBaseAlpha(0.7f) //the alpha of the underlying children
//            .setHighlightAlpha(0.6f) // the shimmer alpha amount
//            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
//            .setAutoStart(true)
//            .build()
//
//// This is the placeholder for the imageView
//        val shimmerDrawable = ShimmerDrawable().apply {
//            setShimmer(shimmer)
//        }
//
//        Glide.with(context).load(dataPic[position].download_url).placeholder(shimmerDrawable).into(holder.itemView.iv_image)
//        holder.itemView.nama_obat.text   = dataPic[position].height.toString()
//        holder.itemView.harga_obat.text = dataPic[position].author
//
////        val picItem = currentList[position]
////        holder.itemView.apply {
//////            ivImage.load(picItem.download_url)
////            tvImageId.text = picItem.id
////            tvImageSize.text = "${picItem.width} x ${picItem.height}"
////            tvImageAuthor.text = picItem.author
////        }
//    }
//
//
//    fun setData(newList: List<PicsResponseItem>){
//        dataPic = newList
//        notifyDataSetChanged()
//    }
//
////    override fun getItemCount(): Int {
////        return if(dataPic.size > limit){
////            limit
////        } else {
////            dataPic.size
////        }
////
////    }
//
//}