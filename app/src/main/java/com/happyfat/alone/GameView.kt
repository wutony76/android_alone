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
import java.lang.reflect.Modifier

import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView

class GameView @JvmOverloads constructor(
//    context: Context,
    activity: MainActivity,
    attrs: AttributeSet ?= null,
    defStyleAttr: Int = 0
) : View(activity, attrs, defStyleAttr) {

    val selfActivity: MainActivity = activity
//    private val mContext: Context = context
    private lateinit var canvas: Canvas
    private lateinit var bmp: Bitmap
    val TAG:String = "ttt"
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
        Log.e(TAG, " VIEW init")
        val wd = Resources.getSystem().displayMetrics.widthPixels
        val ht = Resources.getSystem().displayMetrics.heightPixels

        bmp = Bitmap.createBitmap(wd, ht, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bmp)
//        canvas.drawColor(Color.BLACK)
        canvas.drawARGB(100, 100, 100, 100) // 0-255
        // canvas.drawLine(0f, 0f, 100f, 100f, paint) // test draw line.

//        setContent{
//            Greeting("Android Kevin1121")
//        }

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
                Log.e(TAG, "down")
                currentX = event.x
                currentY = event.y

                showProgressDialog(selfActivity)
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "move")
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

        addView(ComposeView(context).apply {
            id = R.id.compose_test_view
            setContent {
                Text("Hello Compose View 2")
            }
        })
    })
    builder.create()
    builder.show()
}