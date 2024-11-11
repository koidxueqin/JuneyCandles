package com.example.juneycandles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

       // searchView = view.findViewById(R.id.searchView);
       // recyclerView = view.findViewById(R.id.fruity_menu);

        // Initialize your RecyclerView and ProductAdapter here
        // Example: productAdapter = new ProductAdapter(productList);
        // recyclerView.setAdapter(productAdapter);

        // Handle search query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search on submit
                filterProducts(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Perform search as text changes
                filterProducts(newText);
                return false;
            }
        });

        return view;
    }

    private void filterProducts(String query) {

       //List<Product> filteredList = getFilteredProductList(query);
     //  productAdapter.updateList(filteredList);
    }
}
