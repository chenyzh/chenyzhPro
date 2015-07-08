package com.chenyzh.alarmmanagercontroller.receiver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.chenyzh.alarmmanagercontroller.controller.NotifyManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class TimeOutReceiver extends BroadcastReceiver {
	
	 private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "tongzhi "+System.currentTimeMillis(), Toast.LENGTH_LONG).show();
		String date = DATE_FORMAT.format(new Date(System.currentTimeMillis()));
		NotifyManager.getInstance().notifyUI("通知Date = "+date);
		long delay = NotifyManager.getInstance().getDelay();
		NotifyManager.getInstance().loopManage(context,delay);
	}

}
