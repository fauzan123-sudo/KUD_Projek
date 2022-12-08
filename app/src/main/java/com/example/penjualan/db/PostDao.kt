package com.example.penjualan.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.penjualan.network.response.Post

interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upInsert(post: Post):Long



}