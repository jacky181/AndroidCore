package jacky.vn.androidcore.base.listener;

import jacky.vn.androidcore.toolbar.ToolbarHelper;

/**
 * Created by Jacky Hua on 27/07/2017.
 */

public interface OnBaseToolbarAction {
    void setTitleToolbar(String var1);

    void setTitleToolbar(String var1, String var2);

    void setTitleBarColor(int var1);

    void showBackButton(boolean var1);

    void showMenu(boolean var1);

    ToolbarHelper getToolbarHelper() throws IllegalAccessException;

}