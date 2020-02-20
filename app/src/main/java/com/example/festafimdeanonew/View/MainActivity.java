package com.example.festafimdeanonew.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.festafimdeanonew.Data.SecurityPreferences;
import com.example.festafimdeanonew.R;
import com.example.festafimdeanonew.View.DetailsActivity;
import com.example.festafimdeanonew.constants.FimDeAnoConstants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private SecurityPreferences mSecurityPreferences;
    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));

        this.mViewHolder.textDaysLeft.setText(daysLeft);


    }


    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm) {

            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY,presence);
            startActivity(intent);
        }
    }

    private void verifyPresence() {
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);
        if (presence.equals("")) {

            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao_confirmado));

        } else if (presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){

            this.mViewHolder.buttonConfirm.setText(getString(R.string.sim));

        } else {

            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao));
        }

    }

    private int getDaysLeft(){
        Calendar calendarToday = Calendar.getInstance();

        int Today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();

        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - Today;

    }

    private static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
