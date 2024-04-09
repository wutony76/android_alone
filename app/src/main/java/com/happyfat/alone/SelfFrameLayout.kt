package com.happyfat.alone

import android.annotation.SuppressLint
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happyfat.alone.logic.PainterData
import com.happyfat.alone.logic.StaticData
import org.json.JSONObject


class SelfFrameLayout @JvmOverloads constructor(
  mainActivity: MainActivity,
//  context: Context,
  attrs: AttributeSet?= null,
  defStyleAttr: Int = 0
): FrameLayout(mainActivity.selfContext, attrs, defStyleAttr) {
  val selfActivity =  mainActivity
  val rowHeight = 45.dp
  val marginDp = 5.dp
  val paddingDp = 3.dp

//  val painter = PainterData()
  var activityContext = selfActivity.selfContext  // get activity context.


  init {
    Log.e ( "ttt", StaticData.toString())
    // add text ui from code.
    /*
    val textView1 = TextView(context)
    textView1.setText("Fanny Hands Lane, London")
    textView1.setTextSize(22f)
    this.addView(textView1)
     */

    // add xml ui.
    /*
    val inflater = LayoutInflater.from(context)
    val view = inflater.inflate(R.layout.test_dialog, null)
    this.addView(view)
     */

    // add compose ui.
    /*
    this.addView( ComposeView(context).apply {
        setContent {
            Text("Hello Compose View 2")
        }
    })
     */

    // Draw UI
    this.addView(GameView(mainActivity))

    // add self painter UI
    this.addView( ComposeView(activityContext).apply {
      setContent {

//        testUi()
//        /*
        Column (
          modifier = Modifier
            .fillMaxSize()
        ){
          // Header
          painterHeadBar ()
          // Content
          Column (
            modifier = Modifier
              .border(1.dp, Color.Black)
              .weight(1.0f)
          ) {
            Text(text = "canvas", modifier = Modifier.fillMaxWidth())
          }
          // Footer
          footerBar()
        }
//         */

      }
    })
  }
  @SuppressLint("UnrememberedMutableState")
  @Composable
  fun testUi () {
    var data: String = PainterData.brushSize.toString()
    val state = remember {mutableStateOf(data)}

    StaticData.uiData = remember {mutableStateOf(data)}
    Log.e("ttt","state1:${state.value} - ${state.javaClass.kotlin}")//Logcat
    Log.e("ttt","state2:${state.value} - ${StaticData.uiData.javaClass.kotlin}")//Logcat

    Column {
      Text(
        text = (StaticData.uiData as MutableState<String>).value,
        color = Color.Red,
        modifier = Modifier.fillMaxWidth()
      )
      Button(
        onClick = {
          PainterData.brushSize ++
          (StaticData.uiData as MutableState<String>).value = PainterData.brushSize.toString()
        }
      ) {
        Text(text = "ADD")
      }
    }
  }

  fun colorItemData (name: String, color: Long): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["name"] = name
        map["color"] = color
        return map
    }

  @OptIn(ExperimentalComposeUiApi::class)
  @Composable
  fun painterHeadBar (modifier: Modifier = Modifier){

    // set data.
    // DATA
//    val colorArr = mutableListOf(
//        colorItemData("RED", 0xffff0000),
//        colorItemData("BLUE", 0xff0000ff),
//        colorItemData("GREEN", 0xff00ff00)
//    )
//    colorArr.add(colorItemData("BLACK", 0xff000000))

    val colorArr = PainterData.colorArr
    // 測試資料Code
//    colorArr.forEachIndexed { index, item ->
//      val jsonObject = JSONObject(item.toString())
//      val name = jsonObject.get("name")
//      val color = jsonObject.get("color")
//      Log.e("ttt", "***** item = ${name} - ${color} - ${color.javaClass.kotlin} ")
//    }

    // BRUSH
    val maxBrush = PainterData.maxSize
    val minBrush = PainterData.minSize
    val selfBrush = PainterData.brushSize.toInt()
    var brushSize by remember{ mutableStateOf( TextFieldValue(selfBrush.toString())) }
    val keyboardController = LocalSoftwareKeyboardController.current // 鍵盤

    Column (
      modifier = Modifier
//        .fillMaxWidth()
        .background(Color.White)
        .border(1.dp, Color.Red)
    ) {

      // 第一行控制列
      Row (
        horizontalArrangement = Arrangement.Center, // 垂直置中
        modifier = Modifier
          //.fillMaxSize() // 內容比例分配
          .padding(5.dp)
          .height(rowHeight)
          .border(1.dp, Color.Black)
      ) {
        Box(
          modifier= Modifier.height(47.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "CONTROL",
            modifier = Modifier
  //                    .border(2.dp, Color.Green)
          )
        }

        // Brush
        BasicTextField (
          modifier = Modifier
            .padding(horizontal = 5.dp)
            .background(Color.White)
            .width(80.dp)
            .height(45.dp)
  //          .border(width = 1.dp, color = Color.Black)
  //          .padding(5.dp)
          ,
          value = brushSize,
          textStyle = TextStyle(
            fontSize = 17.sp
          ),
          onValueChange = {
            brushSize = it
            if (it.text.all { it.isDigit() }){
              brushSize = it
            } else brushSize = TextFieldValue(minBrush.toInt().toString())
          },
          keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
          keyboardActions = KeyboardActions(
            onDone = {
//              Log.e("ttt", "Keyboard.DONE" + brushSize.toString() + " , " + brushSize.text.length )
              if (brushSize.text.length === 0) brushSize = TextFieldValue(minBrush.toInt().toString())
              else if (brushSize.text.toFloat() > maxBrush ) brushSize = TextFieldValue(maxBrush.toInt().toString())
              PainterData.brushSize = brushSize.text.toFloat()
              (StaticData.UIDrawModel.brushSize as MutableState<String>).value = PainterData.brushSize.toString() // 更新UI

//              Log.e("ttt", "Keyboard.DONE2" )
              keyboardController?.hide()
            },
            onNext = { Log.e("ttt", "Keyboard.onNext") }
          ),
          singleLine = true, // 單行顯示
          decorationBox = { innerTextField ->
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .border(
                  width = 1.dp,
                  color = Color.Black,
                  shape = RoundedCornerShape(10.dp)
                )
            ) {
              Row(
                modifier = Modifier.padding(10.dp)
              ) {
                if ( brushSize.text.isEmpty() ) Text(text = "Set Size", style = TextStyle(
                  color = Color(0xff333333),
                  fontSize = 15.sp,
                  textAlign = TextAlign.Center
                ))
                // you have to invoke this function then cursor will focus and you will able to write something
                innerTextField.invoke()
              }
            }
          }
        )

        // 調色盤
        LazyRow (
          modifier = Modifier
            .border(2.dp, Color.Green)
            .fillMaxWidth()
        ) {
          items (colorArr) {
            // 使用 MAP
//            var strColor = it("color").toString()
//            colorItem(it["name"].toString(), Color(strColor.toLong()))

            // 改使用JSON DATA
            val jsonObject = JSONObject(it.toString())
            val jsonName = jsonObject.get("name")
            val jsonColor = jsonObject.get("color")
            colorItem(jsonName.toString(), Color(jsonColor.toString().toLong()))
          }
          item {
            colorItem("TEST", Color(0xff474747))
          }
        }
      }

      // 第二行資訊列
      Row (
        horizontalArrangement = Arrangement.Center, // 垂直置中
        modifier = Modifier
          //.fillMaxSize()
          .padding(5.dp)
          .height(rowHeight)
          .border(1.dp, Color.Black)
      ) {
        val textStyle = TextStyle(
          fontSize = 15.sp,
          textAlign = TextAlign.Center
        )
        StaticData.UIDrawModel.brushSize = remember {mutableStateOf(PainterData.brushSize.toString())}
        StaticData.UIDrawModel.brushColor = remember {mutableStateOf(PainterData.strColor)}

        Text (text = "SIZE:${(StaticData.UIDrawModel.brushSize as MutableState<String>).value}", style = textStyle,  modifier= Modifier
          .weight(1.0F)
          .border(1.dp, Color.Blue))
        Text (text = "COLOR:${(StaticData.UIDrawModel.brushColor as MutableState<String>).value}", style = textStyle,  modifier=Modifier.weight(3.0F))
      }

    }
  }

  @Composable
  fun footerBar (modifier: Modifier = Modifier) {
    Row (
      modifier = Modifier
//        .padding(5.dp)
        .height(rowHeight)
        .background(Color.White)
        .border(1.dp, Color.Red)
        .fillMaxWidth()
        .padding(5.dp)
      ,
      horizontalArrangement = Arrangement.End

    ) {

      Button(
        onClick = {
          Log.e("ttt", "CLICK. BACK")
          selfActivity.setContentView(HelloLayout(selfActivity))
        },
        modifier = Modifier
          .padding(horizontal = marginDp)
      ) {
        Text( text = "BACK")
      }

      Text ( text = "", modifier = Modifier.weight(1.0f) )


      Button(
        onClick = {
          Log.e("ttt", "CLICK. PLAY")
        },
        modifier = Modifier
          .padding(horizontal = paddingDp)
      ) {
        Text( text = "PLAY")
      }

      Button(
        onClick = {
          Log.e("ttt", "CLICK. SAVE")
        },
        modifier = Modifier
      ) {
        Text( text = "SAVE")
      }


    }

  }

  // color splice.
  @Composable
  fun colorItem (
    btnName: String = "Default",
    color: Color = Color.Gray
  ){
//    var isDataUpdate by remember{ mutableStateOf(false) }
    Column (
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
//            .height(30.dp)
//            .width(50.dp)
        .padding(1.dp)
    ) {
        Button(
          onClick = {
//              Log.e("ttt", "onclick1 >" + color.toString())
//            isDataUpdate = !isDataUpdate
            PainterData.strColor = btnName
            PainterData.brushColor = color
            (StaticData.UIDrawModel.brushColor as MutableState<String>).value = PainterData.strColor // 更新ＵＩ
          },
          shape = RoundedCornerShape(50),
          colors = ButtonDefaults.buttonColors(color),
          modifier = Modifier
            .height(30.dp)
            .width(30.dp)
        ) {}

        val textBody = TextStyle(
          color = color,
          fontWeight = FontWeight.Bold,
          fontSize = 10.sp,
          textAlign = TextAlign.Center
        )

        var textStyle by remember { mutableStateOf(textBody) }
        var readyToDraw by remember { mutableStateOf(false) }

        Text(
          text = btnName,
          style = textStyle,
//            modifier = Modifier
//                .width(30.dp)
//                .border(1.dp, Color.Red)
//            ,
          modifier = Modifier
            .width(30.dp)
            .height(15.dp)
            .drawWithContent {
              if (readyToDraw) drawContent()
            }
//              .border(1.dp, Color.Red)
          ,

          onTextLayout = { res ->
//                Log.e("ttt", res.toString() )
              Log.e("ttt", res.didOverflowWidth.toString() +" , "+ textStyle.fontSize.toString() )
//                Log.e("ttt", res. )
              if (res.didOverflowWidth) {
                  textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
              } else {
                  readyToDraw = true
              }
          }
        )
    }
  }
}


//private fun Any.get(s: String): Any {
//
//}

//private operator fun Any.get(s: String): Any {
//  Log.e("ttt.gg", s + "  ******")
//  return ""
//}
