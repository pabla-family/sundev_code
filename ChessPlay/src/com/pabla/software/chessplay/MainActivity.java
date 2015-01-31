package com.pabla.software.chessplay;

import com.google.devtools.simple.runtime.components.Component;
import com.google.devtools.simple.runtime.components.HandlesEventDispatching;

//import com.google.devtools.simple.runtime.components.android.Canvas;
//import com.google.devtools.simple.runtime.components.android.Form;
//import com.google.devtools.simple.runtime.components.android.HorizontalArrangement;
//import com.google.devtools.simple.runtime.components.android.Label;
//import com.google.devtools.simple.runtime.components.android.ListPicker;
//import com.google.devtools.simple.runtime.components.android.VerticalArrangement;
import com.google.devtools.simple.runtime.components.android.util.CsvUtil;
import com.google.devtools.simple.runtime.events.EventDispatcher;
import com.google.devtools.simple.runtime.components.android.*;
import com.google.devtools.simple.runtime.components.util.YailList;



public class MainActivity extends Form  implements HandlesEventDispatching
{
	
	private VerticalArrangement m_vertical_arrangement1;
	private HorizontalArrangement m_horizontal_arrangement1;
	private ListPicker m_bluetooth_connection_picker;
	private Label m_connection_status_label;
	private Canvas m_chess_board;
	private ImageSprite m_black_pawn1;
	
	void $define()
	{
		// Create the chess board
		m_vertical_arrangement1 = new VerticalArrangement(this);
		m_horizontal_arrangement1 = new HorizontalArrangement(m_vertical_arrangement1);
		m_bluetooth_connection_picker = new ListPicker(m_horizontal_arrangement1);
		m_bluetooth_connection_picker.ElementsFromString("Test");
//
        //Right now, for purpose sample, we are just hardcoding items and storing into strCsvElements string
        String strCsvElements = "item1, item2, item3, item4";
        try {
            //Using Util class of CsvUtil, invoke static method of fromCsvRow to convert strCsvElements into YailList
        	YailList yaillist = CsvUtil.fromCsvRow(strCsvElements);
//
//            //now, set the yailList into picker
            m_bluetooth_connection_picker.Elements(yaillist);
        } catch (Exception e) {
            //TODO: update for proper error generation
            e.printStackTrace();
        }
		m_connection_status_label = new Label(m_horizontal_arrangement1);
		m_connection_status_label.Text("Not Connected ");
		m_chess_board = new Canvas(m_vertical_arrangement1);
		m_chess_board.BackgroundImage("chess_board.png");
		m_chess_board.Width(320);
		m_chess_board.Height(320);
		
		m_black_pawn1 = new ImageSprite(m_chess_board);
		
		m_black_pawn1.X(0.0d);
		m_black_pawn1.Y(40.0d);
		m_black_pawn1.Picture("black_pawn.png");
		EventDispatcher.registerEventForDelegation(this, "ChessBoardTouch", "Touched");
		
		
	}

	@Override
	public boolean dispatchEvent(Component component, String componentName,
			String eventName, Object[] args) {
		// TODO Auto-generated method stub
		//return super.dispatchEvent(component, componentName, eventName, args);
		if(component.equals(m_chess_board) && eventName.equals("Touched"))
		{
			
		}
		
        if (component.equals(m_bluetooth_connection_picker) && eventName.equals("Click")) {
        	m_bluetooth_connection_picker.Open();
            return true;

        }  //else if ..   TODO: add as needed
		return true;
	}


}
