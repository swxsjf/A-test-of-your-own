package com.example.myapplication.design;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Android Studio.
 * User: 身为行
 * Date: 2019/4/30
 * Time: 15:48
 * Describe: ${as}
 */
public class MyHeadImageView extends AppCompatImageView {
    private Paint paint;
    private Shader shader;
    private Matrix matrix;
    private Bitmap bitmap;

    public MyHeadImageView(Context context) {
        this(context,null);
    }

    public MyHeadImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyHeadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        BitmapDrawable drawable = (BitmapDrawable) this.getDrawable();
        bitmap = drawable.getBitmap();
        shader = new BitmapShader(bitmap,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setShader(shader);

        matrix = new Matrix();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int measuredHeight = this.getMeasuredHeight();
        int measuredWidth = this.getMeasuredWidth();

        float scaleHeight = measuredHeight * 1.0f / bitmapHeight;
        float scaleWidth = measuredWidth * 1.0f / bitmapWidth;

        float max = Math.max(scaleWidth, scaleHeight);

        matrix.setScale(scaleWidth, scaleHeight);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);

        int radius = Math.min(measuredWidth, measuredHeight) / 2;
        canvas.drawCircle(measuredWidth/2,measuredHeight/2,radius,paint);
    }
}
