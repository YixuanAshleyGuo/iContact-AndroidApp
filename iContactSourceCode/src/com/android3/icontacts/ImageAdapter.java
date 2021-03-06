package com.android3.icontacts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	// 定义Context
	private Context		mContext;		
	// 定义整型数组 即图片源
	private Integer[]	mImageIds = 
	{ 
			
			R.drawable.i1, 
			R.drawable.i2, 
			R.drawable.i3, 
			R.drawable.i4, 
			R.drawable.i5, 
			R.drawable.i6, 
			R.drawable.i7,
			R.drawable.i8,
			R.drawable.i9,
	};

	// 声明 ImageAdapter
	public ImageAdapter(Context c)
	{
		mContext = c;
	}

	// 获取图片的个数
	public int getCount()
	{
		return mImageIds.length;
	}

	// 获取图片在库中的位置
	public Object getItem(int position)
	{
		return position;
	}

	// 获取图片ID
	public long getItemId(int position)
	{
		return mImageIds[position];//--------------
	}

	public View getView(int position, View convertView, ViewGroup parent){
		ImageView imageview = new ImageView(mContext);
		// 给ImageView设置资源
		imageview.setImageResource(mImageIds[position]);
		// 设置布局 图片50×50显示
		imageview.setLayoutParams(new Gallery.LayoutParams(50, 50));//宽度和高度
		// 设置显示比例类型
		imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageview;
	}
}

