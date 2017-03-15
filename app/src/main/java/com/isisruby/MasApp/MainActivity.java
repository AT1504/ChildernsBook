package com.isisruby.MasApp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    public EditText TaxYear;
    public EditText Bruto;
    public AutoCompleteTextView CitySpinner;
    private final ArrayList<String> CITIES = new ArrayList<>();
    private JSONArray json_arr;
    private double uptownRate=0.0, uptownCeiling=0.0;

    private static final int RESULT_SETTINGS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.m_layout);
        mLayout.requestFocus();
        Bruto = (EditText) findViewById(R.id.bruto_edittxt);

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
        Button NekudotZikui = (Button) findViewById(R.id.btn_nekudot_zikui);
        NekudotZikui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NekudotZikui.class);
                startActivity(i);
            }
        });


        Button MasCalcBtn;
        MasCalcBtn = (Button)findViewById(R.id.mas_calc);
        MasCalcBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i("MasResult", String.valueOf(CalculateMas()));
            }
        });
        displayUserSettings();
        JSONObject json_obj;
        String name;
        try {
            json_arr  = new JSONArray(readJSONFromAsset());
            for (int i = 0; i < json_arr.length(); i++) {
                json_obj = json_arr.getJSONObject(i);
                name = json_obj.getString("city_name");
                CITIES.add(name);
            }
        }catch (JSONException e1) {
            e1.printStackTrace();
        }
        CityChooserByAutoComplete();
    }

    public String readJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("up_towns_israel.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private Double CalculateMas() {
        int TaxYearInput = 0;
        int[] MadregotMas = {0,0,0,0,0,0};
        int[] ShiurMas = {0,0,0,0,0,0};
        double BrutoInput=0;

        if (TaxYear!=null){
            String myFormat = "yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
            TaxYearInput = Integer.parseInt(dateFormat.format(myCalendar.getTime()));
        }

        if (Bruto.length() == 0 || Bruto.equals("") || Bruto == null){
            Toast.makeText(MainActivity.this, "נא, הכנס את גובה המשכורת שלך",
                    Toast.LENGTH_LONG).show();
        }else{
            BrutoInput = Double.parseDouble(this.Bruto.getText().toString());
        }

        double resultMas=0;
        if (TaxYearInput==2017){
            MadregotMas = getResources().getIntArray(R.array.madregat_mas_2017);
            ShiurMas = getResources().getIntArray(R.array.shiur_mas_2017);
        }
        else if (TaxYearInput==2016){
            MadregotMas = getResources().getIntArray(R.array.madregat_mas_2016);
            ShiurMas = getResources().getIntArray(R.array.shiur_mas_2016);
        }

        for (int i = 0; i < 6; i++) {
            if (BrutoInput > MadregotMas[i]) {
                if (i!=0 && i!=5) {resultMas += (MadregotMas[i]-MadregotMas[i-1])*ShiurMas[i]/100;}
                else if (i==0) {resultMas += (MadregotMas[0])*ShiurMas[i]/100;}
                else if (BrutoInput > MadregotMas[i] && i==5) {resultMas += (BrutoInput-MadregotMas[i-1])*ShiurMas[i]/100;}
            } else if(BrutoInput < MadregotMas[i] && i!=0){
                resultMas += (BrutoInput-MadregotMas[i-1])*ShiurMas[i]/100;
                break;
            } else if (BrutoInput < MadregotMas[0]){
                resultMas = BrutoInput*ShiurMas[0]/100;
                break;
            }
        }
        // Extra tax for High Income
        double ExtraMadrega = Double.valueOf(getResources().getString(R.string.extra_income_madrega));
        if (BrutoInput>ExtraMadrega) {
            double ExtraShiur = Double.valueOf(getResources().getString(R.string.extra_income_shiur));
            resultMas += (BrutoInput-ExtraMadrega)*ExtraShiur;
        }
        return resultMas;
    }

    public void CityChooserByAutoComplete() {
        CitySpinner = (AutoCompleteTextView) findViewById(R.id.citys_spinner);
        // Create an ArrayAdapter using the string array and a default CitySpinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, CITIES);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the CitySpinner
        CitySpinner.setAdapter(adapter);
        CitySpinner.setOnItemClickListener(this);
        CitySpinner.setSingleLine();
    }

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
                startActivityForResult(i, RESULT_SETTINGS);
                break;
            case R.id.action_about:
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    public Calendar myCalendar = Calendar.getInstance();

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

        String myFormat = "dd/MM/yyyy";
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
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//        String settings = "";
//        settings = settings + "Password: " + sharedPrefs.getString("prefUserPassword", "NOPASSWORD");
//        settings = settings + "\nRemind For Update:" + sharedPrefs.getBoolean("prefLockScreen", false);
//        settings = settings + "\nUpdate Frequency: "
//                + sharedPrefs.getString("prefUpdateFrequency", "NOUPDATE");
//        TextView textViewSetting = (TextView) findViewById(R.id.textUserSettings);
//        textViewSetting.setText(settings);
    }

    // AutocompleteEditText Methods
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject json_obj;
        String city = parent.getItemAtPosition(position).toString();
        try {
            for (int i = 0; i < json_arr.length(); i++) {
                json_obj = json_arr.getJSONObject(i);
                if (json_obj.getString("city_name") == city){
                    uptownRate = json_obj.getDouble("rate_2016");
                    uptownCeiling = json_obj.getDouble("ceiling_2016");
                    Log.i("uptown:","["+uptownRate+"],["+uptownCeiling+"]");
                    break;
                }
            }
        }catch (JSONException e1) {
            e1.printStackTrace();
        }
        Log.i("json_arr - length", ""+json_arr.length());
    }
}




