package com.example.kthota.eureQaApp;

import android.os.AsyncTask;
import android.util.Log;

import com.edb.util.Base64;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveFeedTask extends AsyncTask<String, Void, Void> {

    protected boolean loginStatus=false;
    protected int status=0;
    protected String LoginStatus="";
    protected Void doInBackground(String... params) {

        Log.d("params",params[0]+params[1]+params[2]);
        String line1="";
        String username = params[0];
        String password = params[1];
        String domain = params[2];
        try {
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod("https://www.acmetech.eureqatest.com/AuthUserService?wsdl");
            method.addRequestHeader("Content-Type", "text/xml");
            method.addRequestHeader("Soapaction", "http://webservice.auth.eureqa.test.com/AuthUser");
            String postData = "<?xml version=\"1.0\" " +
                    "encoding=\"UTF-8\"?>" +
                    "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://webservice.auth.eureqa.test.com/\">  " +
                    "<SOAP-ENV:Body>    " +
                    "<ns1:AuthUser>      " +
                    "<arg0>" + username + "</arg0>      " +
                    "<arg1>" + password + "</arg1>      " +
                    "<arg2>" + domain + "</arg2>      " +
                    "<arg3>2</arg3>    " +
                    "</ns1:AuthUser>  " +
                    "</SOAP-ENV:Body>" +
                    "</SOAP-ENV:Envelope>";
            method.setRequestEntity(new ByteArrayRequestEntity(postData.getBytes()));
            status = client.executeMethod(method);
            Log.d("status",status+"");
            BufferedReader rd = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                line1=line1+line;
            }
            method.releaseConnection();


            Log.d("Response",line1);

            LoginStatus=line1.substring(line1.lastIndexOf("<return>")+8,line1.lastIndexOf("</return>"));
            Log.d("Response",LoginStatus);

        } catch(Exception e) {
            e.printStackTrace();
        }


        return null;

    }

    protected String onPostExecute() {
        return LoginStatus;
    }


}



class RetrieveGetTask extends AsyncTask<String, Void, String> {
    String line1="";
    String list=null;

    @Override
    protected String doInBackground(String... params) {
        publishProgress();

        Log.d("params",params[0]+params[1]+params[2]+params[3]);


        try {
            URL url = new URL ("https://www.eureqatest.com/rest/v1/"+params[0]+"/executions/"+params[3]);
            String encoding = Base64.encodeBytes((params[1] + ":" + params[2]).getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Content-Type", "application/json");


		    /*HttpClient client = new HttpClient();
		    PostMethod method = new PostMethod("https://www.acmetech.eureqatest.com/AuthUserService?wsdl");
//		    method.addRequestHeader("Accept", "application/json");
		    method.addRequestHeader("Content-Type", "text/xml");
		    method.addRequestHeader("Soapaction", "http://webservice.auth.eureqa.test.com/AuthUser");
//		    String encoding = Base64.encodeBytes(("kthota" + ":" + "99896605").getBytes("UTF-8"));
//		    method.addRequestHeader("AUTHORIZATION", "Basic " + encoding);
		    String postData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://webservice.auth.eureqa.test.com/\">  <SOAP-ENV:Body>    <ns1:AuthUser>      <arg0>kthota</arg0>      <arg1>99896605</arg1>      <arg2>acmetech</arg2>      <arg3>2</arg3>    </ns1:AuthUser>  </SOAP-ENV:Body></SOAP-ENV:Envelope>";
		    method.setRequestEntity(new ByteArrayRequestEntity(postData.getBytes()));
		    int status = client.executeMethod(method);
		    System.out.println("Status:" + status);
		    BufferedReader rd = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
//		    String line1 = "";
		    while ((line1 = rd.readLine()) != null) {
		    System.out.println(line1);
		    }
		    method.releaseConnection();
*/




//            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   =
                    new BufferedReader(new InputStreamReader(content));
            String line;

            while ((line = in.readLine()) != null) {
                line1 = line1+line;

            }
            Log.d("Rest Response",line1);
//	        Toast.makeText(MultiFingersActivity.this,line1,Toast.LENGTH_LONG).show();

//            ParseJSON
            try {
                org.json.JSONObject obj = new org.json.JSONObject(line1);
                String status=obj.get("status").toString();
                String messasge=obj.get("message").toString();
                Log.d("status",status);
                Log.d("message",messasge);

                Log.d("st",(status.equals("Success") && messasge.equals("Retrieved Successfully"))+"");

                if(status.equals("Success") && messasge.equals("Retrieved Successfully"))
                {
                    JSONObject runDefinitionInformation=obj.getJSONObject("details").getJSONObject("runDefinitionInformation");
                    String runId=runDefinitionInformation.get("runId").toString();
                    String name=runDefinitionInformation.get("name").toString();
                    String executionStatus=runDefinitionInformation.get("executionStatus").toString();
                    String scheduledBy=runDefinitionInformation.get("scheduledBy").toString();
                    String startTime=runDefinitionInformation.get("startTime").toString();
                    String endTime=runDefinitionInformation.get("endTime").toString();
                    String executionTime=runDefinitionInformation.get("duration").toString();
                    String queuedTime=runDefinitionInformation.get("queuedTime").toString();

                    JSONObject testingContext=obj.getJSONObject("details").getJSONObject("testingContext");
                    String environment=testingContext.get("environment").toString();
                    String cloudPlatform=testingContext.get("cloudPlatform").toString();
                    String operatingSystem=testingContext.get("operatingSystem").toString();

                    JSONObject testScenarioDetails=obj.getJSONObject("details").getJSONObject("testScenarioDetails");
                    String scTotal=testScenarioDetails.get("total").toString();
                    String scPassed=testScenarioDetails.get("passed").toString();
                    String scpassPercentage=testScenarioDetails.get("passPercentage").toString();

                    JSONObject testScriptDetails=obj.getJSONObject("details").getJSONObject("testScriptDetails");
                    String scrTotal=testScriptDetails.get("total").toString();
                    String scrPassed=testScriptDetails.get("passed").toString();
                    String scrpassPercentage=testScriptDetails.get("passPercentage").toString();

                    JSONObject testInstructionDetails=obj.getJSONObject("details").getJSONObject("testInstructionDetails");
                    String inTotal=testInstructionDetails.get("total").toString();
                    String inPassed=testInstructionDetails.get("passed").toString();
                    String inpassPercentage=testInstructionDetails.get("passPercentage").toString();

                    Log.d("Run-Id:",runId);
                    list=runId+","+name+","+executionStatus+","+scheduledBy+","+startTime+","+endTime+","+executionTime+","+queuedTime+
                            ","+environment+","+cloudPlatform+","+operatingSystem+
                            ","+scTotal+","+scPassed+","+scpassPercentage+
                            ","+scrTotal+","+scrPassed+","+scrpassPercentage+
                            ","+inTotal+","+inPassed+","+inpassPercentage+","+status+","+messasge;



                }

            }catch (Exception e)
            {

            }

//		    System.out.println(line1);
        } catch(Exception e) {
            e.printStackTrace();
        }


      return list;
    }

}