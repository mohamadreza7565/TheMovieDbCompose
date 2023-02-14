package com.mohammadreza.moviedbcompose.global

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.compose.ui.graphics.toArgb
import com.mohammadreza.moviedbcompose.ui.theme.White
import org.koin.java.KoinJavaComponent

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
object ScreenController {

    fun Activity.removeStatusBar() {
        window.statusBarColor = Color.TRANSPARENT
        window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    fun Activity.showStatusBar(color:Int = White.toArgb()) {
        window.statusBarColor = color
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }


    fun Activity.setLightStatus() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    }

    fun Activity.setDartStatus() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    }

    fun getStatusBarHeight(): Int {
        val mContext: Context by KoinJavaComponent.inject(Context::class.java)
        var result = 0
        val resourceId = mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = mContext.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}