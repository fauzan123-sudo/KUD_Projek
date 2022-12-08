package com.example.penjualan.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "post"
)

data class Post (
    @PrimaryKey(autoGenerate = true)
    val uid:Int? = null,
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)