package com.godiya.carrent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button register_btn;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        register_btn=(Button)findViewById(R.id.home_register_btn);
        login_btn=(Button)findViewById(R.id.home_login_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goregistraion=new Intent(HomeActivity.this,RegisterActivity.class);
                startActivity(goregistraion);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gologin=new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(gologin);
            }
        });
    }
}
