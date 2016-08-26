package org.spmakings.examshell.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by apple on 27/05/16.
 */
public class AvenirLightEditText extends EditText {

    public AvenirLightEditText(Context context) {
        super(context);
        init(context);
    }

    public AvenirLightEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public AvenirLightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        super.setTypeface(FontCache.get("AvenirLTStd-Light.otf", context));
    }
}
