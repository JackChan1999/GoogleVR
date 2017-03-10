package com.google.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.vr.R;
import com.google.vr.activity.ImageDetailActivity;
import com.google.vr.bean.ImageItem;

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

public class VrPanoAdapter extends BaseQuickAdapter<ImageItem> {
	public VrPanoAdapter(List<ImageItem> data) {
		super(R.layout.vr_pano_list_item, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, ImageItem item) {
		//获取ImageView,并显示图片到ImageView上
		ImageView iv_pano = helper.getView(R.id.iv_pano);
		Context context = helper.getConvertView().getContext();
		Glide.with(context).load(item.url).into(iv_pano);
		//设置标题到TextView上
		helper.setText(R.id.tv_title, item.title);

		View main = helper.getView(R.id.ll_item_main);
		main.setTag(item);
		main.setOnClickListener(listener);

	}
	private View.OnClickListener listener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
//			Toast.makeText(v.getContext(), "点击", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(v.getContext(),ImageDetailActivity.class);
			ImageItem item= (ImageItem) v.getTag();
			intent.putExtra("url",item.url);
			intent.putExtra("mp3",item.mp3);
			v.getContext().startActivity(intent);
		}
	};
}
