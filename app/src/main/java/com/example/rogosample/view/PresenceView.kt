package com.example.rogosample.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.colorbot.OnScrollPadChangeListener
import com.example.rogosample.R

class PresenceView: View {
    private var mBgPaint: Paint? = null
    private var mFgPaint: Paint? = null
    private var horizontalLinePaint: Paint? = null
    private var verticalLinePaint: Paint?= null
    private var mBgRect: RectF? = null
    private var horizontalLineColor = 0
    private var verticalLineColor = 0
    private var presenceList = arrayListOf<Int>()
    private var rowNumber: Int = 0
    private var columnNumber: Int = 0
    private var indicatorWidth: Int = 0
    private var listener: OnScrollPadChangeListener?= null
    constructor(context: Context?) : super(context) {
        init(null, 0)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(
            attrs, defStyleAttr
        )
    }
    private fun init(attrs: AttributeSet?, defStyleAttr: Int) {
        isFocusable = true
        mBgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mBgPaint!!.style = Paint.Style.STROKE
        mFgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mFgPaint!!.style = Paint.Style.FILL
        horizontalLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        horizontalLinePaint!!.style = Paint.Style.FILL
        verticalLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        verticalLinePaint!!.style = Paint.Style.FILL
        mBgRect = RectF()
        if (attrs != null) {
            val attr =
                context.obtainStyledAttributes(attrs, R.styleable.PresenceView, defStyleAttr, 0)
            horizontalLineColor = attr.getColor(
                R.styleable.PresenceView_horizontalLineColor,
                Color.parseColor("#8F8F8F")
            )
            verticalLineColor = attr.getColor(
                R.styleable.PresenceView_verticalLineColor,
                Color.parseColor("#5a5b5f")
            )
            rowNumber = attr.getInt(
                R.styleable.PresenceView_rowNumber,
                5
            )
            columnNumber = attr.getInt(
                R.styleable.PresenceView_columnNumber,
                5
            )
            indicatorWidth = attr.getInt(
                R.styleable.PresenceView_indicatorWidth,
                5
            )
            mBgPaint!!.strokeWidth = indicatorWidth.toFloat()
            mBgPaint!!.color = horizontalLineColor
            mFgPaint!!.color = attr.getColor(
                R.styleable.PresenceView_presenceColor,
                Color.parseColor("#FFFFFF")
            )
            horizontalLinePaint!!.color = horizontalLineColor
            verticalLinePaint!!.color = verticalLineColor
            attr.recycle()
        } else {

        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        verticalLinePaint?.let {
            var startX = width / columnNumber
            for(i in 1 .. columnNumber) {
                canvas.drawRect(
                    startX.toFloat(),
                    0f,
                    startX + 3f,
                    height.toFloat(),
                    it
                )
                startX += width / columnNumber
            }
        }

        horizontalLinePaint?.let {
            var startY = height / rowNumber
            for(i in 1 .. rowNumber) {
                canvas.drawRect(
                    0f,
                    startY.toFloat(),
                    width.toFloat(),
                    startY + 3f,
                    it
                )
                startY += height / rowNumber
            }
        }
//        mBgPaint?.let {
//            canvas.drawRoundRect(
//                0f,
//                0f,
//                width.toFloat(),
//                height.toFloat(),
//                30f,
//                30f,
//                it
//            )
//        }
        mBgPaint?.let {
            canvas.drawRect(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                it
            )
        }
//        var startX = 0f
//        var startY = 0f
//        mBgPaint?.let {
//            for(i in 1 .. columnNumber) {
//                startY = 0f
//                for(j in 1 ..rowNumber) {
//                    canvas.drawRect(
//                        startX,
//                        startY,
//                        startX + ((width - (columnNumber * 3f)) / columnNumber),
//                        startY + ((width - (columnNumber * 3f)) / columnNumber),
//                        it
//                    )
//
//                    startY += (width / columnNumber)
//                }
//                startX += (width / columnNumber)
//            }
//        }
        for((i, value) in presenceList.withIndex()) {
            if (value == 1) {
                mFgPaint?.let {
//                    canvas.drawRect(
//                        ((width / columnNumber).toFloat() * Math.ceil(((columnNumber) / 2).toDouble())).toFloat(),
//                        (height / columnNumber) * (i).toFloat(),
//                        ((width / columnNumber).toFloat() * Math.ceil(((columnNumber) / 2).toDouble())).toFloat() + (width / columnNumber),
//                        (height / columnNumber) * (i).toFloat() + (height / columnNumber),
//                        it
//                    )
                    val d = resources.getDrawable(R.drawable.ic_people, null)
                    d.setBounds(
                        ((width / columnNumber) * Math.ceil((columnNumber / 2).toDouble()).toInt()),
                        (height / columnNumber) * (i),
                        (((width / columnNumber) * Math.ceil((columnNumber / 2).toDouble()) + (width / columnNumber))).toInt(),
                        (height / columnNumber) * (i) + (height / columnNumber),
                    )
                    d.draw(canvas)
                }
            } else {
                mBgPaint?.let {
                    canvas.drawRect(
                        ((width / columnNumber).toFloat() * Math.ceil(((columnNumber) / 2).toDouble())).toFloat(),
                        (height / columnNumber) * (i).toFloat(),
                        ((width / columnNumber).toFloat() * Math.ceil(((columnNumber) / 2).toDouble())).toFloat() + (width / columnNumber),
                        (height / columnNumber) * (i).toFloat() + (height / columnNumber),
                        it
                    )
                }

            }
        }

    }

    public fun drawNew(list: ArrayList<Int>) {
        presenceList = list
        postInvalidate()
    }

}