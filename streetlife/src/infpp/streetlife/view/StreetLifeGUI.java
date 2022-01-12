/**
 * 
 */
package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import infpp.streetlife.model.StreetObject;

public class StreetLifeGUI extends JFrame {
	
	private StreetLifeGUI gui;
	private JPanel generalPanel;
	private JPanel controlPanel;
	private JPanel drawingArea;
	private JPanel controllingArea;
	private JPanel addingArea;
	private JPanel deletingArea;
	private JPanel loadingSavingArea;
	//private JPanel controllingArea;
	
	/**
	 * constructor of the general StreetLifeGUI
	 */
	public StreetLifeGUI() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
	}
	
	private void builtGUI() {
		//structuring via JPanels
		
				generalPanel = new JPanel(new BorderLayout());
				gui.setContentPane(generalPanel);
				
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
				JButton startButton = new JButton("Start");
				JButton stopButton = new JButton("Stop");
				JButton stepButton = new JButton("Schritt");
				JButton quitButton = new JButton("Quit");
				controllingArea.add(startButton);
				controllingArea.add(stopButton);
				controllingArea.add(stepButton);
				controllingArea.add(quitButton);
				
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
	

}
