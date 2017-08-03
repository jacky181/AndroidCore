package jacky.vn.androidcore.base.listener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

import jacky.vn.androidcore.base.fragment.BaseFragment;

/**
 * Created by Jacky Hua on 27/07/2017.
 */

public interface OnFragmentAction {
    void replaceFragment(int var1, BaseFragment var2);

    void replaceFragment(int var1, BaseFragment var2, Bundle var3);

    void replaceFragment(BaseFragment var1);

    void replaceFragment(BaseFragment var1, boolean var2, int var3, int var4);

    void replaceFragment(BaseFragment var1, Bundle var2);

    void replaceFragment(int var1, Fragment var2);

    void replaceFragment(int var1, Fragment var2, boolean var3);

    void replaceFragmentWithSharedElement(BaseFragment var1, List<View> var2);

    void addFragment(BaseFragment var1);

    void addFragment(BaseFragment var1, boolean var2);

    void addFragmentWithSharedElement(BaseFragment var1, List<View> var2);

    void popBackStack();

    void removeFragment(Fragment var1);

    int getSizeFragmentManager();

    void clearAllFragments();

    BaseFragment getLastFragment();
}
