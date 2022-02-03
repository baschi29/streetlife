package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import infpp.streetlife.FileLoader;
import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.Model;
import infpp.streetlife.model.StreetObject;
/**
 *  The StreetGUI represents the graphical interface of the street with the display area, control buttons, menu bar etc. 
 *  Essentially the swing/AWT-part of the overall view.
 * @author Cornelius, Bastian
 * @version 1.18
 * @since 2022-01-28
 *
 */
public class StreetGUI extends JFrame implements ActionListener{

	//used resources with paths
	private final String FROG_PATH = "img/frog.png";
	private final String STREET_PATH = "img/asphalt.png";
	private final String PLAY_PATH = "img/play.png";
	private final String PAUSE_PATH = "img/pause.png";
	private final String STEP_PATH = "img/next.png";
	private final String QUIT_PATH = "img/close.png";
	private final String SAVE_PATH = "img/save.png";
	private final String LOAD_PATH = "img/load.png";
	
	//global buttons/menuItems used for actionlistener
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnStart;
	private JButton btnStop;
	private JButton btnStep;
	private JButton btnFrog;
	
	private JMenuItem mntmFileMenuItemLoad;
	private JMenuItem mntmFileMenuItemSave;
	private JMenuItem mntmFileMenuQuit;
	private JMenuItem mntmHelpMenuAbout;
	
	private JPanel contentPane;
	private Model model;
	private Controller controller;

	private JComboBox<String> comboBoxInsert;
	private JComboBox<StreetObject> comboBoxDelete;
	
	private JLayeredPane layeredPane;
	
	private DrawingSpace tp;
	private JSpinner FrogSpinner;
	private JLabel lblSizeLabel;
	private JLabel lblSizeDisplay;
	private JSeparator separator_1;
	
	private FileLoader fl = new FileLoader();
	
	private int NumberOfLanes;
	
	
	/**
	 * Launch the application. Used for debug
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StreetGUI frame = new StreetGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public StreetGUI(Model model) throws IOException {
		this.model = model;
		
		setPreferredSize(new Dimension(1200,800));
		setTitle("Froschsimulator 2022");
		setBounds(100, 100, 1200, 600);
		
		Image img = fl.loadImageIcon(FROG_PATH).getImage();
		this.setIconImage(img);

		//dont just quit when closing, but ask the user if he/she is sure
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					closeProgram();
				 }
			});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		this.mntmFileMenuItemLoad = new JMenuItem("Load");
		ImageIcon imgLoad = fl.loadImageIcon(LOAD_PATH);
		mntmFileMenuItemLoad.setIcon(imgLoad);
		mntmFileMenuItemLoad.addActionListener(this);
		mnFileMenu.add(mntmFileMenuItemLoad);
		
		this.mntmFileMenuItemSave = new JMenuItem("Save");
		ImageIcon imgSave = fl.loadImageIcon(SAVE_PATH);
		mntmFileMenuItemSave.setIcon(imgSave);
		mntmFileMenuItemSave.addActionListener(this);
		mnFileMenu.add(mntmFileMenuItemSave);
		
		JSeparator separator = new JSeparator();
		mnFileMenu.add(separator);
		
		this.mntmFileMenuQuit = new JMenuItem("Quit");
		ImageIcon imgQuit = fl.loadImageIcon(QUIT_PATH);
		mntmFileMenuQuit.setIcon(imgQuit);
		mntmFileMenuQuit.addActionListener(this);
		mnFileMenu.add(mntmFileMenuQuit);
		
		JMenu mnViewMenu = new JMenu("View");
		menuBar.add(mnViewMenu);
		
		JMenu mnHelpMenu = new JMenu("Help");
		menuBar.add(mnHelpMenu);
		
		mntmHelpMenuAbout = new JMenuItem("About");
		mnHelpMenu.add(mntmHelpMenuAbout);
		mntmHelpMenuAbout.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0, 0, 67, 87, 154, 171, 233, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		ImageIcon imgStart = fl.loadImageIcon(PLAY_PATH);
		ImageIcon imgPause = fl.loadImageIcon(PAUSE_PATH);
		
		this.btnStart = new JButton("Start");
		btnStart.setIcon(imgStart);
		btnStart.addActionListener(this);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 0;
		buttonPanel.add(btnStart, gbc_btnStart);
		
		this.btnStop = new JButton("Stop");
		btnStop.setIcon(imgPause);
		btnStop.addActionListener(this);
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 5, 5);
		gbc_btnStop.gridx = 1;
		gbc_btnStop.gridy = 0;
		buttonPanel.add(btnStop, gbc_btnStop);
		
		this.btnStep = new JButton("Step");
		ImageIcon imgStep = fl.loadImageIcon(STEP_PATH);
		btnStep.setIcon(imgStep);
		btnStep.addActionListener(this);

		GridBagConstraints gbc_btnStepButton = new GridBagConstraints();
		gbc_btnStepButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnStepButton.gridx = 2;
		gbc_btnStepButton.gridy = 0;
		buttonPanel.add(btnStep, gbc_btnStepButton);
		
		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridheight = 2;
		gbc_separator_1.insets = new Insets(0, 0, 0, 5);
		gbc_separator_1.gridx = 3;
		gbc_separator_1.gridy = 0;
		buttonPanel.add(separator_1, gbc_separator_1);
		
		lblSizeLabel = new JLabel("Current Street Size:");
		GridBagConstraints gbc_lblSizeLabel = new GridBagConstraints();
		gbc_lblSizeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblSizeLabel.gridx = 4;
		gbc_lblSizeLabel.gridy = 0;
		buttonPanel.add(lblSizeLabel, gbc_lblSizeLabel);
		
		lblSizeDisplay = new JLabel("0 x 0 ");
		GridBagConstraints gbc_lblSizeDisplay = new GridBagConstraints();
		gbc_lblSizeDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblSizeDisplay.gridx = 5;
		gbc_lblSizeDisplay.gridy = 0;
		buttonPanel.add(lblSizeDisplay, gbc_lblSizeDisplay);
		
		JLabel lblInsert = new JLabel("Insert Car");
		GridBagConstraints gbc_lblInsert = new GridBagConstraints();
		gbc_lblInsert.insets = new Insets(0, 0, 5, 5);
		gbc_lblInsert.anchor = GridBagConstraints.EAST;
		gbc_lblInsert.gridx = 7;
		gbc_lblInsert.gridy = 0;
		buttonPanel.add(lblInsert, gbc_lblInsert);
		
		
		comboBoxInsert = new JComboBox<String>();
		
			
		GridBagConstraints gbc_comboBoxInsert = new GridBagConstraints();
		gbc_comboBoxInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInsert.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInsert.gridx = 8;
		gbc_comboBoxInsert.gridy = 0;
		buttonPanel.add(comboBoxInsert, gbc_comboBoxInsert);
		
		
		
		btnInsert = new JButton("Insert");
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		btnInsert.addActionListener(this);
		gbc_btnInsert.insets = new Insets(0, 0, 5, 0);
		gbc_btnInsert.gridx = 9;
		gbc_btnInsert.gridy = 0;
		buttonPanel.add(btnInsert, gbc_btnInsert);
		
		btnFrog = new JButton("Release the frog!");
		btnFrog.addActionListener(this);
		GridBagConstraints gbc_btnFrog = new GridBagConstraints();
		gbc_btnFrog.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFrog.gridwidth = 2;
		gbc_btnFrog.insets = new Insets(0, 0, 0, 5);
		gbc_btnFrog.gridx = 0;
		gbc_btnFrog.gridy = 1;
		buttonPanel.add(btnFrog, gbc_btnFrog);
		
		FrogSpinner = new JSpinner();
		FrogSpinner.setModel(new SpinnerNumberModel(0, 0, 15, 1));
		GridBagConstraints gbc_FrogSpinner = new GridBagConstraints();
		gbc_FrogSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_FrogSpinner.gridx = 2;
		gbc_FrogSpinner.gridy = 1;
		buttonPanel.add(FrogSpinner, gbc_FrogSpinner);
		
		JLabel lblDelete = new JLabel("Delete Car");
		GridBagConstraints gbc_lblDelete = new GridBagConstraints();
		gbc_lblDelete.insets = new Insets(0, 0, 0, 5);
		gbc_lblDelete.anchor = GridBagConstraints.EAST;
		gbc_lblDelete.gridx = 7;
		gbc_lblDelete.gridy = 1;
		buttonPanel.add(lblDelete, gbc_lblDelete);
		
		comboBoxDelete = new JComboBox<StreetObject>();
		comboBoxDelete.setRenderer(new ComboBoxRenderer());
		
		GridBagConstraints gbc_comboBoxDelete = new GridBagConstraints();
		gbc_comboBoxDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDelete.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxDelete.gridx = 8;
		gbc_comboBoxDelete.gridy = 1;
		buttonPanel.add(comboBoxDelete, gbc_comboBoxDelete);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridx = 9;
		gbc_btnDelete.gridy = 1;
		buttonPanel.add(btnDelete, gbc_btnDelete);
		
		//create a new DrawingSpace as a display area
		tp =  new DrawingSpace(model.getModelState());
		
		//painting the background, making sure that the image gets properly tiled
		BufferedImage asphalt = fl.loadImage(STREET_PATH);
		
			
		// creating a TexturePaint object out of the Image and telling the DrawingSpace to used it as the background
		tp.setTexture(new TexturePaint(asphalt, new Rectangle(0,0,asphalt.getWidth(),asphalt.getHeight())));
		
		
		contentPane.add(tp);		
		pack();
		
		
	
	}

	/**
	 * sets the model for the display
	 * @param model Model that should be displayed
	 */
	public void setModel(Model model) {
		this.model= model;
		
	}
	
	/**
	 * sets the size that should be displayed on the gui
	 *
	 * @param x the width of the street
	 * @param y the height of the street
	 * @param ln the number of lanes used in current model
	 */
	public void setSizeDisplay(int x, int y, int ln) {
		this.NumberOfLanes = ln;
		String currentSize = Integer.toString(x) + " x " + Integer.toString(y) + "\n LaneNumber: " + Integer.toString(ln);
		this.lblSizeDisplay.setText(currentSize);
		refresh();
		
	}
	
	/**
	 * sets the controller that manages this view
	 * @param cntrl Controller, that controls this view
	 */
	public void setController(Controller cntrl) {
		this.controller = cntrl;
		
	}
	
	/**
	 * sets a StringArray for displaying the possible types of cars that should be displayed inside the comboBox, essentially "setting up the buffet"
	 * @param ArrStr string array with every possible car type
	 */
	public void setPossibleCars(ArrayList<String> ArrStr) {
		for (String str : ArrStr) {
			comboBoxInsert.addItem(str);
		}
	}
	
	/**
	 * adds a car to the list of active cars
	 * @param car 
	 */
	public void addCurrentCar(StreetObject car) {
		comboBoxDelete.addItem(car);
		System.out.println("Car was added");
		this.repaint();
	}
	
	/**
	 * removes a car from the list of active cars
	 * @param car
	 */
	public void removeCurrentCar(StreetObject car) {
		comboBoxDelete.removeItem(car);
	}

	/**
	 * implements the actionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if quit is pressed, open a new CloseDialog to make sure
		if (e.getSource() == mntmFileMenuQuit) {
			this.closeProgram();
		}
		
		else if (e.getSource() == mntmFileMenuItemLoad) {
			JFileChooser fc = new JFileChooser("./");
		    File file;
		    int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            	{
                	file = fc.getSelectedFile();
                	//System.out.println(file.getAbsolutePath());
                	try {
						this.controller.loadFromFile(file.getAbsolutePath());
					} catch (Exception exc) {
						ErrorDialog dia = new ErrorDialog(exc);
						dia.setVisible(true);
					}
            	}
            }
		
		else if (e.getSource() == mntmFileMenuItemSave) {
			JFileChooser fc = new JFileChooser("./");
		    File file;
		    int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            	{
                	file = fc.getSelectedFile();
                	//System.out.println(file.getAbsolutePath());
                	try {
						this.controller.saveToFile(file.getAbsolutePath());
					} catch (Exception exc) {
						ErrorDialog dia = new ErrorDialog(exc);
						dia.setVisible(true);
					}
            	}
            }
		
		
		
		//if start is pressed, start the model thread
		else if (e.getSource() == btnStart) {
			System.out.println("Start pressed"); //Debug
			
			try {
				this.controller.start();
			}
			catch (Exception exc) {
				//launch an errorDialog for notifying the user
				ErrorDialog dia = new ErrorDialog(exc);
				dia.setVisible(true);
			}
		}
		
		//if step is pressed, move the whole model for one step only
		else if (e.getSource() == btnStep) {
			
			System.out.println("Step pressed"); //Debug
			
			try {
				this.controller.step();
			}
			catch (Exception exc) {
				//launch an errorDialog for notifying the user
				ErrorDialog dia = new ErrorDialog(exc);
				dia.setVisible(true);
			}
		}
		
		//if stop is pressed, stop the model thread
		else if (e.getSource() == btnStop) {
			System.out.println("Stop pressed");
			try {
				this.controller.stop();
			}
			catch (Exception exc) {
				//launch an errorDialog for notifying the user
				ErrorDialog dia = new ErrorDialog(exc);
				dia.setVisible(true);
			}
		}
		
		//if insert is pressed, add the selected car
		else if (e.getSource() == btnInsert) {
			System.out.println("Insert pressed");	//Debug
			try {
				this.controller.addMovingObject(comboBoxInsert.getSelectedItem().toString());
			} 
			catch (Exception exc) {
				//launch an errorDialog for notifying the user
				ErrorDialog dia = new ErrorDialog(exc);
				dia.setVisible(true);
			}
				
		}
		
		//if delete is pressed, delete the selected car
		else if (e.getSource() == btnDelete) {
			System.out.println("Delete pressed"); //Debug
			try {
				this.controller.deleteObject(comboBoxDelete.getItemAt(comboBoxDelete.getSelectedIndex()));
				
			} 
			catch (Exception exc) {
				//launch an errorDialog for notifying the user
				ErrorDialog dia = new ErrorDialog(exc);
				dia.setVisible(true);
			}
		}
		
		//if the frog-button is pressed, unleash the beasts
		else if (e.getSource() == btnFrog) {
			try {
				int frogAmount = (int) this.FrogSpinner.getValue();
				this.controller.releaseTheFrogs(frogAmount); 
			}
			catch (Exception exc) {
				//launch an errorDialog for notifying the user
				ErrorDialog dia = new ErrorDialog(exc);
				dia.setVisible(true);
			}
		}
		
		//if help is pressed, launch a new help window
		else if (e.getSource() == mntmHelpMenuAbout) {
			try {
				HelpDialog dia = new HelpDialog();
				dia.setVisible(true);
			}
			catch (Exception exc) {
				//launch an errorDialog for notifying the user
				ErrorDialog dia = new ErrorDialog(exc);
				dia.setVisible(true);
			}
		}
		
		//regardless of the action, refresh the gui to keep everything up-to-date
		this.tp.refresh();
	}


	/**
	 * refreshes the gui to get everything up-to-date
	 */
	public void refresh() {
		this.repaint();
		
	}
	
	/**
	 * sets the number of lanes that should be displayed
	 * @param laneNumber
	 */
	public void setLaneNumber(int laneNumber) {
		this.tp.setLaneNumber(laneNumber);
		
	}
	
	/**
	 * opens a new confirmation dialog for closing the program
	 */
	private void closeProgram() {
		CloseDialog dia = new CloseDialog();
		dia.setVisible(true);
		
	}

 }
	

