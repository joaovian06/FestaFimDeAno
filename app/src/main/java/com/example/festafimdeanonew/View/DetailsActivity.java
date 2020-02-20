package com.example.festafimdeanonew.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festafimdeanonew.Data.SecurityPreferences;
import com.example.festafimdeanonew.R;
import com.example.festafimdeanonew.constants.FimDeAnoConstants;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewHolder mViewHolder = new ViewHolder ();
    private SecurityPreferences mSecurityPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkBox = findViewById(R.id.checkbox);
        this.mViewHolder.checkBox.setOnClickListener(this);

        this.loadDataFromActivity();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkbox) {

            if (this.mViewHolder.checkBox.isChecked()){
               this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);
            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            }

        }
    }

    private void loadDataFromActivity(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if (presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mViewHolder.checkBox.setChecked(true);
            } else {
                this.mViewHolder.checkBox.setChecked(false);
            }
        }
    }

    private static class ViewHolder {
       CheckBox checkBox;
    }
}
