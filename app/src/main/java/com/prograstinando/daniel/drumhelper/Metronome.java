package com.prograstinando.daniel.drumhelper;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Metronome extends AppCompatActivity {

    Handler metronomeHandler = new Handler();
    Button buttonPlus1, buttonPlus5, buttonPlus10, buttonMinus1, buttonMinus5, buttonMinus10;
    Button buttonTempo, buttonStartStop;
    CheckBox checkBoxSound, checkBoxFlash;
    ImageView flash;
    ToneGenerator generator;
    int tempo = 60;
    Boolean active = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);
        generator = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        flash = (ImageView) findViewById(R.id.flash);
        checkBoxSound = (CheckBox) findViewById(R.id.checkBoxSound);
        buttonTempo = (Button) findViewById(R.id.buttonTempo);
        buttonTempo.setText(Integer.toString(tempo));
        buttonTempo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                inputTempo();
            }
        });
        buttonStartStop = (Button) findViewById(R.id.buttonStartStop);
        buttonStartStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(active){
                    active = !active;
                    metronomeHandler.removeCallbacks(runnable);
                }
                else {
                    active = !active;
                    metronomeHandler.post(runnable);
                }
            }
        });
    }

    //EditText tempoText = (EditText) findViewById(R.id.editTextTempo)

    public void alterTempo(View view){
        int aux;
        switch (view.getId()){
            /*case R.id.buttonTempo:
                break;*/
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
        editText.setHint(Integer.toString(tempo));
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if (Integer.parseInt(editText.getText().toString()) > 0) {
                                tempo = Integer.parseInt(editText.getText().toString());
                                buttonTempo.setText(Integer.toString(tempo));
                            }
                        }
                        catch(Exception e){
                            Boolean valid = false;
                            messageBox("Exception:", "Invalid number.");
                        }
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

    private void messageBox(String method, String message)
    {
        Log.d("EXCEPTION: " + method,  message);

        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(checkBoxSound.isChecked()) generator.startTone(ToneGenerator.TONE_CDMA_ONE_MIN_BEEP, 20);
            metronomeHandler.postDelayed(runnable, 60000/tempo);
        }
    };
}
