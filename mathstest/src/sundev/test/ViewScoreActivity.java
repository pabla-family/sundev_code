package sundev.test;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewScoreActivity extends Activity implements OnItemSelectedListener {
	 private static MathsTestDbAdapter mDbHelper;
	private TextView tvInitial1;
	private TextView tvScore1;
	private TextView tvInitial2;
	private TextView tvScore2;
	private TextView tvInitial3;
	private TextView tvScore3;
	private TextView tvInitial4;
	private TextView tvScore4;
	private TextView tvInitial5;
	private TextView tvScore5;
	static Spinner gameTypeSpinner2;
	static Spinner gameLevelSpinner2;
	static Button ButtonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new MathsTestDbAdapter(this);
        mDbHelper.open();
        setContentView(R.layout.high_scores);
        
        gameTypeSpinner2 = (Spinner) findViewById(R.id.spinnerGameType2);
		gameTypeSpinner2.setOnItemSelectedListener(this);
		gameLevelSpinner2 = (Spinner) findViewById(R.id.spinnerGameLevel2);
		gameLevelSpinner2.setOnItemSelectedListener(this);
		
		gameTypeSpinner2.setSelection(cUserPreferences.gameTypeSelection);
		gameLevelSpinner2.setSelection(cUserPreferences.gameLevelSelection);
		
		ButtonExit = (Button) findViewById(R.id.buttonExit);
        
        ButtonExit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
		
        fillData();
        
    }
    private void fillData()
    {
		
    	Cursor topScoreCursor = mDbHelper.fetchScoresBasedOnSpecifiedGameLevelAndType(this.getResources().getStringArray(R.array.gameLevelArray)[gameLevelSpinner2.getSelectedItemPosition()],this.getResources().getStringArray(R.array.gameTypeArray)[gameTypeSpinner2.getSelectedItemPosition()]);
    	
    	startManagingCursor(topScoreCursor);
    	    	
    	tvInitial1 = (TextView) findViewById(R.id.initials1);
    	tvScore1 = (TextView) findViewById(R.id.score1);
    	tvInitial2 = (TextView) findViewById(R.id.initials2);
    	tvScore2 = (TextView) findViewById(R.id.score2);
    	tvInitial3 = (TextView) findViewById(R.id.initials3);
    	tvScore3 = (TextView) findViewById(R.id.score3);
    	tvInitial4 = (TextView) findViewById(R.id.initials4);
    	tvScore4 = (TextView) findViewById(R.id.score4);
    	tvInitial5 = (TextView) findViewById(R.id.initials5);
    	tvScore5 = (TextView) findViewById(R.id.score5);
    	
    	// Will use switch case without breaks to populate table
    	topScoreCursor.moveToFirst();
    	switch (topScoreCursor.getCount())
    	{
    	case 5:	
    		tvInitial5.setText(""+topScoreCursor.getString(1));
    		tvScore5.setText(""+topScoreCursor.getDouble(2));
    		topScoreCursor.moveToNext();
    	case 4:
    		tvInitial4.setText(""+topScoreCursor.getString(1));
    		tvScore4.setText(""+topScoreCursor.getDouble(2));
    		topScoreCursor.moveToNext();
    	case 3:
    		tvInitial3.setText(""+topScoreCursor.getString(1));
    		tvScore3.setText(""+topScoreCursor.getDouble(2));
    		topScoreCursor.moveToNext();
    	case 2:
    		tvInitial2.setText(""+topScoreCursor.getString(1));
    		tvScore2.setText(""+topScoreCursor.getDouble(2));
    		topScoreCursor.moveToNext();
    	case 1:
    		tvInitial1.setText(""+topScoreCursor.getString(1));
    		tvScore1.setText(""+topScoreCursor.getDouble(2));
    		topScoreCursor.moveToNext();
    		break;
    	}
    }
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		tvInitial1.setText("NA");
		tvScore1.setText("NA");
		tvInitial2.setText("NA");
		tvScore2.setText("NA");
		tvInitial3.setText("NA");
		tvScore3.setText("NA");
		tvInitial4.setText("NA");
		tvScore4.setText("NA");
		tvInitial5.setText("NA");
		tvScore5.setText("NA");
		fillData();
		
	}

}
