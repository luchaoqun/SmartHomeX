package com.smarthomex;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class WebTask extends AsyncTask<String, Void, String> {

	private Socket socket;
	private final String IP = "183.190.207.95";
	private final int PORT = 7777;
	public int msgNum;
	public String tempmsg;
	public String cdmsg;
	public String hummsg;
	public String dewmsg;
	private NetWorkInterface WebComponents;

	public interface NetWorkInterface {
		void updateUI(String result);
	}

	public WebTask(NetWorkInterface Components) {
		WebComponents = Components;
	}

	@Override
	protected String doInBackground(String... params) {
		msgNum = Integer.parseInt(params[0]);
		try {
			socket = new Socket(IP, PORT);
			System.err.println("连接成功");

			// send
			OutputStream os = socket.getOutputStream();
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(os)), true);
			out.println("r");
			System.err.println("发送成功");
			// receive
			// InputStream is = socket.getInputStream();
			// InputStreamReader isr = new InputStreamReader(is);
			// BufferedReader br = new BufferedReader(isr);
			// String line;
			// StringBuilder builder = new StringBuilder();
			// while((line=br.readLine())!=null){
			// builder.append(line);
			// }

			InputStream is = socket.getInputStream();
			byte[] buffer = new byte[1024];
			int length = is.read(buffer);
			String str = new String(buffer, 0, length);
			System.err.println("接收成功");

			System.err.println(str);

			os.close();
			is.close();
			socket.close();
			System.err.println("连接关闭");
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		// Toast.makeText(, "正在刷新", Toast.LENGTH_SHORT).show();
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			JSONObject jsonmsg = new JSONObject(result);
			System.err.println(jsonmsg);
			JSONObject jsondata = jsonmsg.getJSONObject("d");
			System.err.println(jsondata);
			int temp = jsondata.getInt("1");
			int cd = jsondata.getInt("0");
			int hum = jsondata.getInt("2");
			double dew = jsondata.getDouble("3");
			tempmsg = String.valueOf(temp);
			cdmsg = String.valueOf(cd);
			hummsg = String.valueOf(hum);
			dewmsg = String.valueOf(dew);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		switch (msgNum) {
		case 1:
			WebComponents.updateUI(tempmsg);
			break;
		case 2:
			WebComponents.updateUI(cdmsg);
			break;
		case 3:
			WebComponents.updateUI(hummsg);
			break;
		case 4:
			WebComponents.updateUI(dewmsg);
			break;

		default:
			break;
		}

	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO 自动生成的方法存根
		super.onProgressUpdate(values);
	}

	@Override
	protected void onCancelled(String result) {
		// TODO 自动生成的方法存根
		super.onCancelled(result);
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

}
