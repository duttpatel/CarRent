package com.godiya.carrent;

import android.content.Context;
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
 * Created by DUTT PATEL on 11/4/2017.
 */

public class CarListAdapter extends ArrayAdapter<Car> implements View.OnClickListener{

    List<Car> carlist;
    Context context;
    public CarListAdapter(ArrayList<Car> carlist,Context context)
    {
        super(context,R.layout.searchcar_row_layout,carlist);
        this.context=context;
        this.carlist=carlist;
    }

    @Override
    public void onClick(View view) {
        int position =(Integer)view.getTag();
        Object obj=getItem(position);
        Car selectedAgenet=(Car)obj;
        switch(view.getId())
        {

        }
    }

    @Override
    public void add(@Nullable Car object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Car getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Car car1=getItem(position);
        ViewHolder vhld;
        final View result;
        if(convertView==null)
        {
            vhld=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.searchcar_row_layout,parent,false);
            vhld.CompanyName=(TextView)convertView.findViewById(R.id.row_carname_txt);
            vhld.Seats=(TextView)convertView.findViewById(R.id.row_seat_txt);
            vhld.Rate=(TextView)convertView.findViewById(R.id.row_rate_txt);
            vhld.Mileage=(TextView)convertView.findViewById(R.id.row_mileage_txt);
            vhld.Gear=(TextView)convertView.findViewById(R.id.row_geartype_txt);
            vhld.CarId=(TextView)convertView.findViewById(R.id.row_carid_txt);
            vhld.CarImg=(ImageView)convertView.findViewById(R.id.row_car_img);
            result=convertView;
            convertView.setTag(vhld);
        }
        else
        {
            vhld=(ViewHolder)convertView.getTag();
            result=convertView;
        }
        vhld.CompanyName.setText(car1.getCompanyName()+" "+car1.getModelName());
        vhld.Seats.setText("Seats : "+car1.getSeats());
        vhld.Rate.setText(car1.getRate()+" /day");
        vhld.Mileage.setText("Milage : "+car1.getMileage());
        vhld.Gear.setText("Gear : "+car1.getGear());
        return convertView;
    }



    private static class ViewHolder
    {
        TextView CompanyName;
        TextView Seats;
        TextView Rate;
        TextView Mileage;
        TextView Gear;
        TextView CarId;
        ImageView CarImg;
    }

}
