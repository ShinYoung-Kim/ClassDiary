package com.ksy.classdiary2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ClassDao {
    @Query("SELECT * FROM tb_Class")
    fun getAll(): List<ClassDBData>

    @Insert
    fun insertAll(vararg classDBData:ClassDBData) : List<Long>

    @Delete
    fun delete(classDBData:ClassDBData)
}