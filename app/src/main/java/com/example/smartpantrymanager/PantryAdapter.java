package com.example.smartpantrymanager;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class PantryAdapter extends CursorAdapter {

    public PantryAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor,
                        ViewGroup parent) {
        return LayoutInflater.from(context).inflate(
                android.R.layout.simple_list_item_2,
                parent,
                false
        );
    }

    @Override
    public void bindView(View view, Context context,
                         Cursor cursor) {

        TextView title = view.findViewById(
                android.R.id.text1);

        TextView subtitle = view.findViewById(
                android.R.id.text2);

        String name = cursor.getString(
                cursor.getColumnIndexOrThrow(
                        "name"));

        double quantity = cursor.getDouble(
                cursor.getColumnIndexOrThrow(
                        "quantity"));

        String unit = cursor.getString(
                cursor.getColumnIndexOrThrow(
                        "unit"));

        String expiryDate = cursor.getString(
                cursor.getColumnIndexOrThrow(
                        "expiry_date"));

        title.setText(name);
        subtitle.setText(
                formatQuantity(quantity) + " " + unit);

        if (expiryDate != null
                && !expiryDate.trim().isEmpty()) {
            subtitle.append(" • Expires: " + expiryDate);
        }
    }

    private String formatQuantity(double quantity) {
        if (quantity == Math.floor(quantity)) {
            return String.valueOf((long) quantity);
        }

        return String.valueOf(quantity);
    }
}
