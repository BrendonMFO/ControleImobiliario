package com.cristallium.brendon.imobiliario.Imovel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ImovelDatabaseHelper extends SQLiteOpenHelper {

    private final static String TABLE_NAME = "Imovel";
    private final static String COL1 = "id_imovel";
    private final static String COL2 = "valor_imovel";
    private final static String COL3 = "endereco_imovel";
    private final static String COL4 = "informacao_imovel";
    private final static String COL5 = "proprietario_imovel";

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

    public boolean addImovel(Imovel imovel) {
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, createContent(imovel)) != -1;
    }

    public boolean editImovel(Integer id, Imovel imovel) {
        SQLiteDatabase db = getWritableDatabase();
        return db.update(TABLE_NAME, createContent(imovel), "id_imovel = " + id, null) != -1;
    }

    public ArrayList<Imovel> getAllImoveis() {
        ArrayList<Imovel> imoveis =  new ArrayList<Imovel>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor data = db.rawQuery(String.format("SELECT * FROM %s", TABLE_NAME), null);
        while (data.moveToNext()) {
            Imovel imovel = new Imovel();
            imovel.setValues(data.getString(2), data.getString(4), data.getString(3), data.getInt(1));
            imoveis.add(imovel);
        }
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
