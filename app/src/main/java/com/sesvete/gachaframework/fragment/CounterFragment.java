package com.sesvete.gachaframework.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sesvete.gachaframework.R;
import com.sesvete.gachaframework.helper.CounterHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CounterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

// bomo še pogruntali pol katere začetne argumente bomo dali notri

public class CounterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int counterProgressNumber = 0;
    private TextView txtCounterProgressNumber;
    private MaterialButton btnCounterPlusOne;
    private MaterialButton btnCounterPlusTen;
    private CounterHelper counterHelper;

    public CounterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CounterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CounterFragment newInstance(String param1, String param2) {
        CounterFragment fragment = new CounterFragment();
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
        counterHelper = new CounterHelper();
        View view = inflater.inflate(R.layout.fragment_counter, container, false);
        TextView txtCounterProgressNumber;
        MaterialButton btnCounterPlusOne;
        txtCounterProgressNumber = view.findViewById(R.id.txtCounterProgressNumber);

        btnCounterPlusOne = view.findViewById(R.id.btnCounterPlusOne);
        btnCounterPlusTen = view.findViewById(R.id.btnCounterPlusTen);
        btnCounterPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount = counterHelper.counterPlusOne(txtCounterProgressNumber.getText().toString());
                txtCounterProgressNumber.setText(String.valueOf(newCount));
            }
        });
        btnCounterPlusTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount = counterHelper.counterPlusTen(txtCounterProgressNumber.getText().toString());
                txtCounterProgressNumber.setText(String.valueOf(newCount));
            }
        });

        return view;
    }

    //primer če hočeš dat direkt v texview
    /*
    private void counterPlusOne(TextView txtCounter){
        String stringCounter = txtCounter.getText().toString();
        int numCounter = Integer.parseInt(stringCounter);
        numCounter++;
        txtCounter.setText(String.valueOf(numCounter));
    }

    private void counterPlusTen(TextView txtCounter){
        String stringCounter = txtCounter.getText().toString();
        int numCounter = Integer.parseInt(stringCounter);
        numCounter = numCounter + 10;
        txtCounter.setText(String.valueOf(numCounter));
    }
     */
}