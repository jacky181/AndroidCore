package jacky.vn.androidcore.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import jacky.vn.androidcore.R;
import jacky.vn.androidcore.base.CallbackObject;
import jacky.vn.androidcore.base.activity.BaseActivity;
import jacky.vn.androidcore.base.activity.BaseMainActivity;

/**
 * Created by Jacky Hua on 27/07/2017.
 */

public abstract class BaseFragment extends Fragment {
    protected View viewRoot;
    private Unbinder unbinder;

    public OnCallbackListener getCallbackListener() {
        return callbackListener;
    }

    public void setCallbackListener(OnCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    private BaseFragment.OnCallbackListener callbackListener;
    private FrameLayout containerPage;
    private ProgressBar progressPage;
    private TextView tvMessagePage;
    private View contentPage;
    private boolean hasStatusPageView;

    public BaseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this.getStatusPageView() > 0) {
            this.hasStatusPageView = true;
            this.viewRoot = LayoutInflater.from(this.getContext()).inflate(this.getStatusPageView(), container, false);
            this.contentPage = LayoutInflater.from(this.getContext()).inflate(this.getLayoutId(), (ViewGroup) null);
            try {
                this.initStatusWidgets();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            this.viewRoot = LayoutInflater.from(this.getContext()).inflate(this.getLayoutId(), container, false);
            this.hasStatusPageView = false;
        }
        this.preInitLayout();
        this.unbinder = ButterKnife.bind(this, this.viewRoot);
        return this.viewRoot;
    }

    protected abstract void preInitLayout();

    public void showProgressPage(boolean isShow) {
        if (isShow) {
            this.containerPage.setVisibility(View.GONE);
            this.progressPage.setVisibility(View.VISIBLE);
            this.tvMessagePage.setVisibility(View.GONE);
        } else {
            this.containerPage.setVisibility(View.VISIBLE);
            this.progressPage.setVisibility(View.GONE);
            this.tvMessagePage.setVisibility(View.GONE);
        }

    }

    public void showMessagePage(String message) {
        if (!TextUtils.isEmpty(message)) {
            this.containerPage.setVisibility(View.GONE);
            this.progressPage.setVisibility(View.GONE);
            this.tvMessagePage.setVisibility(View.VISIBLE);
            this.tvMessagePage.setText(message);
        }

    }

    private void initStatusWidgets() throws IllegalAccessException {
        containerPage = (FrameLayout) viewRoot.findViewById(R.id.containerPage);
        progressPage = (ProgressBar) viewRoot.findViewById(R.id.progressPage);
        tvMessagePage = (TextView) viewRoot.findViewById(R.id.tvMessagePage);
        if (this.containerPage == null) {
            throw new IllegalAccessException("Can\'t find containerPage id");
        } else if (this.progressPage == null) {
            throw new IllegalAccessException("Can\'t find progressPage id");
        } else if (this.tvMessagePage == null) {
            throw new IllegalAccessException("Can\'t find tvMessagePage id");
        } else {
            this.containerPage.addView(this.contentPage);
        }
    }

    protected abstract int getLayoutId();


    protected int getStatusPageView() {
        return 0;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.bindView();
        this.bindMenu();
    }

    private void bindMenu() {

    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unBindView();
        this.unBindMenu();
    }

    private void unBindMenu() {

    }

    public void bindView() {
    }

    public void unBindView() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
    }

    public BaseActivity getBaseActivity() {
        if (this.getActivity() instanceof BaseActivity) {
            return (BaseActivity) this.getActivity();
        } else {
            throw new NullPointerException("Can\t cast this activity to BaseActivity");
        }
    }

    public FragmentHelper getFragmentHelper(){
        if(this.getBaseActivity() instanceof BaseMainActivity) {
            return ((BaseMainActivity)this.getBaseActivity()).fragmentHelper;
        } else {
            throw new NullPointerException("Can\'t find Fragment Helper");
        }
    }

    public interface OnCallbackListener {
        void onCallback(CallbackObject var1);
    }
}
