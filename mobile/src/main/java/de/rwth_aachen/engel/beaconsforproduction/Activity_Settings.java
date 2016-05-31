package de.rwth_aachen.engel.beaconsforproduction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Domino on 31.05.2016.
 */
public class Activity_Settings extends AppCompatActivity {
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
        }
}
