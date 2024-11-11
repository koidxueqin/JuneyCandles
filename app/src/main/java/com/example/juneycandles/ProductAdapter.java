package com.example.juneycandles;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private SharedPreferences sharedPreferences; // Initialize in the constructor

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE); // Setup here
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_container, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Bind product data to views
        holder.productName.setText(product.getProductName());
        holder.productDesc.setText(product.getProductDesc());
        holder.productPrice.setText(String.format("$%.2f", product.getProductPrice()));

        // Set product image from drawable
        int imageResId = context.getResources().getIdentifier(product.getProductImg(), "drawable", context.getPackageName());
        holder.productImage.setImageResource(imageResId);

        // Set up size and color spinners
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(
                context, R.array.size_options, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(
                context, R.array.color_options, android.R.layout.simple_spinner_dropdown_item);

        holder.sizeSpinner.setAdapter(sizeAdapter);
        holder.colorSpinner.setAdapter(colorAdapter);

        // Add button click listener
        holder.addButton.setOnClickListener(v -> {
            String selectedSize = holder.sizeSpinner.getSelectedItem().toString();
            String selectedColor = holder.colorSpinner.getSelectedItem().toString();
            int custId = sharedPreferences.getInt("cust_id", -1);

            if (custId != -1) {
                boolean isAdded = addToCart(custId, product.getProductId(), selectedSize, selectedColor);
                Toast.makeText(context, isAdded ? "Product added to cart!" : "Failed to add product to cart.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Please log in first.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productDesc, productPrice;
        Spinner sizeSpinner, colorSpinner;
        ImageView productImage;
        Button addButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productDesc = itemView.findViewById(R.id.product_desc);
            productPrice = itemView.findViewById(R.id.product_price);
            sizeSpinner = itemView.findViewById(R.id.size_spinner);
            colorSpinner = itemView.findViewById(R.id.color_spinner);
            productImage = itemView.findViewById(R.id.product_img);
            addButton = itemView.findViewById(R.id.add_button);
        }
    }

    private boolean addToCart(int custId, int productId, String size, String color) {
        DBHelper dbHelper = new DBHelper(context);
        boolean result = dbHelper.insertToCart(custId, productId, size, color);
        dbHelper.close(); // Close the database connection
        return result;
    }
}
