package com.todayin.nlight.utils;

import android.content.Context;
import android.content.Intent;

import com.todayin.nlight.activity.RegisterActivity;

/**
 * Created by yg on 2019/1/15.
 */

public class JumpUtil {
    public static void jumpToRegister(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}
