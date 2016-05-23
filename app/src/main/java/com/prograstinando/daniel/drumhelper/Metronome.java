package com.prograstinando.daniel.drumhelper;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Metronome extends AppCompatActivity {

    Handler metronomeHandler = new Handler();
    Button buttonPlus1, buttonPlus5, buttonPlus10, buttonMinus1, buttonMinus5, buttonMinus10;
    Button buttonTempo;
    private TextView resultText;
    int tempo = 60;// = Integer.parseInt(tempoText.getText().toString());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);
        buttonTempo = (Button) findViewById(R.id.buttonTempo);
        buttonTempo.setText(Integer.toString(tempo));
        buttonTempo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTempo();
            }
        });
    }

    //EditText tempoText = (EditText) findViewById(R.id.editTextTempo)

    public void alterTempo(View view){
        int aux;
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
        buttonTempo.setText(Integer.toString(tempo));
    }

    protected void inputTempo(){
        LayoutInflater layoutInflater = LayoutInflater.from(Metronome.this);
        View promptView = layoutInflater.inflate(R.layout.activity_input_tempo,null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Metronome.this);
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.tempoDialogInput);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        resultText.setText(editText.getText());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
