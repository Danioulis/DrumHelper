package com.prograstinando.daniel.drumhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Metronome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);
    }

    //EditText tempoText = (EditText) findViewById(R.id.editTextTempo);
    int tempo = 60;// = Integer.parseInt(tempoText.getText().toString());

    public void alterTempo(View view){
        /*int aux;
        switch (view.getId()){
            case R.id.buttonPlusOne:
                aux = 1;
                break;
            case R.id.buttonMinusOne:
                aux = -1;
                break;
            case R.id.buttonPlusFive:
                aux = 5;
                break;
            case R.id.buttonMinusFive:
                aux = -5;
                break;
            case R.id.buttonPlusTen:
                aux = 10;
                break;
            case R.id.buttonMinusTen:
                aux = -10;
                break;
            default:
                aux = 0;
                break;
        }
        tempo += aux;
        tempoText.setText(Integer.toString(tempo));*/
    }
}
