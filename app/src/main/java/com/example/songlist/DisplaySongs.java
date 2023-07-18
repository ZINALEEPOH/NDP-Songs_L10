package com.example.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplaySongs extends AppCompatActivity {

    ListView lv;
    ArrayList<Songs> al;
    //ArrayAdapter aa;
    Button btn5Stars;
    CustomAdapter ca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_songs);

        lv = findViewById(R.id.lv);
        btn5Stars = findViewById(R.id.btn5Stars);

        al = new ArrayList<Songs>();
        ca = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(ca);

        DBHelper dbh = new DBHelper(DisplaySongs.this);
        al.clear();
        al.addAll(dbh.getSongs());
        ca.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Songs target = al.get(position);

                Intent intent = new Intent(DisplaySongs.this, ManageSong.class);
                intent.putExtra("data", target);
                startActivity(intent);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(DisplaySongs.this);
                al.clear();
                int filterText = 5;
                al.addAll(dbh.getSongsStar(filterText));

                ca.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(DisplaySongs.this);
        al.clear();
        al.addAll(dbh.getSongs());
        ca.notifyDataSetChanged();
    }
}