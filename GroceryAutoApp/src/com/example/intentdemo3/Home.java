//Activity1: Invoking a user-defined sub-activity
//sending and receiving results from the sub-activity
package com.example.intentdemo3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity 
{ 
	Button btnRandom;
	Button btnUserInput; 
	
	// arbitrary interprocess communication ID (just a nickname!)
	private final int IPC_ID = (int) (10001 * Math.random());
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);  
		try 
		{
			setContentView(R.layout.home);
			
		    ScrollView sv = new ScrollView(this);
		    final LinearLayout ll = new LinearLayout(this);
		    ll.setOrientation(LinearLayout.VERTICAL);
		    sv.addView(ll); 
						
			btnRandom = (Button) findViewById(R.id.btnSumValues); 
			btnRandom.setOnClickListener(new Clicker1());
			
			btnUserInput = (Button) findViewById(R.id.btnUserInput); 
			btnUserInput.setOnClickListener(new Clicker_UserInput()); 
		} 
		
		catch (Exception e) 
		{
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}// onCreate

	private class Clicker1 implements OnClickListener 
	{
		public void onClick(View v) 
		{
			try 
			{			
				// create an Intent to talk to Activity2
				Intent myIntentA1A2 = new Intent(Home.this,
												 GroceryList.class);			
				
				// call Activity2 and wait for results
				startActivityForResult(myIntentA1A2, IPC_ID);
			} 
			
			catch (Exception e) 
			{
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
		}// onClick
	}// Clicker1
	
	private class Clicker_UserInput implements OnClickListener 
	{
		public void onClick(View v) 
		{
			try 
			{				
				// create an Intent to talk to Activity2
				Intent myIntentA1A2 = new Intent(Home.this,
												 GroceryList.class);			
				
				// call Activity2 and wait for results
				startActivityForResult(myIntentA1A2, IPC_ID);
			} 
			
			catch (Exception e) 
			{
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
		}// onClick
	}// Clicker1
}// AndroIntent1
