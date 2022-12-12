package com.example.ws_session1.services.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ws_session1.R;
import com.example.ws_session1.models.BitmapModel;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<BitmapModel> {

    private List<BitmapModel> items_list;
    private int custom_layout_id;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<BitmapModel> objects) {
        super(context, resource, objects);
        items_list = objects;
        custom_layout_id = resource;
    }

    @Override
    public int getCount() {
        return items_list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(custom_layout_id, null);
        }

        ImageView imageView = v.findViewById(R.id.item_image);

        String tmp;
        if (items_list.get(position).getFileTime().equals("0")){
            tmp = "";
        }else{
            tmp = items_list.get(position).getFileTime().split("_")[1];
            tmp = tmp.substring(0,2) + ":" + tmp.substring(2,4);
        }
        imageView.setImageBitmap(items_list.get(position).getBitmap());
        ((TextView)v.findViewById(R.id.item_time)).setText(tmp);
        return v;
    }
}
