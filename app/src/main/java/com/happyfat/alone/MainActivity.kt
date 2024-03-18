package com.happyfat.alone

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.happyfat.alone.theme.TestTheme

class MainActivity : AppCompatActivity() {

    lateinit var selfContext: Context
    lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContent {
////            TestTheme {
////                Greeting("Android Kevin")
////            }
//            Greeting("Android Tonny12345")
//        }

        selfContext = this
        val hiBtn : Button = findViewById(R.id.hi_btn)
        val canvasBtn : Button = findViewById(R.id.canvas_btn)
        imageView = findViewById(R.id.imageView)

        hiBtn.setOnClickListener {
            Toast.makeText(selfContext, "hi", Toast.LENGTH_SHORT).show()
        }
        canvasBtn.setOnClickListener {
//            setContentView(GameView(this))
            setContentView(SelfFrameLayout(this))
//            testDrawLine()
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_UP -> {
                Log.e("ttt", "up")
            }
            MotionEvent.ACTION_DOWN -> {
                Log.e("ttt", "down")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e("ttt", "move")
            }
        }
        return true
    }

    fun testDrawLine () {
        Log.e("ttt", "testDrawLine")
        val bmp = Bitmap.createBitmap(700, 700, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        canvas.drawColor(Color.BLACK)
        val paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8F
        paint.isAntiAlias = true
        val offset = 50
        canvas.drawLine(offset.toFloat(), (canvas.height/2).toFloat(), (canvas.width - offset).toFloat(), (canvas.height /
                2).toFloat(), paint)
        canvas.drawLine( 5.0f, 5.0f, 100.0f, 100.0f, paint )
        imageView.setImageBitmap(bmp)
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "hello $name!")
}

//新增
//新增
@Preview(showBackground = true)
@Composable
//定義預覽可組合函式
fun DefaultPreview() {
//    TestTheme {
//        Greeting("Android Kevin")
//    }
    Greeting("Android Kevin -991")
}