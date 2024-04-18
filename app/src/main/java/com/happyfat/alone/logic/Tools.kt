package com.happyfat.alone.logic

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.happyfat.alone.MainActivity
import com.happyfat.alone.R

class Tools {
  companion object {

    // 確認權限
    fun checkWritable( activity: MainActivity):Boolean {
      val activityContext = activity.selfContext
      if(ActivityCompat.checkSelfPermission(activityContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),0)
        return false
      } else {
        return true
      }
    }

    //
    fun createViewBmp(view: View, outBitmap: Bitmap? = null): Bitmap? {
      Log.e(StaticData.logTag, "所見螢幕截圖 view = ${view} ")
      var bitmap = Bitmap.createBitmap(
//        100, 100,
        view.width,
        view.height,
        Bitmap.Config.ARGB_8888
      )

      if (bitmap === null) view.draw(Canvas(bitmap)) // view截圖
      else bitmap = outBitmap!! // canvas.img
      return bitmap
    }

    fun getBitmapFromImage(context: Context, bitmap: Bitmap? = null, drawable: Int = R.drawable.ic_launcher_background): Bitmap {
      val db = ContextCompat.getDrawable(context, drawable)
      val width = db!!.intrinsicWidth
      val height = db.intrinsicHeight

      // 已開始畫圖（SAVE）
      if (bitmap !== null) {
        val bit = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bit)
        // 縮放
        canvas.scale(
          (bitmap.width/5f)/bitmap.width,
          (bitmap.height/5f)/bitmap.height
        )
        canvas.drawBitmap(
          bitmap,
          Rect(0, 0, bitmap.width, bitmap.height),
          Rect(0, 0, bitmap.width, bitmap.height),
//          0f, 0f,
          null
          )

        return bit
      }

      // 未開始畫圖
      // use bitmap img
      val bit = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
      val canvas = Canvas(bit)
      val paint = Paint()
      paint.color = Color.RED
      paint.style = Paint.Style.STROKE
      paint.strokeWidth = 8F
      paint.isAntiAlias = true
      canvas.drawRect(0f,0f,width.toFloat(),height.toFloat(), paint)
//      canvas.drawLine( 5.0f, 5.0f, 100.0f, 100.0f, paint )
      val wdPaint = Paint()
      wdPaint.color = Color.RED
      wdPaint.textSize = 40f
      canvas.drawText( "NO Data.", (width/2).toFloat()-80, (height/2).toFloat(), wdPaint)

//      db.setBounds(0, 0, canvas.width, canvas.height)
//      db.draw(canvas)
      return bit
    }

  }
}