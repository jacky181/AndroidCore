package jacky.vn.androidcore.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jacky Hua on 10/08/2017.
 */

public class FontHelper {
    private static final String TAG = FontHelper.class.getSimpleName();
    private static HashMap<String, Typeface> fonts = new HashMap<>();
    public static final int LIGHT_FONT = 0;
    public static final int REGULAR_FONT = 1;
    public static final int MEDIUM_FONT = 2;


    public static Typeface getFont(String font, Context context) {
        Typeface typeface = fonts.get(font);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), font);
            fonts.put(font, typeface);
        }
        return typeface;
    }

    public static void setFont(TextView textView, String font) {
        try {
            Typeface typeface = getFont(font, textView.getContext());
            textView.setTypeface(typeface);
        } catch (Exception e) {
            Log.i(TAG, "setFont: " + e.getMessage());
        }
    }

    public static void setTypeFont(TextView textView, int type, List<String> fontArrays) {
        setFont(textView, fontArrays.get(type));
    }

}
