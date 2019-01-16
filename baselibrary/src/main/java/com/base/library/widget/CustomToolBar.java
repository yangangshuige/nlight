package com.base.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.library.R;

public class CustomToolBar extends LinearLayout implements View.OnClickListener {

    private View naviView;
    private ImageView img_left;
    private ImageView img_right;
    private TextView txt_right;
    private TextView txt_middle;

    private View layout_left;
    private View layout_right;

    private View line;

    private CustomToolBarListener mListener;
    private CustomToolBarStyle barStyle;

    public static final int MISS = 0;
    public static final int IMG = 1;
    public static final int TXT = 2;

    public interface CustomToolBarListener {
        void navigationLeft();

        void navigationRight();
    }

    public void setCustomToolBarListener(CustomToolBarListener listener) {
        mListener = listener;
    }

    public CustomToolBar(Context context) {
        this(context, null);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        barStyle = new CustomToolBarStyle();
        //自定义的属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar, defStyleAttr, 0);
        barStyle.left_type = a.getInt(R.styleable.CustomToolBar_navigationLeft_type, IMG);//默认后退键为可见的
        barStyle.right_type = a.getInt(R.styleable.CustomToolBar_navigationRight_type, MISS);//默认不可见
        barStyle.right_imgId = a.getResourceId(R.styleable.CustomToolBar_navigationRight_img, R.color.colorAccent);
        barStyle.right_txt = a.getString(R.styleable.CustomToolBar_navigationRight_txt);
        barStyle.right_txt_color = a.getResourceId(R.styleable.CustomToolBar_navigationRight_txt_color, R.color.black);
        barStyle.title_txt_color = a.getResourceId(R.styleable.CustomToolBar_navigationTitle_txt_color, R.color.black);
        barStyle.left_imgId = a.getResourceId(R.styleable.CustomToolBar_navigationLeft_img, R.mipmap.ic_arrow_left);
        barStyle.strTitle = a.getString(R.styleable.CustomToolBar_navigationTitle);
        barStyle.backgroundColor = a.getResourceId(R.styleable.CustomToolBar_navigation_bg, R.color.white);
        boolean isShowLine = a.getBoolean(R.styleable.CustomToolBar_nashow_bottomline, true);
        a.recycle();
        showViewBarStyle(barStyle);
        showBottomLine(isShowLine);
    }

    public void showBottomLine(boolean isShowLine) {
        line.setVisibility(isShowLine ? VISIBLE : GONE);
    }

    public void setRightGone() {

        layout_right.setVisibility(GONE);
    }

    public void setRightVisible() {
        layout_right.setVisibility(VISIBLE);
    }

    public void showViewBarStyle(CustomToolBarStyle barStyle) {
        //left
        switch (barStyle.left_type) {
            case MISS:
                img_left.setVisibility(GONE);
                break;
            case IMG:
                img_left.setVisibility(VISIBLE);
                img_left.setImageResource(barStyle.left_imgId);
                break;
        }
        //right
        switch (barStyle.right_type) {
            case MISS:
                img_right.setVisibility(GONE);
                txt_right.setVisibility(GONE);
                break;
            case IMG:
                img_right.setVisibility(VISIBLE);
                txt_right.setVisibility(GONE);
                img_right.setImageResource(barStyle.right_imgId);
                break;
            case TXT:
                img_right.setVisibility(GONE);
                txt_right.setVisibility(VISIBLE);
                txt_right.setText(barStyle.right_txt);
                txt_right.setTextColor(getResources().getColor(barStyle.right_txt_color));
                break;
        }
        setNaviTitle(barStyle.strTitle);
        naviView.setBackgroundColor(getResources().getColor(barStyle.backgroundColor));
        txt_middle.setTextColor(getResources().getColor(barStyle.title_txt_color));
    }

    public void setNaviTransParent() {
        naviView.setBackgroundResource(android.R.color.transparent);
    }

    public void setLeftViewInvisible(int viewInvisible) {
        layout_left.setVisibility(viewInvisible);
    }

    public void setNaviTitle(String title) {
        txt_middle.setText(title);
    }

    public void setRightText(String rightText) {
        layout_right.setVisibility(VISIBLE);
        txt_right.setVisibility(View.VISIBLE);
        img_right.setVisibility(GONE);
        txt_right.setText(rightText);
    }

    public void setRightTextColor(int color) {
        txt_right.setTextColor(color);
    }

    public void setRightTextVisible(int viewInvisible) {
        layout_right.setVisibility(viewInvisible);
        txt_right.setVisibility(viewInvisible);
    }

    public void setBackgroundAlpha(float alpha) {
        naviView.setAlpha(alpha);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        naviView = inflater.inflate(R.layout.custom_toolbar, this);
        layout_left = naviView.findViewById(R.id.topview_left_layout);
        layout_right = naviView.findViewById(R.id.topview_right_layout);

        img_left = (ImageView) naviView.findViewById(R.id.top_view_back);
        img_right = (ImageView) naviView.findViewById(R.id.top_view_right);

        txt_middle = (TextView) naviView.findViewById(R.id.navigationbar_title);
        txt_right = (TextView) naviView.findViewById(R.id.delete_text);

        line = naviView.findViewById(R.id.line);
        //
        layout_left.setOnClickListener(this);
        layout_right.setOnClickListener(this);
        //沉浸式
//        setFitsSystemWindows(true);
//        setClipToPadding(true);

    }

    @Override
    public void onClick(View v) {
        if (mListener == null) {
            return;
        }
        int i = v.getId();
        if (i == R.id.topview_left_layout) {
            mListener.navigationLeft();

        } else if (i == R.id.topview_right_layout) {
            mListener.navigationRight();

        }
    }

    public class CustomToolBarStyle {
        public int left_type;//导航条左边的类型：0，不可见，1：图片，
        public int right_type;//导航条右边的类型：0：不可见，1：图片，2文字
        public String strTitle = ""; //中间标题
        public int right_imgId;
        public String right_txt = "";
        public int right_txt_color;
        public int title_txt_color;
        public int left_imgId;
        public int backgroundColor;
    }
}
