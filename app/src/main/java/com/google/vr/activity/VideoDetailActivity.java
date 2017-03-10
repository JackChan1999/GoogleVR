package com.google.vr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.vr.R;

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
public class VideoDetailActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_detail);
		init();
	}

	private void init() {
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		String img = intent.getStringExtra("img");
		String text = intent.getStringExtra("text");
		final String play = intent.getStringExtra("play");

		TextView tvTitle= (TextView) findViewById(R.id.title_text);
		tvTitle.setText(title);

		ImageView ivImg= (ImageView) findViewById(R.id.detail_img_view);
		Glide.with(this).load(img).into(ivImg);

		TextView tv_detail= (TextView) findViewById(R.id.detail_text);
		tv_detail.setText(text);

		findViewById(R.id.play_link).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(VideoDetailActivity.this,VideoPlayerActivity.class);
				intent.putExtra("play",play);
				startActivity(intent);
			}
		});

	}
}
