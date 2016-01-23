package com.android3.icontacts;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class IContactEdit extends Activity{
	private EditText name,note,mobph,telph,addr,email,firm,blog;
	private ImageButton okbn,clbn;
	private String passid;
	private Integer passimg;
	private ImageView imgv;
	private Gallery gallery;
	private ImageAdapter imgadapter;
	private static int imgID;
	
	//private Gallery gallery;
	 AutoCompleteTextView autoview;
	 String[] arrayString = new String[] { "@3g.sina.cn", "@sina.com",
	            "@163.com", "@qq.com", "@126.com", "@vip.sina.com", "@sina.cn",
	            "@hotmail.com", "@gmail.com", "@sohu.com", "@yahoo.com", "@tom.com" };
	    	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.icontact_edit);
		
		Bundle bundle=this.getIntent().getExtras();
		passid=bundle.getString("id");
		passimg=bundle.getInt("img");
		name=(EditText)findViewById(R.id.edtname);
		note=(EditText)findViewById(R.id.edtnote);
		mobph=(EditText)findViewById(R.id.edtmobph);
		telph=(EditText)findViewById(R.id.edttelph);
		addr=(EditText)findViewById(R.id.edtaddr);
		email=(EditText)findViewById(R.id.edtemail);
		firm=(EditText)findViewById(R.id.edtfirm);
		blog=(EditText)findViewById(R.id.edtblog);
		okbn=(ImageButton)findViewById(R.id.okbn2);
		clbn=(ImageButton)findViewById(R.id.clbn2);
		
		imgv=(ImageView)findViewById(R.id.img_edit);
		imgv.setImageResource(passimg);
		gallery=(Gallery)findViewById(R.id.gallery_edit);
		imgadapter=new ImageAdapter (this);
		gallery.setAdapter(imgadapter);
	    gallery.setOnItemClickListener(new OnItemClickListener(){

	   
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				imgID=(int)imgadapter.getItemId(position);
				imgv.setImageResource(imgID);
				//imgv.setId(imgID);
				
			}
	    	
	    });
		
		
		MyDBHelper helper=new MyDBHelper(IContactEdit.this);
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
		
			gallery=(Gallery)findViewById(R.id.gallery_edit);
			imgadapter=new ImageAdapter(this);
			gallery.setAdapter(imgadapter);
			imgID=passimg;
			gallery.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position,
						long id) {
					// TODO Auto-generated method stub
					imgID=(int)imgadapter.getItemId(position);
					imgv.setImageResource(imgID);
					//imgv.setId(imgID);
				}
				
			});
			
			autoview = (AutoCompleteTextView)findViewById(R.id.edtemail);
			final MyAdatper adapter=new MyAdatper(this);
			autoview.setAdapter(adapter);
			autoview.addTextChangedListener(new TextWatcher() {           
		            public void onTextChanged(CharSequence s, int start, int before, int count) {
		                    // TODO Auto-generated method stub                    
		            }        
		            public void beforeTextChanged(CharSequence s, int start, int count,
		                            int after) {
		                    // TODO Auto-generated method stub                    
		            }            
		            public void afterTextChanged(Editable s) {
		                    // TODO Auto-generated method stub
		                    String input = s.toString();
		                    adapter.mList.clear();
		                    if (input.length() > 0) {
		                        for (int i = 0; i < arrayString.length; ++i) {
		                        	adapter.mList.add(input+arrayString[i]);
		                         }
		                            
		                       }
		                    adapter.notifyDataSetChanged();
		                    autoview.showDropDown();
		                    }
		    });
			autoview.setThreshold(1);
			
		okbn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				//int imgid=imgv.getId();
				String sname=name.getText().toString();
				String snote=note.getText().toString();
				String smobph=mobph.getText().toString();
				String stelph=telph.getText().toString();
				String saddr=addr.getText().toString();
				String semail=email.getText().toString();
				String sfirm=firm.getText().toString();
				String sblog=blog.getText().toString();
		if(mobph.length()==11||telph.length()==11){	
				ContentValues values=new ContentValues();
				values.put("img", imgID);
				values.put("name", sname);
				values.put("note", snote);
				values.put("mobph", smobph);
				values.put("telph", stelph);
				values.put("addr", saddr);
				values.put("email", semail);
				values.put("firm", sfirm);
				values.put("blog", sblog);
				MyDBHelper helper=new MyDBHelper(IContactEdit.this);
				SQLiteDatabase db=helper.getWritableDatabase();
				db.update("icontactTbl", values,"_id=?",new String[]{passid});
				db.close();
				Intent intent=new Intent();
				intent.setClass(IContactEdit.this, IContactMainActivity.class);
				startActivity(intent);
				finish();		
			    }
				else{
					Toast toast = Toast.makeText(getApplicationContext(), "Please enter 11 mobile phone number!", 30);
					toast.setGravity(Gravity.CENTER,0,0);
					LinearLayout toastView=(LinearLayout)toast.getView();
					ImageView iv=new ImageView(getApplicationContext());
					iv.setImageResource(R.drawable.icon_warn);
					toastView.addView(iv);
			
					toast.show();
				}
			}
		});
		clbn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				Intent intent=new Intent();
				intent.putExtra("id", passid);
				intent.putExtra("img", imgID);
				intent.setClass(IContactEdit.this, IContactShow.class);
				startActivity(intent);
				finish();
			}
		});		
		
	}
  }
}
