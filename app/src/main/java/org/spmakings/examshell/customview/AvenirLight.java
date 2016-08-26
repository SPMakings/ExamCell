package org.spmakings.examshell.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Saikat's Mac on 28/12/15.
 */
public class AvenirLight extends TextView {

    public AvenirLight(Context context) {
        super(context);
        init(context);
    }

    public AvenirLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public AvenirLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        super.setTypeface(FontCache.get("AvenirLTStd-Light.otf", context));
    }
}

