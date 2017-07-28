package jacky.vn.androidcore.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import jacky.vn.androidcore.R.id;
import jacky.vn.androidcore.base.fragment.FragmentHelper;
import jacky.vn.androidcore.base.listener.OnBaseToolbarAction;
import jacky.vn.androidcore.base.listener.OnCallbackToolbarAction;
import jacky.vn.androidcore.helper.FragmentStateHelper;
import jacky.vn.androidcore.toolbar.ToolbarHelper;


/**
 * Created by Jacky Hua on 27/07/2017.
 */

public abstract class  BaseMainActivity<T extends ToolbarHelper> extends BaseActivity implements OnBaseToolbarAction {

    private final String NULL_TOOLBAR_EX = "Can\'t find toolbar of this activity. Please checking it. Note: With raw id : R.id.toolbar";
    public ToolbarHelper toolbarHelper;
    protected Toolbar toolbar;
    public FragmentHelper fragmentHelper;
    public FragmentStateHelper fragmentStateHelper;

    public BaseMainActivity() {
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(this.useFragmentState()) {
            this.fragmentStateHelper = new FragmentStateHelper(this.getSupportFragmentManager(), id.fragment_content);
        } else {
            this.fragmentHelper = new FragmentHelper(this.getSupportFragmentManager(), id.fragment_content);
        }

        super.onCreate(savedInstanceState);
        this.toolbar = (Toolbar)this.findViewById(id.toolbar);

        try {
            this.setupToolbar();
        } catch (IllegalAccessException var3) {
            var3.printStackTrace();
        }

    }

    public void onCallBackToolbar() {
        if(this.useFragmentState()) {
            if(!this.fragmentStateHelper.isRootFragment()) {
                this.fragmentStateHelper.popFragment(1);
            } else {
                this.onBackPressed();
            }
        } else {
            this.fragmentHelper.popBackStack();
        }

    }

    public void setTitleToolbar(String msg) {
        this.toolbarHelper.setTitle(msg);
    }

    public void setTitleToolbar(@NonNull String msg, @NonNull String font) {
        this.toolbarHelper.setTitle(msg, font);
    }

    public void showBackButton(boolean isShow) {
        this.toolbarHelper.showBackButton(isShow, (OnCallbackToolbarAction) this);
    }

    public void setTitleMainColor(int color) {
        this.toolbarHelper.setTitleToolbarColor(color);
    }

    private void setupToolbar() throws IllegalAccessException {
        if(this.toolbar == null) {
            throw new NullPointerException("Can\'t find toolbar of this activity. Please checking it. Note: With raw id : R.id.toolbar");
        } else {
            this.setSupportActionBar(this.toolbar);
            this.toolbar.setBackgroundResource(this.onColorOfToolbar());
            this.toolbarHelper = this.getToolbarHelper();
            if(this.toolbarHelper == null) {
                this.toolbarHelper = new ToolbarHelper(this.toolbar);
            }

            this.toolbarHelper.setLeftIconButton(this.onImageForLeftButtonToolbar());
        }
    }

    public abstract int onColorOfToolbar();

    public abstract int onImageForLeftButtonToolbar();

    public T getToolbarHelper() throws IllegalAccessException {
        return (T) this.toolbarHelper;
    }

    public abstract boolean useFragmentState();

}
