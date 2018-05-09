package com.yoma.roundbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;

/**
 * exp：重写的很多方法主要是为了适配5.0之前的系统，5.0之后的系统实现起来就很简单了
 * <p>
 * Created by ZhuLei on 2017/9/20.
 * Email: zhuleineuq@gmail.com
 */

public class RoundButtonDrawable extends GradientDrawable {

    // 是否圆角自适应
    private boolean mRadiusAdjustBounds;
    // 边框颜色
    private ColorStateList mFillColors;
    // 边框宽度
    private int mStrokeWidth;
    // 填充颜色
    private ColorStateList mStrokeColors;

    @Override
    protected boolean onStateChange(int[] stateSet) {
        boolean result = super.onStateChange(stateSet);

        if (mFillColors != null) {
            int color = mFillColors.getColorForState(stateSet, 0);
            setColor(color);
            result = true;
        }
        if (mStrokeColors != null) {
            int color = mStrokeColors.getColorForState(stateSet, 0);
            setStroke(mStrokeWidth, color);
            result = true;
        }
        return result;
    }

    @Override
    public boolean isStateful() {
        return (mFillColors != null && mFillColors.isStateful())
                || (mStrokeColors != null && mStrokeColors.isStateful())
                || super.isStateful();
    }

    @Override
    protected void onBoundsChange(Rect r) {
        super.onBoundsChange(r);

        if (mRadiusAdjustBounds) {
            // 圆角是宽或者高的一半
            setCornerRadius(Math.min(r.width(), r.height()) / 2);
        }
    }

    public void setStrokeData(int borderWidth, ColorStateList colorBorder) {
        if (hasNativeStateListAPI()) {
            super.setStroke(borderWidth, colorBorder);

            return;
        }

        mStrokeWidth = borderWidth;
        mStrokeColors = colorBorder;
        final int color;
        if (colorBorder == null) {
            color = Color.TRANSPARENT;
        } else {
            color = colorBorder.getColorForState(getState(), 0);
        }
        setStroke(borderWidth, color);
    }

    /**
     * 设置初始背景
     */
    public void setBgData(ColorStateList bgData) {
        if (hasNativeStateListAPI()) {
            super.setColor(bgData);
            return;
        }

        mFillColors = bgData;
        final int color;
        if (bgData == null) {
            color = Color.TRANSPARENT;
        } else {
            color = bgData.getColorForState(getState(), 0);
        }
        setColor(color);
    }

    public void setRadiusAdjustBounds(boolean radiusAdjustBounds) {
        mRadiusAdjustBounds = radiusAdjustBounds;
    }

    private boolean hasNativeStateListAPI() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static RoundButtonDrawable fromAttributeSet(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundButton, defStyleAttr, 0);
        ColorStateList colorBg = ta.getColorStateList(R.styleable.RoundButton_backgroundColor);
        ColorStateList colorBorder = ta.getColorStateList(R.styleable.RoundButton_borderColor);
        int borderWidth = ta.getDimensionPixelSize(R.styleable.RoundButton_borderWidth, 0);
        boolean radiusAdjustBounds = ta.getBoolean(R.styleable.RoundButton_isRadiusAdjustBounds, true);
        int radius = ta.getDimensionPixelSize(R.styleable.RoundButton_radius, 0);
        int radiusTopLeft = ta.getDimensionPixelSize(R.styleable.RoundButton_radiusTopLeft, 0);
        int radiusTopRight = ta.getDimensionPixelSize(R.styleable.RoundButton_radiusTopRight, 0);
        int radiusBottomRight = ta.getDimensionPixelSize(R.styleable.RoundButton_radiusBottomRight, 0);
        int radiusBottomLeft = ta.getDimensionPixelSize(R.styleable.RoundButton_radiusBottomLeft, 0);
        ta.recycle();

        RoundButtonDrawable drawableBg = new RoundButtonDrawable();
        drawableBg.setBgData(colorBg);
        drawableBg.setStrokeData(borderWidth, colorBorder);
        // 设置边角
        if (radiusTopLeft > 0 || radiusTopRight > 0 || radiusBottomRight > 0 || radiusBottomLeft > 0) {
            float[] cornerRadii = new float[]{
                    radiusTopLeft, radiusTopLeft,
                    radiusTopRight, radiusTopRight,
                    radiusBottomRight, radiusBottomRight,
                    radiusBottomLeft, radiusBottomLeft
            };
            drawableBg.setCornerRadii(cornerRadii);
            radiusAdjustBounds = false;
        } else if (radius > 0) {
            drawableBg.setCornerRadius(radius);
            radiusAdjustBounds = false;
        }
        drawableBg.setRadiusAdjustBounds(radiusAdjustBounds);
        return drawableBg;
    }
}
