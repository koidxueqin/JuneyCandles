package com.example.juneycandles;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ZodiacFragment extends Fragment {

    private ProductAdapter productAdapter;
    private List<Product> productList;

    public ZodiacFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_zodiac, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewZodiac);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);

        // Fetch data from the database
        fetchProductsFromDatabase();

        return rootView;
    }

    private void fetchProductsFromDatabase() {
        SQLiteDatabase db = new DBHelper(getContext()).getReadableDatabase();
        Cursor cursor = db.query("Products", null, null, null, null, null, "product_id ASC");

        if (cursor != null && cursor.moveToFirst()) {
            try {
                do {
                    int productId = cursor.getInt(cursor.getColumnIndexOrThrow("product_id"));
                    String productName = cursor.getString(cursor.getColumnIndexOrThrow("product_name"));
                    double productPrice = cursor.getDouble(cursor.getColumnIndexOrThrow("product_price"));
                    String productImg = cursor.getString(cursor.getColumnIndexOrThrow("product_img"));

                    productList.add(new Product(productId, productName, productPrice, productImg));
                } while (cursor.moveToNext());
            } catch (IllegalArgumentException e) {
                Log.e("ZodiacFragment", "Column not found: " + e.getMessage());
            } finally {
                cursor.close();
            }
        }

        productAdapter.notifyDataSetChanged();
    }
}
