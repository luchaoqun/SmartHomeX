package com.smarthomex;

import com.smarthomex.WebTask.NetWorkInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HumFragment extends Fragment implements NetWorkInterface{
	public TextView textHum;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.hum, container, false);
		textHum = (TextView) view.findViewById(R.id.id_card_hum);
		Log.e("", "成功获取控件");
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		WebTask webTask = new WebTask(this);
		webTask.execute("3");
	}

	@Override
	public void updateUI(String result) {
		textHum.setText(result+"  ");
		Log.e("", "成功更新数据");
		
	}
}
