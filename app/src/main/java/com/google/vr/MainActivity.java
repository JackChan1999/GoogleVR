package com.google.vr;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.vr.adapter.MyPagerAdapter;
import com.google.vr.fragment.VrPanoFragmment;
import com.google.vr.fragment.VrVideoFragment;

import java.util.ArrayList;
import java.util.List;
/**
 * ============================================================
 * Copyright：${TODO}有限公司版权所有 (c) 2017
 * Author：   AllenIverson
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * GitBook：  https://www.gitbook.com/@alleniverson
 * <p>
 * Project_Name：GoogleVR
 * Package_Name：com.google.vr
 * Version：1.0
 * time：2016/3/10 0:49
 * des ：${TODO}
 * gitVersion：2.12.0.windows.1
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 */
public class MainActivity extends AppCompatActivity {

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle mToggle;
	private ViewPager vp;
	private TabLayout tabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		initView();
		initActionBar();
		initViewPager();
	}

	private void initViewPager() {
		tabLayout.addTab(tabLayout.newTab());
		tabLayout.addTab(tabLayout.newTab());

		List<Fragment> fragments= new ArrayList<Fragment>();
		fragments.add(new VrPanoFragmment());
		fragments.add(new VrVideoFragment());
		MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
		adapter.setFragments(fragments);
		vp.setAdapter(adapter);

		tabLayout.setupWithViewPager(vp);

		tabLayout.getTabAt(0).setText("全景图片");
		tabLayout.getTabAt(1).setText("全景视频");

	}

	private void initView() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		vp = (ViewPager) findViewById(R.id.vp);
		tabLayout = (TabLayout) findViewById(R.id.tab_layout);
	}

	private void initActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		mToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open,R.string.close);
		mToggle.syncState();
		drawerLayout.addDrawerListener(mToggle);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(mToggle.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
