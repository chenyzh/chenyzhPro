package com.chenyzh.alarmmanagercontroller.controller;

import com.chenyzh.alarmmanagercontroller.receiver.TimeOutReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class NotifyManager {
	
	
	private static NotifyManager instance;
	private NotifyManager() {
	}
	public static NotifyManager getInstance() {
		if(instance == null){
			instance = new NotifyManager();
		}
		return instance;
	}
	
	private NotifyEvent event;
	private AlarmManager mAlarManager;
	private PendingIntent mAlarmIntent;
	
	private long delay;
	
	public void registerEvent(NotifyEvent event) {
		this.event = event;
	}
	
	
	public void notifyUI(Object obj){
		event.onEvent(obj);
	}
	
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	public long getDelay() {
		return delay;
	}
	
	public void loopManage(Context context,long delay) {
		if(mAlarManager == null){
			mAlarManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		}
		Intent intent = new Intent();
		intent.setClass(context, TimeOutReceiver.class);
		mAlarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		mAlarManager.cancel(mAlarmIntent);
		mAlarManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay+SystemClock.elapsedRealtime(), mAlarmIntent);
	}
	
	
	
	public interface NotifyEvent{
		void onEvent(Object obj);
	}
}
