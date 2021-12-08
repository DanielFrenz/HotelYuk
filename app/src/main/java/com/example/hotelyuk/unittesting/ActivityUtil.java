package com.example.hotelyuk.unittesting;

import android.content.Context;
import android.content.Intent;

import com.example.hotelyuk.ui.auth.RegisterActivity;

public class ActivityUtil {
    private Context context;

    public ActivityUtil(Context context) {
        this.context = context;
    }

    public void startMainRegister() {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}
