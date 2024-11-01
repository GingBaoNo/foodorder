package com.example.firstproject.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstproject.Adapter.CartAdapter;
import com.example.firstproject.Helper.ChangeNumberItemsListener;
import com.example.firstproject.Helper.ManagmentCart;
import com.example.firstproject.R;
import com.example.firstproject.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityCartBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        managmentCart=new ManagmentCart(this);

        setVariable();
        calulateCart();
        initList();
    }

    private void initList() {
        if (managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollviewCart.setVisibility(View.GONE);
        }else{
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.cartView.setLayoutManager(linearLayoutManager);
        adapter=new CartAdapter(managmentCart.getListCart(), this, () -> calulateCart());
        binding.cartView.setAdapter(adapter);
    }

    private void calulateCart() {
        double percentTax=0.02;
        double delivery=10;

        tax=Math.round(managmentCart.getTotalFee()*percentTax*100.00)/100;

        double total=Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100;

        double itemTotal=Math.round(managmentCart.getTotalFee()*100)/100;

        binding.totalFeeTxt.setText(itemTotal+"đ");
        binding.taxTxt.setText(tax+"đ");
        binding.deliveryTxt.setText(delivery+"đ");
        binding.totalTxt.setText(total+"đ");
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}