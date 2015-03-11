package com.smarthomex;

import android.text.format.Time;
import android.util.Log;

public class GetTime {
	public Time t;
	public String tString;
	
	public GetTime(){
		Time t=new Time();
		t.setToNow();
		int hour = t.hour; // 0-23  
		int minute = t.minute;
		tString = hour+":"+((minute<10)?("0"+minute):minute);
		Log.e("tString", tString);
	}
	
	public String GetNowTime(){
		return tString;
		
	}
}
