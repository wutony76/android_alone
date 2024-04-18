package com.happyfat.alone

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
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
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.happyfat.alone.theme.TestTheme
import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.happyfat.alone.logic.StaticData
import com.happyfat.alone.logic.Tools
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

  lateinit var selfContext: Context
  lateinit var imageView:ImageView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    selfContext = this
    setContentView(HelloLayout(this))


    /*
    // 測試CODE...

    setContentView(R.layout.activity_main)
    val hiBtn : Button = findViewById(R.id.hi_btn)
    val canvasBtn : Button = findViewById(R.id.canvas_btn)
    imageView = findViewById(R.id.imageView)
    hiBtn.setOnClickListener {
      Toast.makeText(selfContext, "hi", Toast.LENGTH_SHORT).show()
    }
    canvasBtn.setOnClickListener {
//      setContentView(SelfFrameLayout(this)) // start Draw page
      Log.e(StaticData.logTag, "***** selfContext = ${selfContext}")
      setContentView(HelloLayout(this)) // start Draw page

      // ----- 以下測試 UI code. -----
      // setContentView(GameView(this))
      // testDrawLine()
      // testShowDialog(this)

      // 測試 Compose UI 使用 Code
//      setContent {
//        TestTheme {
//          Greeting("Android Kevin")
//        }
//        Greeting("Android Tonny12345")
//        testComposeDrawableToBitmap()
//      }
    }
     */
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

  // 測試畫圖
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

  // 測試自定義Dialog
  fun testShowDialog(activity: MainActivity) {
    Log.e("ttt", "testShowDialog")
    val activityContext = activity.selfContext
    val builder = AlertDialog.Builder(activityContext)
//    val inflater = activity.getLayoutInflater()
//    builder.setView(inflater.inflate(R.layout.test_dialog, null))

    // set View
    builder.setView(LinearLayout(activityContext).apply {
      addView(TextView(activityContext).apply {
        text = "I'm am old TextView"
      })
      addView(TextView(activityContext).apply {
        text = "I'm am old TextView2"
      })
    })

    // 實驗 ComposeView 這邊會閃退
//    builder.setView(ComposeView(activityContext).apply {
//      setContent {
//        Text("Hello Compose View 2")
//      }
//    })
    builder.create()
    builder.show()
  }

  @Composable
  fun Greeting(name: String) {
    Text(text = "hello $name!")
  }

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

  // compose Image test.
  @Composable
  fun testComposeDrawableToBitmap () {
    // val ctx = LocalContext.current
    val ctx = selfContext

    Column() {
      Text(
        modifier = Modifier.padding(6.dp),
        text = "Normal Image",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
      )
      Spacer(modifier = Modifier.height(20.dp))
      Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Android",
        alignment = Alignment.Center
      )
      Spacer(modifier = Modifier.height(20.dp))
      Spacer(modifier = Modifier.height(20.dp))
      Text(
        modifier = Modifier.padding(6.dp),
        text = "Bitmap Image",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
      )
      Spacer(modifier = Modifier.height(20.dp))
      val bitmap = getBitmapFromImage(ctx, R.drawable.ic_launcher_background)

      // on below line we are creating our bitmap image/
      Image(
        modifier = Modifier
          .height(200.dp)
          .width(200.dp),
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "Android",
        alignment = Alignment.Center
      )
    }


  }
  private fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {
    val db = ContextCompat.getDrawable(context, drawable)
    val bit = Bitmap.createBitmap(
      db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bit)
    db.setBounds(0, 0, canvas.width, canvas.height)
    db.draw(canvas)
    return bit
  }

}


