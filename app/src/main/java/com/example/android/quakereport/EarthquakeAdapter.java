package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by senamit on 20/9/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeItems> {


    public EarthquakeAdapter(Context context, ArrayList<EarthquakeItems> earthquakeItemses ){

        super(context, 0, earthquakeItemses);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView =convertView;

        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_items, parent, false);


        }


        EarthquakeItems currentEarthquakeItems = getItem(position);

        TextView txt_mag = (TextView) listItemView.findViewById(R.id.txt_mag);
//        txt_mag.setText(currentEarthquakeItems.getMagnitude());

        String magnitude = magnitudeFormatter(currentEarthquakeItems.getMagnitude());
        txt_mag.setText(magnitude);




        TextView txt_direction = (TextView) listItemView.findViewById(R.id.txt_direction);
        TextView txt_place = (TextView) listItemView.findViewById(R.id.txt_place);
//        txt_location.setText(currentEarthquakeItems.getLocation());


        String split = currentEarthquakeItems.getLocation();

        String[] parts = split.split("of");


        String part1 = parts[0];


        String part2 = parts[1];

        txt_direction.setText(part1);
       txt_place.setText(part2);




        TextView txt_time = (TextView)listItemView.findViewById(R.id.txt_time);
        TextView txt_date = (TextView)listItemView.findViewById(R.id.txt_date);

        Date dateObject = new Date(currentEarthquakeItems.getTime());


        String formattedDate = formatDate(dateObject);

        String formattedTime = formatTime(dateObject);

        txt_date.setText(formattedDate);
        txt_time.setText(formattedTime);

        return listItemView;

    }

    private String magnitudeFormatter(double magnitude) {

        DecimalFormat formatter = new DecimalFormat("0.0");
        return  formatter.format(magnitude);


    }

    private String formatTime(Date dateObject) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
