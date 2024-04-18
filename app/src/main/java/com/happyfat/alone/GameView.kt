package com.happyfat.alone

import android.app.AlertDialog
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
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.lang.reflect.Modifier

import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ComposeView
import com.happyfat.alone.logic.PainterData
import com.happyfat.alone.logic.StaticData

class GameView @JvmOverloads constructor(
//    context: Context,
    activity: MainActivity,
    attrs: AttributeSet ?= null,
    defStyleAttr: Int = 0,
    canvasWidth: Int? = null,
    canvasHeight: Int? = null
) : View(activity, attrs, defStyleAttr) {

    val activityContext: Context = activity.selfContext
    private lateinit var selfCanvas: Canvas
    public lateinit var bmp: Bitmap
    val TAG:String = "ttt"
    lateinit var paint:Paint

    init {
      Log.e(TAG, " VIEW init = " + PainterData.brushSize)

      // view init.
      StaticData.isDraw = false

      /*
        paint = Paint().apply {
//        color = Color.RED // 畫筆顏色
          color = PainterData.brushColor.toArgb()
          isAntiAlias = true // 定義是否應用邊緣平滑。
          isDither = true // 影響精度高於設備的顏色下採樣的方式。
          style = Paint.Style.STROKE // 指定要繪製的圖元是填充、描邊還是兩者（以相同顏色）。default: FILL
          strokeJoin = Paint.Join.ROUND // 指定線條和曲線段如何在描邊路徑上連接 , default: MITER
          strokeCap = Paint.Cap.ROUND // 指定描邊線和路徑的開始和結束方式。 default: BUTT
          strokeWidth = PainterData.brushSize
//        strokeWidth = 10f // 以像素為單位指定筆劃的寬度。 default: Hairline-width (really thin)
        }
       */
      var wd = canvasWidth !== null ? Resources.getSystem().displayMetrics.widthPixels
      var ht = Resources.getSystem().displayMetrics.heightPixels

      if ()

      bmp = Bitmap.createBitmap(wd, ht, Bitmap.Config.ARGB_8888)
      selfCanvas = Canvas(bmp)
//        canvas.drawColor(Color.BLACK)
      selfCanvas.drawARGB(100, 100, 100, 100) // 0-255

      // test draw line.
      // canvas.drawLine(0f, 0f, 100f, 100f, paint)
//        showProgressDialog(selfActivity, "Can start draw.")

      Toast.makeText(activityContext, "Can start draw.", Toast.LENGTH_SHORT).show()
    }

    private var touchX = 0f
    private var touchY = 0f
    private var currentX = 0f
    private var currentY = 0f
    override fun onDraw(canvas: Canvas) {
        Log.e(TAG, "onDraw")
        super.onDraw(canvas)
        canvas.drawBitmap(bmp, 0f, 0f, null)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
      when (event!!.action) {
        MotionEvent.ACTION_UP -> {
          Log.e(TAG, "up")
        }
        MotionEvent.ACTION_DOWN -> {
          StaticData.isDraw = true // start draw.
          currentX = event.x
          currentY = event.y
          Log.e(TAG, "down" + currentX + currentY)

          paint = Paint().apply {
            color = PainterData.brushColor.toArgb()
            isAntiAlias = true // 定義是否應用邊緣平滑。
            isDither = true // 影響精度高於設備的顏色下採樣的方式。
            style = Paint.Style.STROKE // 指定要繪製的圖元是填充、描邊還是兩者（以相同顏色）。default: FILL
            strokeJoin = Paint.Join.ROUND // 指定線條和曲線段如何在描邊路徑上連接 , default: MITER
            strokeCap = Paint.Cap.ROUND // 指定描邊線和路徑的開始和結束方式。 default: BUTT
            strokeWidth = PainterData.brushSize
          }
        }
        MotionEvent.ACTION_MOVE -> {
            Log.e(TAG, "move")
            val stopX = event.x
            val stopY = event.y
            selfCanvas.drawLine(currentX, currentY, stopX, stopY, paint)
            currentX = event.x
            currentY = event.y
            invalidate()
        }
      }
      return true
    }

  fun getViewCanvasBitmap(): Bitmap {
    return bmp
  }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "hello $name!")
//}
@Composable
fun helloTest (str: String) {
    Text(text = "hello" + str)
}


fun showProgressDialog(activity: MainActivity) {
    val builder = AlertDialog.Builder(activity)
    val inflater = activity.getLayoutInflater()
//    builder.setView(inflater.inflate(R.layout.test_dialog, null))
    builder.setView(LinearLayout(activity).apply {
        addView(TextView(context).apply {
            text = "I'm am old TextView"
        })


//        addView(ComposeView(context).apply {
//            id = R.id.compose_test_view
//            setContent {
//                Text("Hello Compose View 2")
//            }
//        })
    })
    builder.create()
    builder.show()
}