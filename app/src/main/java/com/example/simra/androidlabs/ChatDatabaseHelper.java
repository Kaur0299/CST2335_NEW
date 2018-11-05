package com.example.simra.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class ChatDatabaseHelper extends SQLiteOpenHelper {

    protected static final String ACTIVITY_NAME = "ChatDatabaseHelper";
    private static final String DATABASE_NAME ="Messages.db " ;
    private static final int VERSION_NUM = 1 ;
    protected static final String TABLE_NAME = "Chats" ;
    protected static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "_msg";

    public ChatDatabaseHelper(Context ctx){
        super(ctx,DATABASE_NAME,null,VERSION_NUM);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " + TABLE_NAME + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                KEY_MESSAGE + " String);"
        );
        Log.i(ACTIVITY_NAME,"Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        Log.i(ACTIVITY_NAME,"Calling onUpgrade, oldVersion=" + oldVersion + "newVersion=" + newVersion);

    }

}
