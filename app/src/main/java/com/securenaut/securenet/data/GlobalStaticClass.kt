package com.securenaut.securenet.data

import android.graphics.drawable.Drawable
import org.json.JSONObject

object GlobalStaticClass {

    lateinit var apkHash : String
    lateinit var staticAnalysisReport : JSONObject
    lateinit var appIconDrawable: Drawable
    lateinit var appName: String
}