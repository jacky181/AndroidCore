package jacky.vn.androidcore.base.fragment;



import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.Iterator;
import java.util.List;

import jacky.vn.androidcore.R.id;
import jacky.vn.androidcore.R.anim;
import jacky.vn.androidcore.base.listener.OnFragmentAction;

/**
 * Created by Jacky Hua on 27/07/2017.
 */

public class FragmentHelper implements OnFragmentAction {
    private FragmentManager fragmentManager;
    private  int idContent;

    public FragmentHelper(FragmentManager fragmentManager, int idContent) {
        this.idContent = id.fragment_content;
        this.fragmentManager = fragmentManager;
        this.idContent = idContent;
    }

    public void replaceFragment(int id, BaseFragment baseFragment) {
        this.fragmentManager.beginTransaction().replace(id, baseFragment).addToBackStack(baseFragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void replaceFragment(int id, BaseFragment baseFragment, Bundle bundle) {
        baseFragment.setArguments(bundle);
        this.fragmentManager.beginTransaction().replace(id, baseFragment).addToBackStack(baseFragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void replaceFragment(BaseFragment baseFragment) {
        this.fragmentManager.beginTransaction().setCustomAnimations(anim.fade_in, anim.fade_out).replace(this.idContent,
                baseFragment).addToBackStack(baseFragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void replaceFragment(BaseFragment baseFragment, boolean isBackStack, int inAnim, int outAnim) {
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction()
                .setCustomAnimations(inAnim, outAnim).replace(this.idContent, baseFragment);
        if(isBackStack) {
            fragmentTransaction.addToBackStack(baseFragment.getClass().getName());
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    public void replaceFragment(BaseFragment baseFragment, Bundle bundle) {
        baseFragment.setArguments(bundle);
        this.fragmentManager.beginTransaction().replace(this.idContent, baseFragment).addToBackStack(baseFragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void replaceFragment(int id, Fragment fragment) {
        this.fragmentManager.beginTransaction().replace(id, fragment).addToBackStack(fragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void replaceFragment(int id, Fragment fragment, boolean isBackStack) {
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        if(isBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    public void replaceFragmentWithSharedElement(BaseFragment baseFragment, List<View> views) {
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction().replace(this.idContent, baseFragment);
        if(views != null && !views.isEmpty()) {
            Iterator var4 = views.iterator();

            while(var4.hasNext()) {
                View view = (View)var4.next();
                if(Build.VERSION.SDK_INT >= 21) {
                    fragmentTransaction.addSharedElement(view, view.getTransitionName());
                }
            }
        }

        fragmentTransaction.addToBackStack(baseFragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void addFragment(BaseFragment baseFragment) {
        this.fragmentManager.beginTransaction().setCustomAnimations(anim.fade_in, anim.fade_out).add(this.idContent, baseFragment).addToBackStack(baseFragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void addFragment(BaseFragment baseFragment, boolean isBackStack) {
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(anim.fade_in, anim.fade_out).add(this.idContent, baseFragment);
        if(isBackStack) {
            fragmentTransaction.addToBackStack(baseFragment.getClass().getName());
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    public void addFragmentWithSharedElement(BaseFragment baseFragment, List<View> views) {
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction().add(this.idContent, baseFragment);
        if(views != null && !views.isEmpty()) {
            Iterator var4 = views.iterator();

            while(var4.hasNext()) {
                View view = (View)var4.next();
                if(Build.VERSION.SDK_INT >= 21) {
                    fragmentTransaction.addSharedElement(view, view.getTransitionName());
                }
            }
        }

        fragmentTransaction.addToBackStack(baseFragment.getClass().getName()).commitAllowingStateLoss();
    }

    public void popBackStack() {
        this.fragmentManager.popBackStack();
    }

    public void removeFragment(Fragment baseFragment) {
        this.fragmentManager.beginTransaction().remove(baseFragment).commitAllowingStateLoss();
    }

    public int getSizeFragmentManager() {
        return this.fragmentManager.getFragments().size();
    }

    public void clearAllFragments() {
        for(int i = 0; i < this.fragmentManager.getBackStackEntryCount(); ++i) {
            this.fragmentManager.popBackStackImmediate((String)null, 1);
        }

    }

    public BaseFragment getLastFragment() {
        return (BaseFragment)this.fragmentManager.getFragments().get(this.getSizeFragmentManager() - 1);
    }
}
