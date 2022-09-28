package com.ksy.classdiary2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.ListPopupWindow.WRAP_CONTENT

class ClassByDayAdapter
    (val context: Context, val classByDayList : ArrayList<ClassByDayItem>)
    : BaseAdapter() {
    //var classDBHelper = ClassDBHelper.getClassDBHelperInstance(context)
    //var classDatabase = classDBHelper.writableDatabase

    val settingContext = context

    private val createdClassInfoList : ArrayList<CreatedClassInfo>? = null

    override fun getCount(): Int {
        return classByDayList.size
    }

    override fun getItem(p0: Int): Any {
        return classByDayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.class_by_day_item, null)

        val day = view.findViewById<TextView>(R.id.day)
        val addClassEditText = view.findViewById<EditText>(R.id.addClassEditText)
        val addClassBtn = view.findViewById<Button>(R.id.addClassBtn)
        val linearLayoutInListView = view.findViewById<LinearLayout>(R.id.linearLayoutInListView)

        val classByDayItem = classByDayList[p0]
        day.setText(classByDayItem.day)
        addClassEditText.tag = p0
        addClassBtn.tag = p0
        addClassBtn.setText("수업 추가")
        addClassEditText.hint = "수업명을 입력하세요"
        addClassEditText.width = 0
        addClassEditText.height = WRAP_CONTENT
        addClassEditText.gravity = 1

        var db : ClassDB? = null
        var classList = mutableListOf<ClassDBData>()

        db = ClassDB.getInstance(context)

        val savedContents = db!!.classDao().getAll()
        if(savedContents.isNotEmpty()) {
            classList.addAll(savedContents)
        }

        for(i: Int in 0..classList.size - 1) {
            addClass(linearLayoutInListView, classList[i], classByDayItem.day)
        }

        addClassBtn.setOnClickListener {
            val linearLayout = LinearLayout(context)
            val classTextView = TextView(context)
            val classDeleteBtn = ImageView(context)

            classTextView.setText(addClassEditText.text)
            classDeleteBtn.setImageResource(R.drawable.ic_close)
            //linearLayout.orientation = horizontal
            linearLayout.addView(classTextView)
            linearLayout.addView(classDeleteBtn)
            linearLayoutInListView.addView(linearLayout)

            //classDBHelper.insertRecord(classTextView.text.toString(), classByDayItem.day)

            createdClassInfoList?.add(CreatedClassInfo(classByDayItem.day,
                classTextView.text as String
            ))

            var lecture = ClassDBData(0, classTextView.text.toString(), classByDayItem.day)
            //lecture.setId(db?.classDao()?.insertAll(lecture))
            //primary id 문제

            //printTable()

            classDeleteBtn.setOnClickListener{
                classTextView.setVisibility(View.GONE)
                classDeleteBtn.setVisibility(View.GONE)
                db.classDao().delete(lecture)
                //db.classDao().delete(ClassDBData(0, classTextView.text.toString(), classByDayItem.day))
                deleteClass(classByDayItem.day, classTextView.text)
                //var query = "DELETE FROM class WHERE class = (classTextView.text as String);"
                //classDatabase.execSQL(query)
            }
        }

        return view
    }

    fun deleteClass(day: String, className: CharSequence) {
        createdClassInfoList?.remove(CreatedClassInfo(day, className as String))
    }

    fun addClass(linearLayoutInListView : LinearLayout, data : ClassDBData, day : String) {
        if (day.equals(data.classDay)) {
            val linearLayout = LinearLayout(context)
            val classTextView = TextView(context)
            val classDeleteBtn = ImageView(context)

            classTextView.setText(data.className)
            classDeleteBtn.setImageResource(R.drawable.ic_close)
            //linearLayout.orientation = horizontal
            linearLayout.addView(classTextView)
            linearLayout.addView(classDeleteBtn)
            linearLayoutInListView.addView(linearLayout)

            //classDBHelper.insertRecord(classTextView.text.toString(), classByDayItem.day)

            createdClassInfoList?.add(CreatedClassInfo(data.classDay,
                data.className
            ))

            //printTable()

            classDeleteBtn.setOnClickListener{
                classTextView.setVisibility(View.GONE)
                classDeleteBtn.setVisibility(View.GONE)
                deleteClass(data.classDay, data.className)
                //var query = "DELETE FROM class WHERE class = (classTextView.text as String);"
                //classDatabase.execSQL(query)
            }
        }
    }

    @JvmName("getCreatedClassInfoList1")
    fun getCreatedClassInfoList(): ArrayList<CreatedClassInfo>? {
        return createdClassInfoList
    }

    /*
    private fun printTable() {
        var cursor = classDBHelper.orderByDay()
        var result = ""

        result += "row 개수 : " + cursor.getCount() + "\n";
        while (cursor.moveToNext()) {
            //var itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ClassDataBase.ClassData._ID)).toString()
            //var name = cursor.getString(cursor.getColumnIndexOrThrow(ClassDataBase.ClassData.COLUMN_CLASS_NAME))
            //var day = cursor.getInt(cursor.getColumnIndexOrThrow(ClassDataBase.ClassData.COLUMN_WEEKDAY))

            //result += (itemId + " " + name + " " + day + "\n")
        }

        Toast.makeText(settingContext, result, Toast.LENGTH_LONG).show()
        Log.d("printTable", "printTable이 실행되었습니다.")
        Log.d("printTable", result)
        cursor.close()
    }

     */
}