package com.todayin.nlight.utils;

import android.text.TextUtils;

/**
 * Created by yg on 15/6/1.
 */
public class CheckInputUtils {

    public  static String errorMsg ="";

    public static boolean isLegalMobile(String moblie){
        if(TextUtils.isEmpty(moblie)){
            errorMsg = "手机号码不能为空";
            return false;
        }

        if(moblie.length() != 11 )
        {
            errorMsg = "手机号码不合法";
            return false;
        }
        return true;
    }

    public static boolean isLegalVerify(String verifycode){
        if(verifycode.equals("") || verifycode == null){
            errorMsg = "验证码不能为空";
            return false;
        }
        return true;
    }

    public static boolean isLegalPwd(String pwd, String aginpwd){
        if(pwd.equals("") || pwd == null){
            errorMsg = "密码不能为空";
            return false;
        }
        if(pwd.length() <6)
        {
            errorMsg = "密码不能少于6位";
            return false;
        }
//        if (!pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$")) {
//            errorMsg = "密码必须是数字和字母组合";
//            return false;
//        }
        if(!aginpwd.equals(pwd))
        {
            errorMsg = "密码不一致";
            return false;
        }
        return true;
    }
    public static boolean isLegalPwd(String pwd){
        if(pwd.equals("") || pwd == null){
            errorMsg = "密码不能为空";
            return false;
        }
        if(pwd.length() <6)
        {
            errorMsg = "密码不能少于6位";
            return false;
        }
//        if (!pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$")) {
//            errorMsg = "密码必须是数字和字母组合";
//            return false;
//        }
        return true;
    }
    public static boolean isLegaloldPwd(String pwd){
        if(pwd.equals("") || pwd == null){
            errorMsg = "新密码不能为空";
            return false;
        }
        if(pwd.length() <6)
        {
            errorMsg = "新密码格式错误";
            return false;
        }
        return true;
    }
}
