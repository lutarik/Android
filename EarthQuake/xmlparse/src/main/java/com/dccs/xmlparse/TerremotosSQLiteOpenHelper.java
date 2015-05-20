package com.dccs.xmlparse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by androidm on 20/05/2015.
 */
public class TerremotosSQLiteOpenHelper extends SQLiteOpenHelper {

    Context context;
    String name;
    int version;

    public TerremotosSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
        this.version= version;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String[] queries = context.getResources().getStringArray(R.array.script_creacion);

        ejecutarScript(db, queries);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String[] queries;
        for (int i = oldVersion;i<newVersion;i++){
            switch (i){
                case 1:
                    queries = context.getResources().getStringArray(R.array.script_updateV1);
                    ejecutarScript(db, queries);
                    break;
                case 2:
                    queries = context.getResources().getStringArray(R.array.script_updateV2);
                    ejecutarScript(db, queries);
                    break;
            }

        }
    }

    private void ejecutarScript(SQLiteDatabase db, String[] queries) {
        db.beginTransaction();

        try {
            for(String query:queries){
                db.execSQL(query);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
