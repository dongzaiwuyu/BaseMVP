package com.don.basemvp.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.don.basemvp.R;

/***
 *@author Don
 *@date on 2020/4/23 14:18
 *@describe 默认加载框
 */
public class BaseLoadingPopupWindow extends PopupWindow {

    private TextView tv_tips;

    public BaseLoadingPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public BaseLoadingPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BaseLoadingPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseLoadingPopupWindow(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.setBackgroundDrawable(null);
        View contentView = LayoutInflater.from(context).inflate(R.layout.widget_base_pop, null);
        tv_tips = contentView.findViewById(R.id.BasePopupWindow_Tips);
        this.setContentView(contentView);
    }

    public void UpdateLoadingTips(String tips) {
        if (tv_tips != null && !TextUtils.isEmpty(tips)) {
            tv_tips.setText(tips);
        }
    }
}
