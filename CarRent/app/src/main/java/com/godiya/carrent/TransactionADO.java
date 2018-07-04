package com.godiya.carrent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DUTT PATEL on 11/9/2017.
 */

public class TransactionADO {
    String transactionDetails;

    public TransactionADO(String transactionDetails) {
        this.transactionDetails = transactionDetails;
        System.out.print(transactionDetails);
    }

    public ArrayList<Trasaction> getTransaction() throws JSONException {
        ArrayList<Trasaction> transaction = new ArrayList<Trasaction>();
        JSONObject jsonObject;
        JSONArray jsonArray;
        jsonObject=new JSONObject(transactionDetails);
        jsonArray=jsonObject.getJSONArray("Response");
        int count=0;
        while(count<jsonArray.length())
        {

            JSONObject obj=jsonArray.getJSONObject(count);
            String Id;
            String PickupDate;
            String ReturnDate;
            String Amount;
            String Status;

            Id=obj.getString("Id");
            PickupDate=obj.getString("PickupDate");
            ReturnDate=obj.getString("ReturnDate");
            Amount=obj.getString("Amount");
            Status=obj.getString("PaymentStatus");

            Trasaction car=new Trasaction(Id,PickupDate,ReturnDate,Amount,Status);
            transaction.add(car);
            count++;
        }
        return  transaction;
    }
}
