package com.happyfat.alone

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class GameView constructor(context: Context) : View(context) {

    private val mContext: Context = context
    private lateinit var canvas: Canvas
    private lateinit var bmp: Bitmap
    val paint = Paint().apply {
        color = Color.RED // 畫筆顏色
        isAntiAlias = true // 定義是否應用邊緣平滑。
        isDither = true // 影響精度高於設備的顏色下採樣的方式。
        style = Paint.Style.STROKE // 指定要繪製的圖元是填充、描邊還是兩者（以相同顏色）。default: FILL
        strokeJoin = Paint.Join.ROUND // 指定線條和曲線段如何在描邊路徑上連接 , default: MITER
        strokeCap = Paint.Cap.ROUND // 指定描邊線和路徑的開始和結束方式。 default: BUTT
        strokeWidth = 10f // 以像素為單位指定筆劃的寬度。 default: Hairline-width (really thin)
    }

    init {
        Log.e("ttt", "init")
        val wd = Resources.getSystem().displayMetrics.widthPixels
        val ht = Resources.getSystem().displayMetrics.heightPixels

        bmp = Bitmap.createBitmap(wd, ht, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bmp)
        canvas.drawColor(Color.BLACK)
        canvas.drawLine(0f, 0f, 100f, 100f, paint)
    }

    private var touchX = 0f
    private var touchY = 0f
    private var currentX = 0f
    private var currentY = 0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                Log.e("ttt", "up")
            }
            MotionEvent.ACTION_DOWN -> {
                Log.e("ttt", "down")
                currentX = event.x
                currentY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e("ttt", "move")
                val stopX = event.x
                val stopY = event.y
                canvas.drawLine(currentX, currentY, stopX, stopY, paint)
                currentX = event.x
                currentY = event.y

                invalidate()
            }
        }
        return true
    }
}