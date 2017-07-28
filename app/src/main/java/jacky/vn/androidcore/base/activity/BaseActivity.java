package jacky.vn.androidcore.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import jacky.vn.androidcore.base.listener.OnBaseToolbarAction;
import jacky.vn.androidcore.toolbar.ToolbarHelper;

/**
 * Created by Jacky Hua on 27/07/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements OnBaseToolbarAction{

    public Unbinder unbinder;

    public BaseActivity() {
    }


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
        this.bindView();
        this.setUpViewAndData();
    }

    protected abstract int getLayoutId();

    protected abstract void setUpViewAndData();

    public void bindView() {
        this.unbinder = ButterKnife.bind(this);
    }

    public void unBindView(){
        if (this.unbinder !=null){
            this.unbinder.unbind();
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        this.unBindView();
    }
}
