package com.ashish.workindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.ashish.workindia.Fragments.GridFragment;
import com.ashish.workindia.Fragments.MainFragment;
import com.ashish.workindia.Model.Items;
import com.ashish.workindia.Model.ResponseModel;
import com.ashish.workindia.Network.ApiClient;
import com.ashish.workindia.Network.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Created by Ashish Kr Yadav

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    String TAG=MainActivity.class.getSimpleName();

    BottomNavigationView bottomNavigationView;
    Fragment fragment = null;
    ProgressBar progressBar;
    public List<Items> itemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callApi();

        bottomNavigationView=findViewById(R.id.navigationView);
        progressBar=findViewById(R.id.pbar);


        bottomNavigationView.setOnItemSelectedListener(this);

        itemsList =new ArrayList<>();
        fragment=new MainFragment();




    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.screen_one:
                fragment = new MainFragment();
                break;

            case R.id.screen_two:
                fragment = new GridFragment();
                break;
        }

        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        Log.d(TAG,"LOAD_FRAG_CALLED "+fragment);
        Bundle bundle=new Bundle();
        if (itemsList!=null){

            bundle.putSerializable("data", (Serializable) itemsList);
        }
        //switching fragment
        if (fragment != null) {
            fragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    void callApi(){
        final ApiInterface requestInterface = ApiClient.getClient();
        Call<ResponseModel> call = requestInterface.getResponse();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if(response.body()!= null){

                    if (response.code()==200){

                        itemsList =response.body().getData().getItems();
                        loadFragment(fragment);
                        progressBar.setVisibility(View.GONE);
                    }else {

                        Toast.makeText(MainActivity.this,""+response.body().getError(),Toast.LENGTH_LONG).show();
                    }

                }

            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });

    }


}