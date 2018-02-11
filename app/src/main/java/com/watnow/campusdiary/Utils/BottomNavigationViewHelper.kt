package com.watnow.campusdiary.Utils

import android.util.Log
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

/**
 * Created by saitoushunsuke on 2018/02/12.
 */
open class BottomNavigationViewHelper {
    private val TAG: String = "BottomNavigationViewHelper"

    fun setupBottomNavigationView(bottomNavigationViewEx: BottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView")
        bottomNavigationViewEx.enableAnimation(false)
        bottomNavigationViewEx.enableItemShiftingMode(false)
        bottomNavigationViewEx.enableShiftingMode(false)
        bottomNavigationViewEx.setTextVisibility(false)
    }
}