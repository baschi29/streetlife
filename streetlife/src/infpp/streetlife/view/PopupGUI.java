package infpp.streetlife.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * OLD GUI
 * @author Cornelius
 *
 */
public class PopupGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton cancelButton;
	private JButton quitButton;
	
	public PopupGUI() {
		
		builtGUI();
		
		this.setSize(400,100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
	private void builtGUI() {
		
		this.contentPane = new JPanel(new GridLayout(1,2));
		
		cancelButton = new JButton("Abbrechen");
		quitButton = new JButton("plz kil");
		
		cancelButton.addActionListener(this);
		quitButton.addActionListener(this);
		
		this.contentPane.add(this.cancelButton);
		this.contentPane.add(this.quitButton);
		
		this.setContentPane(this.contentPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.quitButton){
			System.exit(0);
		}
		else if (e.getSource() == this.cancelButton){
			this.dispose();
		}
		
	
	}

}
