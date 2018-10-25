package com.example.android.educationalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView numbers=(TextView)findViewById(R.id.numbers);
        TextView colors=(TextView)findViewById(R.id.colors);
        TextView family=(TextView)findViewById(R.id.family);
        TextView phrases=(TextView)findViewById(R.id.phrases);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inumbers=new Intent(MainActivity.this,Numbers_Activity.class);
                startActivity(inumbers);
            }
        });
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icolors=new Intent(MainActivity.this,Colors_Activity.class);
                startActivity(icolors);


            }
        });
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ifamily=new Intent(MainActivity.this,Family_Activity.class);
                startActivity(ifamily);


            }
        });
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iphrases=new Intent(MainActivity.this,Phrases_Activity.class);
                startActivity(iphrases);

            }
        });

    }


}
