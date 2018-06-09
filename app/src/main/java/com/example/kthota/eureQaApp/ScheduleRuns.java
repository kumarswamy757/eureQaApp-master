package com.example.kthota.eureQaApp;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;
//import javax.xml.bind.DatatypeConverter;


import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;

public class ScheduleRuns extends AppCompatActivity  {
    private EditText domain;
    private EditText username;
    private EditText password;
    protected TextView message;
    private EditText testingContextKey;
    private EditText status;

    private View login;
    private View mainactivity;


//    LoginActivity loginActivity=new LoginActivity();


    String URL = "http://192.168.0.49:8080/eureQaExecutionsRestApi/api/rest/authenticationService";
        /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    static final Integer WRITE_EXST = 0x3;
    static final Integer INT = 0x3;
    static final Integer READ_EXST = 0x4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ask();
        Login();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void Login() {

        // get edittext component
        domain = (EditText) findViewById(R.id.domain);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        testingContextKey = (EditText) findViewById(R.id.testingKey);
        status = (EditText) findViewById(R.id.status);
        login = (View) findViewById(R.id.login);
        message = (TextView)findViewById(R.id.message);
        mainactivity = (View) findViewById(R.id.activity_main);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(attemptLogin()) {
                        showProgress(true);
                        auth();
                    }
//                        Thread.sleep(10000);
//                    Toast.makeText(MultiFingersActivity.this,"Clicked", Toast.LENGTH_LONG).show();
                }catch (Exception e)
                {

                }
            }
        });

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    protected void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mainactivity.setVisibility(show ? View.GONE : View.VISIBLE);
            mainactivity.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mainactivity.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mainactivity.setVisibility(show ? View.VISIBLE : View.GONE);
            mainactivity.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mainactivity.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mainactivity.setVisibility(show ? View.VISIBLE : View.GONE);
            mainactivity.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



    /*public void onCreate1() {
//        Toast.makeText(MultiFingersActivity.this,"Failed",Toast.LENGTH_LONG).show();
//        setContentView(R.layout.activity_main);

		*//* START Test/Play with beanstalk API *//*
        String username = "kthota";
        String host = "http://192.168.0.43:8080/eureQaExecutionsRestApi/api/rest/authenticationService";
        String password = "99896605";

        String urlBasePath = host;
        String urlApiCall_FindAllRepositories = urlBasePath;

        try {
            HttpClient client = new DefaultHttpClient();

            AuthScope as = new AuthScope(host, 443);
            UsernamePasswordCredentials upc = new UsernamePasswordCredentials(
                    username, password);

            ((AbstractHttpClient) client).getCredentialsProvider()
                    .setCredentials(as, upc);

            BasicHttpContext localContext = new BasicHttpContext();

            BasicScheme basicAuth = new BasicScheme();
            localContext.setAttribute("preemptive-auth", basicAuth);

            HttpHost targetHost = new HttpHost(host, 443, "https");

            HttpGet httpget = new HttpGet(urlApiCall_FindAllRepositories);
            httpget.setHeader("Content-Type", "application/json");

            Toast.makeText(MultiFingersActivity.this,"Failed",Toast.LENGTH_LONG).show();

            HttpResponse response = client.execute(targetHost, httpget,
                    localContext);

            HttpEntity entity = response.getEntity();
            Object content = EntityUtils.toString(entity);

            Toast.makeText(MultiFingersActivity.this,content.toString(),Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(MultiFingersActivity.this,"Failed",Toast.LENGTH_LONG).show();
//            Log.d(MY_APP_TAG, "Error: " + e.getMessage());
        }

		*//* END Test/Play with beanstalk API *//*
    }
*/

public void auth()throws IOException
{

//    Toast.makeText(MultiFingersActivity.this,domain.getText(),Toast.LENGTH_LONG).show();
    JsEvaluator jsEvaluator = new JsEvaluator(this);
    jsEvaluator.evaluate("function fun() {\n" +
            "    var xhr = new XMLHttpRequest();\n" +
            "    xhr.open(\"POST\", \"https://www.eureqatest.com/rest/v1/"+domain.getText()+"/executions\", false);\n" +
            "    xhr.setRequestHeader('Content-Type', 'application/json');\n" +
            "    xhr.setRequestHeader(\"Authorization\", \"Basic \" + btoa('"+username.getText()+":"+password.getText()+"'));\n" +
            "    xhr.setRequestHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');\n" +
            "    xhr.setRequestHeader(\"Access-Control-Allow-Credentials\", \"true\");\n" +
            "    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');\n" +
            "    xhr.send('{\"RunDefinitionTestingContextKeys\":[{\"id\":\""+testingContextKey.getText()+"\",\"status\":\""+status.getText()+"\"}]}');\n" +
            "    return xhr.responseText;\n" +
            "    \n" +
            "}\n" +
            "fun();", new JsCallback() {
        @Override
        public void onResult(String Response) {

//            Toast.makeText(MultiFingersActivity.this,Response,Toast.LENGTH_LONG).show();
            Log.d("Response",Response);
            //JSON Parsing
            try {
                org.json.JSONObject obj = new org.json.JSONObject(Response);
                String status=obj.get("status").toString();

                if (status.equals("Success"))
                {
                    if(obj.getJSONArray("testingContextKeys").getJSONObject(0).get("statusMessage").toString().equals("Call is Successful"))
                    {
                        String RunId=obj.getJSONArray("testingContextKeys").getJSONObject(0).get("runId").toString();
                        Intent intent=new Intent(ScheduleRuns.this,DisplayScheduledStatus.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("RunId", RunId);
                        bundle.putString("Status", obj.getJSONArray("testingContextKeys").getJSONObject(0).get("statusMessage").toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        showProgress(false);
                        Toast.makeText(ScheduleRuns.this,RunId,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(ScheduleRuns.this,"Run Schedule failed",Toast.LENGTH_LONG).show();
                    }
                }else{
                    message.setTextColor(Color.RED);
                    message.setText(obj.get("message").toString().toUpperCase());

                    Timer t = new Timer(false);
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    message.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    }, 5000);


                }


            }catch (Exception e)
            {
                showProgress(false);
            }
//

//            Toast.makeText(MultiFingersActivity.this,Response,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(String s) {
            Toast.makeText(ScheduleRuns.this,s,Toast.LENGTH_LONG).show();
        }
    });


}

        // add a keylistener to keep track user input
    /*login.setOnKeyListener(new OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
//            String DisplayText = "";


            // if keydown and "enter" is pressed
            *//*if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_0)) {

                // display a floating message
                Toast.makeText(MultiFingersActivity.this,
                        domain.getText()+" "+username.getText()+" "+password.getText(), Toast.LENGTH_LONG).show();


                DisplayText = domain.getText().toString();
                writeToFile(DisplayText);

//                    Toast.makeText(MultiFingersActivity.this,
//                            "File Written", Toast.LENGTH_LONG).show();

               return true;

                }*//*
//                else if ((event.getAction() == KeyEvent.ACTION_DOWN)
//                        && (keyCode == KeyEvent.KEYCODE_9)) {
//
//                    // display a floating message
//                    Toast.makeText(MultiFingersActivity.this,
//                            "Number 9 is pressed!", Toast.LENGTH_LONG).show();
//                    return true;
//                }

                return false;
            }
        });*/
//    }

//    ****************************************************************************
@TargetApi(Build.VERSION_CODES.M)
private void askForPermission(String permission, Integer requestCode) {
    if (ContextCompat.checkSelfPermission(ScheduleRuns.this, permission) != PackageManager.PERMISSION_GRANTED) {

        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(ScheduleRuns.this, permission)) {

            //This is called if user has denied the permission before
            //In this case I am just asking the permission again
            ActivityCompat.requestPermissions(ScheduleRuns.this, new String[]{permission}, requestCode);

        } else {

            ActivityCompat.requestPermissions(ScheduleRuns.this, new String[]{permission}, requestCode);
        }
    }
//    else {
//        Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
//    }
}
//    ***************************************************************************

   /* @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }*/


    public void ask(){

        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXST);
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXST);
        askForPermission(Manifest.permission.INTERNET,INT);

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void writeToFile(String data)
    {
        ask();

        // Get the directory for the user's public pictures directory.
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                //Environment.DIRECTORY_PICTURES
                                Environment.DIRECTORY_DOWNLOADS+"/"
                        );

        // Make sure the path directory exists.
        if(!path.exists())
        {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "Kumar.txt");

        // Save your stream, don't forget to flush() it before closing it.

        try
        {
            if(!file.exists())
            file.createNewFile();

//            Toast.makeText(MultiFingersActivity.this,
//                    file.getAbsolutePath(), Toast.LENGTH_LONG).show();

            try(FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println("\n"+data);
                //more code
//                out.println("more text");
                //more code
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }



            /*FileOutputStream fOut = new FileOutputStream(file);
            FileWriter fw=new FileWriter(file);
//            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            fw.append(data);
            fw.close();

            fOut.flush();
            fOut.close();*/


        }
        catch (IOException e)
        {
//            Log.v("Exception", "File write failed: " + e.toString());
//            Toast.makeText(MultiFingersActivity.this,
//                    "Failed", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    private boolean attemptLogin() {

        // Reset errors.
        domain.setError(null);
        username.setError(null);
        password.setError(null);
        testingContextKey.setError(null);
        status.setError(null);

        // Store values at the time of the login attempt.
        String Domain = domain.getText().toString();
        String Username=username.getText().toString();
        String Password = password.getText().toString();
        String TestingContextKey=testingContextKey.getText().toString();
        String Status = status.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(Domain)) {
            domain.setError(getString(R.string.error_field_required));
            focusView = domain;
            cancel = true;
        }

        // Check for a valid domain address.
        if (TextUtils.isEmpty(Username)) {
            username.setError(getString(R.string.error_field_required));
            focusView = username;
            cancel = true;
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(Password)) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
            cancel = true;
        }


        if (TextUtils.isEmpty(TestingContextKey)) {
            testingContextKey.setError(getString(R.string.error_field_required));
            focusView = testingContextKey;
            cancel = true;
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(Status)) {
            status.setError(getString(R.string.error_field_required));
            focusView = status;
            cancel = true;
        }


       /* else if (!isEmailValid(email)) {
            domain.setError(getString(R.string.error_invalid_email));
            focusView = domain;
            cancel = true;
        }*/

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } /*else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }*/
        if((TextUtils.isEmpty(Domain)==false && TextUtils.isEmpty(Username)==false &&
                TextUtils.isEmpty(Password)==false && TextUtils.isEmpty(TestingContextKey)==false && TextUtils.isEmpty(Status)==false))
        {
            return true;
        }
        else return false;
    }

}



