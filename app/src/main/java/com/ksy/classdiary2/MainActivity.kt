package com.ksy.classdiary2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {
    var db : ClassDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        var classList = mutableListOf<ClassDBData>()

        db = ClassDB.getInstance(this)!!

        val savedContents = db!!.classDao().getAll()
        if(savedContents.isNotEmpty()) {
            classList.addAll(savedContents)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar = findViewById<CalendarView>(R.id.calendar)

        calendar.setOnDateChangeListener{
            view, year, month, dayOfMonth ->
            val calendarForDay = Calendar.getInstance()
            calendarForDay.set(year, month + 1, dayOfMonth)
            val dayInInt : Int = calendarForDay.get(Calendar.DAY_OF_WEEK)
            val dayOfWeek = when(dayInInt) {
                4 -> "월"
                5 -> "화"
                6 -> "수"
                7 -> "목"
                1 -> "금"
                else -> "nothing"
            }
            setDay(dayOfWeek, db!!)

            Toast.makeText(this, "요일이 선택되었습니다 : " + dayOfWeek, Toast.LENGTH_SHORT).show()
        }

        setSupportActionBar(toolbar)


    }

    fun setDay(dayFromFragment : String, db :ClassDB) {
        val classFragment : ClassFragment = supportFragmentManager.findFragmentById(R.id.classFragment) as ClassFragment
        classFragment.setDay(dayFromFragment, db)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.getItemId()) {
            R.id.menu_setting -> {
                val intent = Intent(getApplicationContext(), SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}