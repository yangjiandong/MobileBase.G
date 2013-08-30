package com.ek.mobileapp.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ek.mobilebapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UpdateLogActivity extends Activity {
	final static String TAG = "UpdateLogActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.update_log_layout);

		Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
		leftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		TextView titleTv = (TextView) findViewById(R.id.custom_title_label);
		titleTv.setText("更新历史");

		 TextView contextTv = (TextView) findViewById(R.id.changetxt);
		 contextTv.setText(readChangelog());
	}

	private CharSequence readChangelog() {
		StringBuffer sb = new StringBuffer();
		InputStream is = this.getResources().openRawResource(
				R.raw.changelogs);

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			String line = br.readLine();
			while (null != line) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
		} catch (IOException e) {
			Log.e(TAG, "", e);

		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.e(TAG, "", e);
			}
		}
		return sb.toString();
	}
}
