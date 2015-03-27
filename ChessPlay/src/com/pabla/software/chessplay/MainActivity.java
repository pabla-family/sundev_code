package com.pabla.software.chessplay;

import com.google.devtools.simple.runtime.components.Component;
import com.google.devtools.simple.runtime.components.HandlesEventDispatching;
import com.google.devtools.simple.runtime.events.EventDispatcher;
import com.google.devtools.simple.runtime.components.android.*;
import com.google.devtools.simple.runtime.components.util.YailList;

public class MainActivity extends Form  implements HandlesEventDispatching
{	
	private VerticalArrangement m_vertical_arrangement1;
	private HorizontalArrangement m_horizontal_arrangement1;
	private HorizontalArrangement m_horizontal_arrangement2;
	private ListPicker m_bluetooth_connection_picker;
	private Label m_connection_status_label;
	private Label m_white_moves_label;
	private Label m_black_moves_label;
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
	private TinyDB m_database;
	
	private boolean m_connected;
	private boolean m_playing_black;
	private boolean m_my_move;
	private boolean m_first_touch;
	
	private String m_move_from;
	private String m_move_to;
	
	private int m_screen_width;
	private ChessGuru m_chess_guru;
	
	private static int M_CHESS_BOARD_SQUARES_PER_ROW = 8;
		
	void $define()
	{

		// Initally the connection status is false and we assume playing white
		m_connected = false;
		m_playing_black = false;
		m_my_move = true;
		
		// Initialise the components
		m_vertical_arrangement1 = new VerticalArrangement(this);
		m_horizontal_arrangement1 = new HorizontalArrangement(m_vertical_arrangement1);
		m_bluetooth_connection_picker = new ListPicker(m_horizontal_arrangement1);
		m_bluetooth_client = new BluetoothClient(this);
		m_bluetooth_server = new BluetoothServer(this);
		m_bluetooth_server.AcceptConnection(" ");
		m_clock = new Clock(this);
		m_clock.TimerEnabled(false);
		m_clock.TimerInterval(1000);
		
		m_database = new TinyDB(this);
		m_chess_guru = new ChessGuru(m_database);
		
		m_connection_status_label = new Label(m_horizontal_arrangement1);
		m_connection_status_label.Text("Not Connected ");
		m_chess_board = new Canvas(m_vertical_arrangement1);
		
		m_horizontal_arrangement2 = new HorizontalArrangement(m_vertical_arrangement1);
		m_white_moves_label = new Label(m_horizontal_arrangement2);
		m_white_moves_label.Text("White Moves\n");
		m_black_moves_label = new Label(m_horizontal_arrangement2);
		m_black_moves_label.Text("     Black Moves\n");
		
		m_chess_board.BackgroundImage("chess_board.png");
		
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
		
		// Need to set rotates to false
		// beacuse a bug in the api being used means that otherwise you will not be able
		// to resize the sprite
		m_black_pawn1.Rotates(false);
		m_black_pawn2.Rotates(false);
		m_black_pawn3.Rotates(false);
		m_black_pawn4.Rotates(false);
		m_black_pawn5.Rotates(false);
		m_black_pawn6.Rotates(false);
		m_black_pawn7.Rotates(false);
		m_black_pawn8.Rotates(false);
		m_black_rook1.Rotates(false);
		m_black_knight1.Rotates(false);
		m_black_bishop1.Rotates(false);
		m_black_queen.Rotates(false);
		m_black_king.Rotates(false);
		m_black_bishop2.Rotates(false);
		m_black_knight2.Rotates(false);
		m_black_rook2.Rotates(false);
		m_white_pawn1.Rotates(false);
		m_white_pawn2.Rotates(false);
		m_white_pawn3.Rotates(false);
		m_white_pawn4.Rotates(false);
		m_white_pawn5.Rotates(false);
		m_white_pawn6.Rotates(false);
		m_white_pawn7.Rotates(false);
		m_white_pawn8.Rotates(false);
		m_white_rook1.Rotates(false);
		m_white_knight1.Rotates(false);
		m_white_bishop1.Rotates(false);
		m_white_queen.Rotates(false);
		m_white_king.Rotates(false);
		m_white_bishop2.Rotates(false);
		m_white_knight2.Rotates(false);
		m_white_rook2.Rotates(false);

		
		EventDispatcher.registerEventForDelegation(this, "appName", "Initialize");
		EventDispatcher.registerEventForDelegation(this, "ChessBoardTouch", "Touched");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerClick", "Click");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerBeforePicking", "BeforePicking");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerAfterPicking", "AfterPicking");
		EventDispatcher.registerEventForDelegation(this, "BluetoothServerConnectionAccepted", "ConnectionAccepted");
		EventDispatcher.registerEventForDelegation(this, "TimerTriggered", "Timer");
				
	}
	
	void setBlackPlayerBoard(int screenWidth)
	{	
		double square_size = screenWidth/M_CHESS_BOARD_SQUARES_PER_ROW;
		m_white_rook1.X(square_size*0); //A8
		m_white_rook1.Y(square_size*0); //A8
		m_white_knight1.X(square_size*1);
		m_white_knight1.Y(square_size*0);
		m_white_bishop1.X(square_size*2);
		m_white_bishop1.Y(square_size*0);
		m_white_king.X(square_size*3);
		m_white_king.Y(square_size*0);
		m_white_queen.X(square_size*4);
		m_white_queen.Y(square_size*0);
		m_white_bishop2.X(square_size*5);
		m_white_bishop2.Y(square_size*0);
		m_white_knight2.X(square_size*6);
		m_white_knight2.Y(square_size*0);
		m_white_rook2.X(square_size*7);	
		m_white_rook2.Y(square_size*0);
		
		m_white_pawn1.X(square_size*0);
		m_white_pawn1.Y(square_size*1);
		m_white_pawn2.X(square_size*1);
		m_white_pawn2.Y(square_size*1);
		m_white_pawn3.X(square_size*2);
		m_white_pawn3.Y(square_size*1);
		m_white_pawn4.X(square_size*3);
		m_white_pawn4.Y(square_size*1);
		m_white_pawn5.X(square_size*4);
		m_white_pawn5.Y(square_size*1);
		m_white_pawn6.X(square_size*5);
		m_white_pawn6.Y(square_size*1);
		m_white_pawn7.X(square_size*6);
		m_white_pawn7.Y(square_size*1);
		m_white_pawn8.X(square_size*7);
		m_white_pawn8.Y(square_size*1);
		
		m_black_pawn1.X(square_size*0); 
		m_black_pawn1.Y(square_size*6); 
		m_black_pawn2.X(square_size*1);
		m_black_pawn2.Y(square_size*6);
		m_black_pawn3.X(square_size*2);
		m_black_pawn3.Y(square_size*6);
		m_black_pawn4.X(square_size*3);
		m_black_pawn4.Y(square_size*6);
		m_black_pawn5.X(square_size*4);
		m_black_pawn5.Y(square_size*6);
		m_black_pawn6.X(square_size*5);
		m_black_pawn6.Y(square_size*6);
		m_black_pawn7.X(square_size*6);
		m_black_pawn7.Y(square_size*6);
		m_black_pawn8.X(square_size*7);
		m_black_pawn8.Y(square_size*6);
		
		m_black_rook1.X(square_size*0); //A8
		m_black_rook1.Y(square_size*7); //A8
		m_black_knight1.X(square_size*1);
		m_black_knight1.Y(square_size*7);
		m_black_bishop1.X(square_size*2);
		m_black_bishop1.Y(square_size*7);
		m_black_king.X(square_size*3);
		m_black_king.Y(square_size*7);
		m_black_queen.X(square_size*4);
		m_black_queen.Y(square_size*7);
		m_black_bishop2.X(square_size*5);
		m_black_bishop2.Y(square_size*7);
		m_black_knight2.X(square_size*6);
		m_black_knight2.Y(square_size*7);
		m_black_rook2.X(square_size*7);	
		m_black_rook2.Y(square_size*7);
		

	}
	void setWhitePlayerBoard(int screenWidth)
	{
		double square_size = screenWidth/M_CHESS_BOARD_SQUARES_PER_ROW;
		
		m_black_rook1.X(square_size*0); //A8
		m_black_rook1.Y(square_size*0); //A8
		m_black_knight1.X(square_size*1);
		m_black_knight1.Y(square_size*0);
		m_black_bishop1.X(square_size*2);
		m_black_bishop1.Y(square_size*0);
		m_black_queen.X(square_size*3);
		m_black_queen.Y(square_size*0);
		m_black_king.X(square_size*4);
		m_black_king.Y(square_size*0);
		m_black_bishop2.X(square_size*5);
		m_black_bishop2.Y(square_size*0);
		m_black_knight2.X(square_size*6);
		m_black_knight2.Y(square_size*0);
		m_black_rook2.X(square_size*7);	
		m_black_rook2.Y(square_size*0);
		
		m_black_pawn1.X(square_size*0);
		m_black_pawn1.Y(square_size*1);
		m_black_pawn2.X(square_size*1);
		m_black_pawn2.Y(square_size*1);
		m_black_pawn3.X(square_size*2);
		m_black_pawn3.Y(square_size*1);
		m_black_pawn4.X(square_size*3);
		m_black_pawn4.Y(square_size*1);
		m_black_pawn5.X(square_size*4);
		m_black_pawn5.Y(square_size*1);
		m_black_pawn6.X(square_size*5);
		m_black_pawn6.Y(square_size*1);
		m_black_pawn7.X(square_size*6);
		m_black_pawn7.Y(square_size*1);
		m_black_pawn8.X(square_size*7);
		m_black_pawn8.Y(square_size*1);
		

		
		m_white_pawn1.X(square_size*0);
		m_white_pawn1.Y(square_size*6);
		m_white_pawn2.X(square_size*1);
		m_white_pawn2.Y(square_size*6);
		m_white_pawn3.X(square_size*2);
		m_white_pawn3.Y(square_size*6);
		m_white_pawn4.X(square_size*3);
		m_white_pawn4.Y(square_size*6);
		m_white_pawn5.X(square_size*4);
		m_white_pawn5.Y(square_size*6);
		m_white_pawn6.X(square_size*5);
		m_white_pawn6.Y(square_size*6);
		m_white_pawn7.X(square_size*6);
		m_white_pawn7.Y(square_size*6);
		m_white_pawn8.X(square_size*7);
		m_white_pawn8.Y(square_size*6);
		
		m_white_rook1.X(square_size*0);
		m_white_rook1.Y(square_size*7);
		m_white_knight1.X(square_size*1);
		m_white_knight1.Y(square_size*7);
		m_white_bishop1.X(square_size*2);
		m_white_bishop1.Y(square_size*7);
		m_white_queen.X(square_size*3);
		m_white_queen.Y(square_size*7);
		m_white_king.X(square_size*4);
		m_white_king.Y(square_size*7);
		m_white_bishop2.X(square_size*5);
		m_white_bishop2.Y(square_size*7);
		m_white_knight2.X(square_size*6);
		m_white_knight2.Y(square_size*7);
		m_white_rook2.X(square_size*7);	
		m_white_rook2.Y(square_size*7);
		
	}
	
	@Override
	public boolean dispatchEvent(Component component, String componentName,
			String eventName, Object[] args) 
	{
		if (component.equals(this) && eventName.equals("Initialize"))
        {
			m_screen_width = this.Width();
			m_chess_board.Width(m_screen_width);
			m_chess_board.Height(m_screen_width);
			setWhitePlayerBoard(m_screen_width);
			
			m_black_pawn1.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn2.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn3.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn4.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn5.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn6.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn7.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn8.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_rook1.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_knight1.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_bishop1.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_queen.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_king.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_bishop2.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_knight2.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_rook2.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn1.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn2.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn3.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn4.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn5.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn6.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn7.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn8.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_rook1.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_knight1.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_bishop1.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_queen.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_king.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_bishop2.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_knight2.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_rook2.Width(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			
			m_black_pawn1.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn2.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn3.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn4.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn5.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn6.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn7.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_pawn8.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_rook1.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_knight1.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_bishop1.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_queen.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_king.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_bishop2.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_knight2.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_black_rook2.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn1.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn2.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn3.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn4.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn5.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn6.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn7.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_pawn8.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_rook1.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_knight1.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_bishop1.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_queen.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_king.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_bishop2.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_knight2.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			m_white_rook2.Height(m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW);
			
			m_first_touch = true;
        }
		else if (component.equals(m_bluetooth_connection_picker) && eventName.equals("Click")) 
		{
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
				setWhitePlayerBoard(m_screen_width);
			}
		}
		else if (component.equals(m_bluetooth_server) && eventName.equals("ConnectionAccepted"))
		{
			m_connection_status_label.Text("Connected As Black");
			m_clock.TimerEnabled(true);
			m_connected = true;
			m_playing_black = true;
			m_my_move = false;
			setBlackPlayerBoard(m_screen_width);
		}
		else if(component.equals(m_chess_board) && eventName.equals("Touched")  && m_connected == true)
		{
			if (m_my_move == true)
			{
				if (m_first_touch == true)
				{
					m_move_from = convertXYtoChessCoordinate((int)Double.parseDouble(args[0].toString()),
																 (int)Double.parseDouble(args[1].toString()),
															 m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW,
															 m_playing_black);
					m_first_touch =false;
				}
				else
				{
				
					m_move_to = convertXYtoChessCoordinate((int)Double.parseDouble(args[0].toString()),
							(int)Double.parseDouble(args[1].toString()),
							m_screen_width/M_CHESS_BOARD_SQUARES_PER_ROW,
							m_playing_black);

					if ((m_playing_black == true))
					{
						m_bluetooth_server.SendText( "Move From " + m_move_from + " to " + m_move_to);
					}
					else
					{

						m_bluetooth_client.SendText( "Move From " + m_move_from + " to " + m_move_to);
					}
					m_my_move = false;
					m_first_touch =true;
			
	
				}
			}

		}
		else if(component.equals(m_clock) && eventName.equals("Timer"))
		{	
			if ((m_playing_black == true) && (m_my_move == false))
			{
				if (m_bluetooth_server.BytesAvailableToReceive() >0)
				{
					m_white_moves_label.Text(m_white_moves_label.Text() 
							+ m_bluetooth_server.ReceiveText(m_bluetooth_server.BytesAvailableToReceive())+"\n");
					m_my_move = true;
				}
				
			}
			else if ((m_playing_black == false) && (m_my_move == false))
			{
				if (m_bluetooth_client.BytesAvailableToReceive() >0)
				{
					m_black_moves_label.Text(m_black_moves_label.Text() 
							+ m_bluetooth_client.ReceiveText(m_bluetooth_client.BytesAvailableToReceive())+"\n");
					m_my_move = true;
				}				
			}
		}
		return true;
	}
	
	String convertXYtoChessCoordinate(int x, int y, int squareSize, boolean isPlayingBlack)
	{
		String theChessCoordinate;

		// remember top left is 0,0
		// So when playing black 0,0 is H1 and when playing white 0,0 is A8
		String blackColumnCordinates[]={"H","G","F","E","D","C","B","A"};
		String blackRowCordinates[]={"1","2","3","4","5","6","7","8"};
		String whiteColumnCordinates[]={"A","B","C","D","E","F","G","H"};
		String whiteRowCordinates[]={"8","7","6","5","4","3","2","1"};
		
		if (isPlayingBlack == true)
		{
			theChessCoordinate = blackColumnCordinates[x/squareSize]+blackRowCordinates[y/squareSize];
		}
		else
		{
			theChessCoordinate = whiteColumnCordinates[x/squareSize]+whiteRowCordinates[y/squareSize];
		}
		return theChessCoordinate;
		
	}
	
	// Note that x and y are passed as objects because we want to effectively pass by 
	// reference
	void convertChessCoordinateToXY(String chessCoord, Double x, Double y, int squareSize, boolean isPlayingBlack)
	{
		Double blackColCordinates[]={7.0,6.0,5.0,4.0,3.0,2.0,1.0,0.0};
		Double blackRowCordinates[]={0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0};
		Double whiteColCordinates[]={0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0};
		Double whiteRowCordinates[]={7.0,6.0,5.0,4.0,3.0,2.0,1.0,0.0};
		
		Double colCoord[];
		Double rowCoord[];
		
		if (isPlayingBlack == true)
		{
			colCoord = blackColCordinates;
			rowCoord = blackRowCordinates;
		}
		else
		{
			colCoord = whiteColCordinates;
			rowCoord = whiteRowCordinates;
		}
		switch (chessCoord.charAt(0))
		{
			case 'A' :
				x = colCoord[0];
				y = rowCoord[0];
				break;
			case 'B' :
				x = colCoord[1];
				y = rowCoord[1];
				break;
			case 'C' :
				x = colCoord[2];
				y = rowCoord[2];
				break;
			case 'D' :
				x = colCoord[3];
				y = rowCoord[3];
				break;
			case 'E' :
				x = colCoord[4];
				y = rowCoord[4];
				break;
			case 'F' :
				x = colCoord[5];
				y = rowCoord[5];
				break;
			case 'G' :
				x = colCoord[6];
				y = rowCoord[6];
				break;
			case 'H' :
				x = colCoord[7];
				y = rowCoord[7];
				break;
		}
		x = x*squareSize;
		y = y*squareSize;
	}



}
