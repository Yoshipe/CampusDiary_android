package com.watnow.campusdiary.reference

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.ListView
import com.watnow.campusdiary.R

/**
 * Created by saitoushunsuke on 2018/02/15.
 */
data class ReferenceListViewAdapter(private val context: Context, private val listView: ListView) {
    val notificationLists: List<String> = listOf(context.getString(R.string.Reference0),
                                                        context.getString(R.string.Reference1),
                                                        context.getString(R.string.Reference2),
                                                        context.getString(R.string.Reference3),
                                                        context.getString(R.string.Reference4),
                                                        context.getString(R.string.Reference5),
                                                        context.getString(R.string.Reference6),
                                                        context.getString(R.string.Reference7),
                                                        context.getString(R.string.Reference8),
                                                        context.getString(R.string.Reference9),
                                                        context.getString(R.string.Reference10),
                                                        context.getString(R.string.Reference11),
                                                        context.getString(R.string.Reference12),
                                                        context.getString(R.string.Reference13),
                                                        context.getString(R.string.Reference14),
                                                        context.getString(R.string.Reference15),
                                                        context.getString(R.string.Reference16),
                                                        context.getString(R.string.Reference17),
                                                        context.getString(R.string.Reference18),
                                                        context.getString(R.string.Reference19),
                                                        context.getString(R.string.Reference20),
                                                        context.getString(R.string.Reference21),
                                                        context.getString(R.string.Reference22),
                                                        context.getString(R.string.Reference23),
                                                        context.getString(R.string.Reference24),
                                                        context.getString(R.string.Reference25),
                                                        context.getString(R.string.Reference26),
                                                        context.getString(R.string.Reference27),
                                                        context.getString(R.string.Reference28))


    private val notificationListAdapter: ArrayAdapter<String> = ArrayAdapter(this.context, android.R.layout.simple_list_item_1, notificationLists)
    init {
        this.listView.adapter = notificationListAdapter
    }
}