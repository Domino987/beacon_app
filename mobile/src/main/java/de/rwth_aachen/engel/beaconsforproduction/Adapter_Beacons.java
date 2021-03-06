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
public class Adapter_Beacons extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Beacon> inReach,other;
    private final OnItemClickListener listener;
    private String header1String,header2String;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    public Adapter_Beacons(List<Beacon> inReach,List<Beacon> other, OnItemClickListener listener,String header1String,String header2String){
        this.header1String = header1String;
        this.header2String = header2String;
        this.inReach=inReach;
        this.other=other;
        this.listener = listener;
    }
    public void addItem(Beacon beacon){
        inReach.add(beacon);
        notifyItemInserted(inReach.indexOf(beacon));
        other.add(beacon);
        notifyItemInserted(other.indexOf(beacon));
    }
    public void removeItem(Beacon beacon){
        inReach.remove(beacon);
        other.remove(beacon);
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(Beacon item);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_header, parent, false);
            return  new VHHeader(v);
        }
        else if(viewType == TYPE_ITEM)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
            return new VHItem(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof VHHeader)
        {
            VHHeader VHheader = (VHHeader)holder;
            if(position == 0) {
                VHheader.txtTitle.setText(header1String);
            }
            if(position == inReach.size()+1){
                VHheader.txtTitle.setText(header2String);
            }
        }
        else if(holder instanceof VHItem)
        {
            VHItem VHitem = (VHItem)holder;
            List<Beacon> selected;
            int margin;
            if(position<inReach.size()+1){
                selected = inReach;
                margin = position-1;
            }
            else{
                selected = other;
                margin = position-(inReach.size()+2);
            }
            if (!selected.isEmpty() && !selected.get(margin).isOrder()) {
                VHitem.bindMachine(selected.get(margin), listener);
            }
            else if(!selected.isEmpty() && selected.get(margin).isOrder()){
                VHitem.bindOrder(selected.get(margin), listener);
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }
    private boolean isPositionHeader(int position)
    {
        return position == 0 || position == inReach.size() + 1;
    }
    @Override
    public int getItemCount() {
        return inReach.size()+other.size()+2;
    }
    public class VHItem extends RecyclerView.ViewHolder {
        public TextView name;
        public VHItem(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.listViewItemText);
        }
        public void bindMachine(final Beacon item, final OnItemClickListener listener) {
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
        public void bindOrder(final Beacon item, final OnItemClickListener listener) {
            if(item.getName()!=null) {
                name.setText(item.getName());
                itemView.setTag(item);
                itemView.setBackgroundColor(Color.parseColor("#FFA500"));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(listener != null)
                        listener.onItemClick(item);
                }
            });
        }
    }
    class VHHeader extends RecyclerView.ViewHolder{
        TextView txtTitle;
        public VHHeader(View itemView) {
            super(itemView);
            this.txtTitle = (TextView)itemView.findViewById(R.id.listview_header_title);
        }
    }
}
