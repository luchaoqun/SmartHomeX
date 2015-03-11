package com.smarthomex;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class WebTask extends AsyncTask<String, Void, String> {

	private Socket socket;
	private InetAddress serverIP;
	private final int PORT = 7777;
	private int isconnected = 0;
	//���������
	private OutputStream os;
	private OutputStreamWriter osw;
	private PrintWriter out;
	private InputStream is;
	
	public String fmsg;
	public String tmpString;

	private View Webview;
	private NetWorkInterface WebComponents;
	
	class HomeDate{
		public String tempmsg;
		public String cdmsg;
		public String hummsg;
		public String dewmsg;		
	}

	public interface NetWorkInterface {
		void updateUI(String result);
	}

	public WebTask(NetWorkInterface Components,View view) {
		WebComponents = Components;
		Webview = view;
	}

	@Override
	protected String doInBackground(String... params) {
		//���������ʶ
		fmsg = params[0];
		try {
			serverIP = InetAddress.getByName("lulucn.vicp.cc");
			//if(socket.isConnected()&&!socket.isClosed()){
			if(isconnected == 1){
				Log.e("����״̬","socket�ѽ�������");
			}
			else{
				socket = new Socket("111.227.218.205", PORT);
				System.err.println("���ӳɹ�");
				//��ȡ�����
				os = socket.getOutputStream();
				osw = new OutputStreamWriter(os);
				out = new PrintWriter(osw, true);
				//��ȡ������
				is = socket.getInputStream();
				isconnected = 1;
			}

			// send
			out.println("r");
			Log.e("���ͳɹ�","r");
			
			// receive
			byte[] buffer = new byte[1024];
			int length = is.read(buffer);
			String str = new String(buffer, 0, length);
			Log.e("���ճɹ�","�յ��ַ���:"+str);

//			os.close();
//			is.close();
//			socket.close();
//			System.err.println("���ӹر�");
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		Toast.makeText(Webview.getContext(), "����ˢ�µ�"+fmsg+"ҳ���ݣ����Ժ�", Toast.LENGTH_SHORT).show();
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			JSONObject jsonmsg = new JSONObject(result);
			JSONObject jsondata = jsonmsg.getJSONObject("d");
			System.err.println(jsondata);
//			int temp = jsondata.getInt("1");
//			int cd = jsondata.getInt("0");
//			int hum = jsondata.getInt("2");
//			double dew = jsondata.getDouble("3");
			double data = jsondata.getDouble(fmsg);
//			tempmsg = String.valueOf(temp);
//			cdmsg = String.valueOf(cd);
//			hummsg = String.valueOf(hum);
//			dewmsg = String.valueOf(dew);
			tmpString = String.valueOf(data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		WebComponents.updateUI(tmpString);
//		switch (fmsg) {
//		case 1:
//			WebComponents.updateUI(tempmsg);
//			break;
//		case 2:
//			WebComponents.updateUI(cdmsg);
//			break;
//		case 3:
//			WebComponents.updateUI(hummsg);
//			break;
//		case 4:
//			WebComponents.updateUI(dewmsg);
//			break;
//
//		default:
//			break;
//		}
		Toast.makeText(Webview.getContext(), "ˢ�³ɹ�", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO �Զ����ɵķ������
		super.onProgressUpdate(values);
	}

	@Override
	protected void onCancelled(String result) {
		// TODO �Զ����ɵķ������
		super.onCancelled(result);
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

}
