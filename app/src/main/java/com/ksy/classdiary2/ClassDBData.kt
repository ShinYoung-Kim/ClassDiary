package com.ksy.classdiary2

import androidx.room.*

@Entity(tableName = "tb_Class")
data class ClassDBData (
    @PrimaryKey(autoGenerate = true) val id: Long,
    var className : String,
    var classDay : String
        )