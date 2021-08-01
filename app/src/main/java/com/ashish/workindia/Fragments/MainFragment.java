package com.ashish.workindia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ashish.workindia.Adapters.RecyclerViewAdapter;
import com.ashish.workindia.Model.Items;
import com.ashish.workindia.R;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements RecyclerViewAdapter.OnSearchResultList {

    String TAG=ListFragment.class.getSimpleName();

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<Items> list;
    SearchView searchView;
    LinearLayout errorLayout;
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
        searchView=getView().findViewById(R.id.search_view);
        errorLayout=getView().findViewById(R.id.errorViewLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);

        adapter=new RecyclerViewAdapter(list,getContext(),this);
        adapter.setVIEW_TYPE(0);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onResultList(List<Items> itemsList) {

        if (itemsList.size()==0){

            recyclerView.setVisibility(View.GONE);
            errorLayout.setVisibility(View.VISIBLE);

        }else{

            recyclerView.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.GONE);

        }
    }
}