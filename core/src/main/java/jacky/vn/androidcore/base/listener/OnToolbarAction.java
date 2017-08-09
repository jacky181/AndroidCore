package jacky.vn.androidcore.base.listener;

import android.view.View;

/**
 * Created by Jacky Hua on 27/07/2017.
 */

public interface OnToolbarAction {
    void setTitle(String title);

    void setTitle(String title1, String title2);

    void setTitleToolbarColor(int var1);

    void showToolbar(boolean var1);

    void showBackButton(boolean var1);

    void showBackButton(boolean var1, OnCallbackToolbarAction var2);

    void setLeftIconButton(int var1);

    void setRightToolbarButton(String var1, View.OnClickListener var2);

    void setRightIconButton(int var1, View.OnClickListener var2);

    void setupSearchView(int hintIcon);

}
