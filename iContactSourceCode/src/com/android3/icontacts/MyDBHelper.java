package com.android3.icontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper{
	//数据库名，表名
	private static final String DB_NAME="icontact.db";
	private static final String TBL_NAME="icontactTbl";
	//创建表的语句
	private static final String
			CREATE_TBL="CREATE TABLE "+"icontactTbl (_id INTEGER DEFAULT '1' NOT NULL"+" PRIMARY KEY AUTOINCREMENT,"
					+ "img INTEGER, name TEXT, note TEXT,mobph TEXT, telph TEXT, addr TEXT, email TEXT, firm TEXT, blog TEXT)";   
	//SQLiteDatabase实例
	private SQLiteDatabase db;
	
	//构造方法
	public MyDBHelper(Context context){
		super(context, DB_NAME, null, 2);
		System.out.printf("创建数据库");
	}
	//创建表
	public void onCreate (SQLiteDatabase db){
		this.db=db;
		db.execSQL(CREATE_TBL);
		System.out.printf("创建表");
	}
	//插入方法
	public void insert(ContentValues values){
		//获得SQLiteDataBase实例
		SQLiteDatabase db=getWritableDatabase();
		//插入
		db.insert(TBL_NAME, null, values);
		db.close();
		System.out.printf("数据库操作");
	}
	//查询方法
	public Cursor query(){
		System.out.printf("数据库查询方法");
		//获得SQLiteDataBase实例
		SQLiteDatabase db=getWritableDatabase();
		//查询获得Cursor 
		Cursor c=db.query(TBL_NAME, null, null, null, null, null, null);
		return c;
	}
	//删除方法
	public void del(int id){
		System.out.printf("数据库删除方法");
		if(db==null){
			SQLiteDatabase db=getWritableDatabase();
			db.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
		}
	}
	//关闭数据库
	public void close(){
		System.out.printf("数据库删除方法");
		if(db!=null){
			db.close();
		}
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
		onCreate(db);
	}
}
