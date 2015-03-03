package com.smarthomex;

import com.smarthomex.WebTask.NetWorkInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DewFragment extends Fragment implements NetWorkInterface{
	public TextView textDew;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.dew, container, false);
		textDew = (TextView) view.findViewById(R.id.id_card_dew);
		Log.e("", "成功获取控件");
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		WebTask webTask = new WebTask(this);
		webTask.execute("4");
	}

	@Override
	public void updateUI(String result) {
		textDew.setText(result+"  ");
		Log.e("", "成功更新数据");
		
	}
}
