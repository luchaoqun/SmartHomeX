package com.smarthomex;

import com.smarthomex.WebTask.NetWorkInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TempFragment extends Fragment implements NetWorkInterface{

	public TextView textTemp;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.temp, container, false);
		textTemp = (TextView) view.findViewById(R.id.id_card_temp);
		Log.e("", "成功获取控件");
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		WebTask webTask = new WebTask(this);
		webTask.execute("1");
	}

	@Override
	public void updateUI(String result) {
		textTemp.setText(result+"℃");
		Log.e("", "成功更新数据");
		
	}


}
