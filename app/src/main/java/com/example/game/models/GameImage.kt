package com.example.game.models

data class GameImage(
    val id: Long,
    val type: ImageType,
    var isConnected: Boolean = false,
    var isOpened: Boolean = false,
    var isClickable: Boolean = true,
)