package com.example.timer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    Boolean active=false;
    Button go;
    CountDownTimer countDownTimer;
public void reset(){
    textView.setText("0:30");
    seekBar.setProgress(30);
    seekBar.setEnabled(true);
    countDownTimer.cancel();
    go.setText("GO!");

    active=false;
}
    public  void buttonclick (View view){
        if (active){
           reset();
        }else {


            active = true;
            seekBar.setEnabled(false);
            go.setText("STOP!");

            countDownTimer= new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updatetimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.you);
                    mediaPlayer.start();
                    reset();
                }
            }.start();

        }
    }
public void updatetimer(int secondsleft){
    int minutes= secondsleft/60;
    int seconds =secondsleft-(minutes*60);

    String secondString =Integer.toString(minutes);

    if(seconds<=9){
        secondString="0" +secondString;
    }

    textView.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        seekBar=findViewById(R.id.seekBar);
         textView=findViewById(R.id.textView);
         go=findViewById(R.id.button);



        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
            updatetimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
