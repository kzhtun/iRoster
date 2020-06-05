package com.info121.iroster.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.info121.iroster.App;
import com.info121.iroster.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.internal.Util;
import utils.Utils;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.date)
    TextView mDate;

    @BindView(R.id.time)
    TextView mTime;

    @BindView(R.id.ver)
    TextView mUiVersion;

    @BindView(R.id.user_name)
    EditText mUserName;


    Context mContext = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        String dateString = Utils.convertDateToString(Calendar.getInstance().getTime(), "EEE dd MMM yyyy");
        String timeString = Utils.convertDateToString(Calendar.getInstance().getTime(), "hh:mm a");

        mDate.setText(dateString);
        mTime.setText(timeString);

        final Handler timer = new Handler(getMainLooper());
        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                showTime();
                timer.postDelayed(this, 60000);
            }
        }, 60000);

        mUiVersion.setText("Ver " + Utils.getVersionName(mContext));
    }

    private void showTime() {
        String dateString = Utils.convertDateToString(Calendar.getInstance().getTime(), "hh:mm a");
        mTime.setText(dateString.toString());
    }



    @OnClick(R.id.forgot_password)
    public void forgotPasswordOnClick(){
        startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
    }


    @OnClick(R.id.login)
    public void loginOnClick(){
        App.userName = mUserName.getText().toString();
        App.lastLogin = Utils.convertDateToString(Calendar.getInstance().getTime(), "EEE, dd MMM yyyy, hh:mm a");
        MainActivity.setLoginInfo();

        finish();
    }


}
