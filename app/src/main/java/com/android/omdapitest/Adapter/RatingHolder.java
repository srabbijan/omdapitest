package com.android.omdapitest.Adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.omdapitest.R;

public class RatingHolder extends RecyclerView.ViewHolder {
    TextView Value,Source;
    LinearLayout rev;
    public RatingHolder(View item) {
        super(item);
        Value = item.findViewById(R.id.Value);
        Source = item.findViewById(R.id.Source);
    }
}
