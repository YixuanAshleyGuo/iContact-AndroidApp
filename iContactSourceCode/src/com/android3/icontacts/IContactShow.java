package com.android3.icontacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class IContactShow extends Activity{
	private TextView name,note,mobph,telph,addr,email,firm,blog;
	private ImageButton backbn,editbn,delbn;
	private String passid;
	private Integer passimg;
	private ImageView imageview;
	private ImageAdapter imageadapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.icontact_show);
		Bundle bundle=this.getIntent().getExtras();
		passid=bundle.getString("id");
		passimg=bundle.getInt("img");
		
	    //Toast.makeText(IContactShow.this, "onCreate()--imgID="+passimg, Toast.LENGTH_LONG).show();
		
		name=(TextView)findViewById(R.id.shname);
		note=(TextView)findViewById(R.id.shnote);
		mobph=(TextView)findViewById(R.id.shmobph);
		telph=(TextView)findViewById(R.id.shtelph);
		addr=(TextView)findViewById(R.id.shaddr);
		email=(TextView)findViewById(R.id.shemail);
		firm=(TextView)findViewById(R.id.shfirm);
		blog=(TextView)findViewById(R.id.shblog);
		backbn=(ImageButton)findViewById(R.id.backbn);
		editbn=(ImageButton)findViewById(R.id.editbn);
		delbn=(ImageButton)findViewById(R.id.delbn);
		
		imageview=(ImageView)findViewById(R.id.img_show);
		imageadapter=new ImageAdapter(this);
		imageview.setImageResource(passimg);
		MyDBHelper helper=new MyDBHelper(IContactShow.this);
		SQLiteDatabase db=helper.getReadableDatabase();
		Cursor cursor=db.query("icontactTbl", 
				new String[] {"name","note","mobph","telph","addr","email","firm","blog"},
				"_id=?", new String [] {passid}, null, null, null);
		while(cursor.moveToNext()){
			Contact modle=new Contact();
			name.setText(modle.setName(cursor.getString(cursor.getColumnIndex("name"))));
			note.setText(modle.setName(cursor.getString(cursor.getColumnIndex("note"))));
			mobph.setText(modle.setName(cursor.getString(cursor.getColumnIndex("mobph"))));
			telph.setText(modle.setName(cursor.getString(cursor.getColumnIndex("telph"))));
			addr.setText(modle.setName(cursor.getString(cursor.getColumnIndex("addr"))));
			email.setText(modle.setName(cursor.getString(cursor.getColumnIndex("email"))));
			firm.setText(modle.setName(cursor.getString(cursor.getColumnIndex("firm"))));
			blog.setText(modle.setName(cursor.getString(cursor.getColumnIndex("blog"))));
		}
			
			backbn.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Intent intent=new Intent();
					intent.setClass(IContactShow.this, IContactMainActivity.class);
					startActivity(intent);
					finish();
				}
			});
			
			editbn.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					
					Intent intent=new Intent();
					intent.putExtra("id",passid );
					intent.putExtra("img", passimg);
					intent.setClass(IContactShow.this, IContactEdit.class);
					startActivity(intent);
					finish();
				}
			});
			//对话框
			final AlertDialog.Builder builder=new AlertDialog.Builder(this);
			//删除键，弹出对话框进行确认
			delbn.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					builder.setTitle("warning")
					.setMessage("Are you sure to delete this CONTACT?")
					.setIcon(R.drawable.icon_warn)
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							//删除数据
							MyDBHelper helper= new MyDBHelper(IContactShow.this);
							SQLiteDatabase db=helper.getWritableDatabase();
							db.delete("icontactTbl", "_id=?", new String[]{passid});
							db.close();
							
							Toast toast = Toast.makeText(getApplicationContext(), "You have deleted a contact!", 200);
							toast.setGravity(Gravity.CENTER,0,0);
							
							LinearLayout toastView=(LinearLayout)toast.getView();
							ImageView iv=new ImageView(getApplicationContext());
							iv.setImageResource(R.drawable.icontact_1);
							//toastView.setBackgroundResource(R.drawable.icon_dlg);
							
							toastView.addView(iv);
							toast.show();
							
							Intent intent=new Intent();
							intent.setClass(IContactShow.this, IContactMainActivity.class);
							startActivity(intent);
							finish();
						}
					}).setNegativeButton("NO!",new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog,int which){							
						}
					});
					AlertDialog ad=builder.create();
					ad.getWindow().setLayout(300, 200);
					//ad.getWindow().setBackgroundDrawableResource(R.drawable.icon_dlg);
					ad.show();
				}
			});
	}
}
			
				



