package com.example.rogosample.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.cardview.widget.CardView
import com.example.colorbot.OnScrollPadChangeListener
import kotlin.math.pow
import kotlin.math.sqrt

class JoystickView : CardView, View.OnTouchListener {
    private var padding = height / 5
    private var mBgPaint: Paint? = null
    private var mFgPaint: Paint? = null
    private var mSecondPaint: Paint? = null
    private var mBgRect: RectF? = null
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var innerX: Float = 0f
    private var innerY: Float = 0f
    private var isPressing: Boolean = false
    private var listener: OnScrollPadChangeListener?= null


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int) {
//        isFocusable = true
//        mBgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
//        mBgPaint!!.style = Paint.Style.FILL
//        mFgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
//        mFgPaint!!.style = Paint.Style.FILL
//        mSecondPaint = Paint(Paint.ANTI_ALIAS_FLAG)
//        mSecondPaint!!.style = Paint.Style.FILL
//        mBgRect = RectF()
//        setOnTouchListener(this)
//        if (attrs != null) {
//            val attr =
//                context.obtainStyledAttributes(attrs, R.styleable.JoystickView, defStyleAttr, 0)
//            val mBgColor = attr.getColor(
//                R.styleable.JoystickView_jsBgColor,
//                Color.parseColor("#1e2023")
//            )
//            val mSecondColor = attr.getColor(
//                R.styleable.JoystickView_jsSecondColor,
//                Color.parseColor("#e4e4e4")
//            )
//            val mFgColor = attr.getColor(
//                R.styleable.JoystickView_jsFgColor,
//                Color.parseColor("#FFFFFF")
//            )
//            mBgPaint!!.color = mBgColor
//            mSecondPaint!!.color = mSecondColor
//            mFgPaint!!.color = mFgColor
//            attr.recycle()
//        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

//    fun onScrollChange(l: OnScrollPadChangeListener) {
//        listener = l
//    }
//
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        canvas.drawColor(resources.getColor(R.color.black))
//        mBgPaint?.let {
//            canvas.drawCircle(
//                (width / 2).toFloat(),
//                (height / 2).toFloat(),
//                (width / 2).toFloat(),
//                it
//            )
//        }
//        mSecondPaint?.let {
//            canvas.drawCircle(
//                (width / 2).toFloat(),
//                (height / 2).toFloat(),
//                (width / 5).toFloat(),
//                it
//            )
//        }
//        mFgPaint?.let {
//            canvas.drawCircle(
//                innerX.toFloat(),
//                innerY.toFloat(),
//                (width / 5).toFloat(),
//                it
//            )
//        }
//
//    }
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
//        val width = MeasureSpec.getSize(widthMeasureSpec)
//        setMeasuredDimension(width, height)
//        innerX = (width / 2).toFloat()
//        innerY = (height / 2).toFloat()
//        centerX = (width / 2).toFloat()
//        centerY = (height / 2).toFloat()
//        padding = (height / 5)
//    }
//
//
//    override fun onTouch(p0: View, p1: MotionEvent): Boolean {
//        if (p0 == this) {
//            if (p1.actionMasked != MotionEvent.ACTION_UP) {
//                isPressing = true
//                val distance = sqrt(
//                    (p1.x - (width / 2)).toDouble().pow(2.0) + (p1.y - (height / 2)).toDouble()
//                        .pow(2.0)
//                )
//                if (distance < (width / 2 - padding)) {
//                    innerX = p1.x
//                    innerY = p1.y
//                } else {
//                    val ratio = (width/4) / distance
//                    innerX = ((centerX + (p1.x - centerX) * ratio).toFloat())
//                    innerY = ((centerY + (p1.y - centerY) * ratio).toFloat())
//                }
//                val cx = p1.x - width / 2
//                val cy = p1.y - height / 2
//                val angle = (Math.toDegrees(Math.atan2(cy.toDouble(), cx.toDouble())) + 360f) % 360f
//                listener?.onScroll(angle, isPressing)
//                invalidate()
//                attemptClaimDrag()
//                return true
//            } else {
//                isPressing = false
//                innerX = (width/2).toFloat()
//                innerY = (height/2).toFloat()
//                listener?.onStopScroll()
//                invalidate()
//                attemptClaimDrag()
//                return true
//            }
//        }
//        return super.onTouchEvent(p1)
//    }
//
//    private fun attemptClaimDrag() {
//        parent.requestDisallowInterceptTouchEvent(true)
//    }
}