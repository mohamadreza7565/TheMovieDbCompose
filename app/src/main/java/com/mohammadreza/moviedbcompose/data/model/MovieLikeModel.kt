package com.mohammadreza.moviedbcompose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@Entity(tableName = "tbl_movie_like")
data class MovieLikeModel(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "is_liked")
    private var isLiked: Boolean

){

    fun setLike(isLiked:Boolean){
        this.isLiked = isLiked
    }

    fun getLike() = isLiked

}