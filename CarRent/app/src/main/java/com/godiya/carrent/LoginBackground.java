package com.godiya.carrent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

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

/**
 * Created by DUTT PATEL on 11/1/2017.
 */

class LoginBackground extends AsyncTask<String,Void,String>
{

    Context context;
    public LoginBackground(Context context) {

        this.context=context;
    }

    @Override
    protected String doInBackground(String... params) {
        String pagelink="http://192.168.0.24:7777/test/CarRent/login.php";

        try {

            String username=params[0];
            String password=params[1];
            URL url=new URL(pagelink);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream os=httpURLConnection.getOutputStream();
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            String data= URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                    URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("false"))
        {
            Toast.makeText(context,"Opps Something Wrong",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent=new Intent(context,UserHomeActivity.class);
            intent.putExtra("CustomerDetails",s);
            context.startActivity(intent);
        }
        super.onPostExecute(s);
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
