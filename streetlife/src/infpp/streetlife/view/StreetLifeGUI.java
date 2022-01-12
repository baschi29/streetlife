/**
 * 
 */
package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * OLD GUI
 * @author Basti, Cornelius
 *
 */

public class StreetLifeGUI extends JFrame implements ActionListener {
	
	private JPanel generalPanel;
	private JPanel controlPanel;
	private JPanel drawingArea;
	private JPanel controllingArea;
	private JPanel addingArea;
	private JPanel deletingArea;
	private JPanel loadingSavingArea;
	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Stop");
	private JButton stepButton = new JButton("Schritt");
	private JButton quitButton = new JButton("Quit");
	//private JPanel controllingArea;
	
	/**
	 * constructor of the general StreetLifeGUI
	 */
	public StreetLifeGUI() {
		
		try { SwingUtilities.invokeAndWait(
				new Runnable() {
			public void run() {
				builtGUI();
			}
		});
			
		} catch (Exception exc) {
			System.out.println("Error: " + exc);
		}
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
	}
	
	private void builtGUI() {
		
		
				this.setTitle("StreetLife - die sehr epische Froschsimulation 2022");
	
		
				//structuring via JPanels
		
				generalPanel = new JPanel(new BorderLayout());
				this.setContentPane(generalPanel);
				
				controlPanel = new JPanel(new GridLayout(1, 4, 10, 5));
				generalPanel.add(controlPanel, BorderLayout.PAGE_START);
				
				loadingSavingArea = new JPanel(new GridLayout(2, 1));
				controlPanel.add(loadingSavingArea);
				//Elements in the loadingSavingArea
				JButton loadButton = new JButton("Laden");
				JButton saveButton = new JButton("Speichern");
				loadingSavingArea.add(loadButton);
				loadingSavingArea.add(saveButton);
				
				controllingArea = new JPanel(new GridLayout(2, 3));
				controlPanel.add(controllingArea);
				//Elements in the controlling Area
				
				controllingArea.add(startButton);
				controllingArea.add(stopButton);
				controllingArea.add(stepButton);
				controllingArea.add(quitButton);
				quitButton.addActionListener(this);
				
				addingArea = new JPanel(new GridLayout(2, 3));
				controlPanel.add(addingArea);
				//Elements in the addingArea
				String addableObjects[] = {"Dies tut nichts", "Dies auch nicht"};
				JComboBox<String> addingComboBox = new JComboBox<>(addableObjects); //Dicker Platzhalter
				JLabel xPosLabel = new JLabel("X-Koordinate");
				JTextField xPos = new JTextField();
				JLabel yPosLabel = new JLabel("Y-Koordinate");
				JTextField yPos = new JTextField();
				JButton addingButton = new JButton("Einfügen");
				addingArea.add(xPosLabel);
				addingArea.add(xPos);
				addingArea.add(addingComboBox);
				addingArea.add(yPosLabel);
				addingArea.add(yPos);
				addingArea.add(addingButton);		
				
				deletingArea = new JPanel(new GridLayout(2, 3));
				controlPanel.add(deletingArea);
				//Elements in the deletingArea
				JComboBox<String> deletingComboBox = new JComboBox<>(addableObjects);
				JButton deletingButton = new JButton("Entfernen");
				deletingArea.add(deletingComboBox);
				deletingArea.add(deletingButton);
				
				drawingArea = new JPanel();
				generalPanel.add(drawingArea, BorderLayout.CENTER);
				//Elements in the drawing Area
				
				
				
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.quitButton){
			PopupGUI popup = new PopupGUI();
			popup.setVisible(true);
		}
		
	}
	

}
