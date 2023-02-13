package abbosbek.mobiler.daggerhilt.adapter

import abbosbek.mobiler.daggerhilt.databinding.ImageLayoutBinding
import abbosbek.mobiler.daggerhilt.models.ImageItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    private val diffCallBack = object : DiffUtil.ItemCallback<ImageItem>(){
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallBack)

    fun submitList(list: List<ImageItem>) = differ.submitList(list)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val currImage = differ.currentList[position]

        holder.binding.apply {
            textView.text = currImage.description

            val imageLink = currImage.urls.regular
            image.load(imageLink){
                crossfade(true)
                crossfade(1000)
                scale(Scale.FILL)
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}