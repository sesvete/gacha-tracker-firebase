package com.sesvete.gachatrackerfirebase.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sesvete.gachatrackerfirebase.R;
import com.sesvete.gachatrackerfirebase.helper.CounterHelper;
import com.sesvete.gachatrackerfirebase.helper.DialogHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

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
    private TextView txtCounterHistoryFeaturedUnitDescription;
    private TextView txtCounterProgressGuaranteedDescription;
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
    private String game;
    private String bannerType;
    private int softPity;
    private int wishValue;
    private String currencyType;
    FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String uid;

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

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();

        txtCounterProgressNumber = view.findViewById(R.id.txt_counter_progress_number);
        txtCounterHistoryNumber = view.findViewById(R.id.txt_counter_history_number);
        txtCounterHistoryNumber = view.findViewById(R.id.txt_counter_history_number);
        txtCounterHistoryUnit = view.findViewById(R.id.txt_counter_history_unit);
        cardCounterProgress = view.findViewById(R.id.card_counter_progress);
        imgCounterProgressGuaranteedDescription = view.findViewById(R.id.img_counter_progress_guaranteed_description);
        imgCounterHistoryFeaturedUnitStatus = view.findViewById(R.id.img_counter_history_featured_unit_status);

        btnCounterPlusOne = view.findViewById(R.id.btn_counter_plus_one);
        btnCounterPlusTen = view.findViewById(R.id.btn_counter_plus_ten);
        btnCounterPlusX = view.findViewById(R.id.btn_counter_plus_x);
        btnCounterConfirm = view.findViewById(R.id.btn_counter_confirm);
        txtCounterSpentTillJackpot = view.findViewById((R.id.txt_counter_spent_till_jackpot));
        txtCounterSpentTillJackpotDescription = view.findViewById(R.id.txt_counter_spent_till_jackpot_description);
        txtCounterSpentTillJackpotCurrency = view.findViewById(R.id.txt_counter_spent_till_jackpot_currency);
        txtCounterSpentTillJackpotCurrencyDescription = view.findViewById(R.id.txt_counter_spent_till_jackpot_currency_description);
        txtCounterSpentTillJackpotTotal = view.findViewById(R.id.txt_counter_spent_till_jackpot_total);
        txtCounterSpentTillJackpotTotalDescription = view.findViewById(R.id.txt_counter_spent_till_jackpot_total_description);
        txtCounterHistoryFeaturedUnitDescription = view.findViewById(R.id.txt_counter_history_featured_unit_description);
        txtCounterProgressGuaranteedDescription = view.findViewById(R.id.txt_counter_progress_guaranteed_description);

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
        game = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("game", "genshin_impact");
        bannerType = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("banner", "limited");
        if (bannerType.equals("standard") || bannerType.equals("bangboo")){
            txtCounterHistoryFeaturedUnitDescription.setVisibility(View.GONE);
            txtCounterProgressGuaranteedDescription.setVisibility(View.GONE);
            imgCounterHistoryFeaturedUnitStatus.setVisibility(View.GONE);
            imgCounterProgressGuaranteedDescription.setVisibility(View.GONE);
        }

        softPity = adjustSoftPity(game, bannerType);
        wishValue = adjustWishValue(game);
        currencyType = adjustCurrencyString(game);

        CounterHelper.initialSetup(txtCounterProgressNumber, imgCounterProgressGuaranteedDescription, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotTotal, softPity, wishValue, uid, game, bannerType);

        initialTextviewAdjust(game, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotalDescription);

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

                EditText inputXCounter = dialogView.findViewById(R.id.input_x_counter);
                MaterialButton btnXConfirm = dialogView.findViewById(R.id.btn_x_confirm);
                MaterialButton btnXCancel = dialogView.findViewById(R.id.btn_x_cancel);

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

                        EditText inputConfirmCounter = dialogView.findViewById(R.id.input_confirm_counter);
                        MaterialButton btnConfirmConfirm = dialogView.findViewById(R.id.btn_confirm_confirm);
                        MaterialButton btnConfirmCancel = dialogView.findViewById(R.id.btn_confirm_cancel);
                        RadioGroup rGrConfirm = dialogView.findViewById(R.id.r_gr_confirm);
                        if (guaranteed || bannerType.equals("standard") || bannerType.equals("bangboo")){
                            rGrConfirm.setVisibility(View.GONE);
                            featuredUnitStatus = true;
                            rGrConfirm.check(R.id.won_radio_button);
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

                RadioGroup rGrConfirm = dialogView.findViewById(R.id.r_gr_confirm);
                TextView txtConfirmTitle = dialogView.findViewById(R.id.txt_confirm_title);
                RadioButton wonRadioButton = dialogView.findViewById(R.id.won_radio_button);
                RadioButton lostRadioButton = dialogView.findViewById(R.id.lost_radio_button);

                txtConfirmTitle.setText(R.string.update_counter);
                wonRadioButton.setText("G");
                lostRadioButton.setText("N");

                EditText inputUpdateCounter = dialogView.findViewById(R.id.input_confirm_counter);
                MaterialButton btnConfirmConfirm = dialogView.findViewById(R.id.btn_confirm_confirm);
                MaterialButton btnConfirmCancel = dialogView.findViewById(R.id.btn_confirm_cancel);
                inputUpdateCounter.setHint(R.string.enter_counter_number_hint);
                inputUpdateCounter.setInputType(InputType.TYPE_CLASS_NUMBER);

                if (bannerType.equals("standard") || bannerType.equals("bangboo")){
                    rGrConfirm.setVisibility(View.GONE);
                    featuredUnitStatus = true;
                    rGrConfirm.check(R.id.won_radio_button);
                }
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
        if (checkedChoice == R.id.won_radio_button){
            featuredUnitStatus = true;
        } else if (checkedChoice == R.id.lost_radio_button){
            featuredUnitStatus = false;
        }
        rGrConfirm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.won_radio_button){
                    featuredUnitStatus = true;
                } else if (checkedId == R.id.lost_radio_button){
                    featuredUnitStatus = false;
                }
            }
        });
    }

    private void initialTextviewAdjust(String game, TextView currencyTillPity, TextView currencyTotal){
        switch (game){
            case "genshin_impact":
                currencyTillPity.setText(getString(R.string.primogens) + " " + getString(R.string.currency_till_soft_pity));
                currencyTotal.setText(getString(R.string.primogens) + " " + getString(R.string.total_currency_spent));
                break;
            case "honkai_star_rail":
                currencyTillPity.setText(getString(R.string.stellar_jades) + " " + getString(R.string.currency_till_soft_pity));
                currencyTotal.setText(getString(R.string.stellar_jades) + " " + getString(R.string.total_currency_spent));
                break;
            case "zenless_zone_zero":
                currencyTillPity.setText(getString(R.string.polychrome) + " " + getString(R.string.currency_till_soft_pity));
                currencyTotal.setText(getString(R.string.polychrome) + " " + getString(R.string.total_currency_spent));
                break;
            case "tribe_nine":
                currencyTillPity.setText(getString(R.string.enigma_entity) + " " + getString(R.string.currency_till_soft_pity));
                currencyTotal.setText(getString(R.string.enigma_entity) + " " + getString(R.string.total_currency_spent));
                break;
            default:
                currencyTillPity.setText(getString(R.string.primogens)+ " " + getString(R.string.currency_till_soft_pity));
                currencyTotal.setText(getString(R.string.primogens) + " " + getString(R.string.total_currency_spent));
                break;
        }
    }
    private int adjustSoftPity(String game, String bannerType){
        Set<String> weaponBanners = new HashSet<>();
        weaponBanners.add("weapon");
        weaponBanners.add("light_cone");
        weaponBanners.add("w_engine");
        weaponBanners.add("bangboo");

        int softPity;
        if (game.equals("tribe_nine")) {
            softPity = 80;
        } else if (weaponBanners.contains(bannerType)) {
            softPity = 65;
        } else {
            softPity = 75;
        }
        return softPity;
    }

    private int adjustWishValue(String game){
        int wishValue;
        if (game.equals("tribe_nine")){
            wishValue = 120;
        } else {
            wishValue = 160;
        }
        return wishValue;
    }

    private String adjustCurrencyString(String game){
        String currencyType;
        if (game.equals("genshin_impact")){
            currencyType = getString(R.string.primogens);
        } else if (game.equals("honkai_star_rail")) {
            currencyType = getString(R.string.stellar_jades);
        } else if (game.equals("zenless_zone_zero")) {
            currencyType = getString(R.string.polychrome);
        } else {
            currencyType = getString(R.string.enigma_entity);
        }
        return currencyType;
    }
}