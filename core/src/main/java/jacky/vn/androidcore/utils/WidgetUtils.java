package jacky.vn.androidcore.utils;

import android.content.Context;

/**
 * Created by Jacky Hua on 10/08/2017.
 */

public class WidgetUtils {
    public static float pixelToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }
}
