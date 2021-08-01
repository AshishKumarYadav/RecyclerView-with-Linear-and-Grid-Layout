package com.ashish.workindia.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ashish.workindia.Model.Items;
import com.ashish.workindia.R;
import java.util.ArrayList;
import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

    String TAG=RecyclerViewAdapter.class.getSimpleName();
    List<Items> list;
    List<Items> itemFilteredList;
    Context context;
    public static final int ITEM_TYPE_TITLE_LIST = 0;
    public static final int ITEM_TYPE_GRID = 1;
    private int VIEW_TYPE = 0;
    public OnSearchResultList listener;

    public interface OnSearchResultList {
        public void onResultList(List<Items> itemsList);
    }
    public RecyclerViewAdapter(List<Items> list, Context context,OnSearchResultList listener) {
        this.list=list;
        this.context=context;
        this.itemFilteredList =list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG,"view "+parent+" , "+viewType);
        View view = null;
        switch (VIEW_TYPE) {
            case ITEM_TYPE_TITLE_LIST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_items, parent, false);
                break;
            case ITEM_TYPE_GRID:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view, parent, false);
        }
      //  View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.item_name.setText(itemFilteredList.get(position).getName());


        if (VIEW_TYPE==0){
            holder.item_extras.setText(itemFilteredList.get(position).getExtra());
            holder.item_price.setText("MRP : "+itemFilteredList.get(position).getPrice()+".0");
        }else if (VIEW_TYPE==1){

            holder.item_price.setText(""+itemFilteredList.get(position).getPrice()+".0");
        }

    }
    public void setVIEW_TYPE(int viewType) {
        VIEW_TYPE = viewType;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return itemFilteredList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView item_name,item_price,item_extras;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            if (VIEW_TYPE==0){

                item_extras=itemView.findViewById(R.id.extras);
            }

            item_name=itemView.findViewById(R.id.item_name);
            item_price=itemView.findViewById(R.id.mrp);


            imageView=itemView.findViewById(R.id.image_view);
            imageView.setClipToOutline(true);
            imageView.setAdjustViewBounds(true);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().trim().replace(" ","");
                if (charString.isEmpty()) {
                    itemFilteredList = list;
                } else {
                    List<Items> filteredList = new ArrayList<>();
                    for (Items row : list) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().trim().replace(" ","").contains(charString.toLowerCase())) {
                            filteredList.add(row);

                        }
                    }

                    itemFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = itemFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                itemFilteredList = (ArrayList<Items>) filterResults.values;

                if(itemFilteredList.size() == 0)
                {
                    listener.onResultList(itemFilteredList);
                }
                else
                {
                    listener.onResultList(itemFilteredList);
                }
                notifyDataSetChanged();
            }
        };
    }

}
