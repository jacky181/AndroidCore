package jacky.vn.androidcore.widget.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import jacky.vn.androidcore.R;
import jacky.vn.androidcore.helper.ChangeTextSizeHelper;
import jacky.vn.androidcore.helper.FontHelper;
import jacky.vn.androidcore.utils.PreferencesUtils;
import jacky.vn.androidcore.utils.WidgetUtils;


/**
 * Created by Jacky Hua on 10/08/2017.
 */

public abstract class JButton extends AppCompatEditText {

    private List<String> fontArrays;

    public JButton(Context context) {
        super(context);
        init(context, null);
    }


    public JButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public JButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        setUpFontArray();
        int type = 1;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JEditText);
            type = typedArray.getInt(R.styleable.JEditText_etv_font, 1);
            boolean enableChangeSize = typedArray.getBoolean(R.styleable.JEditText_enableChangeSize, true);
            if (enableChangeSize) {
                float fontSizeRatio = PreferencesUtils.getFloat(getContext(), ChangeTextSizeHelper.FONT_SIZE);
                setFontSize(fontSizeRatio);
            }
            typedArray.recycle();
        }
        if (fontArrays.size() > 0) {
            FontHelper.setTypeFont(this, type, fontArrays);
        }
    }

    public void setFontSize(float fontSizeRatio) {
        if (fontSizeRatio <= 0 || fontSizeRatio == 1)
            return;
        setTextSize(TypedValue.COMPLEX_UNIT_SP, WidgetUtils.pixelToSp(getContext(), getTextSize() * fontSizeRatio));
    }

    private void setUpFontArray() {
        if (fontArrays == null) {
            fontArrays = new ArrayList<>();
        }

        fontArrays.clear();
        if (!TextUtils.isEmpty(createLightFont())) {
            fontArrays.add(createLightFont());
        }
        if (!TextUtils.isEmpty(createRegularFont())) {
            fontArrays.add(createRegularFont());
        }
        if (!TextUtils.isEmpty(createMediumFont())) {
            fontArrays.add(createMediumFont());
        }
        if (!TextUtils.isEmpty(createItalicFont())) {
            fontArrays.add(createItalicFont());
        }

    }

    protected abstract String createItalicFont();

    protected abstract String createMediumFont();

    protected abstract String createRegularFont();

    protected abstract String createLightFont();


}
