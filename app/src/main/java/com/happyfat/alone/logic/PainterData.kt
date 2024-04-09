package com.happyfat.alone.logic

import androidx.compose.ui.graphics.Color
import org.json.JSONObject
import java.util.Objects

class PainterData {
  companion object {
    private fun colorItemData(name: String, color: Long): Any {
      val map = mutableMapOf<String, Any>()
      map["name"] = name
      map["color"] = color
      return JSONObject(map.toString())
    }

    var colorArr: MutableList<Any> = kotlin.collections.mutableListOf(
      colorItemData("RED", 0xffff0000),
      colorItemData("BLUE", 0xff0000ff),
      colorItemData("YELLOW", 0xffEAC100),
      colorItemData("BLUE-GREEN", 0xff336666),
      colorItemData("PURPLE", 0xff6C3365),
      colorItemData("INDIGO", 0xff484891),
      colorItemData("GREEN", 0xff00ff00),
      colorItemData("BLACK", 0xff000000)
    )

    val minSize: Float = 3f
    val maxSize: Float = 50f
    val defaultSize: Float = 10f

    var brushSize: Float = defaultSize
    var brushColor: Color = Color(0xffff0000)
    var strColor: String = "RED"

  }
}