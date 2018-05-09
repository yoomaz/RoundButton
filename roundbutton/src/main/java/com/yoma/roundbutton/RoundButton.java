package com.yoma.roundbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by ZhuLei on 2017/9/20.
 * Email: zhuleineuq@gmail.com
 */

public class RoundButton extends Button {

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
