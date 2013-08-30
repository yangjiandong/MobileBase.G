package com.ek.mobileapp.activity;

import com.ek.mobilebapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PassWordActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.password_layout);

		Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
		leftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		TextView titleTv = (TextView) findViewById(R.id.custom_title_label);
		titleTv.setText("修改密码");
	}
}
