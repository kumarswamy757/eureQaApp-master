package com.example.kthota.eureQaApp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class GetRunsStatus extends AppCompatActivity {
    private View mainactivity;
    String list=null;

    /*static{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_runs_status);

        Bundle extras=getIntent().getExtras();
        String str=extras.getString("list");

        Log.d("Got data",str);

        list=str;

        TextView runId = (TextView)findViewById(R.id.runId);
        TextView rdname = (TextView)findViewById(R.id.rdName);
        TextView executionStatus = (TextView)findViewById(R.id.executionStatus);
        TextView scheduledBy = (TextView)findViewById(R.id.scheduledBy);
        TextView startTime = (TextView)findViewById(R.id.startTime);
        TextView endTime = (TextView)findViewById(R.id.endTime);
        TextView executionTime = (TextView)findViewById(R.id.executionTime);
        TextView queuedTime = (TextView)findViewById(R.id.queuedTime);

        TextView environment = (TextView)findViewById(R.id.environment);
        TextView cloudPlatform = (TextView)findViewById(R.id.cloud);
        TextView operatingSystem = (TextView)findViewById(R.id.OS);

        TextView scTotal = (TextView)findViewById(R.id.sceTotal);
        TextView scPassed = (TextView)findViewById(R.id.scePassed);
        TextView scPassPer = (TextView)findViewById(R.id.scePassP);

        TextView scrTotal = (TextView)findViewById(R.id.scrTotal);
        TextView scrPassed = (TextView)findViewById(R.id.scrPassed);
        TextView scrPassPer = (TextView)findViewById(R.id.scrPassP);

        TextView inTotal = (TextView)findViewById(R.id.inTotal);
        TextView inPassed = (TextView)findViewById(R.id.inPassed);
        TextView inPassPer = (TextView)findViewById(R.id.inPassP);

        String split[]=list.split(",");

        runId.setText(split[0]);
        rdname.setText(split[1]);
        executionStatus.setText(split[2]);
        scheduledBy.setText(split[3]);
        startTime.setText(split[4]);
        endTime.setText(split[5]);
        executionTime.setText(split[6]);
        queuedTime.setText(split[7]);

        environment.setText(split[8]);
        cloudPlatform.setText(split[9]);
        operatingSystem.setText(split[10]);

        scTotal.setText(split[11]);
        scPassed.setText(split[12]);
        scPassPer.setText(split[13]);

        scrTotal.setText(split[14]);
        scrPassed.setText(split[15]);
        scrPassPer.setText(split[16]);

        inTotal.setText(split[17]);
        inPassed.setText(split[18]);
        inPassPer.setText(split[19]);



    }



}
