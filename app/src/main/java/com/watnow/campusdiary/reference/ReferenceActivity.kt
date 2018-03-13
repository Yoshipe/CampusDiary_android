package com.watnow.campusdiary.reference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.watnow.campusdiary.R
import com.watnow.campusdiary.utils.BottomNavigationViewHelper
// 以下kotlin-android-extensionsを用いるためのインポート
import kotlinx.android.synthetic.main.layout_reference_center.*
import kotlinx.android.synthetic.main.layout_reference_search_bar.*
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class ReferenceActivity : AppCompatActivity() {
    // BottomNavigationViewの画面遷移でこのアクティビティが何番に値するか
    private val activityNum: Int = 3

    // このActivityのContext
    private val mContext: Context = this

    // onCreateメソッド実行
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reference_activity)
        // BottomNavigationViewのセットアップ
        setupBottomNavigationView()
        // 検索ボタンに対するクリック処理
        searchButton.setOnClickListener {
            search()
        }
    }

    // onResumeメソッド実行
    override fun onResume() {
        super.onResume()

        // ListViewを設定
        ReferenceListViewAdapter(mContext, referenceList)
        // ListViewのクリックに応じて画像のアクティビティに遷移する
        leadImageFromListClicked(referenceList)
    }

    /* *
    *  BottomNavigationView setup
    */
    private fun setupBottomNavigationView() {
        val bottomNavViewHelper = BottomNavigationViewHelper()
        bottomNavViewHelper.setupBottomNavigationView(bottomNavViewBar)
        bottomNavViewHelper.enableNavigation(mContext, bottomNavViewBar)
        val menu: Menu = bottomNavViewBar.menu
        val menuItem: MenuItem = menu.getItem(activityNum)
        menuItem.isChecked = true
    }

    /**
     * ListViewで画像のActivityに遷移させるメソッド
     */
    private fun leadImageFromListClicked(listView: ListView) {
        val intent = Intent(mContext, ReferenceImageActivity::class.java)
        listView.setOnItemClickListener { parent, view, position, id ->
            val item: String = parent.getItemAtPosition(position).toString()
            intent.putExtra("content", item)
            startActivity(intent)
        }
    }

    // 検索ボタンを押した時の処理メソッド
    private fun search() {
        // searchTxtがnullなら初期状態に戻す、それ以外なら検索を行う
        if (searchTxt.text.toString() == null) {
            ReferenceListViewAdapter(mContext, referenceList)
        }
        else {
            // ReferenceListViewAdapter data class にアクセスするためのインスタンス生成
            val listData = ReferenceListViewAdapter(mContext, referenceList)

            // ReferenceListViewAdapter Instance から検索を行った新たなリストを生成
            val newList: List<String> = listData.notificationLists.filter { it.contains(searchTxt.text.toString()) }

            // ReferenceListViewAdapter で新しく作ったListをadapterにセット
            val newAdapter: ArrayAdapter<String> = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, newList)
            referenceList.adapter = newAdapter
        }
    }

}