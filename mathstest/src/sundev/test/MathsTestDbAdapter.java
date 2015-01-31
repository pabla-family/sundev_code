package sundev.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//
// This class handles all the database functions such as create, read, update and delete.
// The SQL lite database is used to store High Scores along with information about the
// level and game type the high score was associated with.
//
public class MathsTestDbAdapter 
{
	// String variables to describe the schema of the database
	private static final String DATABASE_NAME = "Maths_Test_Db";
	private static final String DATABASE_TABLE = "Performace_Stats";
	private static final int DATABASE_VERSION = 1;
	
	private static final String TAG = "MathsTestDbAdapter";
	public static final String KEY_ROWID = "_id";
	public static final String KEY_USER_INITIALS = "initials";
	public static final String KEY_SCORE = "score";
	public static final String KEY_LEVEL = "level";
	public static final String KEY_GAME_TYPE = "type";
	
	// Store an SQL command for creating the database in a string variable called
	// DATABASE_CREATE
    private static final String DATABASE_CREATE ="create table " + DATABASE_TABLE + " ("
    		+ KEY_ROWID + " integer primary key autoincrement, "
    		+ KEY_USER_INITIALS + " text not null, "
    		+ KEY_SCORE + " real not null, " 
    		+ KEY_LEVEL + " text not null, "
    		+ KEY_GAME_TYPE + " text not null);";
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private final Context mCtx;
	
	public MathsTestDbAdapter(Context ctx)
	{
		this.mCtx = ctx;
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper 
	{

	    DatabaseHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {

	        db.execSQL(DATABASE_CREATE);
	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
	        db.execSQL("DROP TABLE IF EXISTS Performance_Stats");
	        onCreate(db);
	    }
	}
    public MathsTestDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }
    
    public long addTopScore(String initials,Double score)
    {
    	// This function adds a high score to the database
    	// The initials and score of the high scorer are passed in, info
    	// about the game level and type are obtained from the preset user
    	// preferences
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(KEY_USER_INITIALS, initials);
    	initialValues.put(KEY_SCORE, score);
    	initialValues.put(KEY_LEVEL, cUserPreferences.gameLevel);
    	initialValues.put(KEY_GAME_TYPE, cUserPreferences.gameType);
    	
    	return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    public boolean deleteScore(long rowId)
    {
    	return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public Cursor fetchAllScores()
    {
    	// This function returns data for all scores in the database in ascending order
    	return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_USER_INITIALS,KEY_SCORE}, null, null, null, KEY_SCORE + " ASC", null);
    }
    public Cursor fetchScoresBasedOnGameLevelAndType()
    {
    	return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_USER_INITIALS,KEY_SCORE}, KEY_LEVEL + " = " + "'" +  cUserPreferences.gameLevel + "'" +  " and " + KEY_GAME_TYPE + " = " + "'" +  cUserPreferences.gameType + "'", null, null,null,KEY_SCORE + " ASC", null);
    }
    public Cursor fetchScoresBasedOnSpecifiedGameLevelAndType(String level, String type)
    {
     	return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_USER_INITIALS,KEY_SCORE}, KEY_LEVEL + " = " + "'" + level + "'" + " and " + KEY_GAME_TYPE + " = " + "'" + type + "'" , null, null,null,KEY_SCORE + " ASC", null);
    }
    
    public boolean updateScore(long rowId,String initials, String score)
    {
    	ContentValues args = new ContentValues();
    	args.put(KEY_USER_INITIALS, initials);
    	args.put(KEY_SCORE, score);
    	args.put(KEY_LEVEL, cUserPreferences.gameLevel);
    	args.put(KEY_GAME_TYPE, cUserPreferences.gameType);
    	
    	return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null)>0;
    }   
}

