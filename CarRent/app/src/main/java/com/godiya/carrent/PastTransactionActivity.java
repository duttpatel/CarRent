package com.godiya.carrent;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

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
import java.util.ArrayList;

public class PastTransactionActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_transaction);

        toolbar=(Toolbar)findViewById(R.id.past_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tarnsaction History");
        toolbar.setNavigationIcon(R.drawable.backarrow);
        toolbar.setTitleTextColor(Color.WHITE);
        listView=(ListView) findViewById(R.id.past_transaction_list);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

        Backgound backgound=new Backgound();
        backgound.execute("3");


    }


    class Backgound extends AsyncTask<String,Void,String>
    {
        String pagelink;
        String response="";
        StringBuilder stringBuilder=new StringBuilder();
        @Override
        protected void onPreExecute() {
            pagelink="http://192.168.0.18:7777/test/CarRent/pasttransaction.php";
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
                String data= URLEncoder.encode("CustomerId","UTF-8")+"="+URLEncoder.encode(params[0],"UTF-8");
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
            TransactionADO ado = new TransactionADO(s);
            ArrayList<Trasaction> trans= new ArrayList<Trasaction>();
            try {
                trans=ado.getTransaction();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PastTransactionAdapter adapter = new PastTransactionAdapter(trans,getApplicationContext());
            listView.setAdapter(adapter);
        }
    }

}
