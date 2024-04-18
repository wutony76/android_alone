package com.happyfat.alone

import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import com.happyfat.alone.logic.HelloData
import com.happyfat.alone.logic.StaticData
import kotlin.random.Random

class HelloLayout @JvmOverloads constructor(
  mainActivity: MainActivity,
  attrs: AttributeSet?= null,
  defStyleAttr: Int = 0
): FrameLayout(mainActivity.selfContext, attrs, defStyleAttr) {
  val activityContext = mainActivity.selfContext


  init {
    val inflater = mainActivity.getLayoutInflater()
    val xmlView = inflater.inflate(R.layout.activity_main, null)
    val hiBtn : Button = xmlView.findViewById(R.id.hi_btn)
    val canvasBtn : Button = xmlView.findViewById(R.id.canvas_btn)

    hiBtn.setOnClickListener {
      val randomMessage = randomMsg()
      Toast.makeText(activityContext, randomMessage, Toast.LENGTH_SHORT).show()
    }
    canvasBtn.setOnClickListener {
      val drawView = SelfFrameLayout(mainActivity)
      StaticData.drawView = drawView.uiDrawView // 保存Canvas
      mainActivity.setContentView(drawView)
    }
    this.addView(xmlView)
  }

  fun randomMsg(): String {
    val helloData = HelloData()
    val randomName =
      helloData.firstJob[Random.nextInt(helloData.firstJob.size)]+
      helloData.name[Random.nextInt(helloData.name.size)]+
      helloData.firstJob[Random.nextInt(helloData.firstJob.size)]
    val greetings =
      helloData.greetings[Random.nextInt(helloData.greetings.size)]

    return randomName + " say: " + greetings + ". Have a good day."
  }
}