package com.smarthomex;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments;
	
	private LinearLayout mTabTemp;
	private LinearLayout mTabCd;
	private LinearLayout mTabHum;
	private LinearLayout mTabDew;

	private ImageButton mImgTemp;
	private ImageButton mImgCd;
	private ImageButton mImgHum;
	private ImageButton mImgDew;
	private ImageButton mImgFresh;
	
	private TextView tvtemp;
	private TextView tvcd;
	private TextView tvhum;
	private TextView tvdew;

	public TextView tvTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initView();
		initEvent();
		
		setSelect(0);
	}

	private void initEvent() {
		mTabTemp.setOnClickListener(this);
		mTabCd.setOnClickListener(this);
		mTabHum.setOnClickListener(this);
		mTabDew.setOnClickListener(this);
		//Ë¢ÐÂ
		mImgFresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				WebTask webTask = new WebTask();
//				webTask.execute("r");
			}
		});
		
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		
		mTabTemp = (LinearLayout) findViewById(R.id.id_tab_temp);
		mTabCd = (LinearLayout) findViewById(R.id.id_tab_cd);
		mTabHum = (LinearLayout) findViewById(R.id.id_tab_hum);
		mTabDew = (LinearLayout) findViewById(R.id.id_tab_dew);

		mImgTemp = (ImageButton) findViewById(R.id.id_tab_temp_img);
		mImgCd = (ImageButton) findViewById(R.id.id_tab_cd_img);
		mImgHum = (ImageButton) findViewById(R.id.id_tab_hum_img);
		mImgDew = (ImageButton) findViewById(R.id.id_tab_dew_img);
		
		mImgFresh = (ImageButton) findViewById(R.id.id_fresh_button);
		
		tvtemp = (TextView) findViewById(R.id.id_text_temp);
		tvcd = (TextView) findViewById(R.id.id_text_cd);
		tvhum = (TextView) findViewById(R.id.id_text_hum);
		tvdew = (TextView) findViewById(R.id.id_text_dew);
		
		tvTitle = (TextView) findViewById(R.id.id_title_textview);

		mFragments = new ArrayList<Fragment>();
		Fragment TempPager = new TempFragment();
		Fragment CdPager = new CdFragment();
		Fragment HumPager = new HumFragment();
		Fragment DewPager = new DewFragment();
		mFragments.add(TempPager);
		mFragments.add(CdPager);
		mFragments.add(HumPager);
		mFragments.add(DewPager);
		
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{

			@Override
			public int getCount()
			{
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mFragments.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			
			@Override
			public void onPageSelected(int arg0)
			{
				int currentItem = mViewPager.getCurrentItem();
				setTab(currentItem);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
		case R.id.id_tab_temp:
			setSelect(0);
			break;
		case R.id.id_tab_cd:
			setSelect(1);
			break;
		case R.id.id_tab_hum:
			setSelect(2);
			break;
		case R.id.id_tab_dew:
			setSelect(3);
			break;
		default:
			break;
		
	}

}

	private void setSelect(int i)
	{
		setTab(i);
		mViewPager.setCurrentItem(i);
	}

	private void setTab(int i)
	{
		resetImgs();
		// ÉèÖÃÍ¼Æ¬ÎªÁÁÉ«
		// ÇÐ»»ÄÚÈÝÇøÓò
		switch (i)
		{
		case 0:
			mImgTemp.setImageResource(R.drawable.temp_press);
			tvtemp.setTextColor(0xffe06b2f);
			break;
		case 1:
			mImgCd.setImageResource(R.drawable.cd_press);
			tvcd.setTextColor(0xffe06b2f);
			break;
		case 2:
			mImgHum.setImageResource(R.drawable.hum_press);
			tvhum.setTextColor(0xffe06b2f);
			break;
		case 3:
			mImgDew.setImageResource(R.drawable.dew_press);
			tvdew.setTextColor(0xffe06b2f);
			break;
		}
	}

	/**
	 * ÇÐ»»Í¼Æ¬ÖÁ°µÉ«
	 */
	private void resetImgs()
	{
		tvtemp.setTextColor(0xff54565b);
		tvcd.setTextColor(0xff54565b);
		tvhum.setTextColor(0xff54565b);
		tvdew.setTextColor(0xff54565b);

		
		mImgTemp.setImageResource(R.drawable.temp_normal);
		mImgCd.setImageResource(R.drawable.cd_normal);
		mImgHum.setImageResource(R.drawable.hum_normal);
		mImgDew.setImageResource(R.drawable.dew_normal);
	}

}
