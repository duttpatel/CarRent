package com.godiya.carrent;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DUTT PATEL on 11/9/2017.
 */

public class PastTransactionAdapter extends ArrayAdapter<Trasaction> {
    List<Trasaction> transaction;
    Context context;

    public PastTransactionAdapter(ArrayList<Trasaction> transaction, Context context) {
        super(context, R.layout.pasttransaction_row_layout, transaction);
        this.context = context;
        this.transaction = transaction;
    }


    public PastTransactionAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Trasaction getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Trasaction temp=getItem(position);
        ViewHolder viewhld;
        final View result;
        if(convertView==null) {
            viewhld=new PastTransactionAdapter.ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.pasttransaction_row_layout,parent,false);
            viewhld.Id=(TextView)convertView.findViewById(R.id.past_id_txt);
            viewhld.PickupDate=(TextView)convertView.findViewById(R.id.past_pickupdate_txt);
            viewhld.ReturnDate=(TextView)convertView.findViewById(R.id.past_returndate_txt);
            viewhld.Amount=(TextView)convertView.findViewById(R.id.past_amount_txt);
            viewhld.Status=(TextView)convertView.findViewById(R.id.past_status_txt);
            result=convertView;
            convertView.setTag(viewhld);
        }
        else {
            viewhld=(PastTransactionAdapter.ViewHolder)convertView.getTag();
            result=convertView;

        }

        viewhld.Id.setText(temp.getId());
        viewhld.ReturnDate.setText(temp.getReturnDate());
        viewhld.PickupDate.setText(temp.getPickupDate());
        viewhld.Amount.setText(temp.getAmount());
        if(temp.getStatus().equals("NO"))
        {
            viewhld.Status.setTextColor(Color.RED);
            viewhld.Status.setText("Not Paid");
        }
        else
        {
            viewhld.Status.setText("Paid");
            viewhld.Status.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView Id;
        TextView PickupDate;
        TextView ReturnDate;
        TextView Amount;
        TextView Status;
    }


}