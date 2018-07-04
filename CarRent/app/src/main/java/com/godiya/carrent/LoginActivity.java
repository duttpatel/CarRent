package com.godiya.carrent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    //control
    EditText Login_user_txt;
    EditText Login_password_txt;
    Button Login_btn;
    Button Cancel_btn;

    //variable
    String UserName;
    String Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //congif
        Login_user_txt=(EditText)findViewById(R.id.login_user_txt);
        Login_password_txt=(EditText)findViewById(R.id.login_password_txt);
        Login_btn=(Button)findViewById(R.id.login_login_btn);
        Cancel_btn=(Button)findViewById(R.id.login_cancle_btn);

        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserName=Login_user_txt.getText().toString();
                Password=Login_password_txt.getText().toString();

                LoginBackground background=new LoginBackground(LoginActivity.this);
                background.execute(UserName,Password);


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
