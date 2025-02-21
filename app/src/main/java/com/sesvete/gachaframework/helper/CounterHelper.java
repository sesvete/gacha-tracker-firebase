package com.sesvete.gachaframework.helper;


import android.content.res.Resources;
import android.widget.TextView;

import com.sesvete.gachaframework.R;

public class CounterHelper {
    public int counterPlusOne(int currentCount){
        return currentCount + 1;
    }

    public int counterPlusTen(int currentCount){
        return currentCount + 10;
    }

    public int counterPlusX(int currentCount, int amountToAdd){
        return currentCount + amountToAdd;
    }

    public void updateSoftPityTracker(Resources resources, int counter, int softPity, int wishValue, String currencyType, TextView txtNumToSoftPity, TextView txtToSoftPity, TextView txtNumCurrencyToSoftPity, TextView txtCurrencyToSoftPity, TextView txtNumTotalSpent, TextView txtTotalSpent){
        String stringNumToSoftPity;
        String stringToSoftPity;
        String stringNumCurrencyToSoftPity;
        String stringCurrencyToSoftPity;
        String stringCombinedCurrencyToSoftPity;
        String stringCurrencyType = resources.getString(R.string.primogens);
        String stringTotalCurrencySpent = resources.getString(R.string.total_currency_spent);
        String stringCombinedTotalSpent = stringCurrencyType + " " + stringTotalCurrencySpent;
        int intTotalSpent = counter * wishValue;
        String stringTotalSpent = String.valueOf(intTotalSpent);
        if (counter < softPity){
            int intToSoftPity = softPity - counter;
            int intCurrencyToSoftPity = intToSoftPity * wishValue;

            stringNumToSoftPity = String.valueOf(intToSoftPity);

            stringToSoftPity = resources.getString(R.string.till_soft_pity);

            stringNumCurrencyToSoftPity = String.valueOf(intCurrencyToSoftPity);
            stringCurrencyToSoftPity = resources.getString(R.string.currency_till_soft_pity);
            stringCombinedCurrencyToSoftPity = stringCurrencyType + " " + stringCurrencyToSoftPity;

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
            stringCombinedCurrencyToSoftPity = stringCurrencyType + " " + stringCurrencyToSoftPity;

            updateTextFields(txtNumToSoftPity, txtToSoftPity, txtNumCurrencyToSoftPity,txtCurrencyToSoftPity, txtNumTotalSpent, txtTotalSpent, stringNumToSoftPity, stringToSoftPity, stringNumCurrencyToSoftPity, stringCombinedCurrencyToSoftPity, stringTotalSpent, stringCombinedTotalSpent);
        }
    }
    private void updateTextFields(TextView txtNumToSoftPity, TextView txtToSoftPity, TextView txtNumCurrencyToSoftPity, TextView txtCurrencyToSoftPity, TextView txtNumTotalSpent, TextView txtTotalSpent, String stringNumToSoftPity, String stringToSoftPity, String stringNumCurrencyToSoftPity, String stringCombinedCurrencyToSoftPity, String stringTotalSpent, String stringCombinedTotalSpent){
        txtNumToSoftPity.setText(stringNumToSoftPity);
        txtToSoftPity.setText(stringToSoftPity);
        txtNumCurrencyToSoftPity.setText(stringNumCurrencyToSoftPity);
        txtCurrencyToSoftPity.setText(stringCombinedCurrencyToSoftPity);
        txtNumTotalSpent.setText(stringTotalSpent);
        txtTotalSpent.setText(stringCombinedTotalSpent);
    }
}
