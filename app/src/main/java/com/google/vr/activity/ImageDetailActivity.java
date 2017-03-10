package com.google.vr.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.google.vr.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

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
public class ImageDetailActivity extends AppCompatActivity {

	private VrPanoramaView vrPano;
	private View loading;
	private MediaPlayer player;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_detail);
		init();
	}

	private void init() {
		initActionBar();
		initPanoView();
	}

	private void initPanoView() {
		vrPano = (VrPanoramaView) findViewById(R.id.vr_pano);
		loading = findViewById(R.id.pb_loading);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		String mp3 = intent.getStringExtra("mp3");
		initPlayer(mp3);
		OkGo.get(url).cacheKey(url).execute(new BitmapCallback() {
			@Override
			public void onSuccess(Bitmap bitmap, Call call, Response response) {
				loading.setVisibility(View.GONE);

				VrPanoramaView.Options options = new VrPanoramaView.Options();
				options.inputType = VrPanoramaView.Options.TYPE_MONO;
				vrPano.loadImageFromBitmap(bitmap, options);
			}
		});

	}

	private void initPlayer(String mp3) {
		if (mp3 != null) {
			player = new MediaPlayer();

			player.setAudioStreamType(AudioManager.STREAM_MUSIC);
			try {
				player.setDataSource(this, Uri.parse(mp3));
				player.prepare();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		vrPano.resumeRendering();
		if (player != null) {
			player.start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		vrPano.pauseRendering();
		if (player != null) {
			player.pause();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		vrPano.shutdown();
		if (player != null) {
			player.stop();
			player.release();
			player = null;
		}
	}

	private void initActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
