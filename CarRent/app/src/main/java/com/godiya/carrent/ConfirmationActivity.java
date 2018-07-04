package com.godiya.carrent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class ConfirmationActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    Car selectedcar;
    String driverselected;
    String childselected;

    //Control
    Toolbar toolbar;
    TextView CarName_txt;
    TextView CarSeat_txt;
    TextView CarMilage_txt;
    TextView CarRate_txt;
    TextView PickupLocation_txt;
    TextView PickupDate_txt;
    TextView PickupTime_txt;
    TextView ReturnLocation_txt;
    TextView ReturnDate_txt;
    TextView ReturnTime_txt;
    TextView Drive_txt;
    TextView Child_txt;
    Button Confirm_btn;

    //Variable
    String CarId;
    String CustomerId;
    String PickupLocation;
    String ReturnLocation;
    String PickupDate;
    String PickupMonth;
    String PickupYear;
    String ReturnDate;
    String ReturnMonth;
    String ReturnYear;
    String PickupTime;
    String ReturnTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        //Share pref
        sharedpreferences = getSharedPreferences("Bookingdetails", Context.MODE_PRIVATE);

        //Config control
        CarName_txt=(TextView)findViewById(R.id.confirm_carname_txt);
        CarSeat_txt=(TextView)findViewById(R.id.confirm_carseat_txt);
        CarMilage_txt=(TextView)findViewById(R.id.confirm_carmileage_txt);
        CarRate_txt=(TextView)findViewById(R.id.confirm_carrate_txt);
        PickupLocation_txt=(TextView)findViewById(R.id.confirm_pickuplocation_txt);
        PickupDate_txt=(TextView)findViewById(R.id.confirm_pickdate_txt);
        PickupTime_txt=(TextView)findViewById(R.id.confirm_pickuptime_txt);
        ReturnLocation_txt=(TextView)findViewById(R.id.confirm_returnlocation_txt);
        ReturnDate_txt=(TextView)findViewById(R.id.confirm_returndate_txt);
        ReturnTime_txt=(TextView)findViewById(R.id.confirm_returntime_txt);
        Drive_txt=(TextView)findViewById(R.id.confirm_driveroption_txt);
        Child_txt=(TextView)findViewById(R.id.confirm_childoption_txt);
        Confirm_btn=(Button)findViewById(R.id.confirm_confirm_btn);
        toolbar=(Toolbar)findViewById(R.id.confirm_toolbar);


        //Config Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Confirmation");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        //Get selected Data
        selectedcar=(Car)getIntent().getSerializableExtra("selectedCar");
        driverselected=getIntent().getStringExtra("Drive");
        childselected=getIntent().getStringExtra("Child");


        CarName_txt.setText(selectedcar.getCompanyName()+" "+selectedcar.getModelName());
        CarSeat_txt.setText(selectedcar.getSeats());
        CarMilage_txt.setText(selectedcar.getMileage());
        CarRate_txt.setText(selectedcar.getRate());
        PickupLocation_txt.setText(sharedpreferences.getString("PickLocationName", ""));
        PickupDate_txt.setText(sharedpreferences.getString("PickDay", "")+"/"+sharedpreferences.getString("PickMonth", "")+"/"+sharedpreferences.getString("PickYear", ""));
        PickupTime_txt.setText(sharedpreferences.getString("PickTime", ""));
        ReturnLocation_txt.setText(sharedpreferences.getString("ReturnLocationName", ""));
        ReturnDate_txt.setText(sharedpreferences.getString("ReturnDay", "")+"/"+sharedpreferences.getString("ReturnMonth", "")+"/"+sharedpreferences.getString("ReturnYear", ""));
        ReturnTime_txt.setText(sharedpreferences.getString("RetrunTime", ""));
        Drive_txt.setText(driverselected);
        Child_txt.setText(childselected);

        //Variable Set
        CarId=selectedcar.getCarId();
        CustomerId=sharedpreferences.getString("CustomerId","");
        PickupLocation=sharedpreferences.getString("PickLocationId","");
        ReturnLocation=sharedpreferences.getString("ReturnLocationId","");
        PickupDate=sharedpreferences.getString("PickDay","");
        PickupMonth=sharedpreferences.getString("PickMonth","");
        PickupYear=sharedpreferences.getString("PickYear","");
        ReturnDate=sharedpreferences.getString("ReturnDay","");
        ReturnMonth=sharedpreferences.getString("ReturnMonth","");
        ReturnYear=sharedpreferences.getString("ReturnYear","");
        PickupTime=sharedpreferences.getString("PickTime","");
        ReturnTime=sharedpreferences.getString("RetrunTime","");




        //Button click
        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingbackground background=new bookingbackground();
                background.execute(CarId,CustomerId,PickupLocation,ReturnLocation,PickupDate,PickupMonth,PickupYear,ReturnDate,ReturnMonth,ReturnYear,PickupTime,ReturnTime);
            }
        });

    }

    class bookingbackground extends AsyncTask <String,Void,String>{

        String pagelink="http://192.168.0.18:7777/test/CarRent/booking.php";
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            CarId=selectedcar.getCarId();
            CustomerId=sharedpreferences.getString("CustomerId","");
            PickupLocation=sharedpreferences.getString("PickLocationId","");
            ReturnLocation=sharedpreferences.getString("ReturnLocationId","");
            PickupDate=sharedpreferences.getString("PickDay","");
            PickupMonth=sharedpreferences.getString("PickMonth","");
            PickupYear=sharedpreferences.getString("PickYear","");
            ReturnDate=sharedpreferences.getString("ReturnDay","");
            ReturnMonth=sharedpreferences.getString("ReturnMonth","");
            ReturnYear=sharedpreferences.getString("ReturnYear","");
            PickupTime=sharedpreferences.getString("PickTime","");
            ReturnTime=sharedpreferences.getString("RetrunTime","");

            URL url= null;
            try
            {
                url = new URL(pagelink);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("CarId","UTF-8")+"="+URLEncoder.encode(CarId,"UTF-8")+"&"+
                        URLEncoder.encode("CustomerId","UTF-8")+"="+URLEncoder.encode(CustomerId,"UTF-8")+"&"+
                        URLEncoder.encode("PickupLocation","UTF-8")+"="+URLEncoder.encode(PickupLocation,"UTF-8")+"&"+
                        URLEncoder.encode("ReturnLocation","UTF-8")+"="+URLEncoder.encode(ReturnLocation,"UTF-8")+"&"+
                        URLEncoder.encode("PickupDate","UTF-8")+"="+URLEncoder.encode(PickupDate,"UTF-8")+"&"+
                        URLEncoder.encode("PickupMonth","UTF-8")+"="+URLEncoder.encode(PickupMonth,"UTF-8")+"&"+
                        URLEncoder.encode("PickupYear","UTF-8")+"="+URLEncoder.encode(PickupYear,"UTF-8")+"&"+
                        URLEncoder.encode("ReturnDate","UTF-8")+"="+URLEncoder.encode(ReturnDate,"UTF-8")+"&"+
                        URLEncoder.encode("ReturnMonth","UTF-8")+"="+URLEncoder.encode(ReturnMonth,"UTF-8")+"&"+
                        URLEncoder.encode("ReturnYear","UTF-8")+"="+URLEncoder.encode(ReturnYear,"UTF-8")+"&"+
                        URLEncoder.encode("PickupTime","UTF-8")+"="+URLEncoder.encode(PickupTime,"UTF-8")+"&"+
                        URLEncoder.encode("ReturnTime","UTF-8")+"="+URLEncoder.encode(ReturnTime,"UTF-8")+"&"+
                        URLEncoder.encode("Driver","UTF-8")+"="+URLEncoder.encode(driverselected,"UTF-8")+"&"+
                        URLEncoder.encode("Child","UTF-8")+"="+URLEncoder.encode(childselected,"UTF-8")+"&"+
                        URLEncoder.encode("Rent","UTF-8")+"="+URLEncoder.encode(selectedcar.getRate());

                bw.write(data);
                bw.flush();
                bw.close();
                os.close();

                InputStream is=httpURLConnection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                String line="";
                String response="";
                while((line=br.readLine())!=null)
                {
                    response+=line;
                }

                br.close();
                is.close();
                httpURLConnection.disconnect();
                return response;




            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Intent intent=new Intent(ConfirmationActivity.this,BookinginfoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("Transaction",s);
            intent.putExtra("SelectedCar",selectedcar);
            startActivity(intent);
            Toast.makeText(ConfirmationActivity.this,s,Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }
    }
}
