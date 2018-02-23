package com.watnow.campusdiary.Reference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.watnow.campusdiary.R
import com.watnow.campusdiary.Utils.BottomNavigationViewHelper
// 以下kotlin-android-extensionsを用いるためのインポート
import kotlinx.android.synthetic.main.layout_reference_center.*
import kotlinx.android.synthetic.main.layout_reference_search_bar.*
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
class ReferenceActivity : AppCompatActivity() {

    // デバッグ用のTAG
    private val TAG: String = "ReferenceActivity"

    // BottomNavigationViewの画面遷移でこのアクティビティが何番に値するか
    private val ACTIVITY_NUM: Int = 3

    // このActivityのContext
    private val mContext: Context = this

    // onCreateメソッド実行
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reference_activity)
        // デバッグ
        Log.d(TAG, "onCreate: starting")

        // BottomNavigationViewのセットアップ
        setupBottomNavigationView()
    }

    // onResumeメソッド実行
    override fun onResume() {
        super.onResume()
        // 検索ボタンに対するクリック処理
        searchButton.setOnClickListener {
            Toast.makeText(this, searchTxt.text, Toast.LENGTH_SHORT).show()
        }

        // ListViewを設定
        ReferenceListViewAdapter(mContext, referenceList)
        // ListViewのクリックに応じて画像のアクティビティに遷移する
        leadImageFromListClicked(referenceList)
    }

    /* *
    *  BottomNavigationView setup
    */
    private fun setupBottomNavigationView() {
        // デバッグ
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView")

        var bottomNavViewHelper: BottomNavigationViewHelper = BottomNavigationViewHelper()
        bottomNavViewHelper.setupBottomNavigationView(bottomNavViewBar)
        bottomNavViewHelper.enableNavigation(mContext, bottomNavViewBar)
        val menu: Menu = bottomNavViewBar.menu
        val menuItem: MenuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }

    /**
     * ListViewで画像のActivityに遷移させるメソッド
     */
    private fun leadImageFromListClicked(listView: ListView) {
        val intent: Intent = Intent(mContext, ReferenceImageActivity::class.java)
        listView.setOnItemClickListener { parent, view, position, id ->
            val item: String = parent.getItemAtPosition(position).toString()
            intent.putExtra("content", item)
            startActivity(intent)
        }
    }

}