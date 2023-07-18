package com.example.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvSingers, tvYear, tvStars;
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        tvSingers = findViewById(R.id.tvSingers);
        tvYear = findViewById(R.id.tvYear);
        tvStars = findViewById(R.id.tvStars);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);

        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                String insertTitle = etTitle.getText().toString();
                String insertSinger = etSingers.getText().toString();
                String insertYear = etYear.getText().toString();
                int finalYear = Integer.parseInt(insertYear);
                int insertStar = 0 ;
                int checkedStars = rgStars.getCheckedRadioButtonId();

                if (checkedStars == R.id.star1){
                    insertStar += 1;
                } else if (checkedStars == R.id.star2) {
                    insertStar += 2;
                } else if (checkedStars == R.id.star3) {
                    insertStar += 3;
                } else if (checkedStars == R.id.star4) {
                    insertStar += 4;
                } else if (checkedStars == R.id.star5) {
                    insertStar += 5;
                }
                db.insertSong(insertTitle,insertSinger,finalYear,insertStar);
                Toast toast = Toast.makeText(btnInsert.getContext(), "Song added successfully", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DisplaySongs.class);
                startActivity(intent);
            }
        });

    }
}