package com.godiya.carrent;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class UserHomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;
    LinearLayout pickdate_layout;
    LinearLayout returndate_layout;

    Spinner pick_txt;
    Spinner return_txt;
    Spinner picktime_txt;
    Spinner returntime_txt;
    TextView pickdate_txt;
    TextView pickmonth_txt;
    TextView pickyear_txt;
    TextView returndate_txt;
    TextView returnmonth_txt;
    TextView returnyear_txt;
    Button GetCar_btn;

    String server_response;
    String getcar_server_response;
    String Locationid;
    String RLocationid;
    String CutomerDetails;
    SharedPreferences sharedpreferences;


    //spinner resource
    String location_id;
    String location_name;
    String[] spinnerArray;
    HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();

    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        //config control
        toolbar=(Toolbar)findViewById(R.id.userhome_toolbar);
        imageView=(ImageView)findViewById(R.id.userhome_img);
        pickdate_layout=(LinearLayout)findViewById(R.id.userhome_pickdate_layout);
        returndate_layout=(LinearLayout)findViewById(R.id.userhome_returndate_layout);
        pickdate_txt=(TextView)findViewById(R.id.userhome_pickdate_txt);
        pickmonth_txt=(TextView)findViewById(R.id.userhome_pickmonth_txt);
        returndate_txt=(TextView)findViewById(R.id.userhome_returndate_txt);
        returnmonth_txt=(TextView)findViewById(R.id.userhome_returnmonth_txt);
        pick_txt=(Spinner)findViewById(R.id.userhome_pick_txt);
        return_txt=(Spinner)findViewById(R.id.userhome_return_txt);
        picktime_txt=(Spinner)findViewById(R.id.userhome_picktime_txt);
        returntime_txt=(Spinner)findViewById(R.id.userhome_returntime_txt);
        pickyear_txt=(TextView)findViewById(R.id.userhome_pickyear_txt);
        returnyear_txt=(TextView)findViewById(R.id.userhome_returnyear_txt);
        GetCar_btn=(Button)findViewById(R.id.userhome_getcar_btn);

        //Variable
        CutomerDetails=getIntent().getStringExtra("CustomerDetails");


        //config toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Car");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });


        //Fill time spinner
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.times,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        picktime_txt.setAdapter(adapter);
        returntime_txt.setAdapter(adapter);

       final Date date=new Date();
        String day = (String) DateFormat.format("dd", date);
        pickdate_txt.setText(day);
        returndate_txt.setText(day);
        String month1  = (String) DateFormat.format("MMM",  date);
        pickmonth_txt.setText(month1);
        returnmonth_txt.setText(month1);
        String year1= (String) DateFormat.format("yyyy", date);
        pickyear_txt.setText(year1);
        returnyear_txt.setText(year1);


        Locationid="";


        //share pref
        sharedpreferences = getSharedPreferences("Bookingdetails", Context.MODE_PRIVATE);

        //Calender dialog

        final DatePickerDialog.OnDateSetListener selectdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        final DatePickerDialog.OnDateSetListener rselectdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelr();
            }

        };


        //open Calander dialoag
        pickdate_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UserHomeActivity.this, selectdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        returndate_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UserHomeActivity.this, rselectdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //spinner selection control
        pick_txt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getSelectedItem().toString();
                String id1 = spinnerMap.get(parent.getSelectedItemPosition());
                Locationid=id1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return_txt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getSelectedItem().toString();
                String id1 = spinnerMap.get(parent.getSelectedItemPosition());
                RLocationid=id1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //button click
        GetCar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(UserHomeActivity.this,Locationid,Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("PickLocationId",Locationid);
                editor.putString("PickLocationName",pick_txt.getSelectedItem().toString());
                editor.putString("ReturnLocationId",RLocationid);
                editor.putString("ReturnLocationName",return_txt.getSelectedItem().toString());
                editor.putString("PickDay",pickdate_txt.getText().toString());
                editor.putString("PickYear",pickyear_txt.getText().toString());
                editor.putString("PickMonth",pickmonth_txt.getText().toString());
                editor.putString("ReturnDay",returndate_txt.getText().toString());
                editor.putString("ReturnYear",returnyear_txt.getText().toString());
                editor.putString("ReturnMonth",returnmonth_txt.getText().toString());
                editor.putString("PickTime",picktime_txt.getSelectedItem().toString());
                editor.putString("RetrunTime",returntime_txt.getSelectedItem().toString());
                editor.putString("CustomerId",CutomerDetails);
                editor.commit();
               new getcarbackground().execute(Locationid);
            }
        });

        //Spinner Filling code
        new fillspineerbackground().execute();


    }
    @Override
    public void onBackPressed() {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to Logout");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        //super.onBackPressed();
    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.requestLayout();
            imageView.getLayoutParams().height=500;

        }

    }
    private void updateLabel() {
        String myFormat = "MMM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.setTime(myCalendar.getTime());
        pickdate_txt.setText(DateFormat.format("dd",cal));
        pickmonth_txt.setText(DateFormat.format("MMM",cal));
        pickyear_txt.setText(DateFormat.format("yyyy",cal));
    }
    private void updateLabelr() {
        String myFormat = "MMM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.setTime(myCalendar.getTime());
        returndate_txt.setText(DateFormat.format("dd",cal));
        returnmonth_txt.setText(DateFormat.format("MMM",cal));
        returnyear_txt.setText(DateFormat.format("yyyy",cal));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem PastData = menu.add("Transaction History");

        PastData.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent =new Intent(UserHomeActivity.this,PastTransactionActivity.class);
                startActivity(intent);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    class fillspineerbackground extends AsyncTask<Void,Void,String>
    {
        String pagelink;
        String response="";
        StringBuilder stringBuilder=new StringBuilder();

        @Override
        protected String doInBackground(Void... params) {
            try {

                //open connection
                URL url=new URL(pagelink);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

                httpURLConnection.setDoInput(true);

                InputStream is=httpURLConnection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                //read response
                while((response=br.readLine())!=null)
                {
                    stringBuilder.append(response+"\n");
                }

                // must close
                br.close();
                is.close();
                httpURLConnection.disconnect();


                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            pagelink="http://192.168.0.18:7777/test/CarRent/get_location.php";
        }

        @Override
        protected void onPostExecute(String result) {
            server_response=result;
            loaddata();
            //Toast.makeText(UserHomeActivity.this,result,Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

    class getcarbackground extends AsyncTask<String,Void,String>
    {
        String pagelink;
        String response="";
        StringBuilder stringBuilder=new StringBuilder();
        @Override
        protected void onPreExecute() {
            pagelink="http://192.168.0.18:7777/test/CarRent/get_car.php";
            //super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                //open connection
                URL url=new URL(pagelink);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bf=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("LocationId","UTF-8")+"="+URLEncoder.encode(params[0],"UTF-8");
                bf.write(data);
                bf.flush();
                bf.close();
                os.close();


                InputStream is=httpURLConnection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                //read response
                while((response=br.readLine())!=null)
                {
                    stringBuilder.append(response+"\n");
                }

                // must close
                br.close();
                is.close();
                httpURLConnection.disconnect();


                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onPostExecute(String s) {
            if(s.isEmpty())
            {
                Toast.makeText(UserHomeActivity.this,"No Data",Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent=new Intent(UserHomeActivity.this,CarListActivity.class);
                intent.putExtra("CarDetail",s);
                startActivity(intent);
                //Toast.makeText(UserHomeActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }
    }
    void loaddata()
    {
        if(server_response==null)
        {
            Toast.makeText(UserHomeActivity.this,"No Data Found",Toast.LENGTH_LONG).show();
        }
        else
        {


            try {
                JSONObject jobj=new JSONObject(server_response);
                JSONArray jarray=jobj.getJSONArray("server_response");
                spinnerArray=new String[jarray.length()];
                int count=0;

                while(count<jarray.length())
                {
                    JSONObject dataobj=jarray.getJSONObject(count);
                    location_id=dataobj.getString("Id");
                    location_name=dataobj.getString("Location");
                    spinnerArray[count]=location_name;
                    spinnerMap.put(count,location_id);
                    count++;
                }

                ArrayAdapter<String> adapter =new ArrayAdapter<String>(UserHomeActivity.this,android.R.layout.simple_spinner_item, spinnerArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                pick_txt.setAdapter(adapter);
                return_txt.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
