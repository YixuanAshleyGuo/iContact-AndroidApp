package com.android3.icontacts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	// ����Context
	private Context		mContext;		
	// ������������ ��ͼƬԴ
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

	// ���� ImageAdapter
	public ImageAdapter(Context c)
	{
		mContext = c;
	}

	// ��ȡͼƬ�ĸ���
	public int getCount()
	{
		return mImageIds.length;
	}

	// ��ȡͼƬ�ڿ��е�λ��
	public Object getItem(int position)
	{
		return position;
	}

	// ��ȡͼƬID
	public long getItemId(int position)
	{
		return mImageIds[position];//--------------
	}

	public View getView(int position, View convertView, ViewGroup parent){
		ImageView imageview = new ImageView(mContext);
		// ��ImageView������Դ
		imageview.setImageResource(mImageIds[position]);
		// ���ò��� ͼƬ50��50��ʾ
		imageview.setLayoutParams(new Gallery.LayoutParams(50, 50));//��Ⱥ͸߶�
		// ������ʾ��������
		imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		return imageview;
	}
}

