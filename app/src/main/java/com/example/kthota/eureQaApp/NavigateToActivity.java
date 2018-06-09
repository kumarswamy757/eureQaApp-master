package com.example.kthota.eureQaApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavigateToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate_to);

        NavigateToDesiredActivity();
    }

    protected void NavigateToDesiredActivity()
    {
        Button scheduleRun=(Button)findViewById(R.id.scheduleRun);
        Button getRunStatus=(Button)findViewById(R.id.getRunStatus);
        Button abortRun=(Button)findViewById(R.id.abortRun);


//        Click Schedule Run
        scheduleRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(NavigateToActivity.this,ScheduleRuns.class);
                startActivity(intent);
            }
        });

//        Click Get Run Status
        getRunStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(NavigateToActivity.this,GetRunStatusDataEntry.class);
                startActivity(intent);
            }
        });

//        Click Abort Run
        abortRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(NavigateToActivity.this, ScheduleRuns.class);
                startActivity(intent);
            }
        });

    }
     @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
