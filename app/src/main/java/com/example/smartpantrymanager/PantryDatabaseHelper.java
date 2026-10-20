package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PantryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_EXPIRY_DATE = "expiry_date";

    public PantryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createIngredientsTable =
                "CREATE TABLE " + TABLE_INGREDIENTS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_QUANTITY + " REAL NOT NULL, " +
                        COLUMN_UNIT + " TEXT NOT NULL, " +
                        COLUMN_EXPIRY_DATE + " TEXT)";

        db.execSQL(createIngredientsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);
    }

    public boolean addIngredient(String name, double quantity,
                                 String unit, String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name.trim());
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_UNIT, unit.trim());
        values.put(COLUMN_EXPIRY_DATE, expiryDate);

        long result = db.insert(TABLE_INGREDIENTS, null, values);
        return result != -1;
    }

    public Cursor getAllIngredients() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT id AS _id, name, quantity, unit, expiry_date " +
                        "FROM ingredients " +
                        "ORDER BY name COLLATE NOCASE ASC",
                null
        );
    }

    public boolean updateIngredient(int id, String name,
                                    double quantity, String unit,
                                    String expiryDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name.trim());
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_UNIT, unit.trim());
        values.put(COLUMN_EXPIRY_DATE, expiryDate);

        int rowsUpdated = db.update(
                TABLE_INGREDIENTS,
                values,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        return rowsUpdated > 0;
    }

    public boolean deleteIngredient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_INGREDIENTS,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        return rowsDeleted > 0;
    }
}
