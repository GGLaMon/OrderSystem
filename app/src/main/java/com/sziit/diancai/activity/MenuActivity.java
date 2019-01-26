package com.sziit.diancai.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.sziit.diancai.R;

public class MenuActivity extends Activity {
	private Bundle bundle;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		// 生成一个SpinnerAdapter
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(
				getActionBarThemedContextCompat(), R.array.language,
				android.R.layout.simple_spinner_dropdown_item);
		// 得到ActionBar
		ActionBar actionBar = getActionBar();
		// 不显示标题
		actionBar.setDisplayShowTitleEnabled(false);
		// 将ActionBar的操作模型设置为NAVIGATION_MODE_LIST
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// 为ActionBar设置下拉菜单和监听器
		actionBar.setListNavigationCallbacks(adapter, new DropDownListenser());
		
		bundle = this.getIntent().getExtras(); 
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	/**
	 * 实现 ActionBar.OnNavigationListener接口
	 */
	class DropDownListenser implements OnNavigationListener {
		// 得到和SpinnerAdapter里一致的字符数组
		String[] listNames = getResources().getStringArray(R.array.language);

		/* 当选择下拉菜单项的时候，将Activity中的内容置换为对应的Fragment */
		@Override
		public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			// 生成自定的Fragment
			MenuFragment menuFragment = new MenuFragment();
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			menuFragment.setArguments(bundle);
			// 将Activity中的内容替换成对应选择的Fragment
			transaction.replace(R.id.container, menuFragment,
					listNames[itemPosition]);
			transaction.commit();
			return true;
		}
	}
}