package developen.client.subject.widget;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class DBRGField extends JComponent {

	private static final long serialVersionUID = 121978123043061571L;

	public DBRGField(){
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(new JTextField(15));
		
		add(new JComboBox<String>(new String[]{"SSP", "DETRAN"}));
		
		add(new JComboBox<String>(new String[]{"SC", "SP"}));
		
	}

}