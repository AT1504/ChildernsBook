package com.isisruby.childrensbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public EditText TaxYear;
    public AutoCompleteTextView CitySpinner;

    private static final int RESULT_SETTINGS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.m_layout);
        mLayout.requestFocus();

        TaxYear = (EditText) findViewById(R.id.tax_year_txt);
        TaxYear.setFocusable(false);
        TaxYear.setClickable(true);
        TaxYear.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           new DatePickerDialog(MainActivity.this, date, myCalendar
                                                   .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                                   myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                                       }
                                   }
        );
        //Button btnSettings=(Button)findViewById(R.id.buttonSettings);

        // start the UserSettingActivity when user clicks on Button
//        btnSettings.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), UserSettingActivity.class);
//                startActivityForResult(i, RESULT_SETTINGS);
//            }
//        });
        displayUserSettings();
        CityChooserBySpinner();
    }

    public void CityChooserBySpinner() {
        CitySpinner = (AutoCompleteTextView) findViewById(R.id.citys_spinner);
        // Create an ArrayAdapter using the string array and a default CitySpinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, CITIES);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the CitySpinner
        CitySpinner.setAdapter(adapter);
        CitySpinner.setOnItemSelectedListener(this);
        CitySpinner.setSingleLine();
    }
    private static final String[] CITIES = new String[] {
            "אשדות יעקב", "ariel", "יונתן", "עזוז", "פקיעין"
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;

        switch (item.getItemId()) {

            case R.id.action_settings:
                i = new Intent(this, UserSettingActivity.class);
                //startActivityForResult(i, RESULT_SETTINGS);
                startActivity(i);
                break;
            case R.id.action_about:
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        TaxYear.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_SETTINGS) {
            displayUserSettings();
        }

    }

    private void displayUserSettings() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String settings = "";
        settings = settings + "Password: " + sharedPrefs.getString("prefUserPassword", "NOPASSWORD");
        settings = settings + "\nRemind For Update:" + sharedPrefs.getBoolean("prefLockScreen", false);
        settings = settings + "\nUpdate Frequency: "
                + sharedPrefs.getString("prefUpdateFrequency", "NOUPDATE");
        TextView textViewSetting = (TextView) findViewById(R.id.textUserSettings);
        textViewSetting.setText(settings);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // implementation for the CitySpinner
        // Spinner view value
        String city = parent.getItemAtPosition(position).toString();
        Log.i("city", city);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // another implementation for the CitySpinner
    }
}




