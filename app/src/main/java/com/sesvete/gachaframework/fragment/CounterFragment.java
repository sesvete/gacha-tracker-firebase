package com.sesvete.gachaframework.fragment;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.sesvete.gachaframework.R;
import com.sesvete.gachaframework.helper.CounterHelper;
import com.sesvete.gachaframework.helper.DialogHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CounterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

// bomo še pogruntali pol katere začetne argumente bomo dali notr

public class CounterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //od tle dalje so moje spremenljivke
    private TextView txtCounterProgressNumber;
    private TextView txtCounterSpentTillJackpot;
    private TextView txtCounterSpentTillJackpotCurrency;
    private TextView txtCounterSpentTillJackpotTotal;
    private TextView txtCounterSpentTillJackpotDescription;
    private TextView txtCounterSpentTillJackpotCurrencyDescription;
    private TextView txtCounterSpentTillJackpotTotalDescription;
    private TextView txtCounterHistoryNumber;
    private TextView txtCounterHistoryUnit;
    private MaterialButton btnCounterPlusOne;
    private MaterialButton btnCounterPlusX;
    private MaterialButton btnCounterPlusTen;
    private MaterialButton btnCounterConfirm;
    private CardView cardCounterProgress;
    private ImageView imgCounterProgressGuaranteedDescription;
    private ShapeableImageView imgCounterHistoryFeaturedUnitStatus;
    private boolean featuredUnitStatus;
    private Calendar calendar;
    private SimpleDateFormat dateFormatter;
    private String formatedDate;

    // to se bo še pobral iz podatkovne baze
    private boolean guaranteed;

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

        View view = inflater.inflate(R.layout.fragment_counter, container, false);

        int softPity = 75;
        int wishValue = 160;
        String currencyType = getString(R.string.primogens);

        txtCounterProgressNumber = view.findViewById(R.id.txtCounterProgressNumber);
        txtCounterHistoryNumber = view.findViewById(R.id.txtCounterHistoryNumber);
        txtCounterHistoryNumber = view.findViewById(R.id.txtCounterHistoryNumber);
        txtCounterHistoryUnit = view.findViewById(R.id.txtCounterHistoryUnit);
        cardCounterProgress = view.findViewById(R.id.cardCounterProgress);
        imgCounterProgressGuaranteedDescription = view.findViewById(R.id.imgCounterProgressGuaranteedDescription);
        imgCounterHistoryFeaturedUnitStatus = view.findViewById(R.id.imgCounterHistoryFeaturedUnitStatus);

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

        // začasno se preveri, če ima player guaranteed
        // TODO: to se bo preverlo iz podatkovne baze
        Drawable currentDrawable = imgCounterProgressGuaranteedDescription.getDrawable();
        Drawable checkmarkDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_checkmark_green);
        if (currentDrawable != null && checkmarkDrawable != null &&
                currentDrawable.getConstantState() != null && checkmarkDrawable.getConstantState() != null &&
                currentDrawable.getConstantState().equals(checkmarkDrawable.getConstantState())) {
            guaranteed = true;
        } else {
            guaranteed = false;
        }

        btnCounterPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CounterHelper.updateCounter(getResources(), txtCounterProgressNumber, 1, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
            }
        });
        btnCounterPlusTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CounterHelper.updateCounter(getResources(), txtCounterProgressNumber, 10, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
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
                MaterialButton btnXConfirm = dialogView.findViewById(R.id.btnXConfirm);
                MaterialButton btnXCancel = dialogView.findViewById(R.id.btnXCancel);

                AlertDialog dialog = builder.create();

                DialogHelper.buildAlertDialogWindowWithKeyboard(dialog, getContext(), inputXCounter, getActivity());

                btnXConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String inputString = inputXCounter.getText().toString();
                        if (inputString.isEmpty()){
                            Toast.makeText(getContext(), R.string.enter_a_number_error, Toast.LENGTH_SHORT).show();
                        } else {
                            try{
                                int numCustomWishes = Integer.parseInt(inputString);
                                if (numCustomWishes <= 0) {
                                    Toast.makeText(getContext(), R.string.num_wishes_grater_0_error, Toast.LENGTH_SHORT).show();
                                } else {
                                    CounterHelper.updateCounter(getResources(), txtCounterProgressNumber, numCustomWishes, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
                                    dialog.dismiss();
                                }
                            } catch (Exception e) {
                                Log.e("bntXConfirm", "An error occurred: " + e.getMessage(), e);
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
            }
        });
        btnCounterConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringCounterNumber = txtCounterProgressNumber.getText().toString();
                try {
                    int intCounterNumber = Integer.parseInt(stringCounterNumber);
                    if (intCounterNumber <= 0) {
                        Toast.makeText(getContext(), R.string.num_wishes_grater_0_error, Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        LayoutInflater dialogInflater = getLayoutInflater();
                        View dialogView = dialogInflater.inflate(R.layout.confirm_unit_dialog, null);
                        builder.setView(dialogView);

                        EditText inputConfirmCounter = dialogView.findViewById(R.id.inputConfirmCounter);
                        MaterialButton btnConfirmConfirm = dialogView.findViewById(R.id.btnConfirmConfirm);
                        MaterialButton btnConfirmCancel = dialogView.findViewById(R.id.btnConfirmCancel);
                        RadioGroup rGrConfirm = dialogView.findViewById(R.id.rGrConfirm);
                        if (guaranteed){
                            rGrConfirm.setVisibility(View.GONE);
                            featuredUnitStatus = true;
                            rGrConfirm.check(R.id.wonRadioButton);
                        }
                        AlertDialog dialog = builder.create();

                        DialogHelper.buildAlertDialogWindowWithKeyboard(dialog, getContext(), inputConfirmCounter, getActivity());

                        radioFunction(rGrConfirm);
                        btnConfirmConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String inputString = inputConfirmCounter.getText().toString();
                                inputString = inputString.toUpperCase();
                                if (inputString.isEmpty()){
                                    Toast.makeText(getContext(), R.string.enter_received_unit_error, Toast.LENGTH_SHORT).show();
                                }
                                else if (rGrConfirm.getCheckedRadioButtonId() == -1) {
                                    Toast.makeText(getContext(), R.string.select_radio_choice_error, Toast.LENGTH_SHORT).show();
                                } else {
                                    // Datum se bo shranil v obliki "yyyy-MM-dd" v podatkovno bazo
                                    calendar = Calendar.getInstance();
                                    dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                    formatedDate = dateFormatter.format(calendar.getTime());
                                    Toast.makeText(getContext(), formatedDate, Toast.LENGTH_SHORT).show();
                                    txtCounterHistoryNumber.setText(stringCounterNumber);
                                    txtCounterHistoryUnit.setText(inputString);
                                    txtCounterProgressNumber.setText(String.valueOf(0));
                                    CounterHelper.updateSoftPityTracker(getResources(), 0, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
                                    if (featuredUnitStatus){
                                        guaranteed = false;
                                        imgCounterProgressGuaranteedDescription.setImageResource(R.drawable.ic_block_red);
                                        imgCounterHistoryFeaturedUnitStatus.setImageResource(R.drawable.ic_checkmark_green);
                                    } else {
                                        guaranteed = true;
                                        imgCounterProgressGuaranteedDescription.setImageResource(R.drawable.ic_checkmark_green);
                                        imgCounterHistoryFeaturedUnitStatus.setImageResource(R.drawable.ic_block_red);
                                    }
                                    dialog.dismiss();
                                }
                            }
                        });
                        btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e("bntCounterConfirm", "An error occurred: " + e.getMessage(), e);
                }
            }
        });
        cardCounterProgress.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater dialogInflater = getLayoutInflater();
                View dialogView = dialogInflater.inflate(R.layout.confirm_unit_dialog, null);
                builder.setView(dialogView);

                RadioGroup rGrConfirm = dialogView.findViewById(R.id.rGrConfirm);
                TextView txtConfirmTitle = dialogView.findViewById(R.id.txtConfirmTitle);
                RadioButton wonRadioButton = dialogView.findViewById(R.id.wonRadioButton);
                RadioButton lostRadioButton = dialogView.findViewById(R.id.lostRadioButton);

                txtConfirmTitle.setText(R.string.update_counter);
                wonRadioButton.setText("G");
                lostRadioButton.setText("N");

                EditText inputUpdateCounter = dialogView.findViewById(R.id.inputConfirmCounter);
                MaterialButton btnConfirmConfirm = dialogView.findViewById(R.id.btnConfirmConfirm);
                MaterialButton btnConfirmCancel = dialogView.findViewById(R.id.btnConfirmCancel);
                inputUpdateCounter.setHint(R.string.enter_counter_number_hint);
                inputUpdateCounter.setInputType(InputType.TYPE_CLASS_NUMBER);

                AlertDialog dialog = builder.create();

                DialogHelper.buildAlertDialogWindowWithKeyboard(dialog, getContext(), inputUpdateCounter, getActivity());

                radioFunction(rGrConfirm);

                btnConfirmConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String inputString = inputUpdateCounter.getText().toString();
                        if (inputString.isEmpty()){
                            Toast.makeText(getContext(), R.string.enter_a_number_error, Toast.LENGTH_SHORT).show();
                        } else if (rGrConfirm.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(getContext(), R.string.select_radio_choice_error, Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                int numCustomWishes = Integer.parseInt(inputString);
                                if (numCustomWishes < 0) {
                                    Toast.makeText(getContext(), R.string.num_wishes_at_least_0, Toast.LENGTH_SHORT).show();
                                } else {
                                    if (featuredUnitStatus) {
                                        guaranteed = true;
                                        imgCounterProgressGuaranteedDescription.setImageResource(R.drawable.ic_checkmark_green);
                                    } else {
                                        guaranteed = false;
                                        imgCounterProgressGuaranteedDescription.setImageResource(R.drawable.ic_block_red);
                                    }
                                    txtCounterProgressNumber.setText(String.valueOf(numCustomWishes));
                                    CounterHelper.updateSoftPityTracker(getResources(), numCustomWishes, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
                                    dialog.dismiss();
                                }
                            } catch (Exception e){
                                Log.e("bntCounterProgress", "An error occurred: " + e.getMessage(), e);
                            }
                        }
                    }
                });
                btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                return true;
            }
        });
        return view;
    }

    private void radioFunction(RadioGroup rGrConfirm){
        int checkedChoice = rGrConfirm.getCheckedRadioButtonId();
        if (checkedChoice == R.id.wonRadioButton){
            featuredUnitStatus = true;
        } else if (checkedChoice == R.id.lostRadioButton){
            featuredUnitStatus = false;
        }
        rGrConfirm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.wonRadioButton){
                    featuredUnitStatus = true;
                } else if (checkedId == R.id.lostRadioButton){
                    featuredUnitStatus = false;
                }
            }
        });
    }
}