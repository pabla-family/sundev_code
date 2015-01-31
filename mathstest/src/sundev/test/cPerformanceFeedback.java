package sundev.test;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.database.Cursor;

public abstract class cPerformanceFeedback 
{
	static Activity myActivity;
	static TextView durationTextView;
	static TextView correctAnswerTextView;
	static TextView wrongAnswerTextView;
	static TextView scoreTextView;
	static double score;

	static void handlePerformanceFeedback(Activity theActivity)
	{
		myActivity = theActivity;
		
		// Display dialog so that user can set preferences
		AlertDialog.Builder builder;
		AlertDialog alertDialog;
		
		LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(TestActivity.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.feedback_dialog,(ViewGroup) myActivity.findViewById(R.id.feedback_layout));

		durationTextView =(TextView)layout.findViewById(R.id.Duration);
		correctAnswerTextView =(TextView)layout.findViewById(R.id.CorrectAnswers);
		wrongAnswerTextView =(TextView)layout.findViewById(R.id.WrongAnswers);
		scoreTextView =(TextView)layout.findViewById(R.id.Score);
		
		durationTextView.setText(" Duration: " + cUserPreferences.gameDuration + " seconds");
		correctAnswerTextView.setText(" Correct Answers: " + TestActivity.correctAnswerCount);
		wrongAnswerTextView.setText(" Wrong Answers: " + TestActivity.wrongAnswerCount);
		double timeRatio;
		timeRatio = (60.0/cUserPreferences.gameDuration);
		score = (TestActivity.correctAnswerCount - TestActivity.wrongAnswerCount)* timeRatio;
		if (score < 0)
		{
			score = 0;
		}
		DecimalFormat df = new DecimalFormat("#.##");
		scoreTextView.setText(" You Scored: " + df.format(score) + " points");
		
		builder = new AlertDialog.Builder(myActivity);
		builder.setView(layout)
			.setTitle("Your Performance Stats")
            .setCancelable(false)
            .setNegativeButton("OK", new DialogInterface.OnClickListener() 
            {
        	   public void onClick(DialogInterface dialog, int id) 
        	   {
        		   dialog.cancel();
        	   }
            });
        alertDialog = builder.create();
        alertDialog.show();
	}
}