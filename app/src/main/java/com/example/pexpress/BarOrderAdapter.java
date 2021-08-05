package com.example.pexpress;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pexpress.AdminShowUsers;
import com.example.pexpress.MainActivity;
import com.example.pexpress.Order;
import com.example.pexpress.R;
import com.example.pexpress.RegisterPage;

import com.example.pexpress.add_order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BarOrderAdapter extends ArrayAdapter<Order> {
    private static final String TAG = "UserListAdapter";
    private Context mContext;
    int mResource;
    public BarOrderAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Order> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String OrderCountry = getItem(position).getCountry_from();
        String Address =  getItem(position).getAddress_to();
        Date date = getItem(position).getDate();
        String Status = getItem(position).getStatus();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView orderCountryTV = (TextView) convertView.findViewById(R.id.orderCountryTV);
        TextView addressTV = (TextView) convertView.findViewById(R.id.addressTV);
        TextView dateTV = (TextView) convertView.findViewById(R.id.dateTV);
        TextView statusTV = (TextView) convertView.findViewById(R.id.statusTV);


        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        orderCountryTV.setText(OrderCountry);
        addressTV.setText(Address);
        dateTV.setText(strDate);
        statusTV.setText(Status);


//        setclicklistener(convertView,tvEmail.getText().toString(),tvName.getText().toString(),UID);
        return convertView;
    }

}
