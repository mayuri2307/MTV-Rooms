package com.aryan.android.oyo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class get_user_data extends AppCompatActivity {
    String hobby,gender,marital,age,number,name;
    Spinner mSpinner,mSpinner2,mSpinner3;
    TextView getage,num,nam;
    ArrayAdapter<CharSequence> adapter,adapter2,adapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_data);
        mSpinner=(Spinner)findViewById(R.id.hobby_spinner);
        getage=(TextView)findViewById(R.id.age_et);
        num=(TextView)findViewById(R.id.ph_et);
        nam=(TextView)findViewById(R.id.name_et);
        adapter= ArrayAdapter.createFromResource(this,R.array.hobby_list,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"is selected",Toast.LENGTH_SHORT).show();
                hobby=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        mSpinner2=(Spinner)findViewById(R.id.gender_et);
        adapter2=ArrayAdapter.createFromResource(this,R.array.gender_list,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner2.setAdapter(adapter2);
        mSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"is selected",Toast.LENGTH_SHORT).show();
                gender=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        mSpinner3=(Spinner)findViewById(R.id.marital_et);
        adapter3=ArrayAdapter.createFromResource(this,R.array.marital_list,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner3.setAdapter(adapter3);
        mSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"is selected",Toast.LENGTH_SHORT).show();
                marital=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }
    public void openNextActivity(View v){
        age=getage.getText()+"";
        number=num.getText()+"";
        name=nam.getText()+"";
        Intent intent=new Intent(get_user_data.this,profile_you.class);
        intent.putExtra("hobby",hobby);
        intent.putExtra("age",age);
        intent.putExtra("number",number);
        intent.putExtra("gender",gender);
        intent.putExtra("status",marital);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
