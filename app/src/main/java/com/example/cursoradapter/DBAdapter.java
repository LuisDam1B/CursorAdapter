package com.example.cursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    SQLiteDatabase sqLiteDatabase;
    OHCategoria ohCategoria;
    Context context;

    public DBAdapter(Context context) {

        this.context=context;
        ohCategoria=new OHCategoria(context,"BBDCategorias",null,1);
    }

    public  void insertarDatosCodigo() {

        sqLiteDatabase=ohCategoria.getWritableDatabase();
        if (sqLiteDatabase != null) {
            ContentValues valores = new ContentValues();
            valores.put("nombre", "ASIR");
            valores.put("cate", "Superior");
            valores.put("idcategoria", 1);
            sqLiteDatabase.insert("categoria", null, valores);
            valores.put("nombre", "DAM");
            valores.put("cate", "Superior");
            valores.put("idcategoria", 2);
            sqLiteDatabase.insert("categoria", null, valores);
            valores.put("nombre", "SMR");
            valores.put("cate", "Medio");
            valores.put("idcategoria", 3);
            sqLiteDatabase.insert("categoria", null, valores);
            sqLiteDatabase.close();
        }
    }

    public Cursor consultarDatos()
    {
        sqLiteDatabase=ohCategoria.getReadableDatabase();
        if(sqLiteDatabase!=null) {

            return sqLiteDatabase.rawQuery("select  idcategoria as _id, nombre, cate from categoria", null);
        }
        return null;
    }
    public void cerrarBD()
    {
        ohCategoria.close();
    }
    class  OHCategoria extends SQLiteOpenHelper {

        String cadena = "create table if not exists categoria(idcategoria INTEGER PRIMARY KEY NOT NULL, nombre TEXT, cate TEXT);";

        public OHCategoria(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(cadena);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
