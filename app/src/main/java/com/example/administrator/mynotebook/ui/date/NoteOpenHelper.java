package com.example.administrator.mynotebook.ui.date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/5/10.
 */
public class NoteOpenHelper extends SQLiteOpenHelper {
    private  static NoteOpenHelper mInstance;
//    boolean isDel, int id,int notifiId, int starId, String title, String content, String createDate, String modifyDate
    private  final  String CREATE_TABLE_NOTE =
            "create table note (id int, notifiId int, starId int, title varchar(40), content text, createtime date, isdel int default 0);";

    private NoteOpenHelper(Context context){
        super(context, "notedb", null,  1);
    }

    public static NoteOpenHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new NoteOpenHelper(context);
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}