package com.sesvete.gachaframework.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sesvete.gachaframework.R;
import com.sesvete.gachaframework.model.PulledUnit;

import java.util.ArrayList;


public class HistoryRecViewAdapter extends RecyclerView.Adapter<HistoryRecViewAdapter.ViewHolder> {

    private ArrayList<PulledUnit> pulledUnits = new ArrayList<>();
    private Context context;

    public HistoryRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtHistoryNumPulls.setText(String.valueOf(pulledUnits.get(position).getNumOfPulls()));
        holder.txtHistoryUnitName.setText(pulledUnits.get(position).getUnitName());
        holder.txtHistoryDateName.setText(pulledUnits.get(position).getDate());

        if (pulledUnits.get(position).isFromBanner()){
            holder.txtHistoryBannerName.setText("Y");
        } else {
            holder.txtHistoryBannerName.setText("N");
        }
    }

    @Override
    public int getItemCount() {
        return pulledUnits.size();
    }

    public void setPulledUnits(ArrayList<PulledUnit> pulledUnits) {
        this.pulledUnits = pulledUnits;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtHistoryNumPulls;
        private TextView txtHistoryUnitName;
        private TextView txtHistoryBannerName;
        private TextView txtHistoryDateName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHistoryNumPulls = itemView.findViewById(R.id.txtHistoryNumPulls);
            txtHistoryUnitName = itemView.findViewById(R.id.txtHistoryUnitName);
            txtHistoryBannerName = itemView.findViewById(R.id.txtHistoryBannerName);
            txtHistoryDateName = itemView.findViewById(R.id.txtHistoryDateName);
        }
    }


}
