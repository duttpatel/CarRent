package com.godiya.carrent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;



public class SelectServiceActivity extends AppCompatActivity {

    Car selectedCar;

    //Control
    Toolbar toolbar;
    CheckBox driver_cb;
    CheckBox childseat_cb;
    Button booking_btn;

    //variable
    String driveselected;
    String childselected;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);


        //variable initial
        driveselected="No";
        childselected="No";
        sharedpreferences=getSharedPreferences("Bookingdetails", Context.MODE_PRIVATE);

        //get selected car
        selectedCar=(Car)getIntent().getSerializableExtra("selectedCar");

        //Config control
        toolbar=(Toolbar)findViewById(R.id.sevice_toolbar);
        driver_cb=(CheckBox)findViewById(R.id.service_driver);
        childseat_cb=(CheckBox)findViewById(R.id.service_childseat);
        booking_btn=(Button)findViewById(R.id.service_booking_btn);


        //config toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Other Service");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        //Config driver checkbox
        driver_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    driveselected="Yes";
                }
                else
                {
                    driveselected="No";
                }
            }
        });

        //config childseat check box
        childseat_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    childselected="Yes";
                }
                else
                {
                    childselected="No";
                }
            }
        });

        //config button
        booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SelectServiceActivity.this,ConfirmationActivity.class);
                intent.putExtra("selectedCar",selectedCar);
                intent.putExtra("Drive",driveselected);
                intent.putExtra("Child",childselected);
                startActivity(intent);
                //Toast.makeText(SelectServiceActivity.this,"Customer Id"+sharedpreferences.getString("CustomerId", ""),Toast.LENGTH_LONG).show();
            }
        });

    }
}
