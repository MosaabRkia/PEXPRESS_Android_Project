package com.example.pexpress;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
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

public class BarNewsAdapter extends ArrayAdapter<News> {
    private static final String TAG = "UserListAdapter";
    private Context mContext;
    int mResource;
    public BarNewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<News> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String url = getItem(position).getUrl();
            String title =getItem(position).getTitle();
            String description = getItem(position).getDescription();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.photoNews);
        TextView tittleTV = (TextView) convertView.findViewById(R.id.tittleNews);
        TextView descriptionTV = (TextView) convertView.findViewById(R.id.descriptionNews);

        Glide.with(mContext).load(url).into(imageView);
        tittleTV.setText(title);
        descriptionTV.setText(description);

        return convertView;
    }

}
