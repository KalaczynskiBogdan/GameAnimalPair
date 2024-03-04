package com.example.game.game.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.game.databinding.ItemGameImageBinding
import com.example.game.models.GameImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ImageAdapter(
    private val clickEvent: (GameImage) -> Unit,
    private val context: Context,
    private val victoryClickedListener: () -> Unit,
) : RecyclerView.Adapter<ImageVH>() {

    private val items: ArrayList<GameImage> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        val binding = ItemGameImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageVH(binding,context, clickEvent)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        holder.bind(items[position])
    }

    fun addAllNotify(gameImages: List<GameImage>) {
        items.clear()
        items.addAll(gameImages)
        notifyDataSetChanged()
    }

    fun openOrCloseImage(image: GameImage) {
        val position = items.indexOfFirst { it.id == image.id }
        val clickedImage = items[position]
        clickedImage.isOpened = clickedImage.isOpened.not()
        notifyItemChanged(position)

        if (clickedImage.isOpened) {
            val otherOpenedImages = items.filter { it.isOpened && it.isConnected.not() }
            if (otherOpenedImages.size < 2) return
            val isCorrectChoice = otherOpenedImages.all { it.type == clickedImage.type }

            if (isCorrectChoice) {
                clickedImage.isConnected = true
                otherOpenedImages.map { it.isConnected = true }
            }
        }

        if (items.all { it.isConnected }) {
            victoryClickedListener()
        }

        CoroutineScope(Dispatchers.Main).launch {
            if (items.count { it.isOpened && it.isConnected.not() } >= 2) {
                items.map { it.isClickable = false }
                notifyDataSetChanged()
                delay(1000)
                resetShownImages()
                items.map { it.isClickable = true }
                notifyDataSetChanged()
            }
        }
    }

    private fun resetShownImages() {
        items.map { it.isOpened = false }
        notifyDataSetChanged()
    }
}