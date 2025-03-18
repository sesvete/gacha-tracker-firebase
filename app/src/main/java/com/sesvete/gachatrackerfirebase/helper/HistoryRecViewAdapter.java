package com.sesvete.gachatrackerfirebase.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.sesvete.gachatrackerfirebase.R;
import com.sesvete.gachatrackerfirebase.model.PulledUnit;

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

        String bannerType = PreferenceManager.getDefaultSharedPreferences(context).getString("banner", "limited");
        if (bannerType.equals("standard") || bannerType.equals("bangboo")){
            holder.imgHistoryBannerName.setVisibility(View.GONE);
        } else if (pulledUnits.get(position).isFromBanner()) {
            holder.imgHistoryBannerName.setImageResource(R.drawable.ic_checkmark_green);
        } else {
            holder.imgHistoryBannerName.setImageResource(R.drawable.ic_block_red);
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
        private ShapeableImageView imgHistoryBannerName;
        private TextView txtHistoryDateName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHistoryNumPulls = itemView.findViewById(R.id.txt_history_num_pulls);
            txtHistoryUnitName = itemView.findViewById(R.id.txt_history_unit_name);
            imgHistoryBannerName = itemView.findViewById(R.id.img_history_banner_name);
            txtHistoryDateName = itemView.findViewById(R.id.txt_history_date_name);
        }
    }


}
