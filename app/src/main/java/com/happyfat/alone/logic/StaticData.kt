package com.happyfat.alone.logic

import android.view.View
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap

class StaticData {
//  companion object {
//    val center = Point(x = 0, y = 0)
//    fun onXAxis(x: Int) = Point(x, y = 0)
//    fun onYAxis(y: Int) = Point(x = 0, y)
//  }

  companion object {
    val logTag = "ttt"
    var isDraw: Boolean = false
    lateinit var uiData: Any
    lateinit var drawView: View

    override fun toString(): String {
      return "TEST Static class data." + isDraw
    }
  }

  class UIDrawModel {
    companion object {
      lateinit var brushSize: Any
      lateinit var brushColor: Any
      lateinit var saveDrawBmp: Any
    }
  }
}