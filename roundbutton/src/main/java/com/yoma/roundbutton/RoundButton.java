package com.yoma.roundbutton;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * 带圆角的 Button，可设置背景，边框
 * <p>
 * 如需在 Java 中指定以上属性, 需要通过 {@link #getBackground()} 获取 {@link RoundButtonDrawable} 对象,
 * 然后使用 {@link RoundButtonDrawable} 提供的方法进行设置。
 * <p>
 * Created by ZhuLei on 2017/9/20.
 * Email: zhuleineuq@gmail.com
 */

public class RoundButton extends AppCompatButton {

    public RoundButton(Context context) {
        super(context);

        init(context, null, 0);
    }

    public RoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0);
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        RoundButtonDrawable drawableBg = RoundButtonDrawable.fromAttributeSet(context, attrs, defStyleAttr);
        setBackgroundDrawable(drawableBg);
    }


}
