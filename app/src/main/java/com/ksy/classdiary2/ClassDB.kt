package com.ksy.classdiary2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ClassDBData::class], version = 1, exportSchema = false)
abstract class ClassDB :RoomDatabase() {
    abstract fun classDao(): ClassDao

    companion object {
        private var instance: ClassDB? = null

        @Synchronized
        fun getInstance(context: Context) : ClassDB? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClassDB::class.java,
                    "database-class"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}