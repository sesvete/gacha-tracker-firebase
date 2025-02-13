package com.sesvete.gachaframework.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sesvete.gachaframework.R;
import com.sesvete.gachaframework.helper.StatsRecViewAdapter;
import com.sesvete.gachaframework.model.Statistic;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MaterialButton btnStatsPersonal;
    private MaterialButton btnStatsGlobal;
    private TextView txtStatsTitle;
    private RecyclerView recyclerViewStats;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        btnStatsGlobal = view.findViewById(R.id.btnStatsGlobal);
        btnStatsPersonal = view.findViewById(R.id.btnStatsPersonal);
        txtStatsTitle = view.findViewById(R.id.txtStatsTitle);
        recyclerViewStats = view.findViewById(R.id.recyclerViewStats);

        // TODO: naredil se bo ločen helper, ki bo zasluže za sestavo lista
        // ne pozabi najprej clearat list ob kliku na gumb

        //initial Load Personal stats
        btnStatsPersonal.setEnabled(false);
        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));
        statisticList.add(new Statistic("Total of pulled units", 2));

        StatsRecViewAdapter adapter = new StatsRecViewAdapter(getContext());
        adapter.setStatisticList(statisticList);
        recyclerViewStats.setAdapter(adapter);
        recyclerViewStats.setLayoutManager(new LinearLayoutManager(getContext()));
        btnStatsGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // za zdaj samo test
                statisticList.clear();
                statisticList.add(new Statistic("Total of pulled units", 3));
                statisticList.add(new Statistic("Total of pulled units", 3));
                adapter.setStatisticList(statisticList);
                //recyclerViewStats.setAdapter(adapter);
            }
        });
        return view;
    }
}