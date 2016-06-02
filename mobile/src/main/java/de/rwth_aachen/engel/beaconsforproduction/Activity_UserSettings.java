package de.rwth_aachen.engel.beaconsforproduction;

import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Domino on 31.05.2016.
 */
public class Activity_UserSettings extends AppCompatActivity {
    EditText email,lastName,firstName,password;
    boolean isStarted;
    SharedPreferences prefs;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_settings);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            if(toolbar!=null) {
                toolbar.setTitle(getString(R.string.userSettings));
                setSupportActionBar(toolbar);
                if(getSupportActionBar()!=null)
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            email = (EditText) findViewById(R.id.editTextEmail);
            firstName = (EditText)findViewById(R.id.editTextFirstName);
            lastName = (EditText)findViewById(R.id.editTextLastName);
            password = (EditText)findViewById(R.id.editTextPassword);
            TextView company = (TextView)findViewById(R.id.editTextCompany);
            isStarted = false;
            prefs = getSharedPreferences("user",MODE_PRIVATE);
            try {
                JSONObject json = new JSONObject(prefs.getString("user",""));
                email.setText(json.getString("email"));
                firstName.setText(json.getString("firstName"));
                lastName.setText(json.getString("lastName"));
                password.setText(json.getString("password"));
                company.setText(json.getString("company"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.save).setVisible(isStarted);
        menu.findItem(R.id.edit).setVisible(!isStarted);
        email.setFocusable(isStarted);
        email.setClickable(isStarted);
        email.setFocusableInTouchMode(isStarted);
        firstName.setFocusable(isStarted);
        firstName.setClickable(isStarted);
        firstName.setFocusableInTouchMode(isStarted);
        lastName.setFocusable(isStarted);
        lastName.setClickable(isStarted);
        lastName.setFocusableInTouchMode(isStarted);
        password.setFocusable(isStarted);
        password.setClickable(isStarted);
        password.setFocusableInTouchMode(isStarted);
        firstName.requestFocus();
        if(!isStarted){
            JSONObject json = new JSONObject();
            try {
                json.put("firstName",firstName.getText());
                json.put("lastName",lastName.getText());
                json.put("email",email.getText());
                json.put("password",password.getText());
                json.put("company","WZL");
                prefs.edit().putString("user", json.toString()).apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        isStarted = !isStarted;
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                invalidateOptionsMenu();
                return true;
            case R.id.save:
                invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_user_settings_edit, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isStarted){
            //savehere;
        }
    }
}
