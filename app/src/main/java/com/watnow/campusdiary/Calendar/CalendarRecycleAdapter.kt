package com.watnow.campusdiary.Calendar

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.text.TextUtils
import android.util.Log
import android.util.SparseBooleanArray
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextClock
import android.widget.TextView
import com.watnow.campusdiary.R
import com.watnow.campusdiary.RealmDB.CalendarDB
import io.realm.Realm
import kotlinx.android.synthetic.main.layout_calendar_item.view.*
import java.util.ArrayList

/**
 * Created by Shogo on 2018/02/12.
 */
class CalendarRecycleAdapter(private val context: Context, private val itemClickListener: CalendarViewHolder.ItemClickListener , private val itemList: List<String>)
    : RecyclerView.Adapter<CalendarViewHolder>() {
    private val calendarDate = CalendarDate()
    private val todayPosition = calendarDate.todayPosition()
    private var selectedItem: SparseBooleanArray = SparseBooleanArray()
    private var myRecyclerView: RecyclerView? = null
    lateinit var realm: Realm
    public var prepostion = todayPosition
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        myRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        myRecyclerView = null
    }

    // １ブロック分の処理
    override fun onBindViewHolder(holder: CalendarViewHolder?, position: Int) {
        holder?.let {
            // 今日の場合背景を変える
            if (this.todayPosition.toInt() == position) {
                it.parentLayout.setBackgroundColor(Color.RED)
            } else {
                it.parentLayout.setBackgroundResource(R.drawable.color_calendar_selector)
            }
            // 日付
            it.itemTextView.text = itemList.get(position)
            // 選択されているアイテムを、選択されている状態にする
            it.parentLayout.isSelected = selectedItem.get(position, false)
            // 月のbackgroundを交互に色を変える
            if(calendarDate.getMonth(position).toInt()%2 != calendarDate.getMonth(calendarDate.todayPosition()).toInt()%2) {
                it.parentLayout.setBackgroundResource(R.drawable.color_calendar_sub_selector)
            }

            // showEventlayoutに含まれる全てのViewを消す
            it.showEventlayout.removeAllViews()
            realm = Realm.getDefaultInstance()
            // アイテムの日付と同じデータをDBから取ってくる
            val todayData = realm.where(CalendarDB::class.java).equalTo("date",calendarDate.getday(position)).findAll()
            // データがあればTextViewを追加する処理
            if (todayData != null) {
                // データのサイズ回TextViewを表示
                for(i in 0..todayData.size-1) {
                    val textView: TextView = TextView(context)
                    textView.setTextColor(Color.WHITE)
                    textView.setTextSize(8F)
                    textView.setText(todayData[i].title)
                    // Textviewの角を丸めるためにbackgroundをGradientDrawableをセットする
                    val drawable = GradientDrawable().apply {
                        cornerRadius = 7F
                        when (todayData[i].theme) {
                            "ルビー" -> setColor(ContextCompat.getColor(context, R.color.ruby))
                            "サファイア" -> setColor(ContextCompat.getColor(context, R.color.sapphire))
                            "エメラルド" -> setColor(ContextCompat.getColor(context, R.color.emerald))
                            "ゴールド" -> setColor(ContextCompat.getColor(context, R.color.gold))
                            "パール" -> setColor(ContextCompat.getColor(context, R.color.perl))
                            "アメジスト" -> setColor(ContextCompat.getColor(context, R.color.amethyst))
                            "タイガーアイ" -> setColor(ContextCompat.getColor(context, R.color.tigerEye))
                            "トパーズ" -> setColor(ContextCompat.getColor(context, R.color.topaz))
                            "ダイヤモンド" -> setColor(ContextCompat.getColor(context, R.color.diamond))
                            else -> setColor(ContextCompat.getColor(context, R.color.diamond))
                        }
                    }
                    textView.background = drawable
                    // 左と上と右に7、余白を開ける
                    val llp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    llp.setMargins(7,7,7,0)
                    textView.layoutParams = llp
                    // paddingの設定
                    textView.setPadding(5,3,3,5)
                    // 文字列がTextViewの幅を超えた場合...で表す
                    textView.ellipsize = TextUtils.TruncateAt.END
                    textView.setHorizontallyScrolling(true)
                    it.showEventlayout.addView(textView)
                }
            realm.close()
            }
            // 学年暦、祝日のデータを取ってくる
            val publicDataNames = calendarDate.getPublicDataNames(position)
            if (publicDataNames.isNotEmpty()) {
                for(i in 0..publicDataNames.size-1) {
                    val textView: TextView = TextView(context)
                    textView.setTextColor(Color.WHITE)
                    textView.setTextSize(8F)
                    textView.setText(publicDataNames[i])
                    val drawable = GradientDrawable().apply {
                        cornerRadius = 7F
                        setColor(ContextCompat.getColor(context, R.color.schoolEvent))
                    }

                    textView.background = drawable
                    val llp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    llp.setMargins(7, 7, 7, 0)
                    textView.layoutParams = llp
                    textView.setPadding(5,3,3,5)
                    it.showEventlayout.addView(textView)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CalendarViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val myView = layoutInflater.inflate(R.layout.layout_calendar_item, parent, false)
        myView.setOnClickListener { view ->
            myRecyclerView?.let {
                val tmpPosition = it.getChildAdapterPosition(view)
                selectedItem.put(prepostion,false)
                selectedItem.put(tmpPosition,true)
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
                prepostion = tmpPosition
            }
        }
        return CalendarViewHolder(myView)
    }
}