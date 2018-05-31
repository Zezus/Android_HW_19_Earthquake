package com.example.HW_19_Earthquake;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by абишевас on 24.04.2018.
 */

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.ViewHolder> {

    private final Context context;
    private final List<Feature> features;

    public EarthquakeAdapter(Context context, List<Feature> features) {

        this.context = context;
        this.features = features;
    }

    @Override
    public EarthquakeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.view_earthquake, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EarthquakeAdapter.ViewHolder holder, int position) {
        int mag = features.get(position).getProperties().getMag().intValue();
        holder.magnitude.setText(String.valueOf(mag));
        switch (mag) {
            case 0:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_dark_green, null));
                break;

            case 1:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_green, null));
                break;

            case 2:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_light_green, null));
                break;

            case 3:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_lime, null));
                break;

            case 4:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_yellow, null));
                break;

            case 5:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_amber, null));
                break;

            case 6:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_orange, null));
                break;

            case 7:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_deep_orange, null));
                break;

            case 8:
                holder.magnitude.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.rounded_textview_red, null));
                break;
        }
        holder.place.setText(features.get(position).getProperties().getPlace());

        SpannableString content = new SpannableString("Детали");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        holder.near.setText(content);
        holder.near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(features.get(position).getProperties().getUrl()));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(features.get(position).getProperties().getTime());

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        holder.time.setText(mYear + "/" + (mMonth + 1) + "/" + mDay);
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView magnitude;
        private final TextView place;
        private final TextView near;
        private final TextView time;

        public ViewHolder(View itemView) {
            super(itemView);

            magnitude = itemView.findViewById(R.id.magnitude_text_view_view_earthquake);
            place = itemView.findViewById(R.id.place_text_view_view_earthquake);
            near = itemView.findViewById(R.id.near_text_view_view_earthquake);
            time = itemView.findViewById(R.id.time_text_view_view_earthquake);
        }
    }
}
