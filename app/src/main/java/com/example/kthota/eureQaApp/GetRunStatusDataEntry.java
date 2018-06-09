package com.example.kthota.eureQaApp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;

public class GetRunStatusDataEntry extends AppCompatActivity {

    private EditText domain;
    private EditText username;
    private EditText password;
    private EditText testingContextKey;
    private View login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_run_status_data_entry);

        Context context=new GetRunStatusDataEntry();
        clearCookiesAndCache(context);

        domain = (EditText) findViewById(R.id.domain);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        testingContextKey = (EditText) findViewById(R.id.testingKey);
        login = (View) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String list=null;

                try {
//                    GetRunsStatus getRunsStatus=new GetRunsStatus();
//                    getRunsStatus.setTextToUI(domain.getText().toString(),username.getText().toString(),password.getText().toString(),testingContextKey.getText().toString());

                    RetrieveGetTask retrieveGetTask=new RetrieveGetTask();
                    String response=null;
                    try {
//        domain  username  password  runId
                        list=retrieveGetTask.execute(domain.getText().toString(),username.getText().toString(),password.getText().toString(),testingContextKey.getText().toString()).get();
                        Log.d(":::::",list);

                        if(list.endsWith("Success,Retrieved Successfully"))
                        {

                            Bundle bundle=new Bundle();
                            bundle.putString("list", list);

                            Intent intent=new Intent(GetRunStatusDataEntry.this,GetRunsStatus.class);
                            intent.putExtras(bundle);

                            startActivity(intent);

                        }
                    }catch (Exception e)
                    {

                    }


                }catch (Exception e)
                {

                }
            }
        });
    }


    public void clearCookiesAndCache(Context context){
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies(null);
        }
        else {
            cookieManager.removeAllCookie();
        }
    }
}
