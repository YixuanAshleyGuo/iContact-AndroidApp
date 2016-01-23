package com.android3.icontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper{
	//���ݿ���������
	private static final String DB_NAME="icontact.db";
	private static final String TBL_NAME="icontactTbl";
	//����������
	private static final String
			CREATE_TBL="CREATE TABLE "+"icontactTbl (_id INTEGER DEFAULT '1' NOT NULL"+" PRIMARY KEY AUTOINCREMENT,"
					+ "img INTEGER, name TEXT, note TEXT,mobph TEXT, telph TEXT, addr TEXT, email TEXT, firm TEXT, blog TEXT)";   
	//SQLiteDatabaseʵ��
	private SQLiteDatabase db;
	
	//���췽��
	public MyDBHelper(Context context){
		super(context, DB_NAME, null, 2);
		System.out.printf("�������ݿ�");
	}
	//������
	public void onCreate (SQLiteDatabase db){
		this.db=db;
		db.execSQL(CREATE_TBL);
		System.out.printf("������");
	}
	//���뷽��
	public void insert(ContentValues values){
		//���SQLiteDataBaseʵ��
		SQLiteDatabase db=getWritableDatabase();
		//����
		db.insert(TBL_NAME, null, values);
		db.close();
		System.out.printf("���ݿ����");
	}
	//��ѯ����
	public Cursor query(){
		System.out.printf("���ݿ��ѯ����");
		//���SQLiteDataBaseʵ��
		SQLiteDatabase db=getWritableDatabase();
		//��ѯ���Cursor 
		Cursor c=db.query(TBL_NAME, null, null, null, null, null, null);
		return c;
	}
	//ɾ������
	public void del(int id){
		System.out.printf("���ݿ�ɾ������");
		if(db==null){
			SQLiteDatabase db=getWritableDatabase();
			db.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
		}
	}
	//�ر����ݿ�
	public void close(){
		System.out.printf("���ݿ�ɾ������");
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
