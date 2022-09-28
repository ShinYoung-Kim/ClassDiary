package com.ksy.classdiary2

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.ListPopupWindow
import com.ksy.classdiary2.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ClassFragment : Fragment() {
    private lateinit var day : String
    var linearLayoutInClassFragment : LinearLayout? = null
    lateinit var db : ClassDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_class, container, false)
        linearLayoutInClassFragment = view.findViewById<LinearLayout>(R.id.memoOnLecture)
        return view
    }

    fun setDay(dayFromFragment: String, db: ClassDB?) {
        this.day = dayFromFragment
        this.db = db!!
        var classList = mutableListOf<ClassDBData>()
        val savedContents = db.classDao().getAll()
        if(savedContents.isNotEmpty()) {
            classList.addAll(savedContents)
        }

        //Toast.makeText(context, "총 " + classList.size + "개 있습니다", Toast.LENGTH_SHORT).show()
        for(i: Int in 0..classList.size - 1) {
            if(classList[i].classDay.equals(this.day + "요일")) {
                addLectureRecord(classList[i].className, linearLayoutInClassFragment!!)
            }
        }
    }

    fun addLectureRecord(className:String, linearLayoutInClassFragment:LinearLayout) {
        val linearLayout = LinearLayout(context)
        val textView = TextView(context)
        val editText = EditText(context)
        val btn = Button(context)

        editText.hint = "메모를 입력하세요"
        editText.width = 0
        editText.height = ListPopupWindow.WRAP_CONTENT
        editText.gravity = 1

        btn.setText("저장하기")
        textView.setText(className)

        linearLayout.addView(textView)
        linearLayout.addView(editText)
        linearLayout.addView(btn)
        linearLayoutInClassFragment.addView(linearLayout)

        Toast.makeText(context, "수업 추가됨", Toast.LENGTH_SHORT).show()

        btn.setOnClickListener {

        }
    }
}