package com.sesvete.gachaframework.helper;

// TODO: ko se bo pobralo podatke iz baze se bodo tudi primerno updatala polja


import android.content.res.Resources;
import android.util.Log;
import android.widget.TextView;

import com.sesvete.gachaframework.R;

public class CounterHelper {

    public static void updateCounter(Resources resources, TextView txtCounterProgressNumber, int amountToAdd, int softPity, int wishValue, String currencyType, TextView txtCounterSpentTillJackpot, TextView txtCounterSpentTillJackpotDescription, TextView txtCounterSpentTillJackpotCurrency, TextView txtCounterSpentTillJackpotCurrencyDescription, TextView txtCounterSpentTillJackpotTotal, TextView txtCounterSpentTillJackpotTotalDescription){
        String stringCounterProgressNumber = txtCounterProgressNumber.getText().toString();
        try {
            int intCounterProgressNumber = Integer.parseInt(stringCounterProgressNumber);
            int counterProgressNumber = counterPlusX(intCounterProgressNumber, amountToAdd);
            txtCounterProgressNumber.setText(String.valueOf(counterProgressNumber));
            updateSoftPityTracker(resources, counterProgressNumber, softPity, wishValue, currencyType, txtCounterSpentTillJackpot, txtCounterSpentTillJackpotDescription, txtCounterSpentTillJackpotCurrency, txtCounterSpentTillJackpotCurrencyDescription, txtCounterSpentTillJackpotTotal, txtCounterSpentTillJackpotTotalDescription);
        }catch (Exception e){
            Log.e("updateCounter", "An error occurred: " + e.getMessage(), e);
        }
    }

    private static int counterPlusX(int currentCount, int amountToAdd){
        return currentCount + amountToAdd;
    }

    public static void updateSoftPityTracker(Resources resources, int counter, int softPity, int wishValue, String currencyType, TextView txtNumToSoftPity, TextView txtToSoftPity, TextView txtNumCurrencyToSoftPity, TextView txtCurrencyToSoftPity, TextView txtNumTotalSpent, TextView txtTotalSpent){
        String stringNumToSoftPity;
        String stringToSoftPity;
        String stringNumCurrencyToSoftPity;
        String stringCurrencyToSoftPity;
        String stringCombinedCurrencyToSoftPity;
        String stringTotalCurrencySpent = resources.getString(R.string.total_currency_spent);
        String stringCombinedTotalSpent = currencyType + " " + stringTotalCurrencySpent;
        int intTotalSpent = counter * wishValue;
        String stringTotalSpent = String.valueOf(intTotalSpent);
        if (counter < softPity){
            int intToSoftPity = softPity - counter;
            int intCurrencyToSoftPity = intToSoftPity * wishValue;

            stringNumToSoftPity = String.valueOf(intToSoftPity);

            stringToSoftPity = resources.getString(R.string.till_soft_pity);

            stringNumCurrencyToSoftPity = String.valueOf(intCurrencyToSoftPity);
            stringCurrencyToSoftPity = resources.getString(R.string.currency_till_soft_pity);
            stringCombinedCurrencyToSoftPity = currencyType + " " + stringCurrencyToSoftPity;

            updateTextFields(txtNumToSoftPity, txtToSoftPity, txtNumCurrencyToSoftPity,txtCurrencyToSoftPity, txtNumTotalSpent, txtTotalSpent, stringNumToSoftPity, stringToSoftPity, stringNumCurrencyToSoftPity, stringCombinedCurrencyToSoftPity, stringTotalSpent, stringCombinedTotalSpent);
        } else if (counter == softPity) {
            stringNumToSoftPity = String.valueOf(0);
            String stringReachedSoftPity = resources.getString(R.string.reached_soft_pity);
            updateTextFields(txtNumToSoftPity, txtToSoftPity, txtNumCurrencyToSoftPity,txtCurrencyToSoftPity, txtNumTotalSpent, txtTotalSpent, stringNumToSoftPity, stringReachedSoftPity, stringNumToSoftPity, stringReachedSoftPity, stringTotalSpent, stringCombinedTotalSpent);
        }
        else {
            int intPastSoftPity = counter - softPity;
            int intCurrencyToSoftPity = intPastSoftPity * wishValue;

            stringNumToSoftPity = String.valueOf(intPastSoftPity);

            stringToSoftPity = resources.getString(R.string.past_soft_pity);

            stringNumCurrencyToSoftPity = String.valueOf(intCurrencyToSoftPity);
            stringCurrencyToSoftPity = resources.getString(R.string.currency_past_soft_pity);
            stringCombinedCurrencyToSoftPity = currencyType + " " + stringCurrencyToSoftPity;

            updateTextFields(txtNumToSoftPity, txtToSoftPity, txtNumCurrencyToSoftPity,txtCurrencyToSoftPity, txtNumTotalSpent, txtTotalSpent, stringNumToSoftPity, stringToSoftPity, stringNumCurrencyToSoftPity, stringCombinedCurrencyToSoftPity, stringTotalSpent, stringCombinedTotalSpent);
        }
    }
    private static void updateTextFields(TextView txtNumToSoftPity, TextView txtToSoftPity, TextView txtNumCurrencyToSoftPity, TextView txtCurrencyToSoftPity, TextView txtNumTotalSpent, TextView txtTotalSpent, String stringNumToSoftPity, String stringToSoftPity, String stringNumCurrencyToSoftPity, String stringCombinedCurrencyToSoftPity, String stringTotalSpent, String stringCombinedTotalSpent){
        txtNumToSoftPity.setText(stringNumToSoftPity);
        txtToSoftPity.setText(stringToSoftPity);
        txtNumCurrencyToSoftPity.setText(stringNumCurrencyToSoftPity);
        txtCurrencyToSoftPity.setText(stringCombinedCurrencyToSoftPity);
        txtNumTotalSpent.setText(stringTotalSpent);
        txtTotalSpent.setText(stringCombinedTotalSpent);
    }

    // to se bo itak še popravilo, ko se bo pobralo iz baze - gledalo se bo katea igra je samo zdaj se mi ne da

    public static void initialSetup(TextView pullsTillSoftPity, TextView currencyTillSoftPity, TextView totalCurrencySpent, int softPity, int wishValue){
        pullsTillSoftPity.setText(String.valueOf(softPity));
        currencyTillSoftPity.setText(String.valueOf(softPity*wishValue));
        totalCurrencySpent.setText("0");
    }


}
