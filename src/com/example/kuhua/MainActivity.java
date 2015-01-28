package com.example.kuhua;

import com.example.kuhua.fragment.ByHistoryFragment;
import com.example.kuhua.fragment.KuHuaShopFragment;
import com.example.kuhua.fragment.MainFragment;
import com.example.kuhua.fragment.MakeMoneyFragment;
import com.example.kuhua.fragment.SettingFragment;

import android.R.anim;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;

public class MainActivity extends FragmentActivity {

	private LayoutInflater mInflater;
	private RadioGroup scv_RadioGroup;
	// private String[] tabTitle = { "��ҳ", "׬Ǯ�ײ�", "�̳�", "��ʷ", "����" };
	private String[] tabTitle = { "��ҳ", "�̳�", "��ʷ", "����" };
	private int viewidth;// ��ʾ�����
	private ImageView view_index;// ѡ��±�
	private ViewPager mViewPager;// �����±�
	private MyFragmentPagerAdapter mAdapter;// viewpager��������
	private int currentIndicatorLeft = 0;// view_index���������λ������
	private ScrollView myscv_case;
	// private int[] tabDrawable = { R.drawable.radio_item_home,
	// R.drawable.radio_item_makemoney, R.drawable.radio_item_market,
	// R.drawable.radio_item_buyhistory, R.drawable.radio_item_setting };
	private int[] tabDrawable = { R.drawable.radio_item_home,
			R.drawable.radio_item_market, R.drawable.radio_item_buyhistory,
			R.drawable.radio_item_setting };

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		this.setContentView(R.layout.main);
		this.findId();
		this.inintData();
		this.initScrolView();
	}

	private void findId() {
		this.scv_RadioGroup = (RadioGroup) findViewById(R.id.scview_radiogroup);
		this.view_index = (ImageView) findViewById(R.id.scview_item_line);
		this.mViewPager = (ViewPager) findViewById(R.id.viewpaper);
		this.myscv_case = (ScrollView) findViewById(R.id.scrollview);
	}

	private void inintData() {
		// ��ʼ���������򻬶�ģ��Ŀ��Ϊ��Ļ������tab��Ŀ
		mInflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		this.viewidth = dm.widthPixels / (this.tabTitle.length);
		// ��ʼ�������±�
		LayoutParams view_index_params = this.view_index.getLayoutParams();
		view_index_params.width = this.viewidth;
		this.view_index.setLayoutParams(view_index_params);
		// ��ʼ��viewpaper
		this.mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		this.mViewPager.setAdapter(mAdapter);
		// ��viewpage��scrollView���м���
		this.setScrollViewPageListener();
	}

	private void initScrolView() {
		this.scv_RadioGroup.removeAllViews();
		for (int i = 0; i < this.tabTitle.length; i++) {
			RadioButton Rb = (RadioButton) this.mInflater.inflate(
					R.layout.radiogroup_item, null);
			Rb.setId(i);
			Rb.setPadding(8, 8, 8, 8);
			Rb.setTextSize(14);
			Rb.setText(this.tabTitle[i]);
			Rb.setCompoundDrawablesWithIntrinsicBounds(0, this.tabDrawable[i],
					0, 0);
			Rb.setLayoutParams(new LayoutParams(this.viewidth,
					LayoutParams.MATCH_PARENT));
			if (i != 0) {
				Rb.setChecked(false);
			}
			this.scv_RadioGroup.addView(Rb);
		}
	}

	private void setScrollViewPageListener() {
		this.mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				if (MainActivity.this.scv_RadioGroup != null
						&& scv_RadioGroup.getChildCount() > position) {
					scv_RadioGroup.getChildAt(position).performClick();
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		this.scv_RadioGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						if (MainActivity.this.scv_RadioGroup
								.getChildAt(checkedId) != null) {
							TranslateAnimation animation = new TranslateAnimation(
									MainActivity.this.currentIndicatorLeft,
									MainActivity.this.scv_RadioGroup
											.getChildAt(checkedId).getLeft(),
									0f, 0f);
							animation.setInterpolator(new LinearInterpolator());
							animation.setDuration(80);
							animation.setFillAfter(true);
							// ִ�ж���
							MainActivity.this.view_index
									.startAnimation(animation);
							// viewpagerͬʱ�л�
							MainActivity.this.mViewPager
									.setCurrentItem(checkedId);
							// �������view_index�Ķ�������
							MainActivity.this.currentIndicatorLeft = MainActivity.this.scv_RadioGroup
									.getChildAt(checkedId).getLeft();
							// ��������֮����ͼ���л�
							MainActivity.this.myscv_case
									.smoothScrollTo(
											(checkedId > 1 ? MainActivity.this.scv_RadioGroup
													.getChildAt(checkedId)
													.getLeft() : 0)
													- MainActivity.this.scv_RadioGroup
															.getChildAt(2)
															.getLeft(), 0);
						}
					}
				});
	}

	class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment ft = null;
			switch (arg0) {
			case 0:
				ft = new MainFragment();
				break;
			// case 1:
			// ft = new MakeMoneyList();
			// break;
			case 1:
				ft = new KuHuaShopFragment();
				break;
			case 2:
				ft = new ByHistoryFragment();
				break;
			case 3:
				ft = new SettingFragment();
				break;
			default:
				break;
			}
			return ft;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return MainActivity.this.tabTitle.length;
		}
	}

}
