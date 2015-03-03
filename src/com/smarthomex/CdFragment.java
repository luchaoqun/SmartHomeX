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
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.cd, container, false);
		textCd = (TextView) view.findViewById(R.id.id_card_cd);
		Log.e("", "成功获取控件");
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		WebTask webTask = new WebTask(this);
		webTask.execute("2");
	}

	@Override
	public void updateUI(String result) {
		textCd.setText(result+"lx");
		Log.e("", "成功更新数据");
		
	}
}
