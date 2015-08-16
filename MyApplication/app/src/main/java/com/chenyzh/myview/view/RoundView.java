package com.chenyzh.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.chenyzh.myview.R;

/**
 * Created by chenyzh on 15/7/24.
 */
public class RoundView extends View {

    private Paint paint;

    private int roundColor;
    private int roundProgressColor;
    private float roundWidth;
    private int textColor;
    private float textSize;
    private int max;
    private boolean textIsDisplayable;
    private int style;

    private int process;



    public static final int STROKE = 0;
    public static final int FILL = 1;

    public RoundView(Context context) {
        this(context, null);
    }

    public RoundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        roundColor = typedArray.getColor(R.styleable.RoundView_roundColor, Color.WHITE);
        roundProgressColor = typedArray.getColor(R.styleable.RoundView_roundProgressColor, Color.BLUE);
        roundWidth = typedArray.getDimension(R.styleable.RoundView_roundWidth, 2f);
        textColor = typedArray.getColor(R.styleable.RoundView_textColor, Color.BLACK);
        textSize = typedArray.getDimension(R.styleable.RoundView_textSize, 16f);
        max = typedArray.getInteger(R.styleable.RoundView_max, 100);
        process = typedArray.getInteger(R.styleable.RoundView_process,80);
        textIsDisplayable = typedArray.getBoolean(R.styleable.RoundView_textIsDisplayable, true);
        style = typedArray.getInt(R.styleable.RoundView_style, 0);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2;
        int radius = (int) (centre - roundWidth / 2);
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);
        canvas.drawCircle(centre, centre, radius, paint);

        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        float centerText = process / max;
        float textWidth = paint.measureText(centerText+"");
        if (textIsDisplayable) {
            canvas.drawText(centerText+"", centre - textWidth / 2, centre + textWidth / 2, paint);
        }

        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundProgressColor);
        RectF rectF = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        switch (style){
            case STROKE:
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(rectF,0,360*process/max,false,paint);
                break;
            case FILL:
                paint.setStyle(Paint.Style.FILL);
                if (process!=0){
                    canvas.drawArc(rectF,0,360*process/max,true,paint);
                }
                break;
        }

    }

    public void setMax(int max){
        if (max<0){
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }
    public int getMax(){
        return max;
    }

    public void setProcess(int process) {
        if (process <0){
            throw new IllegalArgumentException("process not less than 0");
        }
        this.process = process;
        postInvalidate();
    }

    public int getProcess() {
        return process;
    }

}
