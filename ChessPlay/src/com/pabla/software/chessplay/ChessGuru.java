package com.pabla.software.chessplay;

import com.google.devtools.simple.runtime.components.android.TinyDB;

public class ChessGuru
{
	private TinyDB m_database;
	
	public ChessGuru(TinyDB theDatabase)
	{
		m_database=theDatabase;
		
		m_database.StoreValue("A1", "WR1");
		m_database.StoreValue("B1", "WN1");
		m_database.StoreValue("C1", "WB1");
		m_database.StoreValue("D1", "WQ1");
		m_database.StoreValue("E1", "WK1");
		m_database.StoreValue("F1", "WB2");
		m_database.StoreValue("G1", "WN2");
		m_database.StoreValue("H1", "WR2");
		
		m_database.StoreValue("A2","WP1");
		m_database.StoreValue("B2","WP2");
		m_database.StoreValue("C2","WP3");
		m_database.StoreValue("D2","WP4");
		m_database.StoreValue("E2","WP5");
		m_database.StoreValue("F2","WP6");
		m_database.StoreValue("G2","WP7");
		m_database.StoreValue("H2","WP8");
		
		m_database.StoreValue("A3","EMPTY");
		m_database.StoreValue("B3","EMPTY");
		m_database.StoreValue("C3","EMPTY");
		m_database.StoreValue("D3","EMPTY");
		m_database.StoreValue("E3","EMPTY");
		m_database.StoreValue("F3","EMPTY");
		m_database.StoreValue("G3","EMPTY");
		m_database.StoreValue("H3","EMPTY");
		
		m_database.StoreValue("A4","EMPTY");
		m_database.StoreValue("B4","EMPTY");
		m_database.StoreValue("C4","EMPTY");
		m_database.StoreValue("D4","EMPTY");
		m_database.StoreValue("E4","EMPTY");
		m_database.StoreValue("F4","EMPTY");
		m_database.StoreValue("G4","EMPTY");
		m_database.StoreValue("H4","EMPTY");
		
		m_database.StoreValue("A5","EMPTY");
		m_database.StoreValue("B5","EMPTY");
		m_database.StoreValue("C5","EMPTY");
		m_database.StoreValue("D5","EMPTY");
		m_database.StoreValue("E5","EMPTY");
		m_database.StoreValue("F5","EMPTY");
		m_database.StoreValue("G5","EMPTY");
		m_database.StoreValue("H5","EMPTY");
		
		m_database.StoreValue("A6","EMPTY");
		m_database.StoreValue("B6","EMPTY");
		m_database.StoreValue("C6","EMPTY");
		m_database.StoreValue("D6","EMPTY");
		m_database.StoreValue("E6","EMPTY");
		m_database.StoreValue("F6","EMPTY");
		m_database.StoreValue("G6","EMPTY");
		m_database.StoreValue("H6","EMPTY");
		
		m_database.StoreValue("A7","BP1");
		m_database.StoreValue("B7","BP2");
		m_database.StoreValue("C7","BP3");
		m_database.StoreValue("D7","BP4");
		m_database.StoreValue("E7","BP5");
		m_database.StoreValue("F7","BP6");
		m_database.StoreValue("G7","BP7");
		m_database.StoreValue("H7","BP8");
		
		m_database.StoreValue("A8", "BR1");
		m_database.StoreValue("B8", "BN1");
		m_database.StoreValue("C8", "BB1");
		m_database.StoreValue("D8", "BQ1");
		m_database.StoreValue("E8", "BK1");
		m_database.StoreValue("F8", "BB2");
		m_database.StoreValue("G8", "BN2");
		m_database.StoreValue("H8", "BR2");

		
	}
	
}