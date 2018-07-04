package com.godiya.carrent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DUTT PATEL on 11/4/2017.
 */

public class CarAdo {
    String CarDetail;


    //json resource



    public CarAdo(String carDetail) {
        this.CarDetail = carDetail;
    }
    public ArrayList<Car> getCars() throws JSONException {
        JSONObject jsonObject;
        JSONArray jsonArray;
        ArrayList<Car> Cars=new ArrayList<Car>();
        jsonObject=new JSONObject(CarDetail);
        jsonArray=jsonObject.getJSONArray("car_list_server_response");
        int count=0;
        while(count<jsonArray.length())
        {

            JSONObject obj=jsonArray.getJSONObject(count);
            String CarId;
            String CompanyName;
            String ModelName;
            String Seats;
            String Rate;
            String Mileage;
            String Gear;
            String Path;

            CarId=obj.getString("CarId");
            CompanyName=obj.getString("CompanyName");
            ModelName=obj.getString("ModelName");
            Seats=obj.getString("Seats");
            Rate=obj.getString("Rate");
            Mileage=obj.getString("Mileage");
            Gear=obj.getString("GearType");
            Path=obj.getString("Path");

            Car car=new Car(CarId,CompanyName,ModelName,Seats,Rate,Mileage,Gear,Path);
            Cars.add(car);
            count++;
        }

        return  Cars;
    }


}
