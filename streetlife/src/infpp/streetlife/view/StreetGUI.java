package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import infpp.streetlife.controller.Controller;
import infpp.streetlife.model.StreetLifeModel;
import infpp.streetlife.model.StreetObject;
/**
 *  The StreetGUI represents the graphical interface of the street with the displayment area, control buttons etc.
 * @author Cornelius
 *
 */
public class StreetGUI extends JFrame implements ActionListener{

	//used resources
	private final String FROG_PATH = "/frog.png";
	private final String STREET_PATH = "/asphalt.png";
	private final String PLAY_PATH = "/play.png";
	private final String PAUSE_PATH = "/pause.png";
	private final String QUIT_PATH = "/close.png";
	private final String SAVE_PATH = "/save.png";
	private final String LOAD_PATH = "/load.png";
	
	//global buttons used for actionlistener
	private JButton btnInsert;
	private JButton btnDelete;
	private JButton btnStart;
	private JButton btnStop;
	private JButton btnStep;
	
	private JMenuItem mntmFileMenuItemLoad;
	private JMenuItem mntmFileMenuItemSave;
	private JMenuItem mntmFileMenuQuit;
	private JMenuItem mntmHelpMenuAbout;
	
	private JPanel contentPane;
	private ArrayList<StreetObject> nonAddedCars;
	private ArrayList<StreetObject> addedCars;
	private StreetLifeModel model;
	private Controller controller;
	
	private TexturePanel tp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StreetGUI frame = new StreetGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StreetGUI() {
		//nonAddedCars = modelState;
		
		
		
		setTitle("Froschsimulator 2022");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		Image img = new ImageIcon(this.getClass().getResource(FROG_PATH)).getImage();
		this.setIconImage(img);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		this.mntmFileMenuItemLoad = new JMenuItem("Load");
		ImageIcon imgLoad = new ImageIcon(this.getClass().getResource(LOAD_PATH));
		mntmFileMenuItemLoad.setIcon(imgLoad);
		mnFileMenu.add(mntmFileMenuItemLoad);
		
		this.mntmFileMenuItemSave = new JMenuItem("Save");
		ImageIcon imgSave = new ImageIcon(this.getClass().getResource(SAVE_PATH));
		mntmFileMenuItemSave.setIcon(imgSave);
		mnFileMenu.add(mntmFileMenuItemSave);
		
		JSeparator separator = new JSeparator();
		mnFileMenu.add(separator);
		
		this.mntmFileMenuQuit = new JMenuItem("Quit");
		ImageIcon imgQuit = new ImageIcon(this.getClass().getResource(QUIT_PATH));
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
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0, 0, 101, 377, 229, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		ImageIcon imgStart = new ImageIcon(this.getClass().getResource(PLAY_PATH));
		ImageIcon imgPause = new ImageIcon(this.getClass().getResource(PAUSE_PATH));
		
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
		
		JLabel lblInsert = new JLabel("Insert Car");
		GridBagConstraints gbc_lblInsert = new GridBagConstraints();
		gbc_lblInsert.insets = new Insets(0, 0, 5, 5);
		gbc_lblInsert.anchor = GridBagConstraints.EAST;
		gbc_lblInsert.gridx = 5;
		gbc_lblInsert.gridy = 0;
		buttonPanel.add(lblInsert, gbc_lblInsert);
		
		JComboBox<StreetObject> comboBoxInsert = new JComboBox<StreetObject>();
			//TODO
			// cbItems = model.getCars();
		GridBagConstraints gbc_comboBoxInsert = new GridBagConstraints();
		gbc_comboBoxInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInsert.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInsert.gridx = 6;
		gbc_comboBoxInsert.gridy = 0;
		buttonPanel.add(comboBoxInsert, gbc_comboBoxInsert);
		
		JButton btnInsert = new JButton("Insert");
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.insets = new Insets(0, 0, 5, 0);
		gbc_btnInsert.gridx = 7;
		gbc_btnInsert.gridy = 0;
		buttonPanel.add(btnInsert, gbc_btnInsert);
		
		this.btnStep = new JButton("Release the frog!");
		btnStep.addActionListener(this);
		GridBagConstraints gbc_btnStep = new GridBagConstraints();
		gbc_btnStep.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStep.gridwidth = 2;
		gbc_btnStep.insets = new Insets(0, 0, 0, 5);
		gbc_btnStep.gridx = 0;
		gbc_btnStep.gridy = 1;
		buttonPanel.add(btnStep, gbc_btnStep);
		
		JLabel lblDelete = new JLabel("Delete Car");
		GridBagConstraints gbc_lblDelete = new GridBagConstraints();
		gbc_lblDelete.insets = new Insets(0, 0, 0, 5);
		gbc_lblDelete.anchor = GridBagConstraints.EAST;
		gbc_lblDelete.gridx = 5;
		gbc_lblDelete.gridy = 1;
		buttonPanel.add(lblDelete, gbc_lblDelete);
		
		JComboBox comboBoxDelete = new JComboBox();
		GridBagConstraints gbc_comboBoxDelete = new GridBagConstraints();
		gbc_comboBoxDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDelete.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxDelete.gridx = 6;
		gbc_comboBoxDelete.gridy = 1;
		buttonPanel.add(comboBoxDelete, gbc_comboBoxDelete);
		
		JButton btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridx = 7;
		gbc_btnDelete.gridy = 1;
		buttonPanel.add(btnDelete, gbc_btnDelete);
		
		tp = new TexturePanel();
		
		//painting the background, making sure that the image gets properly tiled
		BufferedImage asphalt;
		try {
			//reading the image file as new BufferedReader Image
			asphalt = ImageIO.read(this.getClass().getResource(STREET_PATH));
			
			//setting the texture
			tp.setTexture(new TexturePaint(asphalt, new Rectangle(0,0,asphalt.getWidth(),asphalt.getHeight())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		contentPane.add(tp,BorderLayout.CENTER);
	
	}

	
	public void setModel(StreetLifeModel model) {
		this.model= model;
		
	}
	
	public void setController(Controller cntrl) {
		this.controller = cntrl;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmFileMenuQuit) {
			CloseDialog dia = new CloseDialog();
			dia.setVisible(true);
		}
		else if (e.getSource() == btnStart) {
			System.out.println("Start pressed"); //TODO
			this.controller.start();
		}
		else if (e.getSource() == btnStop) {
			System.out.println("Stop pressed"); //TODO
			this.controller.stop();
		}
		else if (e.getSource() == btnInsert) {
			
		}
		else if (e.getSource() == btnDelete) {
	
		}
		else if (e.getSource() == btnStep) {
			System.out.println("currently no frogs here"); //TODO
		}
		else if (e.getSource() == mntmHelpMenuAbout) { //TODO
			System.out.println("DEFAULT HELP TEXT");
		}
	}

 }
	

