package com.example.kthota.eureQaApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DisplayScheduledStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ResultsDisplay();

    }

    public void ResultsDisplay()
    {
        Bundle bundle = getIntent().getExtras();

        TextView tv = (TextView)findViewById(R.id.header);

        if(bundle.getString("Status").equals("Call is Successful"))
        {
            tv.append("Scheduled Successfully");
            tv.append("\n");
            tv.append(bundle.getString("RunId"));
        }
        else
        {
            tv.setText("Run Scheduling Failed");
        }


    }
    @Override
    public void onBackPressed() {

        Intent intent=new Intent(DisplayScheduledStatus.this,ScheduleRuns.class);
        startActivity(intent);
    }

}
