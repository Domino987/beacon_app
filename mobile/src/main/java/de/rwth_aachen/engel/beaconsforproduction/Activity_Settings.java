package de.rwth_aachen.engel.beaconsforproduction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;

/**
 * Created by Domino on 31.05.2016.
 */
public class Activity_Settings extends AppCompatActivity {
    SharedPreferences prefs;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            if(toolbar!=null) {
                toolbar.setTitle(getString(R.string.action_settings));
                setSupportActionBar(toolbar);
                if(getSupportActionBar()!=null)
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            prefs = getSharedPreferences("settings", MODE_PRIVATE);
            final NumberPicker numPicker = (NumberPicker) findViewById(R.id.numberPickerSettings);
            Spinner unitSpinner = (Spinner) findViewById(R.id.spinnerUnit);
            if(numPicker != null) {
                numPicker.setMaxValue(15);
                numPicker.setMinValue(1);
                numPicker.setValue(prefs.getInt("range", 2));
                numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        prefs.edit().putInt("range", newVal).apply();
                    }
                });
            }
            if(unitSpinner != null){
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.units, android.R.layout.simple_spinner_item);
                unitSpinner.setAdapter(adapter);
                unitSpinner.setSelection(prefs.getInt("unit",0));
                unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        prefs.edit().putInt("unit", position).apply();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
}
