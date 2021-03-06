package com.todayin.nlight.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.base.library.activity.BaseActivity;
import com.todayin.nlight.R;
import com.todayin.nlight.utils.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_eye_close)
    CheckBox ivEyeClose;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        ivEyeClose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                etPassword.setTransformationMethod(isChecked ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.btn_forgot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                JumpUtil.jumpToRegister(this);
                break;
            case R.id.btn_register:
                JumpUtil.jumpToRegister(this);
                break;
            case R.id.btn_forgot:
                break;
        }
    }
}
