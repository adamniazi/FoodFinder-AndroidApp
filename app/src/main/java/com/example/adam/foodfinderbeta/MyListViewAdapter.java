package com.example.adam.foodfinderbeta;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyListViewAdapter extends ArrayAdapter<ResListRow>{

   // Context context;

    public MyListViewAdapter(Context context, ArrayList<ResListRow> rests){
        super(context, 0, rests);
        //this.context = context;
    }

    private class ViewHolder{
        TextView resName;
        TextView address;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        //ViewHolder holder = null;

        ResListRow res = getItem(position);

        //LayoutInflater mInflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            //convertView = mInflator.inflate(R.layout.res_row, null);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.res_row, parent, false);
            // holder = new ViewHolder();
            /*holder.resName = (TextView) convertView.findViewById(R.id.resName);
            holder.address = (TextView) convertView.findViewById(R.id.resAddress);
            convertView.setTag(holder);
            holder.address.setText(ResListRow.getAddress());
        } /*else {
            holder = (ViewHolder) convertView.getTag();
        }*/
        }
        TextView name = (TextView) convertView.findViewById(R.id.resName);
        TextView address = (TextView) convertView.findViewById(R.id.resAddress);
        name.setText(res.resName);
        address.setText(res.resAddress);
        return convertView;
    }
}
