package com.sms.filter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ProgressBar progressBar = null;
	private ImageView logoImageView = null;
	private LinearLayout mainLinearLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		logoImageView = (ImageView)findViewById(R.id.logoImageView);
		mainLinearLayout = (LinearLayout)findViewById(R.id.mainLinearLayout);
		
		progressBar = (ProgressBar)findViewById(R.id.progressBar);
	}

	
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		
		new LogoTask().execute();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	private class LogoTask extends AsyncTask<Void, Integer, Void>
	{

		
		@Override
		protected Void doInBackground(Void... params) {
			progressBar.setProgress(0);
			progressBar.setMax(100);
			
			for(int i = 10;i<110;i++)
			{
				publishProgress(i);
				
				try {Thread.sleep(100);} catch (InterruptedException e) {}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			
			super.onProgressUpdate(values);
			
			progressBar.setProgress(values[0]);
			
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressBar.setVisibility(View.GONE);
			logoImageView.setVisibility(View.GONE);
			mainLinearLayout.setVisibility(View.VISIBLE);
			
		}
		
		
		
		
		
	}
}
