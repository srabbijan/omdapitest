package com.android.omdapitest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.omdapitest.R;
import com.android.omdapitest.Services.Model.RatingModel;

import java.util.ArrayList;
import java.util.List;

public class Rating_Adapter extends RecyclerView.Adapter {
    List<RatingModel> models = new ArrayList<>();
    Context context;

    public Rating_Adapter(List<RatingModel> models, Context context) {
        this.models = models;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rating_list_card,parent,false);
        return new RatingHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RatingModel model = models.get(position);
        final RatingHolder itemHolder = (RatingHolder) holder;

        itemHolder.Source.setText(model.getSource());
        itemHolder.Value.setText(model.getValue());


    }

    @Override
    public int getItemCount() {
        if (models!=null){
            return models.size();
        }
        return 0;
    }
}
