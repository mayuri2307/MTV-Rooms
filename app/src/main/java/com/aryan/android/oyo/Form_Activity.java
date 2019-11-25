package com.aryan.android.oyo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Form_Activity extends AppCompatActivity {
    private final AppCompatActivity activity = Form_Activity.this;
    private DatabaseHelper databaseHelper;
    private Room room;
    Button add;
    Button delete;
    private EditText textName;
    private EditText textCost;
    private EditText textLocation;
    private EditText textDescription;
    private RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_);
        textName= findViewById(R.id.edit_text);
        add=findViewById(R.id.letzgo);
        delete=findViewById(R.id.letzgo2);
        databaseHelper = new DatabaseHelper(activity);
        room=new Room();
        textLocation=findViewById(R.id.edit_text2);
        textCost=findViewById(R.id.editText);
        textDescription=findViewById(R.id.edit_text4);
        rl=findViewById(R.id.rl);


        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                postDataToSQLite();
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                postDataToSQLite1();
            }
        });

    }
    public void postDataToSQLite1(){
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putString("ToRemove", (textName.getText().toString().trim())).apply();
        emptyInputEditText();
        Intent listIntent = new Intent(activity, FirstActivity.class);
        startActivity(listIntent);
        finish();
    }
    public void postDataToSQLite(){
        room.setName(textName.getText().toString().trim());
        room.setLocation(textLocation.getText().toString().trim());
        room.setCost(Integer.parseInt(textCost.getText().toString().trim()));
        room.setDescription(textDescription.getText().toString().trim());

        databaseHelper.addRoom(room);

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(rl, getString(R.string.details_save), Snackbar.LENGTH_LONG).show();
        Intent listIntent = new Intent(activity, FirstActivity.class);
        saveObjectToSharedPreference(getApplicationContext(), "Preference", "Room", room);
        emptyInputEditText();
        startActivity(listIntent);
        finish();
    }
    private void emptyInputEditText(){
        textLocation.setText(null);
        textName.setText(null);
        textCost.setText(null);
        textDescription.setText(null);
    }

    public static void saveObjectToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

}
