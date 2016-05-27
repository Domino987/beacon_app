package de.rwth_aachen.engel.beaconsforproduction;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Domino on 27.05.2016.
 */
public class beaconAdapter extends RecyclerView.Adapter<beaconAdapter.ViewHolder> {
    private List<machine> machines;
    private int itemLayout;
    public beaconAdapter(List<machine> machines,int itemLayout){
        this.machines=machines;
        this.itemLayout=itemLayout;
    }
    @Override
    public beaconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(beaconAdapter.ViewHolder holder, int position) {
        machine machineItem = machines.get(position);
        holder.name.setText(machineItem.getName());
        holder.itemView.setTag(machineItem);
    }

    @Override
    public int getItemCount() {
        return machines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
