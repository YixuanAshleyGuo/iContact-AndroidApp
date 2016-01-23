package com.android3.icontacts;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class IContactAdd extends Activity {
	
	private EditText name,note,mobph,telph,addr,firm,blog;
	private AutoCompleteTextView email;
	private ImageButton okbn,clbn;
	private ImageAdapter imageadapter;
	private Gallery gallery;
	String[] arrayString = new String[] { "@3g.sina.cn", "@sina.com",
            "@163.com", "@qq.com", "@126.com", "@vip.sina.com", "@sina.cn",
            "@hotmail.com", "@gmail.com", "@sohu.com", "@yahoo.com", "@tom.com" };
	AutoCompleteTextView autoview;

	private static int imgID;
	private ImageView imageview;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.icontact_add);	
		
		gallery=(Gallery)findViewById(R.id.gallery_add);
	    imageview=(ImageView)findViewById(R.id.iv_img);
	    imageadapter=new ImageAdapter(this);
	    gallery.setAdapter(imageadapter);
	    gallery.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				imgID=(int)imageadapter.getItemId(position);
				imageview.setImageResource(imgID);
				imageview.setId(imgID);

				
			}
	    	
	    });
	autoview = (AutoCompleteTextView)findViewById(R.id.edemail);
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
	

	    
		name=(EditText)findViewById(R.id.edname);
		note=(EditText)findViewById(R.id.ednote);
		mobph=(EditText)findViewById(R.id.edmobph);
		telph=(EditText)findViewById(R.id.edtelph);
		addr=(EditText)findViewById(R.id.edaddr);
		email=(AutoCompleteTextView)findViewById(R.id.edemail);
		firm=(EditText)findViewById(R.id.edfirm);
		blog=(EditText)findViewById(R.id.edblog);
		okbn=(ImageButton)findViewById(R.id.okbn);
		clbn=(ImageButton)findViewById(R.id.clbn);
		
		okbn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				int imgid=imgID;//imageview.getId();
				String sname=name.getText().toString();
				String snote=note.getText().toString();
				String smobph=mobph.getText().toString();
				String stelph=telph.getText().toString();
				String saddr=addr.getText().toString();
				String semail=email.getText().toString();
				String sfirm=firm.getText().toString();
				String sblog=blog.getText().toString();
				
			if(mobph.length()==11||telph.length()==11){//判断电话是否输入，不能为空和少于11个字符
				ContentValues values=new ContentValues();
				values.put("img", imgid);
				values.put("name", sname);
				values.put("note", snote);
				values.put("mobph", smobph);
				values.put("telph", stelph);
				values.put("addr", saddr);
				values.put("email", semail);
				values.put("firm", sfirm);
				values.put("blog", sblog);
				MyDBHelper helper=new MyDBHelper(IContactAdd.this);
				SQLiteDatabase db=helper.getWritableDatabase();
				db.insert("icontactTbl", null, values);
				db.close();
				
				Intent intent=new Intent();
				intent.setClass(IContactAdd.this, IContactMainActivity.class);
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
				intent.setClass(IContactAdd.this, IContactMainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
	}
	
	

	
}
