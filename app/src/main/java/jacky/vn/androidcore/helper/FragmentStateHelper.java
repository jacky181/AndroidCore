package jacky.vn.androidcore.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import jacky.vn.androidcore.R;
import jacky.vn.androidcore.R.id;
import jacky.vn.androidcore.base.fragment.BaseFragment;
import jacky.vn.androidcore.base.listener.OnFragmentStateAction;


/**
 * Created by Hieu on 26/07/2017.
 */

public class FragmentStateHelper implements OnFragmentStateAction {
    private FragmentManager fragmentManager;
    private int idContent;
    private int stackSelected;
    private int tagCount;
    private List<Stack<BaseFragment>> stacksFragment;
    private BaseFragment[] rootFragments;
    private List<String> fragmentsKeepAlive;

    public FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    public List<Stack<BaseFragment>> getStacksFragment() {
        return this.stacksFragment;
    }

    public BaseFragment[] getRootFragments() {
        return this.rootFragments;
    }

    public List<String> getFragmentsKeepAlive() {
        return this.fragmentsKeepAlive;
    }

    public FragmentStateHelper(FragmentManager fragmentManager, int idContent) {
        this.idContent = id.fragment_content;
        this.stackSelected = -1;
        this.fragmentManager = fragmentManager;
        this.idContent = idContent;
        this.stacksFragment = new ArrayList();
        this.fragmentsKeepAlive = new ArrayList();
    }

    public int getStackSelected() {
        return this.stackSelected;
    }

    public void setStacksRootFragment(BaseFragment... fragments) {
        this.rootFragments = new BaseFragment[fragments.length];

        for(int index = 0; index < fragments.length; ++index) {
            this.stacksFragment.add(new Stack());
        }

        this.rootFragments = fragments;
    }

    public void changeRootFragment(BaseFragment fragments, int stackId) {
        this.clearStack(stackId);
        if(((Stack)this.stacksFragment.get(stackId)).size() > 0) {
            this.beginTrans().remove(this.getFragByTag(((BaseFragment)((Stack)this.stacksFragment.get(stackId)).pop()).getTag())).commitAllowingStateLoss();
        }

        this.rootFragments[stackId] = fragments;
        this.refreshStack(stackId);
    }

    public boolean isRootFragment() {
        return ((Stack)this.stacksFragment.get(this.stackSelected)).size() <= 1;
    }

    public void pushFragment(BaseFragment fragment) {
        this.beginTrans().add(this.idContent, fragment, this.generateTag(fragment)).commitAllowingStateLoss();
        this.detachCurrentFrag();
        ((Stack)this.stacksFragment.get(this.stackSelected)).push(fragment);
    }

    public void pushFragmentKeepOld(BaseFragment fragment) {
        this.beginTrans().add(this.idContent, fragment, this.generateTag(fragment)).commitAllowingStateLoss();
        ((Stack)this.stacksFragment.get(this.stackSelected)).push(fragment);
        this.fragmentsKeepAlive.add(((BaseFragment)((Stack)this.stacksFragment.get(this.stackSelected)).peek()).getTag());
    }

    public void popFragment(int numberPop) {
        if(numberPop >= ((Stack)this.stacksFragment.get(this.stackSelected)).size()) {
            throw new StringIndexOutOfBoundsException("Number pop out of stack size");
        } else {
            for(int index = 0; index < numberPop; ++index) {
                this.beginTrans().remove(this.getFragByTag(((BaseFragment)((Stack)this.stacksFragment.get(this.stackSelected)).pop()).getTag())).commitAllowingStateLoss();
            }

            if(!this.fragmentsKeepAlive.contains(((BaseFragment)((Stack)this.stacksFragment.get(this.stackSelected)).peek()).getTag())) {
                this.beginTrans().attach(this.getFragByTag(((BaseFragment)((Stack)this.stacksFragment.get(this.stackSelected)).peek()).getTag())).commitAllowingStateLoss();
            }

        }
    }

    public void showStack(int stackId) {
        if(stackId != this.stackSelected) {
            this.attackStack(stackId);
            this.detachPrevStack();
            this.stackSelected = stackId;
        }

    }

    public void refreshStack(int stackId) {
        if(stackId == this.stackSelected) {
            this.attackStack(stackId);
        }

    }

    public void replaceFragment(BaseFragment fragment) {
        this.beginTrans().replace(this.idContent, fragment, this.generateTag(fragment)).commitAllowingStateLoss();
        ((Stack)this.stacksFragment.get(this.stackSelected)).pop();
        ((Stack)this.stacksFragment.get(this.stackSelected)).push(fragment);
    }

    public void clearStack(int stackId) {
        Stack stackFragment = (Stack)this.stacksFragment.get(stackId);

        while(stackFragment.size() > 1) {
            this.beginTrans().remove(this.getFragByTag(((BaseFragment)stackFragment.pop()).getTag())).commitAllowingStateLoss();
        }

        this.refreshStack(stackId);
    }

    public void clearAllStacks() {
        Iterator var1 = this.stacksFragment.iterator();

        while(var1.hasNext()) {
            Stack stack = (Stack)var1.next();
            this.clearStack(this.stacksFragment.indexOf(stack));
        }

    }

    private String generateTag(BaseFragment fragment) {
        return fragment.getClass().getName() + ++this.tagCount;
    }

    private Fragment getFragByTag(String tab) {
        return this.fragmentManager.findFragmentByTag(tab);
    }

    private FragmentTransaction beginTrans() {
        return this.fragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
    }

    private void attackStack(int stackId) {
        if(((Stack)this.stacksFragment.get(stackId)).size() == 0) {
            this.initStack(stackId);
        } else {
            this.beginTrans().attach(this.getFragByTag(((BaseFragment)((Stack)this.stacksFragment.get(stackId)).peek()).getTag())).commitAllowingStateLoss();
        }

    }

    private void detachPrevStack() {
        if(this.stackSelected != -1) {
            this.detachCurrentFrag();
        }

    }

    private void detachCurrentFrag() {
        this.beginTrans().detach(this.getFragByTag(((BaseFragment)((Stack)this.stacksFragment.get(this.stackSelected)).peek()).getTag())).commitAllowingStateLoss();
    }

    private void initStack(int stackId) {
        ((Stack)this.stacksFragment.get(stackId)).push(this.rootFragments[stackId]);
        this.beginTrans().add(this.idContent, (Fragment)((Stack)this.stacksFragment.get(stackId)).peek(), this.generateTag((BaseFragment)((Stack)this.stacksFragment.get(stackId)).peek())).commitAllowingStateLoss();
    }
}
