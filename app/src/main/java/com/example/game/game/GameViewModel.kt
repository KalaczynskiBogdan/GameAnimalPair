package com.example.game.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.game.models.GameImage
import com.example.game.models.ImageType
import kotlin.random.Random

class GameViewModel : ViewModel() {

    val gameImagesLD = MutableLiveData<List<GameImage>>()

    init {
        generateGameImages()
    }

    fun restartGame() {
        generateGameImages()
    }

    private fun generateGameImages() {
        val resultList = arrayListOf<GameImage>()

        repeat(2) {
            ImageType.entries.forEach {
                resultList.add(
                    GameImage(
                        id = Random.nextLong(0, 1_000_000_000),
                        type = it,
                    )
                )
            }
        }
        gameImagesLD.value = resultList.shuffled()
    }
}