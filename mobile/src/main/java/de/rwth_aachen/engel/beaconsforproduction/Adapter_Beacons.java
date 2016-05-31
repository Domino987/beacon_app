package de.rwth_aachen.engel.beaconsforproduction;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Domino on 27.05.2016.
 */
public class Adapter_Beacons extends RecyclerView.Adapter<Adapter_Beacons.ViewHolder> {
    private final List items;
    private final OnItemClickListener listener;
    public Adapter_Beacons(List items, OnItemClickListener listener){
        this.items=items;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(machine item);
        void onItemClick(order item);
    }
    @Override
    public Adapter_Beacons.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Adapter_Beacons.ViewHolder holder, int position) {
        if (!items.isEmpty()&&items.get(position) instanceof machine) {
            holder.bindMachine((machine)items.get(position), listener);
        }
        else if(!items.isEmpty()&&items.get(position) instanceof order){
            holder.bindOrder((order)items.get(position), listener);
        }


    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.listViewItemText);
        }
        public void bindMachine(final machine item, final OnItemClickListener listener) {
            if(item.getName()!=null) {
                name.setText(item.getName());
                itemView.setTag(item);
                itemView.setBackgroundColor(Color.parseColor("#551A8B"));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
        public void bindOrder(final order item, final OnItemClickListener listener) {
            if(item.getName()!=null) {
                name.setText(item.getName());
                itemView.setTag(item);
                itemView.setBackgroundColor(Color.parseColor("#FFA500"));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
