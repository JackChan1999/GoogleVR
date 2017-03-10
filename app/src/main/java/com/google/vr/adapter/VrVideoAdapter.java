package com.google.vr.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.vr.R;
import com.google.vr.activity.VideoDetailActivity;
import com.google.vr.bean.VideoItem;

import java.text.SimpleDateFormat;
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
public class VrVideoAdapter extends BaseQuickAdapter<VideoItem> {
	public VrVideoAdapter(List<VideoItem> data) {
		super(R.layout.vr_video_list_item,data);
	}
	private int[] ids={R.id.tag0,R.id.tag1,R.id.tag2};
	@Override
	protected void convert(BaseViewHolder helper, VideoItem item) {
		helper.setText(R.id.topic_init_title,item.title);
		helper.setText(R.id.date_text,new SimpleDateFormat("MM/dd/yyyy").format(item.date));
		ImageView topicImg=helper.getView(R.id.topic_init_img);
		Glide.with(helper.getConvertView().getContext()).load(item.img).into(topicImg);
		String[] tags = item.tags;
		for (int i = 0; i < tags.length; i++) {
			helper.setText(ids[i],tags[i]);
		}

		View main = helper.getView(R.id.video_layout);
		main.setTag(item);
		main.setOnClickListener(listener);

	}
	private View.OnClickListener listener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			VideoItem item= (VideoItem) v.getTag();
			Intent intent=new Intent(v.getContext(),VideoDetailActivity.class);
			//需要传递的数据:
			intent.putExtra("title",item.title);
			intent.putExtra("img",item.img);
			intent.putExtra("text",item.text);
			intent.putExtra("play",item.play);
			v.getContext().startActivity(intent);
		}
	};
}
