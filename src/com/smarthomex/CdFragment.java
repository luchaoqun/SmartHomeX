package com.smarthomex;

import com.smarthomex.WebTask.NetWorkInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CdFragment extends Fragment implements NetWorkInterface{
	public TextView textCd;
	public TextView timeCd;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.cd, container, false);
		textCd = (TextView) view.findViewById(R.id.id_card_cd);
		timeCd = (TextView) view.findViewById(R.id.id_card_time_cd);
		Log.e("", "成功获取Cd控件");
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		WebTask webTask = new WebTask(this,view);
		webTask.execute("light");
	}

	@Override
	public void updateUI(String result) {
		textCd.setText(result+"lx");
		GetTime getTime = new GetTime();
		String tString = getTime.GetNowTime();
		timeCd.setText(tString);
		Log.e("", "成功更新数据");
		
	}
}
