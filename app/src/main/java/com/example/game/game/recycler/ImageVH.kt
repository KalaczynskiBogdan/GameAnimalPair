package com.example.game.game.recycler

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.game.R
import com.example.game.databinding.ItemGameImageBinding
import com.example.game.models.GameImage

class ImageVH(
    private val binding: ItemGameImageBinding,
    private val context: Context,
    private val clickEvent: (GameImage) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gameImage: GameImage) {
        with(binding) {
            val shownImage = gameImage.isOpened || gameImage.isConnected

            ivImage.isVisible = shownImage
            ivClosed.isVisible = shownImage.not()

            ivClosed.setImageResource(R.drawable.ic_locked)
            ivImage.setImageResource(gameImage.type.drawableResId)

            if (gameImage.isClickable.not()) {
                ivClosed.setOnClickListener(null)
            } else {
                ivClosed.setOnClickListener {
                    if (shownImage.not()) {
                        val animation = AnimationUtils.loadAnimation(context, R.anim.flip)
                        binding.ivClosed.startAnimation(animation)

                        binding.ivClosed.postDelayed({
                            clickEvent(gameImage)
                        }, animation.duration)

//                        clickEvent(gameImage)
                    }
                }
            }
        }
    }
}