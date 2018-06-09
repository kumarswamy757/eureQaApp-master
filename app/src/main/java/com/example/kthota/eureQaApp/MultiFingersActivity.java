package com.example.kthota.eureQaApp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MultiFingersActivity extends AppCompatActivity {
    int noofFingers=0;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multifingers);

        tv = (TextView)findViewById(R.id.text);
        View view = findViewById(R.id.layout);
        Button clear_button = (Button)findViewById(R.id.clear_button);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction() & MotionEvent.ACTION_MASK;
                switch(action) {
                    case MotionEvent.ACTION_DOWN : {
                        ++noofFingers;
                        Log.d("Fingers","MotionEvent.ACTION_UP "+noofFingers);
                        tv.setText(noofFingers+"");
                        break;
                    }
                    case MotionEvent.ACTION_MOVE : {
                        tv.setTextSize(40);
                        tv.setText(noofFingers+" fingers moving");
                        Log.d("Fingers","MotionEvent.ACTION_MOVE "+noofFingers);
                        break;
                    }
                    case MotionEvent.ACTION_POINTER_DOWN : {
                        ++noofFingers;
                        Log.d("Fingers","MotionEvent.ACTION_UP "+noofFingers);
                        tv.setText(noofFingers+"");
                        break;
                    }
                    case MotionEvent.ACTION_POINTER_UP : {
                        --noofFingers;
                        Log.d("Fingers","MotionEvent.ACTION_UP "+noofFingers);
                        tv.setText(noofFingers+"");
                        break;
                    }
                    case MotionEvent.ACTION_UP : {
                        --noofFingers;
                        Log.d("Fingers","MotionEvent.ACTION_UP "+noofFingers);
                        tv.setText(noofFingers+"");
                        break;
                    }
                }

                return true;
            }

                /*if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    noofFingers++;
                    System.out.println(noofFingers);
                    tv.setText(noofFingers+"");
                    return true;
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    noofFingers--;
                    System.out.println(noofFingers);
                    tv.setText(noofFingers+"");
                    return true;
                }
                return true;*/
        });

    clear_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            noofFingers=0;
            tv.setText(noofFingers+"");
        }
    });
    }
}


