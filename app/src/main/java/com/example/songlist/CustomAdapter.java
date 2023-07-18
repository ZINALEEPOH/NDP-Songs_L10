package com.example.songlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<Songs> {

    Context context;
    ArrayList<Songs> al;
    int resource;

    public CustomAdapter(Context context, int resource, ArrayList<Songs> al) {
        super(context, resource, al);
        this.context = context;
        this.resource = resource;
        this.al = al;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resource, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvStars = rowView.findViewById(R.id.textViewStars);
        TextView tvSingers = rowView.findViewById(R.id.textViewSingers);

        Songs current = al.get(position);

        tvTitle.setText(current.getTitle());
        tvYear.setText(String.valueOf(current.getYear()));
        tvStars.setText(realStars(current.getStars()));
        tvSingers.setText(current.getSingers());

        return rowView;
    }
    private String realStars(int stars) {
        StringBuilder starsString = new StringBuilder();
        for (int i = 0; i < stars; i++) {
            starsString.append("*");
        }
        return starsString.toString();
    }
}

