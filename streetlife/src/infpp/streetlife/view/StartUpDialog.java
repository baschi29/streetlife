package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.management.InvalidAttributeValueException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import infpp.streetlife.FileLoader;
import infpp.streetlife.StreetLifeMain;
/**
 * The StartUpDialog is the first thing the user interacts with. It is a custom PopUp window , that handles inputs for all the parameters and finally starts the controller. ${date}
 * @author Cornelius, Bastian
 * @version 1.4
 * @since 1.0
 *
 */
public class StartUpDialog  extends JDialog implements ActionListener {

	// JPanel everything is based upon
	private final JPanel contentPanel = new JPanel();
	
	//array for the different simspeed settings
	private final String[] simspeeds = {"Snail","Slow","Fast","Speedy Gonzales"};

	//path for the welcome message
	private final String WELCOME_PATH = "txt/welcome.txt";

	//path to the used image
	private final String FROG_PATH = "img/frog.png";
	private JTextField textFieldLength;
	private JTextField textFieldLanes;
	private JTextField textFieldPath;
	private JComboBox comboBoxSimTime;

	//global buttons for ActionListener
	private JButton btnLaunchButton;
	private JButton btnLoadButton;
	private JButton btnLaunchButton_1;
	
	JToggleButton tglbtnNewToggleButton;
	
	//FileLoader used for loading the icons/text file
	private FileLoader fl = new FileLoader();

	/**
	 * Launch the application. Used for debug
	 */
	public static void main(String[] args) {
		try {
			StartUpDialog dialog = new StartUpDialog();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
		

	/**
	 * Create and build the dialog-window.
	 */
	public StartUpDialog() {
		setType(Type.NORMAL);
		setBounds(100, 100, 620, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				closeProgram();
			 }

		});
		
		//load the frog icon and set it to the imageLabel
		try {
			ImageIcon img = fl.loadImageIcon(FROG_PATH);
			this.setIconImage(img.getImage());
			
			{
				JLabel imgpanel = new JLabel();
				imgpanel.setIcon(new ImageIcon(img.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)));
				contentPanel.add(imgpanel, BorderLayout.WEST);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//creation of every other component, created with WindowBuilder
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{146, 0};
			gbl_panel.rowHeights = new int[]{50, 70, 28, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JTextPane textAreaWelcome = new JTextPane();
				textAreaWelcome.setFont(new Font("Arial", Font.BOLD, 13));
				String labelcontent = "File not found";
				try {
					labelcontent = fl.loadFileAsString(WELCOME_PATH);
				} catch (IOException e) {
					e.printStackTrace();
				}
				textAreaWelcome.setText(labelcontent);
				textAreaWelcome.setEditable(false);
				
				StyledDocument doc = textAreaWelcome.getStyledDocument();
				SimpleAttributeSet center = new SimpleAttributeSet();
				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				doc.setParagraphAttributes(0, doc.getLength(), center, false);
				
				GridBagConstraints gbc_textAreaWelcome = new GridBagConstraints();
				gbc_textAreaWelcome.insets = new Insets(0, 0, 5, 0);
				gbc_textAreaWelcome.fill = GridBagConstraints.BOTH;
				gbc_textAreaWelcome.gridx = 0;
				gbc_textAreaWelcome.gridy = 0;
				panel.add(textAreaWelcome, gbc_textAreaWelcome);
			}
			{
				JPanel panel_newStreet = new JPanel();
				panel_newStreet.setBorder(new TitledBorder(null, "New Street", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel_newStreet = new GridBagConstraints();
				gbc_panel_newStreet.insets = new Insets(0, 0, 5, 0);
				gbc_panel_newStreet.fill = GridBagConstraints.BOTH;
				gbc_panel_newStreet.gridx = 0;
				gbc_panel_newStreet.gridy = 1;
				panel.add(panel_newStreet, gbc_panel_newStreet);
				GridBagLayout gbl_panel_newStreet = new GridBagLayout();
				gbl_panel_newStreet.columnWidths = new int[]{60, 100, 0, 53, 90, 100, 0};
				gbl_panel_newStreet.rowHeights = new int[]{36, 40, 0, 0};
				gbl_panel_newStreet.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panel_newStreet.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
				panel_newStreet.setLayout(gbl_panel_newStreet);
				{
					JLabel lblSizeLabel = new JLabel("Set the parameters for a new street");
					GridBagConstraints gbc_lblSizeLabel = new GridBagConstraints();
					gbc_lblSizeLabel.gridwidth = 6;
					gbc_lblSizeLabel.insets = new Insets(0, 0, 5, 0);
					gbc_lblSizeLabel.fill = GridBagConstraints.VERTICAL;
					gbc_lblSizeLabel.gridx = 0;
					gbc_lblSizeLabel.gridy = 0;
					panel_newStreet.add(lblSizeLabel, gbc_lblSizeLabel);
				}
				{
					JLabel lblLengthLabel = new JLabel("Length");
					GridBagConstraints gbc_lblLengthLabel = new GridBagConstraints();
					gbc_lblLengthLabel.insets = new Insets(0, 0, 5, 5);
					gbc_lblLengthLabel.gridx = 0;
					gbc_lblLengthLabel.gridy = 1;
					panel_newStreet.add(lblLengthLabel, gbc_lblLengthLabel);
				}
				{
					textFieldLength = new JTextField();
					textFieldLength.setText("1200");
					GridBagConstraints gbc_textFieldLength = new GridBagConstraints();
					gbc_textFieldLength.fill = GridBagConstraints.HORIZONTAL;
					gbc_textFieldLength.insets = new Insets(0, 0, 5, 5);
					gbc_textFieldLength.gridx = 1;
					gbc_textFieldLength.gridy = 1;
					panel_newStreet.add(textFieldLength, gbc_textFieldLength);
					textFieldLength.setToolTipText("length of street in pixel");
					textFieldLength.setColumns(10);
				}
				{
					JLabel lblLaneLabel = new JLabel("Num of Lanes");
					GridBagConstraints gbc_lblLaneLabel = new GridBagConstraints();
					gbc_lblLaneLabel.gridwidth = 2;
					gbc_lblLaneLabel.insets = new Insets(0, 0, 5, 5);
					gbc_lblLaneLabel.gridx = 3;
					gbc_lblLaneLabel.gridy = 1;
					panel_newStreet.add(lblLaneLabel, gbc_lblLaneLabel);
				}
				{
					textFieldLanes = new JTextField();
					textFieldLanes.setToolTipText("Number of lanes for street");
					textFieldLanes.setText("5");
					GridBagConstraints gbc_textFieldLanes = new GridBagConstraints();
					gbc_textFieldLanes.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldLanes.fill = GridBagConstraints.HORIZONTAL;
					gbc_textFieldLanes.gridx = 5;
					gbc_textFieldLanes.gridy = 1;
					panel_newStreet.add(textFieldLanes, gbc_textFieldLanes);
					textFieldLanes.setColumns(10);
				}
				{
					tglbtnNewToggleButton = new JToggleButton("Start with preset cars");
					tglbtnNewToggleButton.setToolTipText("should the street spawn some cars?");
					tglbtnNewToggleButton.setSelected(true);
					GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
					gbc_tglbtnNewToggleButton.anchor = GridBagConstraints.EAST;
					gbc_tglbtnNewToggleButton.gridwidth = 2;
					gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 0, 5);
					gbc_tglbtnNewToggleButton.gridx = 0;
					gbc_tglbtnNewToggleButton.gridy = 2;
					panel_newStreet.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
				}
				{
					btnLaunchButton = new JButton("Start!");
					btnLaunchButton.setToolTipText("start with set parameters");
					btnLaunchButton.addActionListener(this);
					{
						JLabel lblSimTimeLabel = new JLabel("SimTime");
						GridBagConstraints gbc_lblSimTimeLabel = new GridBagConstraints();
						gbc_lblSimTimeLabel.insets = new Insets(0, 0, 0, 5);
						gbc_lblSimTimeLabel.anchor = GridBagConstraints.EAST;
						gbc_lblSimTimeLabel.gridx = 3;
						gbc_lblSimTimeLabel.gridy = 2;
						panel_newStreet.add(lblSimTimeLabel, gbc_lblSimTimeLabel);
					}
					{
						comboBoxSimTime = new JComboBox(simspeeds);
						comboBoxSimTime.setToolTipText("Speed of simulation");
						GridBagConstraints gbc_comboBoxSimTime = new GridBagConstraints();
						gbc_comboBoxSimTime.insets = new Insets(0, 0, 0, 5);
						gbc_comboBoxSimTime.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboBoxSimTime.gridx = 4;
						gbc_comboBoxSimTime.gridy = 2;
						panel_newStreet.add(comboBoxSimTime, gbc_comboBoxSimTime);
					}
					GridBagConstraints gbc_btnLaunchButton = new GridBagConstraints();
					gbc_btnLaunchButton.anchor = GridBagConstraints.EAST;
					gbc_btnLaunchButton.gridx = 5;
					gbc_btnLaunchButton.gridy = 2;
					panel_newStreet.add(btnLaunchButton, gbc_btnLaunchButton);
				}
			}
			{
				JPanel panel_loadStreet = new JPanel();
				panel_loadStreet.setBorder(new TitledBorder(null, "Load existing Street", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panel_loadStreet = new GridBagConstraints();
				gbc_panel_loadStreet.fill = GridBagConstraints.BOTH;
				gbc_panel_loadStreet.gridx = 0;
				gbc_panel_loadStreet.gridy = 2;
				panel.add(panel_loadStreet, gbc_panel_loadStreet);
				{
					GridBagLayout gbl_panel_loadStreet = new GridBagLayout();
					gbl_panel_loadStreet.columnWidths = new int[]{112, 86, 77, 0};
					gbl_panel_loadStreet.rowHeights = new int[]{23, 0, 0};
					gbl_panel_loadStreet.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
					gbl_panel_loadStreet.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
					panel_loadStreet.setLayout(gbl_panel_loadStreet);
					
					btnLoadButton = new JButton("Open File");
					btnLoadButton.setToolTipText("open file dialog");
					btnLoadButton.addActionListener(this);
					{
						textFieldPath = new JTextField();
						textFieldPath.setToolTipText("enter the file path");
						GridBagConstraints gbc_textFieldPath = new GridBagConstraints();
						gbc_textFieldPath.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldPath.gridwidth = 2;
						gbc_textFieldPath.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldPath.gridx = 0;
						gbc_textFieldPath.gridy = 0;
						panel_loadStreet.add(textFieldPath, gbc_textFieldPath);
						textFieldPath.setColumns(10);
					}
					GridBagConstraints gbc_btnLoadButton = new GridBagConstraints();
					gbc_btnLoadButton.insets = new Insets(0, 0, 5, 0);
					gbc_btnLoadButton.anchor = GridBagConstraints.NORTHEAST;
					gbc_btnLoadButton.gridx = 2;
					gbc_btnLoadButton.gridy = 0;
					panel_loadStreet.add(btnLoadButton, gbc_btnLoadButton);
				}
				{
					this.btnLaunchButton_1 = new JButton("Start from loaded File!");
					btnLaunchButton_1.setToolTipText("start from file path");
					btnLaunchButton_1.addActionListener(this);
					GridBagConstraints gbc_btnLaunchButton_1 = new GridBagConstraints();
					gbc_btnLaunchButton_1.gridx = 2;
					gbc_btnLaunchButton_1.gridy = 1;
					panel_loadStreet.add(btnLaunchButton_1, gbc_btnLaunchButton_1);
				}
			}
		}
	}
	
	//Implementation of ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if launch is pressed, aquire everything needed and start the simulation
		if (e.getSource() == btnLaunchButton) {
			try {
				int number_of_lanes = Integer.parseInt(this.textFieldLanes.getText());
				int size_of_street = Integer.parseInt(this.textFieldLength.getText());
			
				//reverse the simspeed mp, so that high indexes actually correspond to fast speeds
				int sim_speed = this.simspeeds.length -  this.comboBoxSimTime.getSelectedIndex();
				boolean default_cars = this.tglbtnNewToggleButton.isSelected();
			
				System.out.println(number_of_lanes);
				System.out.println(size_of_street);
				System.out.println(sim_speed);
				System.out.println(default_cars);
			
				StreetLifeMain.startStreetLife(number_of_lanes, size_of_street, sim_speed, default_cars);
			
				this.dispose();
			}
			catch(Exception exc) {
				ErrorDialog dia = new ErrorDialog(new InvalidAttributeValueException("Please enter the required values"));
				dia.setVisible(true);
			}
		}
		
		//if the debug button is pressed, start the simulation with default values
		//in current version not used
//		else if (e.getSource() == btnDebugButton) {
//			
//			StreetLifeMain.startStreetLife();
//			
//			this.dispose();
//		}
		
		//if second launchButton is pressed, load a street from the filepath stated
		else if (e.getSource() == btnLaunchButton_1) {
			try {
				String filepath = textFieldPath.getText();
			
				StreetLifeMain.startStreetLife(filepath);
			
				this.dispose();
			}
			catch(Exception exc) {
				ErrorDialog dia = new ErrorDialog(new InvalidAttributeValueException("Please enter the required values"));
				dia.setVisible(true);
			}
		}
		
		//if loadButton is pressed, launch the file dialog in system explorer to find a file
		else if (e.getSource() == btnLoadButton) {
			
			    JFileChooser fc = new JFileChooser("./");
			    File file;
			    int returnVal = fc.showOpenDialog(this);
	            if (returnVal == JFileChooser.APPROVE_OPTION)
	            	{
	                	file = fc.getSelectedFile();
	                	//System.out.println(file.getAbsolutePath());
	                	textFieldPath.setText(file.getAbsolutePath());
	            	}
	            }
	        
	}
	
	/**
	 * opens a new confirmation dialog for closing the program
	 * @since 1.1
	 */
	
	private void closeProgram() {
		CloseDialog dia = new CloseDialog();
		dia.setVisible(true);
	}
	
}
