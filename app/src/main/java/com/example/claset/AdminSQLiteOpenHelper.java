package com.example.claset;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation. Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
        public AdminSQLiteOpenHelper (@Nullable Context context, @Nullable String name,
                                      @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override

        public void onCreate(SQLiteDatabase sqliteDatabase) {

            sqliteDatabase.execSQL("create table estudiantes (matricula text primary key, " + "nombres text, edad text, id_carrera text)");
        }
            @Override

            public void onUpgrade (SQLiteDatabase sqliteDatabase, int i, int i1){
            }
}