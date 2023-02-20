package com.mohammadreza.moviedbcompose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohammadreza.moviedbcompose.data.database.dao.MovieDao
import com.mohammadreza.moviedbcompose.data.model.MovieLikeModel


@Database(
    entities = [
        MovieLikeModel::class
    ],
    version = 1
)
abstract class RoomAppDatabase : RoomDatabase() {

    abstract fun getMovieDao() : MovieDao

    companion object {
        val DATABASE_NAME = "db_movie"
    }

}

fun createDataBaseInstance(context: Context): RoomAppDatabase {
    return Room.databaseBuilder(
        context,
        RoomAppDatabase::class.java,
        RoomAppDatabase.DATABASE_NAME
    ).allowMainThreadQueries()
        .build()

}
