package jacky.vn.androidcore.helper;

import android.content.Context;

import jacky.vn.androidcore.utils.PreferencesUtils;


public class ChangeTextSizeHelper {
    public static String FONT_SIZE = "font size";
    public static void setTextSizeRatio(Context context,float sizeRatio) {
        PreferencesUtils.putFloat(context, FONT_SIZE, sizeRatio);
    }



    public static float getTextSizeRatio(Context context) {
        return PreferencesUtils.getFloat(context, FONT_SIZE);
    }


}
