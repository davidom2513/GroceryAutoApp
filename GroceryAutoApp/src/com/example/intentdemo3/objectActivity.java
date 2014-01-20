package com.example.intentdemo3;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class objectActivity extends Activity 
{
	private static final long serialVersionUID = 1L;
	private String str;
	
	Button btnCallActivity1;
	TextView txtIncomingData;
	TextView Return;
	
	private int n1, n2, n3, n4, n5;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        
        //bind UI variables to Java code
        txtIncomingData = (TextView)findViewById(R.id.txtIncoming);
        
        Return = (TextView)findViewById(R.id.txtReturn);
        
        btnCallActivity1 = (Button)findViewById(R.id.btnCallMain);
        btnCallActivity1.setOnClickListener(new Clicker1());
        
        // /////////////////////////////////////////////////////////////
        // create a local Intent handler – we have been called!
        Intent myCallerIntentHandler = getIntent();

        // grab the data package with all the pieces sent to us
        Bundle myBundle = myCallerIntentHandler.getExtras();

        // extract the individual data parts from the bundle 
        // observe you know the individual keyNames
        Object paramObject = (Object) myBundle.getSerializable("object");
        
        String object_Str = paramObject.getStr();
        
        int n1 = paramObject.getN1();
        int n2 = paramObject.getN2();
        int n3 = paramObject.getN3();
        int n4 = paramObject.getN4();
        int n5 = paramObject.getN5();
        
        //for debugging purposes - show arriving data 
        txtIncomingData.append("\n\nNumber 1: " +n1);
        txtIncomingData.append("\nNumber 2: " +n2);
        txtIncomingData.append("\nNumber 3: " +n3);
        txtIncomingData.append("\nNumber 4: " +n4);
        txtIncomingData.append("\nNumber 5: " +n5);
        txtIncomingData.append("\nString: " + object_Str);
        
        // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++               
        // do here something with the extracted data. For example,  
        //Calculate Results
        int average = (n1+n2+n3+n4+n5)/5;
        String string_Reverse = new StringBuilder(object_Str).reverse().toString();
                   
        // Returning Results.
        // Go back to myActivity1 with some new data made/change here.           
        myBundle.putInt("average", average);
        myBundle.putString("string", string_Reverse);      
        
        myCallerIntentHandler.putExtras(myBundle);
        
        // just debugging - show returning data 
        Return.append("\n"
            	+ "\n average: " + myBundle.getInt("average")
            	+ "\n Reversed String: " + myBundle.getString("string"));   
        
        // all done! 
        setResult(Activity.RESULT_OK, myCallerIntentHandler);
        
    }//onCreate
    
	private class Clicker1 implements OnClickListener 
	{
		public void onClick(View v) 
		{
			//clear Activity2 screen so Activity1 could be seen
			finish();			
		}//onClick    	
    }//Clicker1
}
