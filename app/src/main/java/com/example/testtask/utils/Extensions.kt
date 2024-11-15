package com.example.testtask.utils

import android.content.res.Resources

/**
 * Convert an Int value from dp to px.
 */
fun Int.toPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * Convert an Int value from px to dp.
 */
fun Int.toDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * Convert a Float value from dp to px.
 */
fun Float.toPx(): Float {
    return (this * Resources.getSystem().displayMetrics.density)
}

/**
 * Convert a Float value from px to dp.
 */
fun Float.toDp(): Float {
    return (this / Resources.getSystem().displayMetrics.density)
}
