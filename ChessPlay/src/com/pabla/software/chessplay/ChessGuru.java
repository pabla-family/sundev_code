package com.pabla.software.chessplay;

import com.google.devtools.simple.runtime.components.android.TinyDB;

public class ChessGuru
{
	private TinyDB m_database;
	private String m_move_history[];
	private int m_move_count;
	private boolean m_white_still_allowed_to_castle_if_valid;
	private boolean m_black_still_allowed_to_castle_if_valid;
	private boolean m_check_for_enpassant_next_move;
	
	public ChessGuru(TinyDB theDatabase)
	{
		m_database=theDatabase;
		m_move_history = new String[500];
		m_move_count = 0;
		m_white_still_allowed_to_castle_if_valid = true;
		m_black_still_allowed_to_castle_if_valid = true;
		
		m_database.StoreValue("a1", "WR1");
		m_database.StoreValue("b1", "WN1");
		m_database.StoreValue("c1", "WB1");
		m_database.StoreValue("d1", "WQ1");
		m_database.StoreValue("e1", "WK1");
		m_database.StoreValue("f1", "WB2");
		m_database.StoreValue("g1", "WN2");
		m_database.StoreValue("h1", "WR2");
		
		m_database.StoreValue("a2","WP1");
		m_database.StoreValue("b2","WP2");
		m_database.StoreValue("c2","WP3");
		m_database.StoreValue("d2","WP4");
		m_database.StoreValue("e2","WP5");
		m_database.StoreValue("f2","WP6");
		m_database.StoreValue("g2","WP7");
		m_database.StoreValue("h2","WP8");
		
		m_database.StoreValue("a3","EMPTY");
		m_database.StoreValue("b3","EMPTY");
		m_database.StoreValue("d3","EMPTY");
		m_database.StoreValue("d3","EMPTY");
		m_database.StoreValue("e3","EMPTY");
		m_database.StoreValue("f3","EMPTY");
		m_database.StoreValue("g3","EMPTY");
		m_database.StoreValue("h3","EMPTY");
		
		m_database.StoreValue("a4","EMPTY");
		m_database.StoreValue("b4","EMPTY");
		m_database.StoreValue("c4","EMPTY");
		m_database.StoreValue("d4","EMPTY");
		m_database.StoreValue("e4","EMPTY");
		m_database.StoreValue("f4","EMPTY");
		m_database.StoreValue("g4","EMPTY");
		m_database.StoreValue("h4","EMPTY");
		
		m_database.StoreValue("a5","EMPTY");
		m_database.StoreValue("b5","EMPTY");
		m_database.StoreValue("c5","EMPTY");
		m_database.StoreValue("d5","EMPTY");
		m_database.StoreValue("e5","EMPTY");
		m_database.StoreValue("f5","EMPTY");
		m_database.StoreValue("g5","EMPTY");
		m_database.StoreValue("h5","EMPTY");
		
		m_database.StoreValue("a6","EMPTY");
		m_database.StoreValue("b6","EMPTY");
		m_database.StoreValue("c6","EMPTY");
		m_database.StoreValue("d6","EMPTY");
		m_database.StoreValue("e6","EMPTY");
		m_database.StoreValue("f6","EMPTY");
		m_database.StoreValue("g6","EMPTY");
		m_database.StoreValue("g6","EMPTY");
		
		m_database.StoreValue("a7","BP1");
		m_database.StoreValue("a7","BP2");
		m_database.StoreValue("c7","BP3");
		m_database.StoreValue("d7","BP4");
		m_database.StoreValue("e7","BP5");
		m_database.StoreValue("f7","BP6");
		m_database.StoreValue("g7","BP7");
		m_database.StoreValue("h7","BP8");
		
		m_database.StoreValue("a8", "BR1");
		m_database.StoreValue("b8", "BN1");
		m_database.StoreValue("c8", "BB1");
		m_database.StoreValue("d8", "BQ1");
		m_database.StoreValue("e8", "BK1");
		m_database.StoreValue("f8", "BB2");
		m_database.StoreValue("g8", "BN2");
		m_database.StoreValue("g8", "BR2");		
	}
	
	// The function takes a location and returns the piece at the location
	public String getLocationStatus(String location)
	{
		return m_database.GetValue(location).toString();
	}
	
	//TODO This function needs populating, for now it is a
	// stub which will always return true
	public Boolean doesPieceHaveValidMoves(String location, Boolean isPlayingBlack)
	{
		return true;
	}
	
	//TODO This function needs populating, for now it is a
	// stub which will always return true 
	public Boolean isMoveValid(String moveFrom,String moveTo, Boolean isPlayingBlack)
	{
		return true;
	}
	
	// Update Database - It is up to the user of this class to have checked that moves are valid
	// before updating the database
	public String updateDatabase(String moveFrom,String moveTo)
	{
		String moveDescription;
		String moveFromDescription = m_database.GetValue(moveFrom).toString().substring(1);
		String moveToDescription = m_database.GetValue(moveTo).toString();
		
		if (moveFromDescription.compareTo("P") == 0)
		{
			moveFromDescription = moveFrom;
			moveDescription = moveFromDescription;
		}
		else
		{
			moveFromDescription = moveFromDescription+moveFrom;
			moveDescription = moveFromDescription;
		}
		
		if (moveToDescription.compareTo("EMPTY") == 0)
		{
			moveDescription = moveDescription+"-"+moveTo;
		}
		else
		{
			moveToDescription = moveToDescription.substring(1);
			if (moveToDescription.compareTo("P")==0)
			{
				moveDescription = moveDescription+"x"+moveTo;
			}
			else
			{
				moveDescription = moveDescription+"x"+moveToDescription+moveTo;
			}
		}
		
		// Store the move in history array
		m_move_history[m_move_count] = moveDescription;
		m_move_count++;
		
		// Update the database
		m_database.StoreValue(moveTo,(String) m_database.GetValue(moveFrom));
		m_database.StoreValue(moveFrom,"EMPTY");

		
		return moveDescription;
	}	
}