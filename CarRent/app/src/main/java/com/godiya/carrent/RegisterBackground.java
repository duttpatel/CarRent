package com.godiya.carrent;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by DUTT PATEL on 10/31/2017.
 */

public class RegisterBackground extends AsyncTask<String,Void,String>{

    Context context;
    public RegisterBackground(Context context) {
        this.context=context;
    }

    @Override
    protected String doInBackground(String... params)
    {

        String pagelink="http://192.168.0.18:7777/test/CarRent/UserRegistration.php";
        try {
            String firstname=params[0];
            String lastname=params[1];
            String gender=params[2];
            String address=params[3];
            String city=params[4];
            String pincode=params[5];
            String contact=params[6];
            String email=params[7];
            String license=params[8];
            String password=params[9];
            URL url=new URL(pagelink);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os=httpURLConnection.getOutputStream();
            BufferedWriter bf=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            String data= URLEncoder.encode("FirstName","UTF-8")+"="+URLEncoder.encode(firstname,"UTF-8")+"&"+
                    URLEncoder.encode("LastName","UTF-8")+"="+URLEncoder.encode(lastname,"UTF-8")+"&"+
                    URLEncoder.encode("Gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"+
                    URLEncoder.encode("Address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                    URLEncoder.encode("City","UTF-8")+"="+URLEncoder.encode(city,"UTF-8")+"&"+
                    URLEncoder.encode("Pincode","UTF-8")+"="+URLEncoder.encode(pincode,"UTF-8")+"&"+
                    URLEncoder.encode("Contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"+
                    URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                    URLEncoder.encode("License","UTF-8")+"="+URLEncoder.encode(license,"UTF-8")+"&"+
                    URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            bf.write(data);
            bf.flush();
            bf.close();
            os.close();
            InputStream is=httpURLConnection.getInputStream();
            is.close();
            httpURLConnection.disconnect();
            return "Register Successfully";

        } catch (Exception e) {
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        Toast.makeText(context,"SUCCESSFULLY REGISTER",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
