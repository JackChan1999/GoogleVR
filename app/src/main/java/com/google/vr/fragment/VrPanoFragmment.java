package com.google.vr.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.vr.adapter.VrPanoAdapter;
import com.google.vr.util.ImageUrlGetter;

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

public class VrPanoFragmment extends BaseFragment{

	@Override
	public RecyclerView.LayoutManager getLayoutManager() {
		return new LinearLayoutManager(getActivity());
	}

	@Override
	public RecyclerView.Adapter getAdatper() {
		return new VrPanoAdapter(ImageUrlGetter.getImageItems());
	}
}
