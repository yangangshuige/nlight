package com.todayin.nlight.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by yg on 2019/1/15.
 */
public class TimeCountDownUtils extends CountDownTimer {

    private TextView txt_show;
    private String originalStr;
    public boolean isCounting;

    /**
     * @param millisInFuture:倒计时的总数；（毫秒为单位）
     * @param countDownInterval             :倒计时时的步宽（毫秒为单位）
     */
    public TimeCountDownUtils(long millisInFuture, long countDownInterval, TextView txt_show) {
        super(millisInFuture, countDownInterval);
        this.txt_show = txt_show;
        this.originalStr = txt_show.getText().toString();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        isCounting = true;
        txt_show.setEnabled(false);
        txt_show.setText("重新获取(" + millisUntilFinished / 1000 + "s)");
    }

    @Override
    public void onFinish() {
        isCounting = false;
        txt_show.setText(originalStr);
        txt_show.setEnabled(true);
    }

    public void stopTick() {
        cancel();
        isCounting = false;
        txt_show.setText(originalStr);
        txt_show.setEnabled(true);
    }


}
