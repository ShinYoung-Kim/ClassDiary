package com.ksy.classdiary2

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView

import com.ksy.classdiary2.placeholder.PlaceholderContent.PlaceholderItem
import com.ksy.classdiary2.databinding.FragmentClassBinding

class MyClassFragmentRecyclerViewAdapter
    (val context: Context, val classByCalendarList : ArrayList<ClassByCalendarItem>)
    : BaseAdapter() {

    //private val classByCalendarList : ArrayList<ClassByCalendarItem>? = null

    override fun getCount(): Int {
        return classByCalendarList.size
    }

    override fun getItem(p0: Int): Any {
        return classByCalendarList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.fragment_class, null)

        val className = view.findViewById<EditText>(R.id.classOnCalender)
        val memo = view.findViewById<TextView>(R.id.memoOnCalender)

        return view

    }
}
