package com.android3.icontacts;

import android.content.Context;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MyViewFactory implements ViewFactory {

	private Context context;
	public MyViewFactory(Context context){
		this.context=context;
	}
	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView iv=new ImageView(context);
		iv.setLayoutParams(new ImageSwitcher.LayoutParams(90,90));
		
		return iv;
	}

}
