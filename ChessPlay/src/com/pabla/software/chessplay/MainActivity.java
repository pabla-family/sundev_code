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
	private ImageSprite m_black_pawn2;
	private ImageSprite m_black_pawn3;
	private ImageSprite m_black_pawn4;
	private ImageSprite m_black_pawn5;
	private ImageSprite m_black_pawn6;
	private ImageSprite m_black_pawn7;
	private ImageSprite m_black_pawn8;
	private ImageSprite m_black_rook1;
	private ImageSprite m_black_knight1;
	private ImageSprite m_black_bishop1;
	private ImageSprite m_black_queen;
	private ImageSprite m_black_king;
	private ImageSprite m_black_bishop2;
	private ImageSprite m_black_knight2;
	private ImageSprite m_black_rook2;
	private ImageSprite m_white_pawn1;
	private ImageSprite m_white_pawn2;
	private ImageSprite m_white_pawn3;
	private ImageSprite m_white_pawn4;
	private ImageSprite m_white_pawn5;
	private ImageSprite m_white_pawn6;
	private ImageSprite m_white_pawn7;
	private ImageSprite m_white_pawn8;
	private ImageSprite m_white_rook1;
	private ImageSprite m_white_knight1;
	private ImageSprite m_white_bishop1;
	private ImageSprite m_white_queen;
	private ImageSprite m_white_king;
	private ImageSprite m_white_bishop2;
	private ImageSprite m_white_knight2;
	private ImageSprite m_white_rook2;
	private BluetoothClient m_bluetooth_client;
	private BluetoothServer m_bluetooth_server;
	private Clock m_clock;
	
	private boolean m_connected;
	private boolean m_playing_black;
	
	void $define()
	{
		// Initally the connection status is false
		m_connected = false;
		m_playing_black = false;
		
		// Create the chess board
		m_vertical_arrangement1 = new VerticalArrangement(this);
		m_horizontal_arrangement1 = new HorizontalArrangement(m_vertical_arrangement1);
		m_bluetooth_connection_picker = new ListPicker(m_horizontal_arrangement1);
		m_bluetooth_client = new BluetoothClient(this);
		m_bluetooth_server = new BluetoothServer(this);
		m_bluetooth_server.AcceptConnection(" ");
		m_clock = new Clock(this);
		m_clock.TimerEnabled(false);
		
  
		m_connection_status_label = new Label(m_horizontal_arrangement1);
		m_connection_status_label.Text("Not Connected ");
		m_chess_board = new Canvas(m_vertical_arrangement1);
		m_chess_board.BackgroundImage("chess_board.png");
		m_chess_board.Width(320);
		m_chess_board.Height(320);
		
		m_black_pawn1 = new ImageSprite(m_chess_board);
		m_black_pawn2 = new ImageSprite(m_chess_board);
		m_black_pawn3 = new ImageSprite(m_chess_board);
		m_black_pawn4 = new ImageSprite(m_chess_board);
		m_black_pawn5 = new ImageSprite(m_chess_board);
		m_black_pawn6 = new ImageSprite(m_chess_board);
		m_black_pawn7 = new ImageSprite(m_chess_board);
		m_black_pawn8 = new ImageSprite(m_chess_board);
		m_black_rook1 = new ImageSprite(m_chess_board);
		m_black_knight1 = new ImageSprite(m_chess_board);
		m_black_bishop1 = new ImageSprite(m_chess_board);
		m_black_queen = new ImageSprite(m_chess_board);
		m_black_king = new ImageSprite(m_chess_board);
		m_black_bishop2 = new ImageSprite(m_chess_board);
		m_black_knight2 = new ImageSprite(m_chess_board);
		m_black_rook2 = new ImageSprite(m_chess_board);
		
		m_white_pawn1 = new ImageSprite(m_chess_board);
		m_white_pawn2 = new ImageSprite(m_chess_board);
		m_white_pawn3 = new ImageSprite(m_chess_board);
		m_white_pawn4 = new ImageSprite(m_chess_board);
		m_white_pawn5 = new ImageSprite(m_chess_board);
		m_white_pawn6 = new ImageSprite(m_chess_board);
		m_white_pawn7 = new ImageSprite(m_chess_board);
		m_white_pawn8 = new ImageSprite(m_chess_board);
		m_white_rook1 = new ImageSprite(m_chess_board);
		m_white_knight1 = new ImageSprite(m_chess_board);
		m_white_bishop1 = new ImageSprite(m_chess_board);
		m_white_queen = new ImageSprite(m_chess_board);
		m_white_king = new ImageSprite(m_chess_board);
		m_white_bishop2 = new ImageSprite(m_chess_board);
		m_white_knight2 = new ImageSprite(m_chess_board);
		m_white_rook2 = new ImageSprite(m_chess_board);
		
		m_black_pawn1.Picture("black_pawn.png");
		m_black_pawn2.Picture("black_pawn.png");
		m_black_pawn3.Picture("black_pawn.png");
		m_black_pawn4.Picture("black_pawn.png");
		m_black_pawn5.Picture("black_pawn.png");
		m_black_pawn6.Picture("black_pawn.png");
		m_black_pawn7.Picture("black_pawn.png");
		m_black_pawn8.Picture("black_pawn.png");
		m_black_rook1.Picture("black_rook.png");
		m_black_knight1.Picture("black_knight.png");
		m_black_bishop1.Picture("black_bishop.png");
		m_black_queen.Picture("black_queen.png");
		m_black_king.Picture("black_king.png");
		m_black_bishop2.Picture("black_bishop.png");
		m_black_knight2.Picture("black_knight.png");
		m_black_rook2.Picture("black_rook.png");
		
		m_white_pawn1.Picture("white_pawn.png");
		m_white_pawn2.Picture("white_pawn.png");
		m_white_pawn3.Picture("white_pawn.png");
		m_white_pawn4.Picture("white_pawn.png");
		m_white_pawn5.Picture("white_pawn.png");
		m_white_pawn6.Picture("white_pawn.png");
		m_white_pawn7.Picture("white_pawn.png");
		m_white_pawn8.Picture("white_pawn.png");
		m_white_rook1.Picture("white_rook.png");
		m_white_knight1.Picture("white_knight.png");
		m_white_bishop1.Picture("white_bishop.png");
		m_white_queen.Picture("white_queen.png");
		m_white_king.Picture("white_king.png");
		m_white_bishop2.Picture("white_bishop.png");
		m_white_knight2.Picture("white_knight.png");
		m_white_rook2.Picture("white_rook.png");
				
		setWhitePlayerBoard();

		EventDispatcher.registerEventForDelegation(this, "ChessBoardTouch", "Touched");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerClick", "Click");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerBeforePicking", "BeforePicking");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerAfterPicking", "AfterPicking");
		EventDispatcher.registerEventForDelegation(this, "BluetoothServerConnectionAccepted", "ConnectionAccepted");
		
		
		
		
	}
	
	void setBlackPlayerBoard()
	{	
		m_white_rook1.X(0.0d);
		m_white_rook1.Y(0.0d);
		m_white_knight1.X(40.0d);
		m_white_knight1.Y(0.0d);
		m_white_bishop1.X(80.0d);
		m_white_bishop1.Y(0.0d);
		m_white_king.X(120.0d);
		m_white_king.Y(0.0d);
		m_white_queen.X(160.0d);
		m_white_queen.Y(0.0d);
		m_white_bishop2.X(200.0d);
		m_white_bishop2.Y(0.0d);
		m_white_knight2.X(240.0d);
		m_white_knight2.Y(0.0d);
		m_white_rook2.X(280.0d);	
		m_white_rook2.Y(0.0d);		
		m_white_pawn1.X(0.0d);
		m_white_pawn1.Y(40.0d);
		m_white_pawn2.X(40.0d);
		m_white_pawn2.Y(40.0d);
		m_white_pawn3.X(80.0d);
		m_white_pawn3.Y(40.0d);
		m_white_pawn4.X(120.0d);
		m_white_pawn4.Y(40.0d);
		m_white_pawn5.X(160.0d);
		m_white_pawn5.Y(40.0d);
		m_white_pawn6.X(200.0d);
		m_white_pawn6.Y(40.0d);
		m_white_pawn7.X(240.0d);
		m_white_pawn7.Y(40.0d);
		m_white_pawn8.X(280.0d);
		m_white_pawn8.Y(40.0d);
		
		m_black_pawn1.X(0.0d);
		m_black_pawn1.Y(240.0d);
		m_black_pawn2.X(40.0d);
		m_black_pawn2.Y(240.0d);
		m_black_pawn3.X(80.0d);
		m_black_pawn3.Y(240.0d);
		m_black_pawn4.X(120.0d);
		m_black_pawn4.Y(240.0d);
		m_black_pawn5.X(160.0d);
		m_black_pawn5.Y(240.0d);
		m_black_pawn6.X(200.0d);
		m_black_pawn6.Y(240.0d);
		m_black_pawn7.X(240.0d);
		m_black_pawn7.Y(240.0d);
		m_black_pawn8.X(280.0d);
		m_black_pawn8.Y(240.0d);
		m_black_rook1.X(0.0d);
		m_black_rook1.Y(280.0d);
		m_black_knight1.X(40.0d);
		m_black_knight1.Y(280.0d);
		m_black_bishop1.X(80.0d);
		m_black_bishop1.Y(280.0d);
		m_black_king.X(120.0d);
		m_black_king.Y(280.0d);
		m_black_queen.X(160.0d);
		m_black_queen.Y(280.0d);
		m_black_bishop2.X(200.0d);
		m_black_bishop2.Y(280.0d);
		m_black_knight2.X(240.0d);
		m_black_knight2.Y(280.0d);
		m_black_rook2.X(280.0d);	
		m_black_rook2.Y(280.0d);
	}
	void setWhitePlayerBoard()
	{
		m_black_pawn1.X(0.0d);
		m_black_pawn1.Y(40.0d);
		m_black_pawn2.X(40.0d);
		m_black_pawn2.Y(40.0d);
		m_black_pawn3.X(80.0d);
		m_black_pawn3.Y(40.0d);
		m_black_pawn4.X(120.0d);
		m_black_pawn4.Y(40.0d);
		m_black_pawn5.X(160.0d);
		m_black_pawn5.Y(40.0d);
		m_black_pawn6.X(200.0d);
		m_black_pawn6.Y(40.0d);
		m_black_pawn7.X(240.0d);
		m_black_pawn7.Y(40.0d);
		m_black_pawn8.X(280.0d);
		m_black_pawn8.Y(40.0d);
		m_black_rook1.X(0.0d);
		m_black_rook1.Y(0.0d);
		m_black_knight1.X(40.0d);
		m_black_knight1.Y(0.0d);
		m_black_bishop1.X(80.0d);
		m_black_bishop1.Y(0.0d);
		m_black_queen.X(120.0d);
		m_black_queen.Y(0.0d);
		m_black_king.X(160.0d);
		m_black_king.Y(0.0d);
		m_black_bishop2.X(200.0d);
		m_black_bishop2.Y(0.0d);
		m_black_knight2.X(240.0d);
		m_black_knight2.Y(0.0d);
		m_black_rook2.X(280.0d);	
		m_black_rook2.Y(0.0d);
		
		m_white_pawn1.X(0.0d);
		m_white_pawn1.Y(240.0d);
		m_white_pawn2.X(40.0d);
		m_white_pawn2.Y(240.0d);
		m_white_pawn3.X(80.0d);
		m_white_pawn3.Y(240.0d);
		m_white_pawn4.X(120.0d);
		m_white_pawn4.Y(240.0d);
		m_white_pawn5.X(160.0d);
		m_white_pawn5.Y(240.0d);
		m_white_pawn6.X(200.0d);
		m_white_pawn6.Y(240.0d);
		m_white_pawn7.X(240.0d);
		m_white_pawn7.Y(240.0d);
		m_white_pawn8.X(280.0d);
		m_white_pawn8.Y(240.0d);
		m_white_rook1.X(0.0d);
		m_white_rook1.Y(280.0d);
		m_white_knight1.X(40.0d);
		m_white_knight1.Y(280.0d);
		m_white_bishop1.X(80.0d);
		m_white_bishop1.Y(280.0d);
		m_white_queen.X(120.0d);
		m_white_queen.Y(280.0d);
		m_white_king.X(160.0d);
		m_white_king.Y(280.0d);
		m_white_bishop2.X(200.0d);
		m_white_bishop2.Y(280.0d);
		m_white_knight2.X(240.0d);
		m_white_knight2.Y(280.0d);
		m_white_rook2.X(280.0d);	
		m_white_rook2.Y(280.0d);
		
	}
	

	@Override
	public boolean dispatchEvent(Component component, String componentName,
			String eventName, Object[] args) {
		// TODO Auto-generated method stub
		//return super.dispatchEvent(component, componentName, eventName, args);
		if(component.equals(m_chess_board) && eventName.equals("Touched"))
		{
			
		}
		else if (component.equals(m_bluetooth_connection_picker) && eventName.equals("Click")) {
        	m_bluetooth_connection_picker.Open();

        } 
		else if (component.equals(m_bluetooth_connection_picker) && eventName.equals("BeforePicking"))
		{

			m_bluetooth_connection_picker.Elements(YailList.makeList(m_bluetooth_client.AddressesAndNames()));
		}
		else if (component.equals(m_bluetooth_connection_picker) && eventName.equals("AfterPicking"))
		{

			m_connected = m_bluetooth_client.Connect(m_bluetooth_connection_picker.Selection());
			
			if (m_connected == true)
			{
				m_bluetooth_server.StopAccepting();
				m_connection_status_label.Text("Connected As White");
				m_clock.TimerEnabled(true);
				m_bluetooth_connection_picker.Enabled(false);
				setBlackPlayerBoard();
			}
		}
		else if (component.equals(m_bluetooth_server) && eventName.equals("ConnectionAccepted"))
		{
			m_connection_status_label.Text("Connected As Black");
			m_clock.TimerEnabled(true);
			m_connected = true;
			m_playing_black = true;
			setWhitePlayerBoard();
		}
		return true;
	}


}
