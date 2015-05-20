package com.dccs.xmlparse;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by androidm on 20/05/2015.
 */
public class TerremotosDAO {

    private SQLiteDatabase db;

    public TerremotosDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void  insertar(Terremoto itemTerremoto){
        db.insert("Terremotos","terr_id",terremotoToCV(itemTerremoto));
    }

    private ContentValues terremotoToCV(Terremoto itemTerremoto) {
        ContentValues valores = new ContentValues();

        valores.put("terr_id", itemTerremoto.getId());

        return valores;
    }
}
