package com.chenyzh.alarmmanagercontroller;

import java.util.List;

import com.chenyzh.alarmmanagercontroller.controller.NotifyManager;
import com.chenyzh.alarmmanagercontroller.controller.NotifyManager.NotifyEvent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends Activity{

	private EditText settingDelay;
	private Button commitBtn;

	
	public static void start(Context from){
		Intent intent = new Intent();
		intent.setClass(from, SettingActivity.class);
		from.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		settingDelay = (EditText)findViewById(R.id.setting_delay);
		commitBtn = (Button)findViewById(R.id.commit);
		settingDelay.setText(NotifyManager.getInstance().getDelay()/1000+"");
		commitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String delay = settingDelay.getText().toString();
				if (TextUtils.isEmpty(delay)) {
					Toast.makeText(SettingActivity.this, "请输入设置的延时时间", Toast.LENGTH_LONG).show();
					return;
				}
				NotifyManager.getInstance().setDelay(Long.parseLong(delay)*1000);
				finish();
						
			}
		});
		
	}
}
