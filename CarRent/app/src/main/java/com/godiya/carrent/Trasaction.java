package com.godiya.carrent;

/**
 * Created by DUTT PATEL on 11/9/2017.
 */

public class Trasaction {
    String Id;
    String PickupDate;
    String ReturnDate;
    String Amount;
    String Status;

    public Trasaction() {
    }

    public Trasaction(String id, String pickupDate, String returnDate, String amount, String status) {
        Id = id;
        PickupDate = pickupDate;
        ReturnDate = returnDate;
        Amount = amount;
        Status = status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPickupDate() {
        return PickupDate;
    }

    public void setPickupDate(String pickupDate) {
        PickupDate = pickupDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
