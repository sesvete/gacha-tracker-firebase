package com.sesvete.gachatrackerfirebase.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sesvete.gachatrackerfirebase.R;
import com.sesvete.gachatrackerfirebase.helper.DatabaseHelper;
import com.sesvete.gachatrackerfirebase.helper.StatsHelper;
import com.sesvete.gachatrackerfirebase.helper.StatsRecViewAdapter;
import com.sesvete.gachatrackerfirebase.model.Statistic;

import java.util.ArrayList;
import java.util.List;


public class StatsFragment extends Fragment {

    private MaterialButton btnStatsPersonal;
    private MaterialButton btnStatsGlobal;
    private TextView txtStatsTitle;
    private RecyclerView recyclerViewStats;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String uid;

    private String game;
    private String bannerType;

    private ArrayList<Integer> pullsForFiveStar;
    private ArrayList<Statistic> statisticList;
    private StatsRecViewAdapter adapter;



    public StatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if (currentUser != null){
            uid = currentUser.getUid();
        }

        game = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("game", "genshin_impact");
        bannerType = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("banner", "limited");


        btnStatsGlobal = view.findViewById(R.id.btn_stats_global);
        btnStatsPersonal = view.findViewById(R.id.btn_stats_personal);
        txtStatsTitle = view.findViewById(R.id.txt_stats_title);
        recyclerViewStats = view.findViewById(R.id.recycler_view_stats);

        // TODO: naredil se bo ločen helper, ki bo zasluže za sestavo lista
        // ne pozabi najprej clearat list ob kliku na gumb

        //initial Load Personal stats
        onPersonalPress(txtStatsTitle, btnStatsPersonal, btnStatsGlobal);

        statisticList = new ArrayList<>();
        adapter = new StatsRecViewAdapter(getContext());
        recyclerViewStats.setAdapter(adapter);
        getPersonalStats(statisticList, uid, game, bannerType);


        recyclerViewStats.setLayoutManager(new LinearLayoutManager(getContext()));
        btnStatsPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPersonalPress(txtStatsTitle, btnStatsPersonal, btnStatsGlobal);
                getPersonalStats(statisticList, uid, game, bannerType);
            }
        });
        btnStatsGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGlobalPress(txtStatsTitle, btnStatsPersonal, btnStatsGlobal);
                statisticList.clear();
                statisticList.add(new Statistic("Total of pulled units", 3));
                statisticList.add(new Statistic("Total of pulled units", 3));
                adapter.setStatisticList(statisticList);
            }
        });
        return view;
    }
    private void onPersonalPress(TextView txtStatsTitle, MaterialButton btnStatsPersonal, MaterialButton btnStatsGlobal){
        txtStatsTitle.setText(R.string.personal_stats);
        btnStatsPersonal.setEnabled(false);
        btnStatsGlobal.setEnabled(true);
    }

    private void onGlobalPress(TextView txtStatsTitle, MaterialButton btnStatsPersonal, MaterialButton btnStatsGlobal){
        txtStatsTitle.setText(R.string.global_stats);
        btnStatsPersonal.setEnabled(true);
        btnStatsGlobal.setEnabled(false);
    }

    private void getPersonalStats(ArrayList<Statistic> statisticList, String uid, String game, String bannerType){
        DatabaseHelper databaseHelper = new DatabaseHelper();

        databaseHelper.getPersonalNumPullsList(uid, game, bannerType, new DatabaseHelper.OnPersonalNumOfPullsListRetrievedCallback() {
            @Override
            public void onNumOfPullsListRetrieved(ArrayList<Integer> numOfPullsList) {
                int[] pullsFor5Star = {78, 54, 77, 80, 56, 43, 76, 80, 81, 56, 66, 45, 23, 86, 24, 77, 78, 76, 74, 2};
                boolean[] won5050 = {true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, true};
                pullsForFiveStar = numOfPullsList;
                Log.d("pulls for 5 star", pullsForFiveStar.toString());
                int intNumWonFiftyFifty = StatsHelper.numWonFiftyFifty(won5050);
                int intNumLostFiftyFifty = StatsHelper.numLostFiftyFifty(won5050);
                double doublePercentageFiftyFifty = StatsHelper.percentageFiftyFifty(intNumWonFiftyFifty, intNumLostFiftyFifty);
                double doubleAvgNumPulls = StatsHelper.avgNumPulls(pullsForFiveStar);
                int intTotalNumPulls = StatsHelper.totalNumPulls(pullsFor5Star);
                int currencyValue;


                if (game.equals("tribe_nine")){
                    currencyValue = 120;
                }
                else {
                    currencyValue = 160;
                }
                statisticList.clear();
                if (!bannerType.equals("standard") && !bannerType.equals("bangboo")){
                    statisticList.add(new Statistic(getString(R.string.percentage_fifty_fifty), doublePercentageFiftyFifty));
                    statisticList.add(new Statistic(getString(R.string.total_won_fifty_fifty), intNumWonFiftyFifty));
                    statisticList.add(new Statistic(getString(R.string.total_lost_fifty_fifty), intNumLostFiftyFifty));
                }
                statisticList.add(new Statistic(getString(R.string.avg_for_five_star), doubleAvgNumPulls));
                statisticList.add(new Statistic(getString(R.string.total_num_pulls), intTotalNumPulls));
                statisticList.add(new Statistic(getString(R.string.avg_currency_five_star), doubleAvgNumPulls * currencyValue));
                statisticList.add(new Statistic(getString(R.string.avg_currency_five_star), intTotalNumPulls * currencyValue));
                Log.d("statistics list", statisticList.toString());

                adapter.setStatisticList(statisticList);

            }
        });
    }
}