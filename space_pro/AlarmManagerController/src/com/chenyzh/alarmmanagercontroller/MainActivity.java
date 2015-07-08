package com.chenyzh.alarmmanagercontroller;

import java.util.ArrayList;
import java.util.List;

import com.chenyzh.alarmmanagercontroller.controller.NotifyManager;
import com.chenyzh.alarmmanagercontroller.controller.NotifyManager.NotifyEvent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements NotifyEvent {

	private ArrayAdapter<String> adapter;
	private Button settingBtn;
	private ListView showData;
	private List<String> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data = new ArrayList<String>();
		settingBtn = (Button) findViewById(R.id.settingBtn);
		settingBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SettingActivity.start(MainActivity.this);
			}
		});
		showData = (ListView) findViewById(R.id.showData);
		NotifyManager.getInstance().registerEvent(this);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		showData.setAdapter(adapter);
		NotifyManager.getInstance().setDelay(10 * 1000);
		NotifyManager.getInstance().loopManage(this,
				NotifyManager.getInstance().getDelay());
	}

	@Override
	public void onEvent(Object obj) {
		Log.d("MainActivity", "obj = "+obj.toString());
		adapter.add(obj.toString());
	}

}
