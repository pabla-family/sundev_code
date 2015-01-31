package sundev.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.ads.*;
import android.database.Cursor;

public class TestActivity extends Activity implements OnClickListener {
    Button ButtonPressed;
    Button ButtonZero;
    Button ButtonOne;
    Button ButtonTwo;
    Button ButtonThree;
    Button ButtonFour;
    Button ButtonFive;
    Button ButtonSix;
    Button ButtonSeven;
    Button ButtonEight;
    Button ButtonNine;
    Button ButtonClear;
    Button ButtonEnter;
    String userInput = "";
    String userInput1 = "";
    String userInput2 = "";
    String userInput3 = "";
    String userInput4 = "";
    long startTime_S;
    int userInputCount = 0;
    static int correctAnswerCount = 0;
    static int wrongAnswerCount = 0;
    static int maxTopScores = 5;
    boolean gameOver = true;  // Initially set to true because there is no game in progress
    String topScorerInitials;
    
    private static MathsTestDbAdapter mDbHelper;
    
    String questionString = "Press any button to begin";
    TextView tvQuestionAnswerString;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Configuration theConfig = getResources().getConfiguration();
        onConfigurationChanged(theConfig);
        cUserPreferences.setPreferences(this);
        mDbHelper = new MathsTestDbAdapter(this);
        mDbHelper.open();
    }
    public static MathsTestDbAdapter getDatabaseHelper()
    {
    	return mDbHelper;
    }
    public void checkIfTopScore()
    {
    	// Get all top scores for the current game type and level
    	Cursor topScoreCursor = mDbHelper.fetchScoresBasedOnGameLevelAndType();
    	startManagingCursor(topScoreCursor);
    	Double lowestTopScore;
    	long rowIdOfLowestTopScore = 0;
    	boolean bTopScore = false;
    	
    	
    	// If the number of maxTopScores has not been reached then
    	// user has a topScore
    	if (topScoreCursor.getCount() < maxTopScores)
    	{
    		// The user has a top score;
    		bTopScore = true;
    		
    	}
    	else
    	{
    		// We need to check if the user has a top score	
    		lowestTopScore = cPerformanceFeedback.score;
    		topScoreCursor.moveToFirst();
    		for (int i = 0;i<topScoreCursor.getCount();i++)
    		{
    			
    			if (topScoreCursor.getDouble(2) < lowestTopScore)
    			{
    				lowestTopScore = topScoreCursor.getDouble(2);
    				rowIdOfLowestTopScore  = topScoreCursor.getLong(0);
    				bTopScore = true;
    			}
    			topScoreCursor.moveToNext();
    		}
    		// Delete the lowestTopScore in the database so that it can be replaced by the players top score
    		mDbHelper.deleteScore(rowIdOfLowestTopScore);  		
    	}
    	if (bTopScore)
		{		
			getTopScorersInitials();
				
		}
    }
    public void getTopScorersInitials()
    {
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

    	alert.setTitle("Top Score");
    	alert.setMessage("Congrats - This is a top score, please enter your name");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(this);
    	input.setMaxEms(3);
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	  String value = input.getText().toString();
    	  topScorerInitials = value;
    	  mDbHelper.addTopScore(topScorerInitials, cPerformanceFeedback.score);
    	  }
    	});

    	alert.show();
  
    }
    public void handleViewHighestScore()
    {
    	// launch a new activity to handle the viewing of highest scores
    	Intent i = new Intent(this, ViewScoreActivity.class);
        startActivity(i);
    }
    // Set up menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    		super.onCreateOptionsMenu(menu);
    		MenuInflater mi = getMenuInflater();
    		mi.inflate(R.menu.main_menu, menu);		
    		return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
    	super.onPrepareOptionsMenu(menu);
    	// Don't display menu in the middle of a game
    	if (!gameOver)
    	{
    		menu.getItem(0).setEnabled(false);
    	}
    	else
    	{
    		menu.getItem(0).setEnabled(true);
    		
    	}
    	return true;    	
    }
    // Handle selection of menu item
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
    	switch(item.getItemId())
    	{
    	case R.id.menu_setPreferences:
    		cUserPreferences.handlePreferenceSettings(this); 
    		break;
    	case R.id.menu_viewHighestScores:
    		handleViewHighestScore();
    		break;
    	}
    	return super.onMenuItemSelected(featureId, item);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if ((newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)&& ((newConfig.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL ))
        {
        	setContentView(R.layout.main_smallscreen);
        	tvQuestionAnswerString = (TextView) findViewById(R.id.textViewQuestion);
        	tvQuestionAnswerString.setText(questionString + userInput);
        	setListeners();
        	 // Look up the AdView as a resource and load a request.
            AdView adView = (AdView)this.findViewById(R.id.adView);
            adView.loadAd(new AdRequest());
        }
        else if((newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)&& ((newConfig.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) != Configuration.SCREENLAYOUT_SIZE_SMALL ))
        {
        	
        	setContentView(R.layout.main_smallscreen);
        	tvQuestionAnswerString = (TextView) findViewById(R.id.textViewQuestion);
        	tvQuestionAnswerString.setText(questionString + userInput);
        	setListeners();
       	 // Look up the AdView as a resource and load a request.
            AdView adView = (AdView)this.findViewById(R.id.adView);
            adView.loadAd(new AdRequest());
        	
        	
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
        	
        	setContentView(R.layout.main);
        	tvQuestionAnswerString = (TextView) findViewById(R.id.textViewQuestion);
        	tvQuestionAnswerString.setText(questionString + userInput);
        	setListeners();
       	 	// Look up the AdView as a resource and load a request.
            AdView adView = (AdView)this.findViewById(R.id.adView);
            adView.loadAd(new AdRequest());       	
        }

    }   
    private void setListeners()
    {
    	//Get handles to buttons
        ButtonZero = (Button) findViewById(R.id.ButtonZero);
        ButtonZero.setOnClickListener(this);
        ButtonOne = (Button) findViewById(R.id.ButtonOne);
        ButtonOne.setOnClickListener(this);
        ButtonTwo = (Button) findViewById(R.id.ButtonTwo);
        ButtonTwo.setOnClickListener(this);
        ButtonThree = (Button) findViewById(R.id.ButtonThree);
        ButtonThree.setOnClickListener(this);
        ButtonFour = (Button) findViewById(R.id.ButtonFour);
        ButtonFour.setOnClickListener(this);
        ButtonFive = (Button) findViewById(R.id.ButtonFive);
        ButtonFive.setOnClickListener(this);
        ButtonSix = (Button) findViewById(R.id.ButtonSix);
        ButtonSix.setOnClickListener(this);
        ButtonSeven = (Button) findViewById(R.id.ButtonSeven);
        ButtonSeven.setOnClickListener(this);
        ButtonEight = (Button) findViewById(R.id.ButtonEight);
        ButtonEight.setOnClickListener(this);
        ButtonNine = (Button) findViewById(R.id.ButtonNine);
        ButtonNine.setOnClickListener(this);
        ButtonClear = (Button) findViewById(R.id.ButtonClear);
        ButtonClear.setOnClickListener(this);
        ButtonEnter = (Button) findViewById(R.id.ButtonEnter);
        ButtonEnter.setOnClickListener(this);   	
    }

	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		ButtonPressed = (Button) v;
		if (questionString == "Press any button to begin")
		{
	        correctAnswerCount = 0;
	        wrongAnswerCount = 0;
			questionString = cCalculator.getQuestion();
			tvQuestionAnswerString.setText(questionString);
			// Also start the timer
			startTime_S = System.currentTimeMillis()/1000;
			gameOver = false;
		}
		else if (ButtonPressed == ButtonEnter)
		{
			// Check if users answer is correct and also if time is up
			// If answer is correct and not time up then display another question
			// If answer is wrong and time is not up then clear wrong answer and display same question
			// If time is up, inform user of how many questions he got correct in the alloted time
			
			// Check if game is over
			if (System.currentTimeMillis()/1000 - startTime_S > cUserPreferences.gameDuration)
			{		
				gameOver = true;							
			}
			// Check if user answer is correct
			if (userInput.equals(cCalculator.getAnswer()))
			{
				//Answer is correct, increment the correct answer count
				correctAnswerCount++;
				if (!gameOver) // Give the user another question
				{
					questionString = cCalculator.getQuestion();
					tvQuestionAnswerString.setText(questionString);				
				}
			}
			else
			{
				wrongAnswerCount++;
				if (!gameOver)
				{
					tvQuestionAnswerString.setText(questionString);				
				}
				
			}
			
			// Present user feedback if game is over
			if (gameOver)
			{
		        questionString = "Press any button to begin";
		        tvQuestionAnswerString.setText(questionString);
		        tvQuestionAnswerString.refreshDrawableState();
		        cPerformanceFeedback.handlePerformanceFeedback(this);
		        checkIfTopScore();
	       			
		    }
			//Clear user input strings
			userInput = "";
			userInputCount = 0;
			
		}
		else if (ButtonPressed == ButtonClear)
		{
			userInputCount--;
			if (userInputCount < 0)
			{
				userInputCount = 0;
			}
			
			switch (userInputCount)
			{
				case 0:
					userInput = "";
					break;
				case 1:
					userInput = userInput1;
					break;
				case 2:
					userInput = userInput1 + userInput2;
					break;
				case 3:
					userInput = userInput1 + userInput2 + userInput3;
					break;
				case 4:
					userInput = userInput1 + userInput2 + userInput3 + userInput4;
					break;			
			}
			tvQuestionAnswerString.setText(questionString + userInput);
			
		}
		else 
		{
			userInputCount++;
			String strTemp = (String)ButtonPressed.getText();
			switch (userInputCount)
			{
				case 1:
					userInput1 = strTemp;
					userInput = userInput1;
					break;
				case 2:
					userInput2 = strTemp;
					userInput = userInput1 + userInput2;
					break;
				case 3:
					userInput3 = strTemp;
					userInput = userInput1 + userInput2 + userInput3;
					break;
				case 4:
					userInput4 = strTemp;
					userInput = userInput1 + userInput2 + userInput3 + userInput4;
					break;
				case 5:
					// User has clearly entered an incorrect answer, clear the answer but do not log
					// an incorrect answer because the user did not press enter
					userInput = "";
					userInputCount = 0;
					break;			
			}
			tvQuestionAnswerString.setText(questionString + userInput);
				
		}
		
	}
}