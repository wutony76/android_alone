package com.happyfat.alone

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView


class SelfFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?= null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    init {
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
        this.addView( ComposeView(context).apply {
            setContent {
                Text("Hello Compose View 2")
            }
        })
    }

}