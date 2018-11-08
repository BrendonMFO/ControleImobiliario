package com.cristallium.brendon.imobiliario.Imovel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ImovelDatabaseHelper extends SQLiteOpenHelper {

    private final static String COL1 = "id_imovel";
    private final static String COL2 = "valor_imovel";
    private final static String COL3 = "endereco_imovel";
    private final static String COL4 = "informacao_imovel";
    private final static String COL5 = "proprietario_imovel";
    private final static String TABLE_NAME = "ImovelDatabase";

    public ImovelDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
                        TABLE_NAME, COL1, COL2, COL3, COL4, COL5)
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP IF TABLE EXISTS %s", TABLE_NAME));
        onCreate(sqLiteDatabase);
    }

    public long addImovel(Imovel imovel) {
        SQLiteDatabase db = getWritableDatabase();
        long insert = db.insert(TABLE_NAME, null, createContent(imovel));
        db.close();
        return insert;
    }

    public void editImovel(Imovel imovel) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, createContent(imovel), "id_imovel = ?", new String[]{imovel.getId().toString()});
        db.close();
    }

    public void removeImovel(Integer id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id_imovel = ?", new String[]{id.toString()});
        db.close();
    }

    public ArrayList<Imovel> getAllImoveis() {
        ArrayList<Imovel> imoveis =  new ArrayList<Imovel>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", TABLE_NAME), null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Imovel imovel = new Imovel();
                imovel.setValues(
                        cursor.getString(cursor.getColumnIndex(COL3)),
                        cursor.getString(cursor.getColumnIndex(COL5)),
                        cursor.getString(cursor.getColumnIndex(COL4)),
                        cursor.getInt(cursor.getColumnIndex(COL2))
                );
                imovel.setId(cursor.getColumnIndex(COL1));
                imoveis.add(imovel);
                cursor.moveToNext();
            }
        }
        db.close();
        return imoveis;
    }

    private ContentValues createContent(Imovel imovel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, imovel.getValor());
        contentValues.put(COL3, imovel.getEndereco());
        contentValues.put(COL4, imovel.getInformacoes());
        contentValues.put(COL5, imovel.getProprietario());
        return contentValues;
    }
}
