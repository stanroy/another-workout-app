package com.stanroy.anotherworkoutapp.presentation.common

import android.annotation.SuppressLint
import android.app.Activity

@SuppressLint("SourceLockedOrientationActivity")
fun lockScreenOrientation(activity: Activity, screenOrientation: Int) {
    activity.requestedOrientation = screenOrientation
}
