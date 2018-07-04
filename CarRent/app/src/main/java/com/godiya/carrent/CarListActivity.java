package com.godiya.carrent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity {

    //Control
    Toolbar toolbar;
    ListView CarList;

    //Server data
    String CarDetail;

    //list of cars
    ArrayList<Car> carlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        //config control
        toolbar=(Toolbar)findViewById(R.id.carlist_toolbar);
        CarList=(ListView)findViewById(R.id.carlist_carlist_list);


        //config toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Car List");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        //get Server response
        CarDetail=getIntent().getExtras().getString("CarDetail");
        Toast.makeText(this,CarDetail,Toast.LENGTH_LONG).show();


        //load Listview
        try {
            loadcarlist();
        } catch (JSONException e) {
            //Toast.makeText(CarListActivity.this,"No Car Found",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }



        //List view click event
        CarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car selectedcar =carlist.get(position);
                Intent intent=new Intent(CarListActivity.this,SelectServiceActivity.class);
                intent.putExtra("selectedCar",selectedcar);
                startActivity(intent);
            }
        });
    }

    public void loadcarlist() throws JSONException {
        CarAdo ado=new CarAdo(CarDetail);
        carlist=ado.getCars();
        CarListAdapter adapter=new CarListAdapter(carlist,getApplicationContext());
        CarList.setAdapter(adapter);
    }
}
