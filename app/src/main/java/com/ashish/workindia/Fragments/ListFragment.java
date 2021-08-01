package com.ashish.workindia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ashish.workindia.Adapters.RecyclerViewAdapter;
import com.ashish.workindia.Model.Items;
import com.ashish.workindia.Model.ResponseModel;
import com.ashish.workindia.Network.ApiClient;
import com.ashish.workindia.Network.ApiInterface;
import com.ashish.workindia.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {

    String TAG=ListFragment.class.getSimpleName();

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<Items> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"LIST_FRAG_CALLED");

        list=new ArrayList<>();
        list=(ArrayList<Items>)getArguments().getSerializable("data");

        Log.d(TAG,"Data "+list.size()+" , "+list);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=getView().findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);

//        adapter=new RecyclerViewAdapter(list,getContext());
//        adapter.setVIEW_TYPE(0);
//        recyclerView.setAdapter(adapter);

    }
}