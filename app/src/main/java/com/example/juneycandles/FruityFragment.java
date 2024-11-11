package com.example.juneycandles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class FruityFragment extends Fragment {

    private RecyclerView fruityMenu;
    private List<Product> productList = new ArrayList<>();
    private SQLiteDatabase db;

    public FruityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fruity, container, false);

        fruityMenu = view.findViewById(R.id.recyclerview);
        fruityMenu.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = getActivity().openOrCreateDatabase("JuneyCandlesDB.db", Context.MODE_PRIVATE, null);
        loadFruityProducts();

       //Back button to return to menu
        Button backIcon = view.findViewById(R.id.backIcon);
        backIcon.setOnClickListener(v -> {
            // Go back to the ProfileFragment
            getActivity().getSupportFragmentManager().popBackStack();
        });




        return view;
    }

    private void loadFruityProducts() {

        Cursor cursor = db.rawQuery("SELECT * FROM Products WHERE product_cat = 'fruity'", null);
        productList.clear();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int productId = cursor.getInt(cursor.getColumnIndex("product_id"));
                String productName = cursor.getString(cursor.getColumnIndex("product_name"));
                double productPrice = cursor.getDouble(cursor.getColumnIndex("product_price"));
                String productDesc = cursor.getString(cursor.getColumnIndex("product_desc"));
                String productImg = cursor.getString(cursor.getColumnIndex("product_img"));
                String productCat = cursor.getString(cursor.getColumnIndex("product_cat"));

                Product product = new Product(productId, productName, productPrice, productDesc, productImg, productCat);
                productList.add(product);
            } while (cursor.moveToNext());
            cursor.close();
        }

        ProductAdapter productAdapter = new ProductAdapter(getActivity(), productList);
        fruityMenu.setAdapter(productAdapter);
    }

}