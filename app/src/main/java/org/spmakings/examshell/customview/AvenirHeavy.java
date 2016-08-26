package org.spmakings.examshell.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by apple on 27/05/16.
 */
public class AvenirHeavy extends TextView {

    public AvenirHeavy(Context context) {
        super(context);
        init(context);
    }

    public AvenirHeavy(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public AvenirHeavy(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        super.setTypeface(FontCache.get("AvenirLTStd-Heavy.otf", context));
    }
}

