package com.example.ichigo.Gui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Listadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<String> items;
    ArrayList<Uri> itemspaths;
    ArrayList<Integer> progresses;

    public Listadapter(Context context, ArrayList<String> items, ArrayList<Uri> paths, ArrayList<Integer> progresses)
    {
        this.context = context;
        this.items = items;
        this.itemspaths =paths;
        this.progresses = progresses;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.row_layout,parent,false);
        Item item = new Item(row);
        return item;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            ((Item)holder).textView.setText(items.get(position));
            ((Item) holder).rowlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ((Item) holder).textView.getText(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,Torrent_Activity.class);
                    intent.putExtra("path",itemspaths.get(position).toString());
                    intent.putExtra("name",items.get(position));
                    context.startActivity(intent);

                }
            });
            ((Item)holder).progressBar.setProgress(progresses.get(position));
            ((Item) holder).button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((Item) holder).togglebutton) {
                        ((Item) holder).button.setImageResource(R.drawable.ic_pause_icon);
                        ((Item)holder).togglebutton = !((Item)holder).togglebutton;
                        ((Item)holder).download_state.setText("Downloading");
                    }
                    else
                    {
                        ((Item) holder).button.setImageResource(R.drawable.ic_play_icon);
                        ((Item)holder).togglebutton = !((Item)holder).togglebutton;
                        ((Item)holder).download_state.setText("paused");
                    }
                }
            });
        }

    @Override
    public int getItemCount() {
        return itemspaths.size();
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView textView;
        TextView download_state;
        ImageButton button;
        ProgressBar progressBar;
        RelativeLayout rowlayout;
        boolean togglebutton;
        public Item(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.nametext);
            download_state = (TextView) itemView.findViewById(R.id.downloading_state);
            button = (ImageButton) itemView.findViewById(R.id.downloadbutton);
            progressBar = (ProgressBar) itemView.findViewById(R.id.torrentprogressbar);
           rowlayout = (RelativeLayout) itemView.findViewById(R.id.rowlayout);}
    }
}
