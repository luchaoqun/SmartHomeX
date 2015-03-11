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
	public TextView timeDew;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.dew, container, false);
		textDew = (TextView) view.findViewById(R.id.id_card_dew);
		timeDew = (TextView) view.findViewById(R.id.id_card_time_dew);
		Log.e("", "成功获取Dew控件");
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		WebTask webTask = new WebTask(this,view);
		webTask.execute("dewpoint");
	}

	@Override
	public void updateUI(String result) {
		textDew.setText(result+"  ");
		GetTime getTime = new GetTime();
		String tString = getTime.GetNowTime();
		timeDew.setText(tString);
		Log.e("", "成功更新数据");
		
	}
}
