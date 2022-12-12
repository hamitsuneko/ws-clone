package com.example.ws_session1.services.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ws_session1.R;
import com.example.ws_session1.models.FeelingsResponse;

import java.util.List;

public class RecyclerCustomAdapter  extends RecyclerView.Adapter<RecyclerCustomAdapter.MyViewHolder>{

    private Context context;
    private List<FeelingsResponse.FeelingModel> models;

    public RecyclerCustomAdapter(Context context, List<FeelingsResponse.FeelingModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feelings_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FeelingsResponse.FeelingModel feelingModel = models.get(position);
        holder.feel_text.setText(feelingModel.getTitle());
        Glide.with(context).load(feelingModel.getImage()).into(holder.feel_image);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView feel_image;
        private TextView feel_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            feel_image = itemView.findViewById(R.id.feel_icon);
            feel_text = itemView.findViewById(R.id.feel_text);
        }

        public ImageView getFeel_image() {
            return feel_image;
        }

        public TextView getFeel_text() {
            return feel_text;
        }
    }
}
