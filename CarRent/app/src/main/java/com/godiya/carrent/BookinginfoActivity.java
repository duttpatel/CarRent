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
import android.widget.TextView;

import static android.graphics.Color.WHITE;

public class BookinginfoActivity extends AppCompatActivity {

    Toolbar toolbar;
    String Transaction;
    Button btn;
    TextView trans;
    TextView amt;
    Car selectedCar;
    SharedPreferences sharedPreferences;
    String cust;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinginfo);

        Transaction=getIntent().getStringExtra("Transaction");
        String detail[]=Transaction.split(",");

        btn=(Button)findViewById(R.id.btn);
        trans=(TextView)findViewById(R.id.transid);
        amt=(TextView)findViewById(R.id.amt);

        sharedPreferences=getSharedPreferences("Bookingdetails", Context.MODE_PRIVATE);
        cust=sharedPreferences.getString("CustomerId","");

        trans.setText(detail[0]);
        amt.setText(detail[1]);


        //Toolbar
        toolbar=(Toolbar)findViewById(R.id.booking_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Booking Detail");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BookinginfoActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                /*SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("PickLocationId");
                editor.remove("PickLocationName");
                editor.remove("ReturnLocationId");
                editor.remove("ReturnLocationName");
                editor.remove("PickDay");
                editor.remove("PickYear");
                editor.remove("PickMonth");
                editor.remove("ReturnDay");
                editor.remove("ReturnYear");
                editor.remove("ReturnMonth");
                editor.remove("PickTime");
                editor.remove("RetrunTime");
                editor.putString("CustomerId",cust);
                editor.apply();
                editor.commit();*/
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}
