package com.pabla.software.BluetoothConnectionTest;

// Import in app inventor api components
import com.google.devtools.simple.runtime.components.Component;
import com.google.devtools.simple.runtime.components.HandlesEventDispatching;
import com.google.devtools.simple.runtime.events.EventDispatcher;
import com.google.devtools.simple.runtime.components.android.*;
import com.google.devtools.simple.runtime.components.util.YailList;

// When using app inventor api, the MainActivity class extends Form and implements
// HandleEventDispatching

// The MainActivity class is responsible for creating and initialising the user interface
// and for handling events raised by the android operating system
public class MainActivity extends Form  implements HandlesEventDispatching
{	
	private VerticalArrangement m_vertical_arrangement1;
	private HorizontalArrangement m_horizontal_arrangement1;
	private ListPicker m_bluetooth_connection_picker;
	private Label m_connection_status_label;
	private Label m_count_label;

	private BluetoothClient m_bluetooth_client;
	private BluetoothServer m_bluetooth_server;
	private String m_server_phone;
	private Clock m_clock;
	
	private boolean m_connected;
	private boolean m_is_client;

	
	private int m_counter;

		
	void $define()
	{

		// Initally the connection status is false
		m_connected = false;
		m_is_client = true;
		
		// Initialise the components
		this.ScreenOrientation("portrait");
		m_vertical_arrangement1 = new VerticalArrangement(this);
		m_horizontal_arrangement1 = new HorizontalArrangement(m_vertical_arrangement1);
		m_bluetooth_connection_picker = new ListPicker(m_horizontal_arrangement1);
		m_bluetooth_client = new BluetoothClient(this);
		m_bluetooth_server = new BluetoothServer(this);
		m_bluetooth_server.AcceptConnection(" ");
		m_clock = new Clock(this);
		m_clock.TimerEnabled(false);
		m_clock.TimerInterval(1000);
		
		m_connection_status_label = new Label(m_horizontal_arrangement1);
		m_connection_status_label.Text("Not Connected ");

		m_count_label  = new Label(m_vertical_arrangement1);
		m_count_label.Text("No number received");

		
		EventDispatcher.registerEventForDelegation(this, "appName", "Initialize");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerClick", "Click");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerBeforePicking", "BeforePicking");
		EventDispatcher.registerEventForDelegation(this, "ConnectionPickerAfterPicking", "AfterPicking");
		EventDispatcher.registerEventForDelegation(this, "BluetoothServerConnectionAccepted", "ConnectionAccepted");
		EventDispatcher.registerEventForDelegation(this, "TimerTriggered", "Timer");
		
		m_counter = 0;
				
	}
	
	@Override
	public boolean dispatchEvent(Component component, String componentName,
			String eventName, Object[] args) 
	{
		if (component.equals(this) && eventName.equals("Initialize"))
        {
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
			m_server_phone = m_bluetooth_connection_picker.Selection();
			m_connected = m_bluetooth_client.Connect(m_server_phone);
			
			if (m_connected == true)
			{
				m_bluetooth_server.StopAccepting();
				m_connection_status_label.Text("Connected As Client");
				m_clock.TimerEnabled(true);
				m_bluetooth_connection_picker.Enabled(false);
			}
		}
		else if (component.equals(m_bluetooth_server) && eventName.equals("ConnectionAccepted"))
		{
			m_connection_status_label.Text("Connected As Server");
			m_clock.TimerEnabled(true);
			
			m_is_client = false;

			m_connected = true;
		}
		else if(component.equals(m_clock) && eventName.equals("Timer"))
		{	

			if(m_is_client == false)
			{

				
				if (m_bluetooth_server.BytesAvailableToReceive() >0)
				{
					String receivedText = m_bluetooth_server.ReceiveText(m_bluetooth_server.BytesAvailableToReceive());

					if (m_counter == Integer.parseInt(receivedText))
					{
						m_count_label.Text(receivedText);
						m_counter++;  // increment to number you need to send
						              // server always sends odd
						m_bluetooth_server.SendText(Integer.toString(m_counter));
						m_counter++;  // increment to number you expect to receive
						              // server expects to receive even
					}
				}
				
			}	
			else
			{
				if (m_counter == 0)
				{
					
					m_bluetooth_client.SendText(Integer.toString(m_counter));
					m_counter++;  // increment to 1
				}
				
				if (m_bluetooth_client.BytesAvailableToReceive() >0)
				{
					String receivedText = m_bluetooth_client.ReceiveText(m_bluetooth_client.BytesAvailableToReceive());



					if (m_counter == Integer.parseInt(receivedText))
					{
						m_count_label.Text(receivedText);
						m_counter++; // increment to number you need to send
						             // client sends even
						m_bluetooth_client.SendText(Integer.toString(m_counter));
						m_counter++; // increment to number you expect to receive
						             // client expect to receive odd
					}
				}
			}
		}
		return true;
	}
}
