package com.watnow.campusdiary.utils

import android.app.Application
import io.realm.Realm

/**
 * Created by saitoushunsuke on 2018/02/28.
 */
class RealmApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Realmの初期化
        Realm.init(this@RealmApplication)
    }
}