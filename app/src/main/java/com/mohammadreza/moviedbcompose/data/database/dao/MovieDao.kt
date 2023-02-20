package com.mohammadreza.moviedbcompose.data.database.dao

import androidx.room.*
import com.mohammadreza.moviedbcompose.data.model.MovieLikeModel

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@Dao
interface MovieDao {

    @Query("SELECT * FROM tbl_movie_like WHERE id = :id")
   suspend fun get(id:Int): MovieLikeModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun like(model: MovieLikeModel): Long

}