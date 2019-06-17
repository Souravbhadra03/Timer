package com.w.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    MediaPlayer timeUp;
    public void updateTime(int time)
    {
        int minutes= time/60;
        int second= time%60;
        String min=Integer.toString(minutes),sec=Integer.toString(second);
        if(minutes!=10)
            min="0"+minutes;
        if(second<10)
            sec="0"+second;
        textView.setText(min+":"+sec);
    }

    public void timeCount(View view)
    {
        Log.i("Buuton:", "Pressed");
        new CountDownTimer(seekBar.getProgress()*1000,1000)
        {
            @Override
            public void onTick(long l) {
                updateTime((int) l/1000);

            }

            @Override
            public void onFinish() {
                timeUp.start();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar= findViewById(R.id.seekBar);
        timeUp = MediaPlayer.create(this,R.raw.timeup);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        textView=findViewById(R.id.textView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              updateTime(i);
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
