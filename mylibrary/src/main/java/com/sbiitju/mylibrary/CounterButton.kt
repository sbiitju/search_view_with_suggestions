package com.sbiitju.mylibrary

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout

class CounterButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var count: Int = 1
    var action: MyAction? = null
    private var counterTextView: AppCompatTextView
    private var addButton: AppCompatButton
    private var minusButton: AppCompatButton

    init {
        LayoutInflater.from(context).inflate(R.layout.counter_button, this, true)
        counterTextView = findViewById(R.id.counterText)
        addButton = findViewById(R.id.addButton)
        minusButton = findViewById(R.id.minusBtn)
        counterTextView.text = count.toString()
        addButton.setOnClickListener {
            if (count == 0){
                count += 1
                minusButton.visibility = VISIBLE
                counterTextView.visibility = VISIBLE
            }else{
                count += 1
            }
            counterTextView.text = count.toString()
            action?.onAddButtonClicked(count)
        }

        minusButton.setOnClickListener {
            if (count > 1) {
                count -= 1
            } else if (count == 1) {
                count -= 1
                minusButton.visibility = INVISIBLE
                counterTextView.visibility = INVISIBLE
                addButton.gravity = Gravity.CENTER
            } else count = count
            counterTextView.text = count.toString()
            action?.onMinusButtonClicked(count)
        }

    }

    interface MyAction {
        fun onAddButtonClicked(count: Int)
        fun onMinusButtonClicked(count: Int)
    }

}

