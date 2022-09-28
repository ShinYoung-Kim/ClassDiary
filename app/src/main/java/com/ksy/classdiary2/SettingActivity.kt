package com.ksy.classdiary2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity() : AppCompatActivity() {
    var classByDayList = arrayListOf<ClassByDayItem>(
        ClassByDayItem("월요일"),
        ClassByDayItem("화요일"),
        ClassByDayItem("수요일"),
        ClassByDayItem("목요일"),
        ClassByDayItem("금요일")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val classByDayAdapter = ClassByDayAdapter(this, classByDayList)
        classByDayListView.adapter = classByDayAdapter

        var db : ClassDB? = null
        var classList = mutableListOf<ClassDBData>()

        db = ClassDB.getInstance(this)

        val savedContents = db!!.classDao().getAll()
        if(savedContents.isNotEmpty()) {
            classList.addAll(savedContents)
        }

        backToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}