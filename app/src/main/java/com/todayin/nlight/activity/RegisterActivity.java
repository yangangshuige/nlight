package com.todayin.nlight.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.base.library.activity.BaseActivity;
import com.base.library.widget.CustomToolBar;
import com.todayin.nlight.R;
import com.todayin.nlight.utils.CheckInputUtils;
import com.todayin.nlight.utils.TimeCountDownUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity{


    @BindView(R.id.tool_bar)
    CustomToolBar toolBar;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_get_code)
    TextView btnGetCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_password)
    CheckBox cbPassword;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;
    @BindView(R.id.cb_password_again)
    CheckBox cbPasswordAgain;
    TimeCountDownUtils timeCountDownUtils;
    @Override
    public int getContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        toolBar.setCustomToolBarListener(this);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etPasswordAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
        cbPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                etPassword.setTransformationMethod(isChecked? HideReturnsTransformationMethod.getInstance():PasswordTransformationMethod.getInstance());
            }
        });
        cbPasswordAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                etPasswordAgain.setTransformationMethod(isChecked? HideReturnsTransformationMethod.getInstance():PasswordTransformationMethod.getInstance());
            }
        });
        timeCountDownUtils=new TimeCountDownUtils(60*1000,1000,btnGetCode);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }


    @OnClick({R.id.btn_get_code, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                String mobile=etTel.getText().toString();
                if(CheckInputUtils.isLegalMobile(mobile)){
                    timeCountDownUtils.start();
                }else {
                    showToast(CheckInputUtils.errorMsg);
                }
                break;
            case R.id.btn_register:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeCountDownUtils.stopTick();
    }

}
