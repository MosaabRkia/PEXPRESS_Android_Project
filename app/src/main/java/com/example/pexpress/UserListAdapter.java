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
import com.example.pexpress.R;
import com.example.pexpress.RegisterPage;
import com.example.pexpress.User;
import com.example.pexpress.add_order;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    private static final String TAG = "UserListAdapter";
    private Context mContext;
    int mResource;
    public UserListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       String firstName = getItem(position).getFirstName();
       String lastName =  getItem(position).getLastName();
       String email = getItem(position).getEmail();
       String password = getItem(position).getPassword();
        String UID = getItem(position).getUID();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvName = (TextView) convertView.findViewById(R.id.nameTV);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.emailTV);


        tvName.setText(firstName + " " + lastName);
        tvEmail.setText(email);
        setclicklistener(convertView,tvEmail.getText().toString(),tvName.getText().toString(),UID);
        return convertView;
    }

    private void setclicklistener(View convertView,String tvEmail,String tvName,String UID) {
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, add_order.class);
                intent.putExtra("email",tvEmail);
                intent.putExtra("fullName",tvName);
                intent.putExtra("UID",UID);
                mContext.startActivity(intent);
            }
        });
    }
}
