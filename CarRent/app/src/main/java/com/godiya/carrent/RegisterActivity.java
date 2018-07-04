package com.godiya.carrent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {
    EditText FirstName_txt;
    EditText LastName_txt;
    Spinner Gender_txt;
    EditText Address_txt;
    EditText City_txt;
    EditText Pincode_txt;
    EditText Contact_txt;
    EditText Email_txt;
    EditText License_txt;
    EditText Password_txt;
    Button Register_btn;
    Button Cancel_btn;

    //Data  Variable
    String FirstName;
    String LastName;
    String Gender;
    String Address;
    String City;
    String Pincode;
    String Contact;
    String Email;
    String License;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //control config
        FirstName_txt=(EditText)findViewById(R.id.register_firstname_txt);
        LastName_txt=(EditText)findViewById(R.id.register_lastname_txt);
        Gender_txt=(Spinner)findViewById(R.id.register_gender_txt);
        Address_txt=(EditText)findViewById(R.id.register_address_txt);
        City_txt=(EditText)findViewById(R.id.register_city_txt);
        Pincode_txt=(EditText)findViewById(R.id.register_pincode_txt);
        Contact_txt=(EditText)findViewById(R.id.register_contact_txt);
        Email_txt=(EditText)findViewById(R.id.register_email_txt);
        License_txt=(EditText)findViewById(R.id.register_license_txt);
        Password_txt=(EditText)findViewById(R.id.register_password_txt);
        Register_btn=(Button)findViewById(R.id.register_signup_btn);
        Cancel_btn=(Button)findViewById(R.id.register_cancle_btn);


        //setup data for spinner
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender_txt.setAdapter(adapter);

        //set event to button

        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirstName=FirstName_txt.getText().toString();
                LastName=LastName_txt.getText().toString();
                Gender=Gender_txt.getSelectedItem().toString();
                Address=Address_txt.getText().toString();
                City=City_txt.getText().toString();
                Pincode=Pincode_txt.getText().toString();
                Contact=Contact_txt.getText().toString();
                Email=Email_txt.getText().toString();
                License=License_txt.getText().toString();
                Password=Password_txt.getText().toString();

                RegisterBackground background=new RegisterBackground(RegisterActivity.this);
                background.execute(FirstName,LastName,Gender,Address,City,Pincode,Contact,Email,License,Password);
                finish();
            }
        });

        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
