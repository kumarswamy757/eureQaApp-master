package com.example.kthota.eureQaApp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class eureQaHomeScreen extends AppCompatActivity  {
    private ProgressBar progressBar;
    TableLayout table=null;
    TextView textView=null;
    Button schedule=null;
    Integer count =1;
    String k=null;
    View scrollView=null;
    Object name=null;
    JSONObject jsonObject1=null;
    List<CharSequence> mHelperNames=null;
    int i=0;
    int y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eureqa_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        textView=(TextView)findViewById(R.id.text);
//        scrollView=findViewById(R.id.fullscreen);
        table=(TableLayout)findViewById(R.id.table);
        schedule=(Button) findViewById(R.id.schedule);
        schedule.setVisibility(View.GONE);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                schedule.setVisibility(View.GONE);
                Log.d("Clicked","Clicked");
//                textView.setText("Fetching...");
                try {
                    table.removeAllViews();
                    count =1;
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(0);

            AsysnTask asysnTask = new AsysnTask();
            asysnTask.execute(100);
        }catch (Exception e)
        {

        }
            }
        });

        schedule.performClick();
//        showProgress(true);

    }

    class AsysnTask extends AsyncTask<Integer, Integer, String> {

        protected int status=0;
        protected String LoginStatus="";
        String line1="";
        String list=null;
        @Override
        protected String doInBackground(Integer... params) {

            publishProgress(count);

//           String line1="";

            try {
                HttpClient client = new HttpClient();
                PostMethod method = new PostMethod("https://www.eureqatest.com/eureQaRestApi/api/TestRuns/GetList");
//		    method.addRequestHeader("Accept", "application/json");
                method.addRequestHeader("Content-Type", "application/json");
                method.addRequestHeader("Soapaction", "http://webservice.auth.eureqa.test.com/AuthUser");
//		    String encoding = Base64.encodeBytes(("kthota" + ":" + "99896605").getBytes("UTF-8"));
//		    method.addRequestHeader("AUTHORIZATION", "Basic " + encoding);
                String postData = "{\"projectId\":\"1744\",\"returnRecentResults\":\"true\",\"searchCriteria\":{\"entityTypeCodeId\":\"\",\"runDefChecked\":true,\"scenariosChecked\":true},\"contextInfo\":{\"timestamp\":\"1233243\",\"sessionId\":\"DBAC674484E5BA97D89CC1A756BB5C65\",\"userId\":\"1866\",\"organizationId\":\"1341\",\"dateFormat\":\"dd/MM/YYYY\",\"timeZone\":\"Asia/Kolkata\",\"timeZoneShortVersion\":\"IST\",\"isDomainLevelEncrytionEnabled\":\"0\",\"maxConcurrencyAllowed\":\"110\",\"localTimeToReset\":\"05:30\",\"totalCapacity\":\"57600\",\"runPartialLoadIndicator\":\"0\",\"downloadInstructionsIndicator\":\"0\"}}";
                method.setRequestEntity(new ByteArrayRequestEntity(postData.getBytes()));
                int status = client.executeMethod(method);
                System.out.println("Status:" + status);
                /*BufferedReader rd = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    line1=line1+line;
                }*/

                method.releaseConnection();

            } catch(Exception e) {
                e.printStackTrace();
            }
//            return line1;
            return "\n" +
                    "\n" +
                    "    {\n" +
                    "        \"status\": \"success\",\n" +
                    "        \"code\": \"OK\",\n" +
                    "        \"developerMessage\": \"\",\n" +
                    "        \"userMessage\": \"Request Processed Successfully.\",\n" +
                    "        \"data\":\n" +
                    "        {\n" +
                    "            \"list\":\n" +
                    "            [\n" +
                    "                {\n" +
                    "                    \"72197\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2DLMUZU\",\n" +
                    "                                \"testingContextId\": \"186321\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"72197\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"6BS7GD\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"copy of Language Verification\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"71998\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\": null,\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"71998\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"4I0KHK\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Test-Wiki-Changes-RD\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"69940\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5ZEK0FN\",\n" +
                    "                                \"testingContextId\": \"177584\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"25YHRZY\",\n" +
                    "                                \"testingContextId\": \"177585\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3SJTCUU\",\n" +
                    "                                \"testingContextId\": \"177757\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4BKTNIE\",\n" +
                    "                                \"testingContextId\": \"177758\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9RIHS3A\",\n" +
                    "                                \"testingContextId\": \"178644\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9G41YVX\",\n" +
                    "                                \"testingContextId\": \"178645\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"07CICBA\",\n" +
                    "                                \"testingContextId\": \"178646\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"RLMO78A\",\n" +
                    "                                \"testingContextId\": \"178647\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1YCWTBS\",\n" +
                    "                                \"testingContextId\": \"178648\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8ZWA1HW\",\n" +
                    "                                \"testingContextId\": \"181177\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6O5AV6J\",\n" +
                    "                                \"testingContextId\": \"181180\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6OGSLMA\",\n" +
                    "                                \"testingContextId\": \"181181\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8F6WCJY\",\n" +
                    "                                \"testingContextId\": \"181182\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"VOPQIII\",\n" +
                    "                                \"testingContextId\": \"181186\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"EXRELMJ\",\n" +
                    "                                \"testingContextId\": \"181187\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"861YPQS\",\n" +
                    "                                \"testingContextId\": \"181188\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4BI3ERV\",\n" +
                    "                                \"testingContextId\": \"181189\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7WKS7CX\",\n" +
                    "                                \"testingContextId\": \"181190\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1RWSG2K\",\n" +
                    "                                \"testingContextId\": \"181191\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2FNCIUE\",\n" +
                    "                                \"testingContextId\": \"181192\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"96WS45X\",\n" +
                    "                                \"testingContextId\": \"181193\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5CF4ADC\",\n" +
                    "                                \"testingContextId\": \"186148\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0XE9FNJ\",\n" +
                    "                                \"testingContextId\": \"186149\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"59GBYUY\",\n" +
                    "                                \"testingContextId\": \"186151\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"69940\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"34KYHS\",\n" +
                    "                        \"statusName\": \"Active\",\n" +
                    "                        \"name\": \"VerifyLanguageSpecificSites\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"69924\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4HO5ZAD\",\n" +
                    "                                \"testingContextId\": \"177568\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1FB6CEF\",\n" +
                    "                                \"testingContextId\": \"177569\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0HBEWZD\",\n" +
                    "                                \"testingContextId\": \"177755\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"472RAHG\",\n" +
                    "                                \"testingContextId\": \"177756\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0YD2OHC\",\n" +
                    "                                \"testingContextId\": \"178649\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4OIEHKW\",\n" +
                    "                                \"testingContextId\": \"178650\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9JI5Y7N\",\n" +
                    "                                \"testingContextId\": \"178651\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1O7CT5A\",\n" +
                    "                                \"testingContextId\": \"178652\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"329AWHD\",\n" +
                    "                                \"testingContextId\": \"178653\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8EUGXZD\",\n" +
                    "                                \"testingContextId\": \"181153\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"49WI2XY\",\n" +
                    "                                \"testingContextId\": \"181166\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3V6ON5W\",\n" +
                    "                                \"testingContextId\": \"181167\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5ECV6SZ\",\n" +
                    "                                \"testingContextId\": \"181168\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4KJWTZJ\",\n" +
                    "                                \"testingContextId\": \"181169\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8IA436N\",\n" +
                    "                                \"testingContextId\": \"181171\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2ODO0EU\",\n" +
                    "                                \"testingContextId\": \"181173\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2CYXWTW\",\n" +
                    "                                \"testingContextId\": \"181174\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9PQXF2T\",\n" +
                    "                                \"testingContextId\": \"181175\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9W3552X\",\n" +
                    "                                \"testingContextId\": \"181176\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1BJ9DSY\",\n" +
                    "                                \"testingContextId\": \"181178\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"44IO0KU\",\n" +
                    "                                \"testingContextId\": \"181179\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5ZLMJ6O\",\n" +
                    "                                \"testingContextId\": \"185576\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"69924\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"1X3FEX\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"ExecuteMultipleBooks\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"69920\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6H4JDUV\",\n" +
                    "                                \"testingContextId\": \"177561\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"14CJWIW\",\n" +
                    "                                \"testingContextId\": \"177562\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"51DZSHS\",\n" +
                    "                                \"testingContextId\": \"177753\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"63605CT\",\n" +
                    "                                \"testingContextId\": \"178654\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1S5IW6A\",\n" +
                    "                                \"testingContextId\": \"178655\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7EHASAB\",\n" +
                    "                                \"testingContextId\": \"178656\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2CF7SHY\",\n" +
                    "                                \"testingContextId\": \"178657\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"056REST\",\n" +
                    "                                \"testingContextId\": \"178658\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"69A15TN\",\n" +
                    "                                \"testingContextId\": \"181125\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6CU3JGI\",\n" +
                    "                                \"testingContextId\": \"181131\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6YBK1FK\",\n" +
                    "                                \"testingContextId\": \"181132\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8JYIYDB\",\n" +
                    "                                \"testingContextId\": \"181133\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7CFXT4B\",\n" +
                    "                                \"testingContextId\": \"181137\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"39SDN2J\",\n" +
                    "                                \"testingContextId\": \"181141\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5FRZAAE\",\n" +
                    "                                \"testingContextId\": \"181149\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7H37E0X\",\n" +
                    "                                \"testingContextId\": \"181150\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"00EF5AK\",\n" +
                    "                                \"testingContextId\": \"181152\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2SC1DRB\",\n" +
                    "                                \"testingContextId\": \"181155\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8US5BOW\",\n" +
                    "                                \"testingContextId\": \"181254\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"69920\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"7KECAS\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"VerifyMultipleItemsinSearchResult\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"70494\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1USYN7R\",\n" +
                    "                                \"testingContextId\": \"180538\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8PQJH5C\",\n" +
                    "                                \"testingContextId\": \"180539\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3FTH5BT\",\n" +
                    "                                \"testingContextId\": \"180540\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5U5TAFN\",\n" +
                    "                                \"testingContextId\": \"180837\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"537AIZS\",\n" +
                    "                                \"testingContextId\": \"180857\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0O3FN7N\",\n" +
                    "                                \"testingContextId\": \"180876\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6RV4KIH\",\n" +
                    "                                \"testingContextId\": \"180881\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3OF2VEX\",\n" +
                    "                                \"testingContextId\": \"180882\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2Y1F85C\",\n" +
                    "                                \"testingContextId\": \"180883\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1W8292E\",\n" +
                    "                                \"testingContextId\": \"180884\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5C7RBAX\",\n" +
                    "                                \"testingContextId\": \"180885\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1VNU4HS\",\n" +
                    "                                \"testingContextId\": \"180886\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2IHTU4S\",\n" +
                    "                                \"testingContextId\": \"181038\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"40A8OEI\",\n" +
                    "                                \"testingContextId\": \"184718\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3TU0D9J\",\n" +
                    "                                \"testingContextId\": \"184719\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"70494\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"4512DI\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Search n Add a Book - for demo\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"63719\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"77RTAZS\",\n" +
                    "                                \"testingContextId\": \"161237\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4BVGC1H\",\n" +
                    "                                \"testingContextId\": \"161238\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1OBCZOH\",\n" +
                    "                                \"testingContextId\": \"161239\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4XJKIXS\",\n" +
                    "                                \"testingContextId\": \"161240\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"40JHEVE\",\n" +
                    "                                \"testingContextId\": \"161241\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3ZYTU9I\",\n" +
                    "                                \"testingContextId\": \"161242\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4TVG09N\",\n" +
                    "                                \"testingContextId\": \"161243\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8WK795B\",\n" +
                    "                                \"testingContextId\": \"161244\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9FE442Y\",\n" +
                    "                                \"testingContextId\": \"161245\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2YBX50S\",\n" +
                    "                                \"testingContextId\": \"161246\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"45K58ON\",\n" +
                    "                                \"testingContextId\": \"161247\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1CAZT9H\",\n" +
                    "                                \"testingContextId\": \"161248\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0CPQX1Y\",\n" +
                    "                                \"testingContextId\": \"161249\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3U2R3SS\",\n" +
                    "                                \"testingContextId\": \"161250\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4N1V97R\",\n" +
                    "                                \"testingContextId\": \"161251\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1AC066O\",\n" +
                    "                                \"testingContextId\": \"161252\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8WBHBZU\",\n" +
                    "                                \"testingContextId\": \"161253\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7WH4VHU\",\n" +
                    "                                \"testingContextId\": \"182501\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"63719\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"398T3O\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Amazon_DD\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"64809\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1EEZFVG\",\n" +
                    "                                \"testingContextId\": \"163430\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"561VYAO\",\n" +
                    "                                \"testingContextId\": \"163431\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"64809\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"7GRAFZ\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"VerifyMultipleLanguages\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"70634\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8T6PQAD\",\n" +
                    "                                \"testingContextId\": \"181290\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"97UBFGE\",\n" +
                    "                                \"testingContextId\": \"181291\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"70634\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"4JEU2C\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"ResetSession-Scope\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"69915\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"59FZBLM\",\n" +
                    "                                \"testingContextId\": \"177545\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"914T33E\",\n" +
                    "                                \"testingContextId\": \"177751\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1ZCZ2DO\",\n" +
                    "                                \"testingContextId\": \"177752\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9YR4GSH\",\n" +
                    "                                \"testingContextId\": \"178638\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5SC5NYJ\",\n" +
                    "                                \"testingContextId\": \"178639\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"08FTGXU\",\n" +
                    "                                \"testingContextId\": \"178640\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0K8D8JW\",\n" +
                    "                                \"testingContextId\": \"178641\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4D41SRB\",\n" +
                    "                                \"testingContextId\": \"178642\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"29DI96F\",\n" +
                    "                                \"testingContextId\": \"178643\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6INB6GF\",\n" +
                    "                                \"testingContextId\": \"181144\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7WWSAWN\",\n" +
                    "                                \"testingContextId\": \"181154\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3RUTZ3U\",\n" +
                    "                                \"testingContextId\": \"181156\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1D3U6EF\",\n" +
                    "                                \"testingContextId\": \"181157\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6EC5D1B\",\n" +
                    "                                \"testingContextId\": \"181159\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9ER32CA\",\n" +
                    "                                \"testingContextId\": \"181160\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3H50XDE\",\n" +
                    "                                \"testingContextId\": \"181161\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1VSFHIU\",\n" +
                    "                                \"testingContextId\": \"181162\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1CWCHZK\",\n" +
                    "                                \"testingContextId\": \"181163\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8N33XAD\",\n" +
                    "                                \"testingContextId\": \"181164\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0CKWBAC\",\n" +
                    "                                \"testingContextId\": \"181170\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9TGTXUS\",\n" +
                    "                                \"testingContextId\": \"181172\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"69915\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"5R9CVX\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"OpenWebsite&Precondition\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"67604\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"15X57UC\",\n" +
                    "                                \"testingContextId\": \"170581\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"772WVIJ\",\n" +
                    "                                \"testingContextId\": \"170582\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1WPQJWB\",\n" +
                    "                                \"testingContextId\": \"170583\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"67604\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"3G8ECN\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"sample run definition\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"64103\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1E0DJWS\",\n" +
                    "                                \"testingContextId\": \"161956\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7A5VJWH\",\n" +
                    "                                \"testingContextId\": \"161957\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7VXSIJS\",\n" +
                    "                                \"testingContextId\": \"161970\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6F2JHYX\",\n" +
                    "                                \"testingContextId\": \"162037\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"773AJCW\",\n" +
                    "                                \"testingContextId\": \"164444\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"95VFSVD\",\n" +
                    "                                \"testingContextId\": \"167700\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"64103\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"3XTTOG\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Amazon _ Merged\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"65956\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"64Y761O\",\n" +
                    "                                \"testingContextId\": \"165821\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8CXA4YI\",\n" +
                    "                                \"testingContextId\": \"165822\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"31E9GJU\",\n" +
                    "                                \"testingContextId\": \"165823\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8VHZ12S\",\n" +
                    "                                \"testingContextId\": \"165824\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"65956\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"4CHNAK\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Amazon_Devices\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"64968\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"7NYSRKI\",\n" +
                    "                                \"testingContextId\": \"163924\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3NFPQ2N\",\n" +
                    "                                \"testingContextId\": \"163925\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"64968\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"83TZ2W\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Test-RD-219-3\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"64953\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3Z5ODXF\",\n" +
                    "                                \"testingContextId\": \"163900\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4I35XWG\",\n" +
                    "                                \"testingContextId\": \"163901\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"64953\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"7A36UF\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Run Definition for Demo - 2-19\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"64615\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8EH6KCV\",\n" +
                    "                                \"testingContextId\": \"163084\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"64615\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"22AD7N\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Execute Multiple Books\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"64440\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"19SV1YH\",\n" +
                    "                                \"testingContextId\": \"162635\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9CW9B5R\",\n" +
                    "                                \"testingContextId\": \"162636\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4NBV56G\",\n" +
                    "                                \"testingContextId\": \"162803\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"64440\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"4CI4AJ\",\n" +
                    "                        \"statusName\": \"Active\",\n" +
                    "                        \"name\": \"Amazon_DD w/o Data driven\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"63806\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"19SV1YH\",\n" +
                    "                                \"testingContextId\": \"161415\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9CW9B5R\",\n" +
                    "                                \"testingContextId\": \"161417\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"63806\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"054UWB\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Amazon_DD w/o Data driven\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"63593\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2YUZFYD\",\n" +
                    "                                \"testingContextId\": \"161026\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"74KLMVF\",\n" +
                    "                                \"testingContextId\": \"161027\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"63593\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"8GIU7Y\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Demo Run Definition 12\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"59778\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\": null,\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"59778\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"8IEA6W\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"copy of Amazon_DD\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"51234\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"77RTAZS\",\n" +
                    "                                \"testingContextId\": \"130284\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4BVGC1H\",\n" +
                    "                                \"testingContextId\": \"130285\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1OBCZOH\",\n" +
                    "                                \"testingContextId\": \"130286\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4XJKIXS\",\n" +
                    "                                \"testingContextId\": \"130287\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"40JHEVE\",\n" +
                    "                                \"testingContextId\": \"130288\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3ZYTU9I\",\n" +
                    "                                \"testingContextId\": \"130289\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4TVG09N\",\n" +
                    "                                \"testingContextId\": \"130290\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8WK795B\",\n" +
                    "                                \"testingContextId\": \"130291\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9FE442Y\",\n" +
                    "                                \"testingContextId\": \"130292\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"2YBX50S\",\n" +
                    "                                \"testingContextId\": \"130293\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"45K58ON\",\n" +
                    "                                \"testingContextId\": \"130294\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1CAZT9H\",\n" +
                    "                                \"testingContextId\": \"130295\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0CPQX1Y\",\n" +
                    "                                \"testingContextId\": \"130296\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3U2R3SS\",\n" +
                    "                                \"testingContextId\": \"130297\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4N1V97R\",\n" +
                    "                                \"testingContextId\": \"130298\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"1AC066O\",\n" +
                    "                                \"testingContextId\": \"157752\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"8WBHBZU\",\n" +
                    "                                \"testingContextId\": \"157753\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"51234\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"68ZSSY\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Amazon_DD\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"58609\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"25ZA8DD\",\n" +
                    "                                \"testingContextId\": \"149033\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9UO6RXJ\",\n" +
                    "                                \"testingContextId\": \"149034\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"58609\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"86KOTY\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"yoyo Cart-Mobile\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"58608\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5K5N6SX\",\n" +
                    "                                \"testingContextId\": \"149030\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3ZKAWHS\",\n" +
                    "                                \"testingContextId\": \"149031\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"58608\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"2DAYFA\",\n" +
                    "                        \"statusName\": \"Active\",\n" +
                    "                        \"name\": \"yoyo Cart - Win10 - Firefox\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"58607\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"9238CFX\",\n" +
                    "                                \"testingContextId\": \"149027\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3FRWEBH\",\n" +
                    "                                \"testingContextId\": \"149028\"\n" +
                    "                            },\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"3IFZ8AU\",\n" +
                    "                                \"testingContextId\": \"149029\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"58607\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"7FA7XU\",\n" +
                    "                        \"statusName\": \"Active\",\n" +
                    "                        \"name\": \"yoyo Cart-Win10-Chrome\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"47886\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\": null,\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"47886\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"84XN2K\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"RD for Bind data test\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"52989\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0H2JWRO\",\n" +
                    "                                \"testingContextId\": \"135912\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"52989\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"8DOAXY\",\n" +
                    "                        \"statusName\": \"WIP\",\n" +
                    "                        \"name\": \"Language Verification\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"16219\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"16O5WZH\",\n" +
                    "                                \"testingContextId\": \"18183\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"16219\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"8REHYG\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"demo rd for SpectraMedix\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"16261\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"0A728AE\",\n" +
                    "                                \"testingContextId\": \"18224\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"16261\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"7VXDVN\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Music & Books IE9 - Staging\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"16258\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"77KZCJF\",\n" +
                    "                                \"testingContextId\": \"18220\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"16258\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"6ZUUIC\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Music & Books CH33 - Staging\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"16257\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5739KOS\",\n" +
                    "                                \"testingContextId\": \"18219\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"16257\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"6AO2EO\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Music & Books CH33-QA\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"26167\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"4OJX9A\",\n" +
                    "                                \"testingContextId\": \"40095\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"26167\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"0HK7YI\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"sample run definition on FF\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"26173\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"5KYETW\",\n" +
                    "                                \"testingContextId\": \"40099\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"26173\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"8WA68J\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"demo RD for CF\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"16215\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"6K08J1T\",\n" +
                    "                                \"testingContextId\": \"18179\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"16215\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"4ZI13S\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Music & Books FF30 - Staging\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"26169\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"72OUUXB\",\n" +
                    "                                \"testingContextId\": \"40097\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"26169\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"6Y7EXB\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Buy a Book and CD_MasterScript On CH\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"26170\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\": null,\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"26170\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"3Y07GB\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Music and Books on IE11\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"19150\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\": null,\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"19150\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"842EIO\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Demo RD for Invessence\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"8587\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"91ACZG\",\n" +
                    "                                \"testingContextId\": \"12921\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"8587\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"91ACZG\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Buy a Book and CD_MasterScript On IE\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"6404\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"49WK6I\",\n" +
                    "                                \"testingContextId\": \"8990\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"6404\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"49WK6I\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Buy a Book and CD on IE\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"6390\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"34SE6S\",\n" +
                    "                                \"testingContextId\": \"8995\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"6390\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"34SE6S\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Buy a Book and CD on FF\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"6389\":\n" +
                    "                    {\n" +
                    "                        \"testingContextInfo\":\n" +
                    "                        [\n" +
                    "                            {\n" +
                    "                                \"testingContextKey\": \"38A1ZJ\",\n" +
                    "                                \"testingContextId\": \"8996\"\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"customerProjectTestRunDefinitionId\": \"6389\",\n" +
                    "                        \"customerprojecttestrundefinitionkey\": \"38A1ZJ\",\n" +
                    "                        \"statusName\": \"Inactive\",\n" +
                    "                        \"name\": \"Buy a Book and CD on Chrome\"\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n";
        }

        @Override
        protected void onPostExecute(String str) {
            try {


                JSONObject jsonObject = new org.json.JSONObject(str);
                String status = jsonObject.get("status").toString();
                String message = jsonObject.get("userMessage").toString();

                Log.d("status",status);
                Log.d("message",message);

                if(status.equals("success") && message.equals("Request Processed Successfully."))
                {
                    Log.d("Entered","Entered");

                    final JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("list");

                    TableRow tableRowHead=new TableRow(eureQaHomeScreen.this);

                    TextView textViewHead=new TextView(eureQaHomeScreen.this);
//                    textViewHead.setBackground(getResources().getDrawable(R.drawable.table_border));
                    textViewHead.setGravity(Gravity.CENTER_HORIZONTAL);
                    textViewHead.setGravity(Gravity.CENTER_VERTICAL);
                    textViewHead.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
//                    textViewHead.setText("xjknkvgxfv");


                    TextView textViewHead1=new TextView(eureQaHomeScreen.this);
                    textViewHead1.setBackground(getResources().getDrawable(R.drawable.table_border));
                    textViewHead1.setGravity(Gravity.CENTER_HORIZONTAL);
//                    textViewHead1.setGravity(Gravity.CENTER_VERTICAL);
                    textViewHead1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                    textViewHead1.setText("\tRun Definition Name");
                    textViewHead1.setTextSize(20);
                    textViewHead1.setTypeface(null, Typeface.BOLD);


                    TextView textViewHead2=new TextView(eureQaHomeScreen.this);
                    textViewHead2.setBackground(getResources().getDrawable(R.drawable.table_border));
                    textViewHead2.setGravity(Gravity.CENTER_HORIZONTAL);
                    textViewHead2.setGravity(Gravity.CENTER_VERTICAL);
                    textViewHead2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                    textViewHead2.setText("\tStatus");
                    textViewHead2.setTextSize(20);
                    textViewHead1.setTypeface(null, Typeface.BOLD);




                    tableRowHead.addView(textViewHead);
                    tableRowHead.addView(textViewHead1);
                    tableRowHead.addView(textViewHead2);

                    table.addView(tableRowHead);


                    final ArrayList<String> statusList=new ArrayList<String>();
                    final ArrayList<String> statusIDs=new ArrayList<String>();

                    for(i=0;i<jsonArray.length();i++)
                    {
//                        72197 parent
                        jsonObject1=jsonArray.getJSONObject(i);

//                        72197


                        name=jsonObject1.names().get(0);

                        String name1= jsonObject1.getJSONObject(name.toString()).get("name").toString();
                        String statusName=jsonObject1.getJSONObject(name.toString()).get("statusName").toString();
                        int id= jsonObject1.getJSONObject(name.toString()).getInt("customerProjectTestRunDefinitionId");

//                        Log.d("jsonobject1",jsonObject1.getJSONObject(name.toString()).getJSONArray("testingContextInfo").toString());
                        Log.d("name",name1);
                        Log.d("status",statusName);
                        statusList.add(statusName);
//                        System.out.println(statusList.get(0));
                        TableRow tableRow=new TableRow(eureQaHomeScreen.this);

                       /* CheckBox checkBox=new CheckBox(eureQaHomeScreen.this);
                        checkBox.setGravity(Gravity.CENTER_HORIZONTAL);
                        checkBox.setGravity(Gravity.CENTER_VERTICAL);
                        checkBox.setBackground(getResources().getDrawable(R.drawable.table_border));
                        checkBox.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
*/
                        ImageButton view=new ImageButton(eureQaHomeScreen.this);
                        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
                        {
                            ((ImageButton) view).setImageResource(getResources().getIdentifier("executionicon", "drawable", getPackageName()));
//                            view.setBackground(getResources().getDrawable(R.drawable.table_border));
                            view.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                            view.setLayoutParams( new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                            view.setId(id);
                            statusIDs.add(id+"");

                        }
                        else
                        {
                            ((ImageButton) view).setImageDrawable(getDrawable(getResources().getIdentifier("executionicon", "drawable", getPackageName())));
//                            view.setBackground(getResources().getDrawable(R.drawable.table_border));
                            view.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                            view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                            view.setId(id);
                            statusIDs.add(id+"");
                        }

                        view.setOnClickListener(new View.OnClickListener(){


                            @Override
                            public void onClick(View view1) {

//                                final AlertDialog.Builder builder = new AlertDialog.Builder(eureQaHomeScreen.this);

                                ImageButton t = (ImageButton) view1;
                                Log.d("Clicked Button", t.getId() + "");
                                int clickedButton=t.getId();


                                for (y=0;y<statusIDs.size();y++)
                                {

                                    if(statusIDs.get(y).equals(clickedButton+"")) {
                                        System.out.println("Y Value: "+y);
                                        break;
                                    }
                                }


                                final    ArrayList<Integer> mSelectedItems=new ArrayList();  // Where we track the selected items

                                    mHelperNames = new ArrayList<CharSequence>();
//                                mHelperNames.add("Test Item 1");
//                                mHelperNames.add("Test Item 2");
                                try {

                                    for(int j=0;j<jsonArray.length();j++) {

                                        Object node72197=jsonArray.getJSONObject(j).names().get(0);
                                        if(node72197.toString().equals(t.getId()+"")) {
                                            Log.d("ZZZZZZZZZZ", node72197.toString());

                                            JSONArray testingContextInfo=jsonArray.getJSONObject(j).getJSONObject(node72197.toString()).getJSONArray("testingContextInfo");
                                            Log.d("testingContextInfo",testingContextInfo.toString());

                                            for (int k=0;k<testingContextInfo.length();k++)
                                            {
                                                JSONObject testingContextKeyParent=testingContextInfo.getJSONObject(k);
                                                mHelperNames.add(testingContextKeyParent.get("testingContextKey").toString());
                                            }

                                        }

                                    }

                                }catch (Exception e)
                                {
                                    Log.d("Exception","Exception Occured");

                                }

                                final AlertDialog.Builder builder = new AlertDialog.Builder(eureQaHomeScreen.this);
                                // Set the dialog title
                                builder.setTitle("eureQa - Testing context(s)")
                                        // Specify the list array, the items to be selected by default (null for none),
                                        // and the listener through which to receive callbacks when items are selected
                                        .setMultiChoiceItems(mHelperNames.toArray(new CharSequence[mHelperNames.size()]), null,
                                                new DialogInterface.OnMultiChoiceClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which,
                                                                        boolean isChecked) {
                                                        if (isChecked) {
                                                            // If the user checked the item, add it to the selected items
                                                            mSelectedItems.add(which);
                                                        } else if (mSelectedItems.contains(which)) {
                                                            // Else, if the item is already in the array, remove it
                                                            mSelectedItems.remove(Integer.valueOf(which));
                                                        }
                                                    }
                                                })
                                        // Set the action buttons
                                        .setPositiveButton("Schedule", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                // User clicked OK, so save the mSelectedItems results somewhere
                                                // or return them to the component that opened the dialog

//                                                ListView lw = ((AlertDialog)dialog).getListView();
//                                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                                                mSelectedItems.get(0);

                                                Log.d("id",mSelectedItems.size()+"");
                                                Log.d("id",mHelperNames.get(mSelectedItems.get(0))+"");
                                                System.out.println("*********************************************************");
                                                for (int selectedItems=0;selectedItems<mSelectedItems.size();selectedItems++)
                                                {
                                                    System.out.println(mHelperNames.get(mSelectedItems.get(selectedItems))+"--->"+statusList.get(y));
                                                }
                                                System.out.println("*********************************************************");

                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });

                                builder.create();

                                builder.show();


                            }

                        });


                        /*ImageView imgView=new ImageView(eureQaHomeScreen.this);
                        imgView.setImageResource(R.drawable.executionicon);
                        imgView.setBackground(getResources().getDrawable(R.drawable.table_border));
                        imgView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
*/
                        TextView textView1=new TextView(eureQaHomeScreen.this);
                        textView1.setBackground(getResources().getDrawable(R.drawable.table_border));
                        textView1.setGravity(Gravity.CENTER_HORIZONTAL);
                        textView1.setGravity(Gravity.CENTER_VERTICAL);
                        textView1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                        textView1.setText("\t"+name1);

                        textView1.setOnClickListener(new View.OnClickListener(){


                            @Override
                            public void onClick(View view) {

                                TextView t=(TextView)view;
                                Log.d("Clicked text",t.getText().toString());

                            }
                        });


                        TextView textView2=new TextView(eureQaHomeScreen.this);
                        textView2.setBackground(getResources().getDrawable(R.drawable.table_border));
                        textView2.setGravity(Gravity.CENTER_HORIZONTAL);
                        textView2.setGravity(Gravity.CENTER_VERTICAL);
                        textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));
                        textView2.setText("\t"+statusName+"\t\t");

                        /*tableRow.addView(checkBox);*/
                        tableRow.addView(view);
                        tableRow.addView(textView1);
                        tableRow.addView(textView2);

                        table.addView(tableRow);

                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            progressBar.setVisibility(View.GONE);
            schedule.setVisibility(View.VISIBLE);
        }

        /*@Override
        protected void onPostExecute(String str){

            Log.d("Entered onPostExecute","");

            try {
                JSONObject jsonObject = new org.json.JSONObject(str);
                String status=jsonObject.get("status").toString();
                String message=jsonObject.get("message").toString();

                if(status.equals("Success") && message.equals("Retrieved Successfully"))
                {
                    JSONArray jsonArrayList=jsonObject.getJSONObject("data").getJSONArray("list");

                    Log.d("HomeScreen Length",jsonArrayList.length()+"");

                    for(int i=0;i<jsonArrayList.length();i++) {
                        JSONObject jsonObject1=jsonArrayList.getJSONObject(i);

                        String runDefinitionKey=jsonObject1.get("testingContextKey").toString();
                        String testRunName=jsonObject1.get("testRunName").toString();
                        String statusCodeName=jsonObject1.get("statusCodeName").toString();

                        Log.d("runDefinitionKey",runDefinitionKey);
                        Log.d("testRunName",testRunName);
                        Log.d("testRunName",testRunName);

                        TableRow tableRow=new TableRow(eureQaHomeScreen.this);

                        TextView textView1=new TextView(eureQaHomeScreen.this);
                        textView1.setBackground(getResources().getDrawable(R.drawable.table_border));
                        textView1.setGravity(Gravity.CENTER_HORIZONTAL);
                        textView1.setText(runDefinitionKey+"\t\t");

                        TextView textView2=new TextView(eureQaHomeScreen.this);
                        textView2.setBackground(getResources().getDrawable(R.drawable.table_border));
                        textView2.setGravity(Gravity.CENTER_HORIZONTAL);
                        textView2.setText(testRunName);

                        TextView textView3=new TextView(eureQaHomeScreen.this);
                        textView3.setBackground(getResources().getDrawable(R.drawable.table_border));
                        textView3.setGravity(Gravity.CENTER_HORIZONTAL);
                        textView3.setText(statusCodeName);

                        tableRow.addView(textView1);
                        tableRow.addView(textView2);
                        tableRow.addView(textView3);

                        table.addView(tableRow);


                    }
                }

            }catch (Exception e)
            {

            }



            progressBar.setVisibility(View.GONE);
//            textView.setText(line1);
//            showProgress(false);
        }*/
        @Override
        protected void onProgressUpdate(Integer... values) {
//            textView.setText("Running..."+ values[0]);
            progressBar.setProgress(values[0]);
        }

    }

}

