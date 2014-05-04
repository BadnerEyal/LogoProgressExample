package logo.progress.example;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


//העלה של לוגו של אפלקציה 
//הרעיון תחילה נסתיר את השכבה השניה 
//נציג את הלוגו אחרי שיגמר הבר יתקדמות נסתיר את הלוגו ונציג את השכבה השניה
public class MainActivity extends Activity {

	private ProgressBar progressBar = null;//הבר
	private ImageView logoImageView = null;//לוגו
	private LinearLayout mainLinearLayout = null;//השכבה השניה
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//בשביל תמונת הלוגו בהתחלה
		logoImageView = (ImageView)findViewById(R.id.logoImageView);
		//בשביל השכבה השניה שנעבור אחרי הלוגו
		mainLinearLayout = (LinearLayout)findViewById(R.id.mainLinearLayout);
		
		//בשביל הבר
		progressBar = (ProgressBar)findViewById(R.id.progressBar);
	}

	
	// כאשר נקבל את המסך נתחיל את הבר
	@Override
	protected void onResume() 
	{
		super.onResume();
		
		new LogoTask().execute();
	}


	//AsyncTask בשביל לקדם את הבר לפי הקצב שניתן לו
	private class LogoTask extends AsyncTask<Void, Integer, Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			//איתחול הבר באפס מקסימום 100 זה בעצם גודל הבר
			progressBar.setProgress(0);
			progressBar.setMax(100);
			
			for(int i = 10;i<110;i++)
			{
				//כל צעד נצא מהטרד כדי לקדם את הבר ונחזור אחרי הקידום
				publishProgress(i);
				
				//נרדים את הטרד 
				//כדי לראות את התקדמות הבר
				try {Thread.sleep(100);} catch (InterruptedException e) {}
			}
			return null;
		}
        //קידום הבר כאשר הטרד רץ מקדמים  וחוזרים חזרה
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			super.onProgressUpdate(values);
			
			progressBar.setProgress(values[0]);
			
		}

		//בסיום הטרד נגיע לפונקציה זו נציג את השכבה השניה ונסתיר את הבר והלוגו
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			//להעלים את הבר והלוגו
			progressBar.setVisibility(View.GONE);
			logoImageView.setVisibility(View.GONE);
			//הצגת שכבה שניה
			mainLinearLayout.setVisibility(View.VISIBLE);
			
		}
		
		
		
		
		
	}
}
