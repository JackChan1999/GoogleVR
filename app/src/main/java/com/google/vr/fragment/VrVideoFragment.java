package com.google.vr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.google.vr.R;
import com.google.vr.adapter.VrVideoAdapter;
import com.google.vr.bean.VideoItem;
import com.google.vr.util.ApiUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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

public class VrVideoFragment extends BaseFragment {

	private RecyclerView recyclerView;

	@Override
	public RecyclerView.LayoutManager getLayoutManager() {
		return new GridLayoutManager(getActivity(), 2);
	}

	@Override
	public RecyclerView.Adapter getAdatper() {
		return null;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		RecyclerView.LayoutManager layoutManager = getLayoutManager();
		recyclerView.setLayoutManager(layoutManager);

		OkGo.get(ApiUrls.URL_Query).cacheKey(ApiUrls.URL_Query).cacheMode(CacheMode.DEFAULT).execute(new StringCallback() {

			@Override
			public void onSuccess(String s, Call call, Response response) {
				try {
					JSONObject obj = new JSONObject(s);
					String content = obj.getString("content");
					List<VideoItem> videoItems = JSON.parseArray(content, VideoItem.class);
					recyclerView.setAdapter(new VrVideoAdapter(videoItems));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
