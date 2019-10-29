package com.shell.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

//import com.bdt.app.bdt_common.utils.ShowLoding;
//import com.bdt.app.bdt_common.view.LoadingDialog;


/**
 * description:
 * class: CommonFragment
 * author: linqiang
 * date:2016/9/1 18:58
 */
public class CommonFragment extends Fragment {
	//private Dialog mLoadingDialog;
	WindowManager windowManager;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

//	/**
//	 * 显示网络请求的过场动画 参数:true 显示 false 不显示
//	 */
//	public void showLoading(boolean show) {
//		initDialog(show, 0, "", false);
//	}
//
//	/**
//	 * 显示网络请求的过场动画 参数:true 显示 false 不显示
//	 */
//	public void showLoading(boolean show, boolean isShowBack) {
//		initDialog(show, 0, "", isShowBack);
//
//	}
//	/**
//	 * 显示网络请求的过场动画 参数:true 显示 false 不显示
//	 */
//	public void showCustomLoading(boolean show,String content) {
//		initDialog(show, 1, content, false);
//
//	}
//	/**
//	 * 显示网络请求的过场动画 参数:true 显示 false 不显示
//	 */
//	public void showCustomLoading(boolean show, String content, boolean isShowBack) {
//		initDialog(show, 1, content, isShowBack);
//
//	}

//	private void initDialog(boolean show, int type, String msg, boolean isShowBack) {
//		try {
//			if (show) {
//				// 由于这个dialog可能是由不同的activity唤起，所以每次都新建
//				if (mLoadingDialog == null) {
//					windowManager = getActivity().getWindowManager();
//					mLoadingDialog = ShowLoding.ShowLodingDialog(getActivity(), 0, "", getActivity(), windowManager, isShowBack);
//					new LoadingDialog(getActivity(),
//							R.style.lodingDialog);
//				}
//				try {
//					mLoadingDialog.show();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else {
//				if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
//					mLoadingDialog.cancel();
//					mLoadingDialog = null;
//				}
//			}
//		} catch (Exception e) {
//
//		}
//	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
