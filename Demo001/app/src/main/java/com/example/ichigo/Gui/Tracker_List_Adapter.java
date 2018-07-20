package com.example.ichigo.Gui;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by ichigo on 6/23/2018.
 */

public class Tracker_List_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<String> items;
    public Tracker_List_Adapter(Context context, Set<Uri> items)
    {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tracker_row_layout,parent,false);
        Item item = new Item(view);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class Item extends RecyclerView.ViewHolder{
        TextView tracker_name;
        TextView tracker_state;
        public Item(View itemView) {
            super(itemView);
            tracker_name = (TextView) itemView.findViewById(R.id.tracker_name);
            tracker_state = (TextView) itemView.findViewById(R.id.tracker_state);
        }
    }

}
