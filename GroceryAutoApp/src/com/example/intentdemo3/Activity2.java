// Activity2. This sub-activity receives a bundle of data,
// data types include: primitive, arrays, complex objects.
// Activity2 performs some work on the data and, returns 
// results to Activity1.
package com.example.intentdemo3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.media.JetPlayer.OnJetEventListener;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends Activity 
{
    TextView txtIncomingData;
    TextView txtGroceries;
    Button   btnCallActivity1;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);
        
        // /////////////////////////////////////////////////////////////
        // create a local Intent handler � we have been called!
        Intent myCallerIntentHandler = getIntent();
                
        btnCallActivity1 = (Button)findViewById(R.id.btnCallActivity1);
        btnCallActivity1.setOnClickListener(new Clicker1());
        
        StrictMode.enableDefaults(); //STRICT MODE ENABLED
        txtGroceries = (TextView) findViewById(R.id.txtGroceryList);
         
         getData();
        
    }//onCreate
    
    public void getData()
    {
    	String result = "";
    	InputStream isr = null;
    	try
    	{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://davidomalley.dynu.com/getAllCustomers.php"); //YOUR PHP SCRIPT ADDRESS 
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            isr = entity.getContent();
    	}
    
        catch(Exception e)
        {
                Log.e("log_tag", "Error in http connection "+e.toString());
                txtGroceries.setText("Couldnt connect to database");
        }
    	
        //convert response to string
        try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) 
                {
                        sb.append(line + "\n");
                }
                isr.close();
         
                result=sb.toString();
        }
        
        catch(Exception e)
        {
                Log.e("log_tag", "Error  converting result "+e.toString());
        }
         
        //parse json data
       try 
       {
    	   String s = "";
    	   JSONArray jArray = new JSONArray(result);
    	   
    	   for(int i=0; i<jArray.length();i++)
    	   {
    		   JSONObject json = jArray.getJSONObject(i);
    		   s = s + 
    				   json.getString("description")+"\n";
    	   }
    	   
    	   txtGroceries.setText(s);
    	
       } 
       
       catch (Exception e) 
       {
    	   // TODO: handle exception
    	   Log.e("log_tag", "Error Parsing Data "+e.toString());
       }
}//GetData
    
	// ///////////////////////////////////////////////////////////////////
	private class Clicker1 implements OnClickListener 
	{
		public void onClick(View v) 
		{
			//clear Activity2 screen so Activity1 could be seen
			finish();			
		}//onClick    	
    }//Clicker1
}//Activity2
