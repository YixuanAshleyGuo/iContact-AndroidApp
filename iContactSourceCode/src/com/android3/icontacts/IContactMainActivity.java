package com.android3.icontacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class IContactMainActivity extends Activity {
	
	private ListView mylistview;
	private ArrayList <Map<String,Object>>list=null;
	Context context=this;
	private SimpleAdapter adapter=null;
	private HashMap<String,Object>map;
	private MyDBHelper dbhp=null;
	private SQLiteDatabase sqlDB=null;
	private Cursor cursor=null;
	private String content;
	private Integer imgid;
	
	AlarmManager aManager;
	ImageButton change,stop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icontact_main);

		Toast toast = Toast.makeText(getApplicationContext(), "Welcome to iContact!", 100);
		toast.setGravity(Gravity.CENTER,0,0);
		LinearLayout toastView=(LinearLayout)toast.getView();
		ImageView iv=new ImageView(getApplicationContext());
		iv.setImageResource(R.drawable.ic_launcher);
		toastView.addView(iv);
		toast.show();

		mylistview=(ListView)findViewById(R.id.listview);
		MySQlite();
		/*final MyDBHelper helper= new MyDBHelper(this);
		Cursor c=helper.query();
		String[] from={"_id","name","note","mobph","telph","addr","email","firm","blog"};
		*/
		
		ImageButton add_bn=(ImageButton)findViewById(R.id.addbn);
	    add_bn.setOnClickListener(new OnClickListener(){
		public void onClick(View v){
			Intent intent_re=new Intent(IContactMainActivity.this,IContactAdd.class);
			startActivity(intent_re);
		}
	});
	    
	    ImageButton srch_bn=(ImageButton)findViewById(R.id.searchbn);
	    srch_bn.setOnClickListener(new OnClickListener(){
	    	@SuppressWarnings("deprecation")
			public void onClick(View v){
	    	final EditText searchText = (EditText)findViewById(R.id.searchtxt);
	    	SimpleCursorAdapter adapter1 = null;
	    	content=searchText.getText().toString().trim();
	    	
	    	dbhp=new MyDBHelper(IContactMainActivity.this);
			sqlDB=dbhp.getReadableDatabase();
	    	cursor=sqlDB.rawQuery("select * from icontactTbl where name like '%"+ content + "%'",null);
	    	list=new ArrayList<Map<String,Object>>();
	    	while(cursor.moveToNext())
			{
				Contact modle=new Contact();
				map=new HashMap<String,Object>();
				map.put("id", modle.setid(cursor.getInt(cursor.getColumnIndex("_id"))));
				map.put("img2", modle.setimg(cursor.getInt(cursor.getColumnIndex("img"))));
				map.put("img", modle.setimg(cursor.getInt(cursor.getColumnIndex("img"))));
				//map.put("img2", "img");
				map.put("name", modle.setName(cursor.getString(cursor.getColumnIndex("name"))));
				map.put("mobph", modle.setmobph(cursor.getString(cursor.getColumnIndex("mobph"))));
				list.add(map);
			}
	    	adapter=new SimpleAdapter(IContactMainActivity.this,list,R.layout.icontact_item,
					new String[]{"id","img2","img","name","mobph"},new int[]{R.id.iid,R.id.iimgt,R.id.iimg,R.id.iname,R.id.imobph});
			
			mylistview.setAdapter(adapter);
			sqlDB.close();
	    	}
	    	});	
	    
	    mylistview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position,
			long arg3) 
			{
			ListView listview=(ListView)parent;
			HashMap<String, Object> data = (HashMap<String, Object>) listview.getItemAtPosition(position); 			
			Intent intent = new Intent();
			TextView tvid = (TextView) view.findViewById(R.id.iid);
			TextView iv=(TextView)view.findViewById(R.id.iimgt);
			intent.putExtra("id", tvid.getText().toString());
			intent.putExtra("img",Integer.parseInt(iv.getText().toString()));
			
			//Toast.makeText(IContactMainActivity.this, "onCreate()--imgID="+iv.getText().toString(), Toast.LENGTH_LONG).show();
			
            intent.setClass(IContactMainActivity.this, IContactShow.class);
			startActivity(intent);
			}
			});
	    
	    change = (ImageButton) findViewById(R.id.changebn);
		stop = (ImageButton) findViewById(R.id.stopbn);
		aManager = (AlarmManager) getSystemService(
			Service.ALARM_SERVICE);
		// 指定启动ChangeService组件
		Intent intent = new Intent(IContactMainActivity.this,
			ChangeService.class);
		// 创建PendingIntent对象
		final PendingIntent pi = PendingIntent.getService(
			IContactMainActivity.this, 0, intent, 0);
		change.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// 设置每隔5秒执行pi代表的组件一次
				aManager.setRepeating(AlarmManager.RTC_WAKEUP
						, 0, 5000, pi);
				change.setEnabled(false);
				stop.setEnabled(true);
				Toast.makeText(IContactMainActivity.this
					, "You have launched the regular wallpaper change!",
					Toast.LENGTH_SHORT).show();
			}
		});
		stop.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				change.setEnabled(true);
				stop.setEnabled(false);
				// 取消对pi的调度
				aManager.cancel(pi);
				Toast.makeText(IContactMainActivity.this
						, "You have canselled the regular wallpaper change!",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void MySQlite()
	{
		MyDBHelper helper=new MyDBHelper(this);
		
		SQLiteDatabase db=helper.getReadableDatabase();
		//查询数据库里的数据
		//helper.onUpgrade(db, 1, 1);
		Cursor c=db.query("icontactTbl", new String[]{"_id","img","name","mobph"},null,null,null,null,null);
		list =new ArrayList<Map<String,Object>>();
		while(c.moveToNext())
		{
			Contact modle=new Contact();
			map=new HashMap<String,Object>();
			map.put("id", modle.setid(c.getInt(c.getColumnIndex("_id"))));
			map.put("img2", modle.setimg(c.getInt(c.getColumnIndex("img"))));
			map.put("img", modle.setimg(c.getInt(c.getColumnIndex("img"))));
			//map.put("img2", "img");			
			map.put("name", modle.setName(c.getString(c.getColumnIndex("name"))));
			map.put("mobph", modle.setmobph(c.getString(c.getColumnIndex("mobph"))));
			list.add(map);
		}
		//创建SimpleAdapter适配器将数据绑定到item显示控件上  
		adapter=new SimpleAdapter(IContactMainActivity.this,list,R.layout.icontact_item,
				new String[]{"id","img2","img","name","mobph"},new int[]{R.id.iid,R.id.iimgt,R.id.iimg,R.id.iname,R.id.imobph});
		
		mylistview.setAdapter(adapter);
		db.close();
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.icontact_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
