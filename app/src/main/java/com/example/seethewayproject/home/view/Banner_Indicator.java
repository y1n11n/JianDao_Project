package com.example.seethewayproject.home.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.seethewayproject.R;

public class Banner_Indicator extends View {

    private Paint mPaint;
    private Context mContext;

    private int bannerImageSize;

    int currrentItemPosion = 0;

    private int recyf_right_defult = 0;

    private int wind_Width;

    public Banner_Indicator(Context context) {
        this(context, null);
    }

    public Banner_Indicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner_Indicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.color_RED));
        getWindWidth();
    }

    public void setBannerImageSize(int bannerImageSize){
        this.bannerImageSize = bannerImageSize;
    }
    public void setCurrentBannerItem(int currrentItemPosion){
        this.currrentItemPosion = currrentItemPosion;
        currrentItemPosion = currrentItemPosion + 1;
        recyf_right_defult = wind_Width/bannerImageSize*currrentItemPosion;
        invalidate();
    }

    private void getWindWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wind_Width = wm.getDefaultDisplay().getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.top = 0;
        rectF.right = recyf_right_defult;
        rectF.bottom = 30;
        canvas.drawRect(rectF, mPaint);
    }
}
