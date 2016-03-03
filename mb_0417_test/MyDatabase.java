package com.example.mb_0417_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rank.db";  
    private static final int DATABASE_VERSION = 1;
    
	public MyDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists person" +
				"(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, score int)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �Զ����ɵķ������
		db.execSQL("ALTER TABLE person ADD COLUMN other STRING");  
	}

}