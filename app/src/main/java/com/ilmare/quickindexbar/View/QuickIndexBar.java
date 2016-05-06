package com.ilmare.quickindexbar.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by zhangchenggeng
 * Time 2016/5/6 8:23.
 * Descripton:
 * History:
 * 版权所有
 */
public class QuickIndexBar extends TextView {


    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    private Paint mPaint;   //画笔
    private int cellWidth;   //宽度
    private int cellHeight; //每个字母所占的高度

    private OnLetterChangedListener onLetterChangedListener;

    public OnLetterChangedListener getOnLetterChangedListener() {
        return onLetterChangedListener;
    }

    public void setOnLetterChangedListener(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

    public interface  OnLetterChangedListener{
        void onLetterChanged(String letter);
    }


    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextSize(20);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < LETTERS.length; i++) {
            String text = LETTERS[i];
            // 计算坐标
            int x = (int) (cellWidth / 2.0f - mPaint.measureText(text) / 2.0f);

            // 获取文本的高度
            Rect bounds = new Rect();// 矩形
            mPaint.getTextBounds(text, 0, text.length(), bounds);
            int textHeight = bounds.height();
            int y = (int) (cellHeight / 2.0f + textHeight / 2.0f + i * cellHeight);

            // 根据按下的字母, 设置画笔颜色
           mPaint.setColor(touchIndex == i ? Color.WHITE : Color.BLACK);
            // 绘制文本A-Z
            canvas.drawText(text, x, y, mPaint);
        }
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        cellWidth = this.getMeasuredWidth();
        int mHeight=this.getMeasuredHeight();
        cellHeight = (int) (mHeight*1.0f/LETTERS.length);
        super.onSizeChanged(w, h, oldw, oldh);
    }



    private int touchIndex=-1;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int position=-1;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                position = (int) (event.getY()/cellHeight);
                if(position>=0&&position<LETTERS.length){
                    if (position!=touchIndex){

                        if(onLetterChangedListener!=null){
                            onLetterChangedListener.onLetterChanged(LETTERS[position]);
                        }
                        //按下事件
                        touchIndex=position;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                position = (int) (event.getY()/cellHeight);
                if(position>=0&&position<LETTERS.length){
                    if (position!=touchIndex){

                        if(onLetterChangedListener!=null){
                            onLetterChangedListener.onLetterChanged(LETTERS[position]);
                        }

                        //按下事件
                        touchIndex=position;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                touchIndex=-1;
                break;
        }

        invalidate();

        return true;
    }
}
