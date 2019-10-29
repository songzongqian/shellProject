package com.shell.home;


import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.shell.home.Bean.JiangLiBean;
import com.shell.home.adapter.HomeAwardAdapter;


import java.util.List;
import java.util.TimerTask;

public class TimeTaskScroll extends TimerTask {
	
	private ListView listView;
	public TimeTaskScroll(FragmentActivity activity, ListView listView, List<JiangLiBean.ResultDataBean> jiangLiList) {
		this.listView = listView;
		listView.setAdapter(new HomeAwardAdapter(activity, jiangLiList));
	}


	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			listView.smoothScrollBy(1, 0);
		};
	};


	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		handler.sendMessage(msg);
	}

}
