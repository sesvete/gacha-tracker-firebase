package com.sesvete.gachatrackerfirebase.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sesvete.gachatrackerfirebase.R;
import com.sesvete.gachatrackerfirebase.helper.HistoryRecViewAdapter;
import com.sesvete.gachatrackerfirebase.model.PulledUnit;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewHistory;
    private TextView txtHistoryBannerDescription;
    private String bannerType;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerViewHistory = view.findViewById(R.id.recycler_view_history);
        txtHistoryBannerDescription = view.findViewById(R.id.txt_history_banner_description);

        bannerType = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("banner", "limited");

        if (bannerType.equals("standard") || bannerType.equals("bangboo")){
            txtHistoryBannerDescription.setVisibility(View.GONE);
        }


        // za zdaj bo provizorično tako, sicer se bo to pobralo iz podatkovne baze
        ArrayList<PulledUnit> pulledUnits = new ArrayList<>();
        pulledUnits.add(new PulledUnit(67, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(27, "Mavuika", false, "2025-06-08"));
        pulledUnits.add(new PulledUnit(47, "Chie", true, "2024-02-01"));
        pulledUnits.add(new PulledUnit(65, "Arle", true, "2024-04-23"));
        pulledUnits.add(new PulledUnit(2, "Childe", false, "2025-03-18"));
        pulledUnits.add(new PulledUnit(12, "YEP", true, "2024-07-18"));
        pulledUnits.add(new PulledUnit(12, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(15, "Furina", false, "2024-06-08"));
        pulledUnits.add(new PulledUnit(23, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(12, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(54, "Furina", false, "2024-06-08"));
        pulledUnits.add(new PulledUnit(43, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(67, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(27, "Mavuika", false, "2025-06-08"));
        pulledUnits.add(new PulledUnit(47, "Chie", true, "2024-02-01"));
        pulledUnits.add(new PulledUnit(65, "Arle", true, "2024-04-23"));
        pulledUnits.add(new PulledUnit(2, "Childe", false, "2025-03-18"));
        pulledUnits.add(new PulledUnit(12, "YEP", true, "2024-07-18"));
        pulledUnits.add(new PulledUnit(12, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(15, "Furina", false, "2024-06-08"));
        pulledUnits.add(new PulledUnit(67, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(27, "Mavuika", false, "2025-06-08"));
        pulledUnits.add(new PulledUnit(47, "Chie", true, "2024-02-01"));
        pulledUnits.add(new PulledUnit(65, "Arle", true, "2024-04-23"));
        pulledUnits.add(new PulledUnit(2, "Childe", false, "2025-03-18"));
        pulledUnits.add(new PulledUnit(12, "YEP", true, "2024-07-18"));
        pulledUnits.add(new PulledUnit(12, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(15, "Furina", false, "2024-06-08"));
        pulledUnits.add(new PulledUnit(23, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(12, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(54, "Furina", false, "2024-06-08"));
        pulledUnits.add(new PulledUnit(43, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(67, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(27, "Mavuika", false, "2025-06-08"));
        pulledUnits.add(new PulledUnit(47, "Chie", true, "2024-02-01"));
        pulledUnits.add(new PulledUnit(65, "Arle", true, "2024-04-23"));
        pulledUnits.add(new PulledUnit(2, "Childe", false, "2025-03-18"));
        pulledUnits.add(new PulledUnit(12, "YEP", true, "2024-07-18"));
        pulledUnits.add(new PulledUnit(12, "Furina", true, "2024-06-08"));
        pulledUnits.add(new PulledUnit(15, "Furina", false, "2024-06-08"));

        HistoryRecViewAdapter adapter = new HistoryRecViewAdapter(getContext());
        adapter.setPulledUnits(pulledUnits);

        recyclerViewHistory.setAdapter(adapter);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}