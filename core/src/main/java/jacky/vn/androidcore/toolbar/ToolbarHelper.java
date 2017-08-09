package jacky.vn.androidcore.toolbar;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import jacky.vn.androidcore.R.id;
import jacky.vn.androidcore.base.listener.OnCallbackToolbarAction;
import jacky.vn.androidcore.base.listener.OnToolbarAction;

/**
 * Created by Jacky Hua on 27/07/2017.
 */

public class ToolbarHelper implements OnToolbarAction {

    protected Toolbar toolbar;
    protected ImageView leftBtn;
    protected EditText edTitle;
    protected TextView rightBtn;
    protected Context context;

    public ToolbarHelper(Toolbar toolbar) throws IllegalAccessException {
        this.toolbar = toolbar;
        this.context = toolbar.getContext();
        this.leftBtn = (ImageView)toolbar.findViewById(id.left_button);
        this.rightBtn = (TextView)toolbar.findViewById(id.right_button);
        if(this.rightBtn == null) {
            throw new IllegalAccessException("Can\'t find this right button");
        } else if(this.leftBtn == null) {
            throw new IllegalAccessException("Can\'t find this Left button");
        } else {
            this.edTitle = (EditText) toolbar.findViewById(id.tvTitleToolbar);
            if(this.edTitle == null) {
                throw new IllegalAccessException("Can\'t find this Title TextView");
            }
        }
    }

    @Override
    public void setTitle(@NonNull String title) {
        if (this.toolbar !=null){
            this.setTitle(title,"");
        }
    }

    @Override
    public void setTitle(@NonNull String title1, String font) {
        this.edTitle.setText(title1);
        if (!TextUtils.isEmpty(font)){
            this.edTitle.setTypeface(Typeface.createFromAsset(this.edTitle.getContext().getAssets(),font));
        }
    }

    @Override
    public void setTitleToolbarColor(int var1) {
        if (this.toolbar !=null){
            this.edTitle.setTextColor(var1);
        }
    }

    @Override
    public void showToolbar(boolean isShow) {
        if (this.toolbar !=null){
            if (isShow){
                this.toolbar.setVisibility(View.VISIBLE);
            }else {
                this.toolbar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showBackButton(boolean isShow) {
        this.showBackButton(isShow, (OnCallbackToolbarAction)null);
    }

    @Override
    public void showBackButton(boolean isShow, final OnCallbackToolbarAction callbackToolbarAction) {
        if(this.toolbar != null) {
            if(isShow) {
                this.leftBtn.setVisibility(View.VISIBLE);
            } else {
                this.leftBtn.setVisibility(View.GONE);
            }
        }

        if (callbackToolbarAction !=null){
            this.leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callbackToolbarAction.onCallbackToolbar();
                }
            });
        }else {
            this.leftBtn.setOnClickListener(null);
        }
    }

    @Override
    public void setLeftIconButton(int drawable) {
        this.leftBtn.setImageResource(drawable);
    }


    @Override
    public void setRightToolbarButton(String text, View.OnClickListener onClickListener) {
        this.rightBtn.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        if (this.rightBtn !=null){
            if (TextUtils.isEmpty(text)){
                this.rightBtn.setVisibility(View.GONE);
            }else {
                this.rightBtn.setText(text);
                this.rightBtn.setVisibility(View.VISIBLE);
                this.setupOnClickListener(onClickListener);
            }
        }
    }

    @Override
    public void setRightIconButton(int iconRes, View.OnClickListener onClickListener) {
        this.rightBtn.setText("");
        if (this.rightBtn !=null){
            if (iconRes <=0){
                this.rightBtn.setVisibility(View.GONE);
            }else {
                this.rightBtn.setVisibility(View.VISIBLE);
                Drawable drawable = ContextCompat.getDrawable(this.context, iconRes);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    this.rightBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,drawable,null);
                }
                this.setupOnClickListener(onClickListener);
            }
        }
    }

    @Override
    public void setupSearchView(int hintIcon) {

        if (hintIcon>0){
            Drawable drawable = ContextCompat.getDrawable(this.context, hintIcon);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                this.edTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable,null,null,null);
            }
        }
    }

    private void setupOnClickListener(final View.OnClickListener onClickListener) {
        if (onClickListener!=null){
            this.rightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClick(view);
                }
            });
        }
    }
}
