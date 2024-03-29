package phinhph25802.example.appfastfood_admin_main.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hainb21127.poly.appfastfood_admin.Activity.NewProducts;
import hainb21127.poly.appfastfood_admin.Adapter.CategoryAdapter;
import hainb21127.poly.appfastfood_admin.Adapter.ProdAdapter;
import hainb21127.poly.appfastfood_admin.DTO.Products;
import hainb21127.poly.appfastfood_admin.R;
import hainb21127.poly.appfastfood_admin.database.FirebaseDB;

public class ProductsFrag extends Fragment {

    RecyclerView recyclerView;
    ProdAdapter adapter1;

    Context context;
    List<Products> mProduct;

    FloatingActionButton fab_sp;

    CategoryAdapter adapterCate;
    List<String>listCategory;
    Products products;

    private String prodId;
    CardView cardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab_sp = view.findViewById(R.id.fab_sp);
        cardView = view.findViewById(R.id.cardView);
        recyclerView = view.findViewById(R.id.rcv_product_fragment);
        mProduct = new ArrayList<>();
        adapter1 = new ProdAdapter(context);
        adapterCate = new CategoryAdapter(context);
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(manager);
        getListProducts();

        oninVisible();
        fab_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewProducts.class));
//                startActivity(new Intent(getActivity(), ChooseImage.class));
            }
        });
    }
    private void getListProducts(){
        FirebaseDatabase database = FirebaseDB.getDatabaseInstance();
        DatabaseReference myRef = database.getReference("products");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mProduct.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Products product = dataSnapshot.getValue(Products.class);
                    product.setId(dataSnapshot.getKey());
                    mProduct.add(product);
                    adapter1.setData(mProduct);
                    recyclerView.setAdapter(adapter1);
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "faild", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void oninVisible(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("managers").child(userId);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int roles = snapshot.child("level").getValue(Integer.class);
                if (roles != 1){
                    cardView.setVisibility(View.INVISIBLE);
                    fab_sp.setVisibility(View.INVISIBLE);
                }else {
                    fab_sp.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}