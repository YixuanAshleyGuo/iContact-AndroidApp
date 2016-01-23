package com.android3.icontacts;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

public class ChangeService extends Service
{
	// ���嶨ʱ�����ı�ֽ��Դ
	int[] wallpapers = new int[]{
		R.drawable.icontact_main,
		R.drawable.icontact_add,
		R.drawable.icontact_show,
		R.drawable.icontact_1
	};
	// ����ϵͳ�ı�ֽ�������
	WallpaperManager wManager;
	// ���嵱ǰ����ʾ�ı�ֽ
	int current = 0;
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// ����������һ�ţ�ϵͳ���¿�ʼ
		if(current >= 4)
			current = 0;
		try
		{
			// �ı��ֽ
			wManager.setResource(wallpapers[current++]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return START_STICKY;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		// ��ʼ��WallpaperManager
		wManager = WallpaperManager.getInstance(this);
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
}

