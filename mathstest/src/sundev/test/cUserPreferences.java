package sundev.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

public abstract class cUserPreferences 
{
	static int gameDuration = 10;
	static String gameType;
	static int gameTypeSelection = 4;
	static String gameLevel;
	static int gameLevelSelection = 0;
	static EditText gameDurationEditText;
	static Spinner gameTypeSpinner;
	static Spinner gameLevelSpinner;
	static Activity myActivity;
	
	static void setPreferences(Activity theActivity)
	{
		myActivity = theActivity;
		gameType = myActivity.getResources().getStringArray(R.array.gameTypeArray)[gameTypeSelection];
		gameLevel = myActivity.getResources().getStringArray(R.array.gameLevelArray)[gameLevelSelection];
		cCalculator.setQuestionDifficulty(myActivity,gameLevel);		
	}
	static void handlePreferenceSettings(Activity theActivity)
	{
		myActivity = theActivity;
		
		// Display dialog so that user can set preferences
		AlertDialog.Builder builder;
		AlertDialog alertDialog;
		
		LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(TestActivity.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.preference_dialog,(ViewGroup) myActivity.findViewById(R.id.preference_layout));
		gameDurationEditText =(EditText)layout.findViewById(R.id.editTextGameDuration);
		gameTypeSpinner = (Spinner) layout.findViewById(R.id.spinnerGameType);
		gameLevelSpinner = (Spinner) layout.findViewById(R.id.spinnerGameLevel);
		

		gameDurationEditText.setText(String.valueOf(gameDuration));
		gameTypeSpinner.setSelection(gameTypeSelection);
		gameLevelSpinner.setSelection(gameLevelSelection);
		
		builder = new AlertDialog.Builder(theActivity);
		builder.setView(layout)
			.setTitle(R.string.strPreferencesDialogTitle)
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() 
            {
        	   public void onClick(DialogInterface dialog, int id) 
        	   {
        		   gameType = gameTypeSpinner.getSelectedItem().toString();
        		   gameTypeSelection = gameTypeSpinner.getSelectedItemPosition();
        		   gameLevel = gameLevelSpinner.getSelectedItem().toString();
        		   gameLevelSelection = gameLevelSpinner.getSelectedItemPosition();
        		   // Make sure the user has not entered a null string before attempting to parse
        		   // If user has done this then set gameDuration to zero and it will be dealt with later
      
        		   if (gameDurationEditText.getText().toString().length()==0)
        		   {
        			   gameDuration = 0;
        		   }
        		   else
        		   {
        			   gameDuration = Integer.parseInt(gameDurationEditText.getText().toString());
        		   }
        		   cCalculator.setQuestionDifficulty(myActivity,gameLevel);
         		   if (cUserPreferences.gameDuration < 10)
         		   {
         			   AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
         			   builder.setTitle("Warning")
         			   .setMessage("You entered a game duration of less than 10s. Your game duration has been defaulted to the minimum value of 10 seconds.")
         			   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
         		        public void onClick(DialogInterface dialog, int which) { 
         		        	cUserPreferences.gameDuration = 10;
         		        }
         		     });
         			 AlertDialog alert =builder.create();
         			 alert.show();
         			 
         		   }
        		   

        	   }
           })
           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
           {
        	   public void onClick(DialogInterface dialog, int id) {
        		   dialog.cancel();
           }
    });
	alertDialog = builder.create();
	alertDialog.show();   	
	}
}