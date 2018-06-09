package com.example.kthota.eureQaApp;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    static final Integer ONE = 0x3;
    static final Integer TWO = 0x3;
    static final Integer THREE = 0x4;

    // UI references.
    protected AutoCompleteTextView domain;
    protected AutoCompleteTextView username;
    protected EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("eureQa");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        askPermissions();
//
//        Intent intent = new Intent(LoginActivity.this, QaSamplesActivity.class);
//        startActivity(intent);

        // Set up the login form.
        domain = (AutoCompleteTextView) findViewById(R.id.domain);
        username = (AutoCompleteTextView) findViewById(R.id.username);


        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (attemptLogin()) {
                    showProgress(true);
                    authentication();
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    public void authentication() {
        /*RetrieveGetTask restGet=new RetrieveGetTask();
        restGet.execute(domain.getText().toString(),username.getText().toString(),mPasswordView.getText().toString(),"8GUO12N-260910");
*/
        RetrieveFeedTask login = new RetrieveFeedTask();
        try {
            login.execute(username.getText().toString(), mPasswordView.getText().toString(), domain.getText().toString()).get();
        } catch (Exception e) {

        }

        if (login.onPostExecute().startsWith("success")) {
            Intent intent = new Intent(LoginActivity.this, QaSamplesActivity.class);
            startActivity(intent);
        } else if (login.onPostExecute().startsWith("blocked")) {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            Log.d("", "Your Free Trial of eureQa has expired. You can continue to use eureQa by subscribing to one of our paid plans. Please contact support if you have any questions.");
        } else {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Incorrect Username/Password. Try again or contact eureQa Support!", Toast.LENGTH_LONG).show();
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(LoginActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
            }
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void askPermissions() {
        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, ONE);
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, TWO);
        askForPermission(Manifest.permission.INTERNET, THREE);
    }

    private boolean attemptLogin() {
        // Reset errors.
        domain.setError(null);
        username.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = domain.getText().toString();
        String Username = username.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid domain address.
        if (TextUtils.isEmpty(email)) {
            domain.setError(getString(R.string.error_field_required));
            focusView = domain;
            cancel = true;
        }
        // Check for a valid username address.
        if (TextUtils.isEmpty(Username)) {
            username.setError(getString(R.string.error_field_required));
            focusView = username;
            cancel = true;
        }
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        if (TextUtils.isEmpty(Username) == false && TextUtils.isEmpty(password) == false && TextUtils.isEmpty(email) == false) {
            return true;
        } else return false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    protected void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

