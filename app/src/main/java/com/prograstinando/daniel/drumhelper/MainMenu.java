package com.prograstinando.daniel.drumhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void openMetronome(View view){
        Intent intent = new Intent(this,Metronome.class);
        startActivity(intent);
    }

    public void openRudimentMetronome(View view){
        Intent intent = new Intent(this,RudimentMetronome.class);
        startActivity(intent);
    }
}
