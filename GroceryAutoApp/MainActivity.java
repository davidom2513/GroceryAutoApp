package com.example.textpract;

import java.nio.channels.AsynchronousCloseException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	Button btn;
	TextView text1;
	TextView text2;
	TextView text3;
	TextView textBut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button) findViewById(R.id.button2);
		
		text1 = (TextView) findViewById(R.id.editText1);
		text2 = (TextView) findViewById(R.id.editText2);
		text3 = (TextView) findViewById(R.id.topTop2);
		
		textBut = (TextView) findViewById(R.id.butText2);
		
		
		
		btn.setOnClickListener( new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				new VerySlowTask().execute();			
			}
		});
	}
	
	
	
	private class VerySlowTask extends AsyncTask<String, Long, Void>
	{
		
		private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

			protected void onPreExecute()
			{
				this.dialog.setMessage("Loading");
				this.dialog.show();
			}
		
		@Override
		protected Void doInBackground(String... params) 
		{
			// TODO Auto-generated method stub
			
			try{
				String s = text2.getText().toString();
				
				String  int1 = text1.getText().toString();
				int i = Integer.parseInt(int1);
				
				String double1 = text3.getText().toString();
				double d = Double.parseDouble(double1);
				
				Intent act1 = new Intent(MainActivity.this,MainActivity2.class);/////////
								
				Bundle myBundle = new Bundle();
				myBundle.putString("string", s);
				myBundle.putInt("int", i);
				myBundle.putDouble("double", d);
				
				act1.putExtras(myBundle);	///////////	
				
				startActivityForResult(act1, 666);/////////
			}
			
			catch(Exception e)
			{
				Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
			return null;
		}
		
		
		
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
