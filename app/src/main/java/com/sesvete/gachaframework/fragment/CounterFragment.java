package com.sesvete.gachaframework.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.sesvete.gachaframework.R;
import com.sesvete.gachaframework.helper.CounterHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CounterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

// bomo še pogruntali pol katere začetne argumente bomo dali notr

    //TODO: popup on +X
    //TODO: popup on confirm 5 star to confirm choice in input into database
    //TODO: če se ti da, custom alert dialog box, sicer lahko uporabiš privzetega

public class CounterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int counterProgressNumber;
    private TextView txtCounterProgressNumber;
    private TextView txtCounterSpentTillJackpot;
    private TextView txtCounterSpentTillJackpotCurrency;
    private TextView txtCounterSpentTillJackpotTotal;
    private TextView txtCounterSpentTillJackpotDescription;
    private TextView txtCounterSpentTillJackpotCurrencyDescription;
    private TextView txtCounterSpentTillJackpotTotalDescription;
    private MaterialButton btnCounterPlusOne;
    private MaterialButton btnCounterPlusX;
    private MaterialButton btnCounterPlusTen;
    private MaterialButton btnCounterConfirm;
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
        int softPity = 75;
        int wishValue = 160;
        String currencyType = getString(R.string.primogens);

        View view = inflater.inflate(R.layout.fragment_counter, container, false);
        txtCounterProgressNumber = view.findViewById(R.id.txtCounterProgressNumber);

        btnCounterPlusOne = view.findViewById(R.id.btnCounterPlusOne);
        btnCounterPlusTen = view.findViewById(R.id.btnCounterPlusTen);
        btnCounterPlusX = view.findViewById(R.id.btnCounterPlusX);
        btnCounterConfirm = view.findViewById(R.id.btnCounterConfirm);
        txtCounterSpentTillJackpot = view.findViewById((R.id.txtCounterSpentTillJackpot));
        txtCounterSpentTillJackpotDescription = view.findViewById(R.id.txtCounterSpentTillJackpotDescription);
        txtCounterSpentTillJackpotCurrency = view.findViewById(R.id.txtCounterSpentTillJackpotCurrency);
        txtCounterSpentTillJackpotCurrencyDescription = view.findViewById(R.id.txtCounterSpentTillJackpotCurrencyDescription);
        txtCounterSpentTillJackpotTotal = view.findViewById(R.id.txtCounterSpentTillJackpotTotal);
        txtCounterSpentTillJackpotTotalDescription = view.findViewById(R.id.txtCounterSpentTillJackpotTotalDescription);
        btnCounterPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterProgressNumber = counterHelper.counterPlusOne(txtCounterProgressNumber.getText().toString());
                txtCounterProgressNumber.setText(String.valueOf(counterProgressNumber));
                counterHelper.updateSoftPityTracker(getResources(), counterProgressNumber, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
            }
        });
        btnCounterPlusTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterProgressNumber = counterHelper.counterPlusTen(txtCounterProgressNumber.getText().toString());
                txtCounterProgressNumber.setText(String.valueOf(counterProgressNumber));
                counterHelper.updateSoftPityTracker(getResources(), counterProgressNumber, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
            }
        });
        btnCounterPlusX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater dialogInflater = getLayoutInflater();
                View dialogView = dialogInflater.inflate(R.layout.plus_x_dialog, null);
                builder.setView(dialogView);

                EditText inputXCounter = dialogView.findViewById(R.id.inputXCounter);
                MaterialButton btnXConfrim = dialogView.findViewById(R.id.btnXConfrim);
                MaterialButton btnXCancel = dialogView.findViewById(R.id.btnXCancel);

                AlertDialog dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                if (window != null) {
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rounded_corners));
                    lp.copyFrom(window.getAttributes());
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    lp.width = (int) (displayMetrics.widthPixels * 0.8);
                    window.setAttributes(lp);
                }
                btnXConfrim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String inputString = inputXCounter.getText().toString();
                        if (inputString.isEmpty()){
                            Toast.makeText(getContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                        } else {
                            int numCustomWishes = Integer.parseInt(inputString);
                            if (numCustomWishes <= 0) {
                                Toast.makeText(getContext(), "Number of wishes must be greater than zero", Toast.LENGTH_SHORT).show();
                            } else {
                                counterProgressNumber = counterHelper.counterPlusX(txtCounterProgressNumber.getText().toString(), numCustomWishes);
                                txtCounterProgressNumber.setText(String.valueOf(counterProgressNumber));
                                counterHelper.updateSoftPityTracker(getResources(), counterProgressNumber, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
                                dialog.dismiss();
                            }
                        }
                    }
                });
                btnXCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /*
                EditText inputCounter = new EditText(getContext());
                inputCounter.setInputType(InputType.TYPE_CLASS_NUMBER);
                inputCounter.setHint("Enter number of wishes");
                builder.setView(inputCounter);
                builder.setTitle("Custom Number");

                builder.setPositiveButton("CONFIRM", (dialog, which) -> {
                    String inputString = inputCounter.getText().toString();
                    if (inputString.isEmpty()){
                        Toast.makeText(getContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    } else {
                        int numCustomWishes = Integer.parseInt(inputString);
                        if (numCustomWishes <= 0) {
                            Toast.makeText(getContext(), "Number of wishes must be greater than zero", Toast.LENGTH_SHORT).show();
                        } else {
                            counterProgressNumber = counterHelper.counterPlusX(txtCounterProgressNumber.getText().toString(), numCustomWishes);
                            txtCounterProgressNumber.setText(String.valueOf(counterProgressNumber));
                            counterHelper.updateSoftPityTracker(getResources(), counterProgressNumber, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
                        }
                    }
                });
                builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.cancel());
                AlertDialog dialog = builder.create();
                dialog.show();

                 */
            }
        });
        btnCounterConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.plus_x_dialog, null); // dialog_custom_input.xml
                builder.setView(dialogView);
                EditText inputCounter = dialogView.findViewById(R.id.inputXCounter);
                AlertDialog dialog = builder.create();
                dialog.show(); // Important: Call show() before getting the window

                Window window = dialog.getWindow();
                if (window != null) {
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(window.getAttributes());

                    // Option 1: Match parent width (but constrain height if needed)
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    // lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // If you need to constrain height
                    // Option 2: Set a specific width as a fraction of screen width
                    // DisplayMetrics displayMetrics = new DisplayMetrics();
                    // getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    // lp.width = (int) (displayMetrics.widthPixels * 0.8); // 80% of screen width
                    window.setAttributes(lp);
                }
                // za zdaj se bo le posodobilo history
                // sicer pa se bo to shrai+nilo v podatkovno bazo

                 */
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