package com.happyfat.alone.logic

import androidx.compose.ui.graphics.Color

class StaticData {
//  companion object {
//    val center = Point(x = 0, y = 0)
//    fun onXAxis(x: Int) = Point(x, y = 0)
//    fun onYAxis(y: Int) = Point(x = 0, y)
//  }

  companion object {
    val logTag = "ttt"
    var isDraw: Boolean = false
    override fun toString(): String {
      return "TEST Static class data." + isDraw
    }
  }
}